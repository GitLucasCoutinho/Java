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
import { useFirebase } from "@/firebase";
import { collection, doc, serverTimestamp, addDoc, updateDoc, deleteDoc } from "firebase/firestore";
import { getRedirectResult } from "firebase/auth";
import { useCollection } from "@/firebase/firestore/use-collection";
import { useMemoFirebase } from "@/firebase/provider";
import { UserAuth } from "@/components/user-auth";
import { SidebarProvider, Sidebar, SidebarInset, SidebarContent, SidebarHeader, SidebarMenu, SidebarMenuItem, SidebarMenuButton, SidebarFooter, SidebarTrigger } from "@/components/ui/sidebar";
import { CheckSquare, Folder } from "lucide-react";
import { TaskSquareIcon } from "@/components/icons";
import { Skeleton } from "@/components/ui/skeleton";


// Categorias de tarefas disponíveis no aplicativo.
const taskCategories = ["Pessoal", "Trabalho", "Compras", "Recados", "Estudo"];

/**
 * Componente da página inicial que renderiza a aplicação principal de lista de tarefas.
 */
export default function Home() {
  const { auth, firestore, user, isUserLoading } = useFirebase();
  const [editingTask, setEditingTask] = useState<Task | null>(null);
  const [selectedCategory, setSelectedCategory] = useState<string>("Pessoal");

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

  const filteredTasks = useMemo(() => {
    if (!tasks) return [];
    if (selectedCategory === "Concluídas") {
      return tasks.filter(task => task.isCompleted);
    }
    return tasks.filter(task => task.category === selectedCategory && !task.isCompleted);
  }, [tasks, selectedCategory]);

  return (
    <SidebarProvider>
    <div className="flex min-h-screen">
      <Sidebar>
          <SidebarHeader>
            <div className="flex items-center gap-2 p-2">
                <TaskSquareIcon className="w-8 h-8 text-primary" />
                <h1 className="font-headline text-2xl font-bold tracking-tight text-primary">
                    TaskFlow
                </h1>
            </div>
          </SidebarHeader>
          <SidebarContent>
            <SidebarMenu>
                <SidebarMenuItem>
                    <h3 className="w-full px-2 pt-4 pb-2 text-sm font-semibold text-muted-foreground">Categorias</h3>
                </SidebarMenuItem>
              {taskCategories.map(category => (
                <SidebarMenuItem key={category}>
                  <SidebarMenuButton
                    isActive={selectedCategory === category}
                    onClick={() => setSelectedCategory(category)}
                    tooltip={category}
                  >
                    <Folder />
                    <span>{category}</span>
                  </SidebarMenuButton>
                </SidebarMenuItem>
              ))}
              <SidebarMenuItem>
                <SidebarMenuButton
                  isActive={selectedCategory === 'Concluídas'}
                  onClick={() => setSelectedCategory('Concluídas')}
                  tooltip="Concluídas"
                >
                  <CheckSquare />
                  <span>Concluídas</span>
                </SidebarMenuButton>
              </SidebarMenuItem>
            </SidebarMenu>
          </SidebarContent>
          <SidebarFooter>
            <UserAuth />
          </SidebarFooter>
      </Sidebar>
      
      <SidebarInset>
        <main className="flex-1 p-4 md:p-8 space-y-8">
            {!user ? (
                 <div className="flex flex-col items-center justify-center min-h-[80vh] text-center">
                    <TaskSquareIcon className="w-24 h-24 text-primary mb-4" />
                    <h2 className="text-2xl font-bold mb-4">Bem-vindo ao TaskFlow</h2>
                    <p className="text-muted-foreground">Sua lista de tarefas inteligente, calma e focada.</p>
                    <p className="text-muted-foreground mt-2">Faça login pela barra lateral para começar.</p>
                </div>
            ) : (
                <>
                <header className="flex items-center gap-4">
                    <SidebarTrigger className="md:hidden" />
                    <h2 className="font-headline text-3xl font-semibold">{selectedCategory}</h2>
                </header>

                {selectedCategory !== "Concluídas" && (
                    <AddTaskForm 
                        onAddTask={handleAddTask} 
                        defaultCategory={selectedCategory}
                    />
                )}
    
                <section>
                    {isLoadingTasks ? (
                        <div className="space-y-3">
                            <Skeleton className="h-16 w-full" />
                            <Skeleton className="h-16 w-full" />
                            <Skeleton className="h-16 w-full" />
                        </div>
                    ) : (
                        <TaskList
                            tasks={filteredTasks}
                            onToggleComplete={handleToggleComplete}
                            onDelete={handleDeleteTask}
                            onEdit={setEditingTask}
                            isCompletedList={selectedCategory === 'Concluídas'}
                        />
                    )}
                </section>
                </>
            )}
        </main>
      </SidebarInset>
      
      <EditTaskDialog
        task={editingTask}
        isOpen={!!editingTask}
        onClose={() => setEditingTask(null)}
        onSave={handleSaveTask}
      />
    </div>
    </SidebarProvider>
  );
}
