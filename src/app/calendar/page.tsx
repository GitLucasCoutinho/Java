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
import { Calendar as CalendarIcon } from "lucide-react";
import { Calendar } from "@/components/ui/calendar";
import { TaskList } from "@/components/task-list";
import { EditTaskDialog } from "@/components/edit-task-dialog";
import { isSameDay, startOfMonth, endOfMonth, eachDayOfInterval, getDay, format } from "date-fns";
import { ptBR } from 'date-fns/locale';
import { Separator } from "@/components/ui/separator";
import { AppLayout } from "@/components/app-layout";

// Mapeia string de dia para número (0=Dom, 1=Seg, ...)
const dayMap: { [key: string]: number } = {
  'SUN': 0, 'MON': 1, 'TUE': 2, 'WED': 3, 'THU': 4, 'FRI': 5, 'SAT': 6
};

export default function CalendarPage() {
  const { firestore, user } = useFirebase();
  const [selectedDate, setSelectedDate] = useState<Date | undefined>(new Date());
  const [currentMonth, setCurrentMonth] = useState(new Date());
  const [editingTask, setEditingTask] = useState<Task | null>(null);

  const tasksCollection = useMemoFirebase(() => {
    if (!user) return null;
    return collection(firestore, "users", user.uid, "tasks");
  }, [firestore, user]);

  const { data: tasks } = useCollection<Task>(tasksCollection);

  const getTasksForDay = (day: Date, allTasks: Task[]) => {
    if (!allTasks) return [];
    return allTasks.filter(task => {
      // Verifica tarefas com data de início
      if (task.startDate) {
        const taskStartDate = task.startDate instanceof Timestamp ? task.startDate.toDate() : new Date(task.startDate);
        if (isSameDay(taskStartDate, day)) {
          return true;
        }
      }
      // Verifica tarefas recorrentes
      if (task.recurringDays && task.recurringDays.length > 0) {
        const dayOfWeek = getDay(day); // 0 for Sunday, 1 for Monday, etc.
        return task.recurringDays.some(recurringDay => dayMap[recurringDay as keyof typeof dayMap] === dayOfWeek);
      }
      return false;
    });
  };

  const taskDays = useMemo(() => {
    if (!tasks) return [];
  
    const daysWithTasks: Date[] = [];
    const interval = { start: startOfMonth(currentMonth), end: endOfMonth(currentMonth) };
    const daysInMonth = eachDayOfInterval(interval);
  
    daysInMonth.forEach(day => {
      const tasksForDay = getTasksForDay(day, tasks);
      if (tasksForDay.length > 0) {
        daysWithTasks.push(day);
      }
    });
  
    return daysWithTasks;
  }, [tasks, currentMonth]);

  const tasksForSelectedDay = useMemo(() => {
    if (!selectedDate || !tasks) return [];
    return getTasksForDay(selectedDate, tasks);
  }, [selectedDate, tasks]);

  const handleToggleComplete = async (taskId: string) => {
    if (!tasksCollection) return;
    const task = tasks?.find((t) => t.id === taskId);
    if (task) {
      const taskRef = doc(tasksCollection, taskId);
      await updateDoc(taskRef, {
        isCompleted: !task.isCompleted,
        updatedAt: serverTimestamp(),
      });
    }
  };

  const handleDeleteTask = async (taskId: string) => {
    if (!tasksCollection) return;
    const taskRef = doc(tasksCollection, taskId);
    await deleteDoc(taskRef);
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

    await updateDoc(taskRef, dataToUpdate);
    setEditingTask(null);
  };

  const DayContent = ({ date }: { date: Date }) => {
    const hasTasks = taskDays.some(d => isSameDay(d, date));

    return (
      <div className="relative h-full w-full flex items-center justify-center">
        <span>{format(date, 'd')}</span>
        {hasTasks && (
          <div className="absolute bottom-1 h-1.5 w-1.5 rounded-full bg-green-900" />
        )}
      </div>
    );
  };

  return (
    <AppLayout pageTitle="Calendário" pageIcon={<CalendarIcon className="h-6 w-6 text-green-900"/>}>
      <main className="flex-1 flex flex-col items-center p-4 md:p-8 space-y-8 mb-20">
        <Calendar
            mode="single"
            selected={selectedDate}
            onSelect={setSelectedDate}
            month={currentMonth}
            onMonthChange={setCurrentMonth}
            className="rounded-md border"
            locale={ptBR}
            components={{
              DayContent: (props) => <DayContent date={props.date} />
            }}
          />

        <Separator className="w-full max-w-2xl" />

        <div className="w-full max-w-2xl">
            <h2 className="text-2xl font-semibold mb-4">
                Tarefas para {selectedDate ? format(selectedDate, "d 'de' MMMM", { locale: ptBR }) : 'o dia selecionado'}
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
    </AppLayout>
  );
}
