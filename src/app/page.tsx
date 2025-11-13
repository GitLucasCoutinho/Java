/**
 * @file Componente principal da página inicial.
 * Gerencia o estado das tarefas, a autenticação do usuário e a interação com o Firestore.
 * Renderiza a lista de tarefas e a nova barra de navegação inferior.
 */
"use client";

import { useState, useMemo } from "react";
import type { Task } from "@/types";
import { AddTaskForm } from "@/components/add-task-form";
import { TaskList } from "@/components/task-list";
import { EditTaskDialog } from "@/components/edit-task-dialog";
import { useFirebase } from "@/firebase";
import { collection, doc, serverTimestamp, addDoc, updateDoc, deleteDoc, Timestamp } from "firebase/firestore";
import { useCollection } from "@/firebase/firestore/use-collection";
import { useMemoFirebase } from "@/firebase/provider";
import { UserAuth } from "@/components/user-auth";
import { Calendar, Plus, ListTodo } from "lucide-react";
import { TaskSquareIcon } from "@/components/icons";
import { Skeleton } from "@/components/ui/skeleton";
import { Button } from "@/components/ui/button";
import {
  Sheet,
  SheetContent,
  SheetHeader,
  SheetTitle,
  SheetTrigger,
} from "@/components/ui/sheet";
import { Separator } from "@/components/ui/separator";

/**
 * Componente da página inicial que renderiza a aplicação principal de lista de tarefas.
 */
export default function Home() {
  const { firestore, user } = useFirebase();
  const [editingTask, setEditingTask] = useState<Task | null>(null);
  const [isAddTaskSheetOpen, setAddTaskSheetOpen] = useState(false);

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
  const handleAddTask = async (taskData: Omit<Task, "id" | "isCompleted" | "userId" | "category"> & { category?: string }) => {
    if (!tasksCollection || !user) return;
    const newTask: Partial<Task> = {
      ...taskData,
      isCompleted: false,
      createdAt: serverTimestamp(),
      updatedAt: serverTimestamp(),
      userId: user.uid,
      category: taskData.category || "Pessoal", // Categoria padrão
    };
    
    if (taskData.startDate) {
      newTask.startDate = Timestamp.fromDate(new Date(taskData.startDate));
    }
    if (taskData.endDate) {
      newTask.endDate = Timestamp.fromDate(new Date(taskData.endDate));
    }
    if (taskData.recurringDays) {
        newTask.recurringDays = taskData.recurringDays;
    }

    addDoc(tasksCollection, newTask as Task);
    setAddTaskSheetOpen(false);
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

    const dataToUpdate: any = {
        ...taskToUpdate,
        updatedAt: serverTimestamp(),
        startDate: updatedTask.startDate ? Timestamp.fromDate(new Date(updatedTask.startDate)) : null,
        endDate: updatedTask.endDate ? Timestamp.fromDate(new Date(updatedTask.endDate)) : null,
        recurringDays: updatedTask.recurringDays || [],
    };


    updateDoc(taskRef, dataToUpdate);
    setEditingTask(null);
  };

  const pendingTasks = useMemo(() => {
    if (!tasks) return [];
    return tasks.filter(task => !task.isCompleted);
  }, [tasks]);

  const completedTasks = useMemo(() => {
    if (!tasks) return [];
    return tasks.filter(task => task.isCompleted);
  }, [tasks]);

  return (
    <div className="flex flex-col min-h-screen bg-background">
      <header className="sticky top-0 z-10 flex items-center justify-between p-4 border-b bg-background/80 backdrop-blur-sm">
        <div className="flex items-center gap-2">
            <TaskSquareIcon className="w-8 h-8 text-primary" />
            <h1 className="text-2xl font-bold tracking-tight text-primary">
                TaskFlow
            </h1>
        </div>
        <UserAuth />
      </header>

      <main className="flex-1 p-4 md:p-8 space-y-8 mb-20">
        {!user ? (
          <div className="flex flex-col items-center justify-center min-h-[70vh] text-center">
            <TaskSquareIcon className="w-24 h-24 text-primary mb-4" />
            <h2 className="text-2xl font-bold mb-4">Bem-vindo ao TaskFlow</h2>
            <p className="text-muted-foreground">Sua lista de tarefas inteligente, calma e focada.</p>
            <p className="text-muted-foreground mt-2 mb-4">Faça login para começar.</p>
            <UserAuth />
          </div>
        ) : (
          <>
            <section>
              <h2 className="font-headline text-3xl font-semibold mb-6">Tarefas de Hoje</h2>
              {isLoadingTasks ? (
                <div className="space-y-3">
                  <Skeleton className="h-16 w-full" />
                  <Skeleton className="h-16 w-full" />
                  <Skeleton className="h-16 w-full" />
                </div>
              ) : (
                <TaskList
                  tasks={pendingTasks}
                  onToggleComplete={handleToggleComplete}
                  onDelete={handleDeleteTask}
                  onEdit={setEditingTask}
                />
              )}
            </section>

            {completedTasks.length > 0 && (
                 <section className="space-y-6">
                    <div className="flex items-center gap-4">
                        <Separator className="flex-1" />
                        <h2 className="font-headline text-2xl font-semibold text-muted-foreground">Concluídas</h2>
                        <Separator className="flex-1" />
                    </div>
                    <TaskList
                        tasks={completedTasks}
                        onToggleComplete={handleToggleComplete}
                        onDelete={handleDeleteTask}
                        onEdit={setEditingTask}
                        isCompletedList
                    />
                </section>
            )}
          </>
        )}
      </main>

      {user && (
        <Sheet open={isAddTaskSheetOpen} onOpenChange={setAddTaskSheetOpen}>
         <footer className="fixed bottom-0 left-0 right-0 z-10 border-t bg-background/95 backdrop-blur-sm">
            <nav className="flex justify-around items-center h-16 max-w-md mx-auto">
                <Button variant="ghost" size="icon" className="h-12 w-12 rounded-full">
                    <Calendar className="h-6 w-6" />
                    <span className="sr-only">Calendário</span>
                </Button>
                <SheetTrigger asChild>
                    <Button variant="default" size="icon" className="h-16 w-16 rounded-full shadow-lg -translate-y-4">
                        <Plus className="h-8 w-8" />
                        <span className="sr-only">Adicionar Tarefa</span>
                    </Button>
                </SheetTrigger>
                <Button variant="ghost" size="icon" className="h-12 w-12 rounded-full">
                    <ListTodo className="h-6 w-6" />
                    <span className="sr-only">Tarefas Pendentes</span>
                </Button>
            </nav>
        </footer>
        <SheetContent side="bottom" className="rounded-t-lg max-h-[90vh] overflow-y-auto">
            <SheetHeader className="text-left mb-6">
                <SheetTitle>Adicionar Nova Tarefa</SheetTitle>
            </SheetHeader>
            <AddTaskForm onAddTask={handleAddTask} onDone={() => setAddTaskSheetOpen(false)} />
        </SheetContent>
        </Sheet>
      )}

      <EditTaskDialog
        task={editingTask}
        isOpen={!!editingTask}
        onClose={() => setEditingTask(null)}
        onSave={handleSaveTask}
      />
    </div>
  );
}
