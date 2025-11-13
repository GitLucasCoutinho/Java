/**
 * @file Página que exibe tarefas agrupadas por categoria.
 */
"use client";

import { useMemo, useState } from "react";
import type { Task } from "@/types";
import { TaskList } from "@/components/task-list";
import { EditTaskDialog } from "@/components/edit-task-dialog";
import { useFirebase } from "@/firebase";
import { collection, doc, serverTimestamp, updateDoc, deleteDoc, Timestamp } from "firebase/firestore";
import { useCollection } from "@/firebase/firestore/use-collection";
import { useMemoFirebase } from "@/firebase/provider";
import { Accordion, AccordionContent, AccordionItem, AccordionTrigger } from "@/components/ui/accordion";
import { Skeleton } from "@/components/ui/skeleton";
import { ListTodo } from "lucide-react";
import { AppLayout } from "@/components/app-layout";


/**
 * Agrupa as tarefas por categoria.
 * @param tasks - Array de tarefas.
 * @returns Um objeto onde as chaves são categorias e os valores são arrays de tarefas.
 */
const groupTasksByCategory = (tasks: Task[]) => {
  return tasks.reduce((acc, task) => {
    const category = task.category || "Sem Categoria";
    if (!acc[category]) {
      acc[category] = [];
    }
    acc[category].push(task);
    return acc;
  }, {} as Record<string, Task[]>);
};


export default function CategoriesPage() {
  const { firestore, user } = useFirebase();
  const [editingTask, setEditingTask] = useState<Task | null>(null);

  // Memoiza a referência da coleção de tarefas.
  const tasksCollection = useMemoFirebase(() => {
    if (!user) return null;
    return collection(firestore, "users", user.uid, "tasks");
  }, [firestore, user]);

  const { data: tasks, isLoading: isLoadingTasks } = useCollection<Task>(tasksCollection);

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

  const handleDeleteTask = async (taskId: string) => {
    if (!tasksCollection) return;
    const taskRef = doc(tasksCollection, taskId);
    deleteDoc(taskRef);
  };

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

  const groupedTasks = useMemo(() => {
    if (!tasks) return {};
    const pending = tasks.filter(t => !t.isCompleted);
    return groupTasksByCategory(pending);
  }, [tasks]);

  const categories = useMemo(() => Object.keys(groupedTasks), [groupedTasks]);

  return (
    <AppLayout pageTitle="Tarefas por Categoria" pageIcon={<ListTodo className="h-6 w-6"/>}>
      <main className="flex-1 p-4 md:p-8 space-y-8 mb-20">
        {!user ? (
          <div className="text-center py-16">
            <p>Faça login para ver suas tarefas.</p>
          </div>
        ) : isLoadingTasks ? (
          <div className="space-y-4">
            <Skeleton className="h-12 w-full" />
            <Skeleton className="h-24 w-full" />
            <Skeleton className="h-12 w-full" />
            <Skeleton className="h-32 w-full" />
          </div>
        ) : categories.length === 0 ? (
          <div className="text-center text-muted-foreground h-full flex items-center justify-center py-16 px-4 border-2 border-dashed rounded-lg">
             <p>Nenhuma tarefa pendente encontrada.</p>
          </div>
        ) : (
          <Accordion type="multiple" defaultValue={categories} className="w-full space-y-4">
            {categories.map((category) => (
              <AccordionItem value={category} key={category} className="border-b-0">
                  <AccordionTrigger className="text-xl font-semibold bg-muted hover:bg-muted/90 px-4 py-3 rounded-lg border">
                    {category}
                  </AccordionTrigger>
                <AccordionContent className="pt-4">
                  <TaskList
                    tasks={groupedTasks[category]}
                    onToggleComplete={handleToggleComplete}
                    onDelete={handleDeleteTask}
                    onEdit={setEditingTask}
                  />
                </AccordionContent>
              </AccordionItem>
            ))}
          </Accordion>
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
