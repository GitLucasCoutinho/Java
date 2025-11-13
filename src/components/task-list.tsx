/**
 * @file Componente para renderizar uma lista de tarefas.
 * Recebe um título e um array de tarefas, e renderiza um TaskCard para cada uma.
 */
"use client";

import type { Task } from "@/types";
import { TaskCard } from "./task-card";

/**
 * Propriedades para o componente TaskList.
 */
type TaskListProps = {
  title: string;                       // Título da lista (ex: "Pessoal", "Concluídas").
  tasks: Task[];                       // Array de tarefas a serem exibidas.
  onToggleComplete: (id: string) => void; // Função para alternar o estado de conclusão.
  onDelete: (id:string) => void;       // Função para excluir uma tarefa.
  onEdit: (task: Task) => void;        // Função para editar uma tarefa.
};

/**
 * Renderiza uma seção contendo uma lista de cartões de tarefas.
 * @param {TaskListProps} props - As propriedades do componente.
 */
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
          {/* Mapeia e renderiza cada tarefa usando o componente TaskCard */}
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
        // Mensagem exibida quando não há tarefas na lista.
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
