/**
 * @file Componente de layout reutilizável com cabeçalho e rodapé.
 * Garante uma navegação consistente em todo o aplicativo.
 */
'use client';

import { useState } from 'react';
import Link from 'next/link';
import { Calendar, Plus, ListTodo, ArrowLeft } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Sheet, SheetContent, SheetTrigger, SheetHeader, SheetTitle, SheetDescription } from '@/components/ui/sheet';
import { AddTaskForm } from '@/components/add-task-form';
import { UserAuth } from '@/components/user-auth';
import { useFirebase } from '@/firebase';
import { addDoc, collection, serverTimestamp, Timestamp } from 'firebase/firestore';
import type { Task } from '@/types';
import { TaskSquareIcon } from './icons';
import { usePathname } from 'next/navigation';

type AppLayoutProps = {
  children: React.ReactNode;
  pageTitle?: string;
  pageIcon?: React.ReactNode;
};

export function AppLayout({ children, pageTitle, pageIcon }: AppLayoutProps) {
  const { firestore, user } = useFirebase();
  const [isAddTaskSheetOpen, setAddTaskSheetOpen] = useState(false);
  const pathname = usePathname();
  const isHomePage = pathname === '/';

  const handleAddTask = async (taskData: Omit<Task, "id" | "isCompleted" | "userId" | "category"> & { category?: string }) => {
    if (!user) return;
    const tasksCollection = collection(firestore, "users", user.uid, "tasks");
    const newTask: Omit<Task, 'id'> = {
      title: taskData.title,
      description: taskData.description || '',
      isCompleted: false,
      createdAt: serverTimestamp(),
      updatedAt: serverTimestamp(),
      userId: user.uid,
      category: taskData.category || "Pessoal",
      startDate: taskData.startDate ? Timestamp.fromDate(new Date(taskData.startDate)) : null,
      endDate: taskData.endDate ? Timestamp.fromDate(new Date(taskData.endDate)) : null,
      recurringDays: taskData.recurringDays || [],
    };

    addDoc(tasksCollection, newTask);
    setAddTaskSheetOpen(false);
  };

  const renderHeaderContent = () => {
    if (isHomePage) {
      return (
        <>
          <TaskSquareIcon className="w-8 h-8 text-green-900" />
          <h1 className="text-2xl font-bold tracking-tight text-green-900">TaskFlow</h1>
        </>
      );
    }
    return (
      <>
        <Link href="/" legacyBehavior={false}>
          <Button variant="ghost" size="icon" aria-label="Voltar para a página inicial">
            <ArrowLeft className="h-6 w-6" />
          </Button>
        </Link>
        <h1 className="text-xl font-bold tracking-tight text-green-900 flex items-center gap-2">
          {pageIcon}
          {pageTitle}
        </h1>
      </>
    );
  };

  return (
    <div className="flex flex-col min-h-screen bg-background">
      <header className="sticky top-0 z-10 flex items-center justify-between p-4 border-b bg-background/80 backdrop-blur-sm">
        <div className="flex items-center gap-2">
          {renderHeaderContent()}
        </div>
        <UserAuth />
      </header>

      {children}

      {user && (
        <Sheet open={isAddTaskSheetOpen} onOpenChange={setAddTaskSheetOpen}>
           <footer className="fixed bottom-0 left-0 right-0 z-10 border-t bg-background/95 backdrop-blur-sm">
            <nav className="flex justify-around items-center h-16 max-w-md mx-auto">
              <Link href="/calendar" legacyBehavior={false}>
                <Button variant="ghost" size="icon" className="h-12 w-12 rounded-full">
                  <Calendar className="h-6 w-6" />
                  <span className="sr-only">Calendário</span>
                </Button>
              </Link>
              <SheetTrigger asChild>
                <Button variant="default" size="icon" className="h-16 w-16 rounded-full shadow-lg -translate-y-4 bg-gradient-radial from-primary to-green-900 text-primary-foreground">
                  <Plus className="h-8 w-8" strokeWidth={2.5}/>
                  <span className="sr-only">Adicionar Tarefa</span>
                </Button>
              </SheetTrigger>
              <Link href="/categories" legacyBehavior={false}>
                <Button variant="ghost" size="icon" className="h-12 w-12 rounded-full">
                  <ListTodo className="h-6 w-6" />
                  <span className="sr-only">Tarefas por Categoria</span>
                </Button>
              </Link>
            </nav>
          </footer>
          <SheetContent side="bottom" className="rounded-t-lg max-h-[90vh] overflow-y-auto">
            <SheetHeader className="text-left mb-6">
              <SheetTitle>Adicionar Nova Tarefa</SheetTitle>
              <SheetDescription>
                Preencha os detalhes abaixo para criar uma nova tarefa na sua lista.
              </SheetDescription>
            </SheetHeader>
            <AddTaskForm onAddTask={handleAddTask} onDone={() => setAddTaskSheetOpen(false)} />
          </SheetContent>
        </Sheet>
      )}
    </div>
  );
}
