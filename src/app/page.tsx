"use client";

import { useState, useMemo, useEffect } from "react";
import type { Task } from "@/types";
import { AddTaskForm } from "@/components/add-task-form";
import { TaskList } from "@/components/task-list";
import { EditTaskDialog } from "@/components/edit-task-dialog";
import { Separator } from "@/components/ui/separator";
import { useFirebase } from "@/firebase";
import { collection, doc, serverTimestamp } from "firebase/firestore";
import { useCollection } from "@/firebase/firestore/use-collection";
import {
  addDocumentNonBlocking,
  updateDocumentNonBlocking,
  deleteDocumentNonBlocking,
} from "@/firebase/non-blocking-updates";
import { useMemoFirebase } from "@/firebase/provider";
import {
  initiateAnonymousSignIn,
  initiateGoogleSignInRedirect,
  handleRedirectResult,
} from "@/firebase/non-blocking-login";
import { Button } from "@/components/ui/button";
import { GoogleIcon } from "@/components/icons";

const taskCategories = ["Pessoal", "Trabalho", "Compras", "Recados", "Estudo"];

export default function Home() {
  const { auth, firestore, user, isUserLoading } = useFirebase();
  const [editingTask, setEditingTask] = useState<Task | null>(null);

  useEffect(() => {
    if (auth && !isUserLoading && !user) {
      handleRedirectResult(auth);
    }
  }, [auth, isUserLoading, user]);

  const tasksCollection = useMemoFirebase(() => {
    if (!user) return null;
    return collection(firestore, "users", user.uid, "tasks");
  }, [firestore, user]);

  const { data: tasks, isLoading: isLoadingTasks } = useCollection<Task>(tasksCollection);

  const handleAddTask = (taskData: Omit<Task, "id" | "isCompleted" | "userId">) => {
    if (!tasksCollection || !user) return;
    const newTask = {
      ...taskData,
      isCompleted: false,
      createdAt: serverTimestamp(),
      updatedAt: serverTimestamp(),
      userId: user.uid,
    };
    addDocumentNonBlocking(tasksCollection, newTask);
  };

  const handleToggleComplete = (taskId: string) => {
    if (!tasksCollection) return;
    const task = tasks?.find((t) => t.id === taskId);
    if (task) {
      const taskRef = doc(tasksCollection, taskId);
      updateDocumentNonBlocking(taskRef, {
        isCompleted: !task.isCompleted,
        updatedAt: serverTimestamp(),
      });
    }
  };

  const handleDeleteTask = (taskId: string) => {
    if (!tasksCollection) return;
    const taskRef = doc(tasksCollection, taskId);
    deleteDocumentNonBlocking(taskRef);
  };

  const handleSaveTask = (updatedTask: Task) => {
    if (!tasksCollection) return;
    const taskRef = doc(tasksCollection, updatedTask.id);
    const { id, ...taskToUpdate } = updatedTask;
    updateDocumentNonBlocking(taskRef, {
      ...taskToUpdate,
      updatedAt: serverTimestamp(),
    });
    setEditingTask(null);
  };

  const { pendingTasksByCategory, completedTasks } = useMemo(() => {
    if (!tasks) {
      return { pendingTasksByCategory: {}, completedTasks: [] };
    }
    return tasks.reduce(
      (acc, task) => {
        if (task.isCompleted) {
          acc.completedTasks.push(task);
        } else {
          if (!acc.pendingTasksByCategory[task.category]) {
            acc.pendingTasksByCategory[task.category] = [];
          }
          acc.pendingTasksByCategory[task.category].push(task);
        }
        return acc;
      },
      {
        pendingTasksByCategory: {} as Record<string, Task[]>,
        completedTasks: [] as Task[],
      }
    );
  }, [tasks]);

  if (isUserLoading) {
    return (
      <div className="flex items-center justify-center min-h-screen">
        <p>Carregando...</p>
      </div>
    );
  }

  if (!user) {
    return (
      <div className="flex flex-col items-center justify-center min-h-screen">
        <h1 className="text-3xl font-bold mb-4">Bem-vindo ao TaskFlow</h1>
        <p className="mb-6">Faça login para gerenciar suas tarefas.</p>
        <div className="flex flex-col sm:flex-row gap-4">
          <Button
            variant="outline"
            onClick={() => initiateGoogleSignInRedirect(auth)}
          >
            <GoogleIcon className="mr-2 h-4 w-4" />
            Entrar com Google
          </Button>
          <Button onClick={() => initiateAnonymousSignIn(auth)}>
            Entrar como Anônimo
          </Button>
        </div>
      </div>
    );
  }


  return (
    <main className="container mx-auto p-4 md:p-8">
      <header className="text-center mb-8">
        <h1 className="font-headline text-4xl md:text-5xl font-bold tracking-tight">
          TaskFlow
        </h1>
        <p className="text-muted-foreground mt-2">
          Sua lista de tarefas inteligente, calma e focada
        </p>
      </header>

      <div className="max-w-7xl mx-auto">
        <AddTaskForm onAddTask={handleAddTask} />

        <Separator className="my-8" />

        <section>
          <h2 className="font-headline text-3xl font-semibold mb-6">Pendentes</h2>
          {isLoadingTasks ? (
             <div className="text-center">Carregando tarefas...</div>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-5 gap-6">
              {taskCategories.map((category) => (
                <TaskList
                  key={category}
                  title={category}
                  tasks={pendingTasksByCategory[category] || []}
                  onToggleComplete={handleToggleComplete}
                  onDelete={handleDeleteTask}
                  onEdit={setEditingTask}
                />
              ))}
            </div>
          )}
        </section>

        <Separator className="my-8" />

        <div className="space-y-8 max-w-3xl mx-auto">
          <TaskList
            title="Concluídas"
            tasks={completedTasks}
            onToggleComplete={handleToggleComplete}
            onDelete={handleDeleteTask}
            onEdit={setEditingTask}
          />
        </div>
      </div>

      <EditTaskDialog
        task={editingTask}
        isOpen={!!editingTask}
        onClose={() => setEditingTask(null)}
        onSave={handleSaveTask}
      />
    </main>
  );
}
