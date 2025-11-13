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
  tasks: Task[];                       // Array de tarefas a serem exibidas.
  onToggleComplete: (id: string) => void; // Função para alternar o estado de conclusão.
  onDelete: (id:string) => void;       // Função para excluir uma tarefa.
  onEdit: (task: Task) => void;        // Função para editar uma tarefa.
  isCompletedList?: boolean;
};

/**
 * Renderiza uma seção contendo uma lista de cartões de tarefas.
 * @param {TaskListProps} props - As propriedades do componente.
 */
export function TaskList({
  tasks,
  onToggleComplete,
  onDelete,
  onEdit,
  isCompletedList,
}: TaskListProps) {
  return (
    <section>
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
        <div className="text-center text-muted-foreground h-full flex items-center justify-center py-16 px-4 border-2 border-dashed rounded-lg">
          <p>
            {isCompletedList
              ? "Nenhuma tarefa concluída ainda."
              : "Sem tarefas aqui. Adicione uma nova!"}
          </p>
        </div>
      )}
    </section>
  );
}
