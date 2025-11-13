"use client";

import { useState, useMemo } from "react";
import type { Task } from "@/types";
import { AddTaskForm } from "@/components/add-task-form";
import { TaskList } from "@/components/task-list";
import { EditTaskDialog } from "@/components/edit-task-dialog";
import { Separator } from "@/components/ui/separator";

const initialTasks: Task[] = [
  {
    id: "1",
    title: "Configurar a estrutura do projeto",
    description: "Inicializar o aplicativo Next.js e instalar dependências.",
    completed: true,
    category: "Trabalho",
  },
  {
    id: "2",
    title: "Projetar a interface do usuário",
    description: "Criar mockups e escolher uma paleta de cores.",
    completed: true,
    category: "Trabalho",
  },
  {
    id: "3",
    title: "Desenvolver componentes principais",
    description: "Construir os componentes TaskCard, TaskList e AddTaskForm.",
    completed: false,
    category: "Trabalho",
  },
  {
    id: "4",
    title: "Integrar sugestões de IA",
    description: "Implementar o recurso GenAI para sugestões de nomes de tarefas.",
    completed: false,
    category: "Estudo",
  },
  {
    id: "5",
    title: "Comprar leite",
    description: "",
    completed: false,
    category: "Compras",
  },
  {
    id: "6",
    title: "Ligar para o médico",
    description: "Agendar consulta de rotina",
    completed: false,
    category: "Recados",
  },
  {
    id: "7",
    title: "Fazer exercícios",
    description: "30 minutos de corrida",
    completed: false,
    category: "Pessoal",
  },
];

const taskCategories = ["Pessoal", "Trabalho", "Compras", "Recados", "Estudo"];

export default function Home() {
  const [tasks, setTasks] = useState<Task[]>(initialTasks);
  const [editingTask, setEditingTask] = useState<Task | null>(null);

  const handleAddTask = (taskData: Omit<Task, "id" | "completed">) => {
    const newTask: Task = {
      ...taskData,
      id: Date.now().toString(),
      completed: false,
    };
    setTasks((prevTasks) => [newTask, ...prevTasks]);
  };

  const handleToggleComplete = (taskId: string) => {
    setTasks((prevTasks) =>
      prevTasks.map((task) =>
        task.id === taskId ? { ...task, completed: !task.completed } : task
      )
    );
  };

  const handleDeleteTask = (taskId: string) => {
    setTasks((prevTasks) => prevTasks.filter((task) => task.id !== taskId));
  };

  const handleSaveTask = (updatedTask: Task) => {
    setTasks((prevTasks) =>
      prevTasks.map((task) =>
        task.id === updatedTask.id ? updatedTask : task
      )
    );
    setEditingTask(null);
  };

  const { pendingTasksByCategory, completedTasks } = useMemo(() => {
    return tasks.reduce(
      (acc, task) => {
        if (task.completed) {
          acc.completedTasks.push(task);
        } else {
          if (!acc.pendingTasksByCategory[task.category]) {
            acc.pendingTasksByCategory[task.category] = [];
          }
          acc.pendingTasksByCategory[task.category].push(task);
        }
        return acc;
      },
      { pendingTasksByCategory: {} as Record<string, Task[]>, completedTasks: [] as Task[] }
    );
  }, [tasks]);

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
