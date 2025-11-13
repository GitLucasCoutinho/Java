/**
 * @file Componente principal da página inicial.
 * Gerencia o estado das tarefas, a autenticação do usuário e a interação com o Firestore.
 * Renderiza a lista de tarefas, o formulário para adicionar novas tarefas e a UI de autenticação.
 */
"use client";

import { useState, useMemo, useEffect } from "react";
import type { Task } from "@/types";
import { AddTaskForm } from "@/components/add-task-form";
import { TaskList } from "@/components/task-list";
import { EditTaskDialog } from "@/components/edit-task-dialog";
import { Separator } from "@/components/ui/separator";
import { useFirebase } from "@/firebase";
import { collection, doc, serverTimestamp, addDoc, updateDoc, deleteDoc, GoogleAuthProvider, signInWithRedirect, getRedirectResult } from "firebase/firestore";
import { useCollection } from "@/firebase/firestore/use-collection";
import { useMemoFirebase } from "@/firebase/provider";
import { UserAuth } from "@/components/user-auth";

// Categorias de tarefas disponíveis no aplicativo.
const taskCategories = ["Pessoal", "Trabalho", "Compras", "Recados", "Estudo"];

/**
 * Componente da página inicial que renderiza a aplicação principal de lista de tarefas.
 */
export default function Home() {
  const { auth, firestore, user, isUserLoading } = useFirebase();
  const [editingTask, setEditingTask] = useState<Task | null>(null);

  // Efeito para lidar com o resultado do redirecionamento de login do Google.
  useEffect(() => {
    if (auth && !isUserLoading && !user) {
      getRedirectResult(auth);
    }
  }, [auth, isUserLoading, user]);

  // Memoiza a referência da coleção de tarefas do usuário no Firestore.
  const tasksCollection = useMemoFirebase(() => {
    if (!user) return null;
    return collection(firestore, "users", user.uid, "tasks");
  }, [firestore, user]);

  // Hook para buscar as tarefas da coleção em tempo real.
  const { data: tasks, isLoading: isLoadingTasks } = useCollection<Task>(tasksCollection);

  /**
   * Adiciona uma nova tarefa ao Firestore.
   * @param {Omit<Task, "id" | "isCompleted" | "userId">} taskData - Os dados da nova tarefa.
   */
  const handleAddTask = async (taskData: Omit<Task, "id" | "isCompleted" | "userId">) => {
    if (!tasksCollection || !user) return;
    const newTask = {
      ...taskData,
      isCompleted: false,
      createdAt: serverTimestamp(),
      updatedAt: serverTimestamp(),
      userId: user.uid,
    };
    addDoc(tasksCollection, newTask);
  };

  /**
   * Alterna o estado de conclusão de uma tarefa.
   * @param {string} taskId - O ID da tarefa a ser atualizada.
   */
  const handleToggleComplete = async (taskId: string) => {
    if (!tasksCollection) return;
    const task = tasks?.find((t) => t.id === taskId);
    if (task) {
      const taskRef = doc(tasksCollection, taskId);
      updateDoc(taskRef, {
        isCompleted: !task.isCompleted,
        updatedAt: serverTimestamp(),
      });
    }
  };

  /**
   * Exclui uma tarefa do Firestore.
   * @param {string} taskId - O ID da tarefa a ser excluída.
   */
  const handleDeleteTask = async (taskId: string) => {
    if (!tasksCollection) return;
    const taskRef = doc(tasksCollection, taskId);
    deleteDoc(taskRef);
  };

  /**
   * Salva as alterações de uma tarefa editada.
   * @param {Task} updatedTask - A tarefa com os dados atualizados.
   */
  const handleSaveTask = async (updatedTask: Task) => {
    if (!tasksCollection) return;
    const taskRef = doc(tasksCollection, updatedTask.id);
    const { id, ...taskToUpdate } = updatedTask;
    updateDoc(taskRef, {
      ...taskToUpdate,
      updatedAt: serverTimestamp(),
    });
    setEditingTask(null);
  };

  // Memoiza a separação das tarefas em "pendentes" (agrupadas por categoria) e "concluídas".
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

  return (
    <main className="container mx-auto p-4 md:p-8">
      <header className="flex justify-between items-center text-center mb-8">
        <div className="flex-1">
          <h1 className="font-headline text-4xl md:text-5xl font-bold tracking-tight">
            TaskFlow
          </h1>
          <p className="text-muted-foreground mt-2">
            Sua lista de tarefas inteligente, calma e focada
          </p>
        </div>
        {/* Componente de autenticação do usuário */}
        <UserAuth />
      </header>
      
      {isUserLoading ? (
        <div className="flex items-center justify-center min-h-[50vh]">
          <p>Carregando...</p>
        </div>
      ) : !user ? (
        <div className="flex flex-col items-center justify-center min-h-[50vh] text-center">
          <h2 className="text-2xl font-bold mb-4">Bem-vindo ao TaskFlow</h2>
          <p className="text-muted-foreground">Faça login para começar a gerenciar suas tarefas.</p>
        </div>
      ) : (
        <>
          <div className="max-w-7xl mx-auto">
            {/* Formulário para adicionar nova tarefa */}
            <AddTaskForm onAddTask={handleAddTask} />

            <Separator className="my-8" />

            <section>
              <h2 className="font-headline text-3xl font-semibold mb-6">Pendentes</h2>
              {isLoadingTasks ? (
                 <div className="text-center">Carregando tarefas...</div>
              ) : (
                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-5 gap-6">
                  {/* Renderiza uma lista de tarefas para cada categoria */}
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
              {/* Lista de tarefas concluídas */}
              <TaskList
                title="Concluídas"
                tasks={completedTasks}
                onToggleComplete={handleToggleComplete}
                onDelete={handleDeleteTask}
                onEdit={setEditingTask}
              />
            </div>
          </div>

          {/* Diálogo para editar uma tarefa existente */}
          <EditTaskDialog
            task={editingTask}
            isOpen={!!editingTask}
            onClose={() => setEditingTask(null)}
            onSave={handleSaveTask}
          />
        </>
      )}
    </main>
  );
}
