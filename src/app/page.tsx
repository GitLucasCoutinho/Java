/**
 * @file Componente principal da página inicial.
 * Gerencia o estado das tarefas, a autenticação do usuário e a interação com o Firestore.
 * Renderiza a lista de tarefas pendentes e concluídas.
 */
"use client";

import { useState, useMemo } from "react";
import type { Task } from "@/types";
import { TaskList } from "@/components/task-list";
import { EditTaskDialog } from "@/components/edit-task-dialog";
import { useFirebase } from "@/firebase";
import { collection, doc, serverTimestamp, updateDoc, deleteDoc, Timestamp } from "firebase/firestore";
import { useCollection } from "@/firebase/firestore/use-collection";
import { useMemoFirebase } from "@/firebase/provider";
import { UserAuth } from "@/components/user-auth";
import { TaskSquareIcon } from "@/components/icons";
import { Skeleton } from "@/components/ui/skeleton";
import { Separator } from "@/components/ui/separator";
import { AppLayout } from "@/components/app-layout";

/**
 * Componente da página inicial que renderiza a aplicação principal de lista de tarefas.
 */
export default function Home() {
  const { firestore, user } = useFirebase();
  const [editingTask, setEditingTask] = useState<Task | null>(null);

  // Memoiza a referência da coleção de tarefas do usuário no Firestore.
  const tasksCollection = useMemoFirebase(() => {
    if (!user) return null;
    return collection(firestore, "users", user.uid, "tasks");
  }, [firestore, user]);

  // Hook para buscar as tarefas da coleção em tempo real.
  const { data: tasks, isLoading: isLoadingTasks } = useCollection<Task>(tasksCollection);

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
    <AppLayout>
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

      <EditTaskDialog
        task={editingTask}
        isOpen={!!editingTask}
        onClose={() => setEditingTask(null)}
        onSave={handleSaveTask}
      />
    </AppLayout>
  );
}
