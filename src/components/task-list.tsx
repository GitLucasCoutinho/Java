"use client";

import type { Task } from "@/types";
import { TaskCard } from "./task-card";

type TaskListProps = {
  title: string;
  tasks: Task[];
  onToggleComplete: (id: string) => void;
  onDelete: (id: string) => void;
  onEdit: (task: Task) => void;
};

export function TaskList({
  title,
  tasks,
  onToggleComplete,
  onDelete,
  onEdit,
}: TaskListProps) {
  return (
    <section>
      <h3 className="font-headline text-2xl font-semibold mb-4 text-center">{title}</h3>
      {tasks.length > 0 ? (
        <div className="space-y-3">
          {tasks.map((task) => (
            <TaskCard
              key={task.id}
              task={task}
              onToggleComplete={onToggleComplete}
              onDelete={onDelete}
              onEdit={onEdit}
            />
          ))}
        </div>
      ) : (
        <div className="text-center text-muted-foreground h-full flex items-center justify-center py-8 px-4 border-2 border-dashed rounded-lg">
          <p>
            {title === "Concluídas"
              ? "Nenhuma tarefa concluída ainda."
              : "Sem tarefas nesta categoria."}
          </p>
        </div>
      )}
    </section>
  );
}
