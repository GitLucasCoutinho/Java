/**
 * @file Página de calendário que exibe tarefas.
 */
"use client";

import { useState, useMemo } from "react";
import type { Task } from "@/types";
import { useFirebase } from "@/firebase";
import { collection, Timestamp, doc, updateDoc, deleteDoc, serverTimestamp } from "firebase/firestore";
import { useCollection } from "@/firebase/firestore/use-collection";
import { useMemoFirebase } from "@/firebase/provider";
import { Calendar as CalendarIcon, ArrowLeft } from "lucide-react";
import { Calendar } from "@/components/ui/calendar";
import { TaskList } from "@/components/task-list";
import { EditTaskDialog } from "@/components/edit-task-dialog";
import { isSameDay, parseISO } from "date-fns";
import { ptBR } from 'date-fns/locale';
import { UserAuth } from "@/components/user-auth";
import { Button } from "@/components/ui/button";
import Link from "next/link";
import { Separator } from "@/components/ui/separator";

export default function CalendarPage() {
  const { firestore, user } = useFirebase();
  const [selectedDate, setSelectedDate] = useState<Date | undefined>(new Date());
  const [editingTask, setEditingTask] = useState<Task | null>(null);

  const tasksCollection = useMemoFirebase(() => {
    if (!user) return null;
    return collection(firestore, "users", user.uid, "tasks");
  }, [firestore, user]);

  const { data: tasks, isLoading: isLoadingTasks } = useCollection<Task>(tasksCollection);

  // Memoiza as tarefas que têm datas
  const tasksWithDates = useMemo(() => {
    if (!tasks) return [];
    return tasks.filter(task => task.startDate || task.endDate);
  }, [tasks]);

  // Dias com tarefas para destacar no calendário
  const daysWithTasks = useMemo(() => {
    return tasksWithDates.map(task => {
        const date = task.startDate || task.endDate;
        if (!date) return null;
        return date instanceof Timestamp ? date.toDate() : new Date(date);
    }).filter((date): date is Date => date !== null);
  }, [tasksWithDates]);

  // Filtra as tarefas para o dia selecionado
  const tasksForSelectedDay = useMemo(() => {
    if (!selectedDate || !tasks) return [];
    return tasks.filter(task => {
      if (!task.startDate) return false;
      const taskDate = task.startDate instanceof Timestamp ? task.startDate.toDate() : new Date(task.startDate);
      return isSameDay(taskDate, selectedDate);
    });
  }, [selectedDate, tasks]);

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

  return (
    <div className="flex flex-col min-h-screen bg-background">
       <header className="sticky top-0 z-10 flex items-center justify-between p-4 border-b bg-background/80 backdrop-blur-sm">
        <div className="flex items-center gap-2">
            <Link href="/" legacyBehavior passHref>
                <Button variant="ghost" size="icon" aria-label="Voltar para a página inicial">
                    <ArrowLeft className="h-6 w-6" />
                </Button>
            </Link>
            <h1 className="text-xl font-bold tracking-tight text-primary flex items-center gap-2">
                <CalendarIcon className="h-6 w-6"/>
                Calendário
            </h1>
        </div>
        <UserAuth />
      </header>

      <main className="flex-1 flex flex-col items-center p-4 md:p-8 space-y-8">
        <Calendar
            mode="single"
            selected={selectedDate}
            onSelect={setSelectedDate}
            className="rounded-md border"
            locale={ptBR}
            modifiers={{
                withTask: daysWithTasks,
            }}
            modifiersStyles={{
                withTask: { 
                    position: 'relative',
                    color: 'hsl(var(--primary))' 
                }
            }}
            components={{
              DayContent: (props) => {
                const isWithTask = daysWithTasks.some(day => isSameDay(day, props.date));
                return (
                  <div className="relative h-full w-full flex items-center justify-center">
                    <span>{props.date.getDate()}</span>
                    {isWithTask && (
                      <div className="absolute bottom-1 h-1 w-1 rounded-full bg-primary" />
                    )}
                  </div>
                );
              },
            }}
          />

        <Separator className="w-full max-w-2xl" />

        <div className="w-full max-w-2xl">
            <h2 className="text-2xl font-semibold mb-4">
                Tarefas para {selectedDate ? selectedDate.toLocaleDateString('pt-BR', { day: '2-digit', month: 'long' }) : 'o dia selecionado'}
            </h2>
            <TaskList
                tasks={tasksForSelectedDay}
                onToggleComplete={handleToggleComplete}
                onDelete={handleDeleteTask}
                onEdit={setEditingTask}
            />
        </div>
      </main>

      <EditTaskDialog
        task={editingTask}
        isOpen={!!editingTask}
        onClose={() => setEditingTask(null)}
        onSave={handleSaveTask}
      />
    </div>
  );
}