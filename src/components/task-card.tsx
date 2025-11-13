/**
 * @file Componente para exibir um único card de tarefa.
 * Mostra o título, descrição (se houver) e ações como completar, editar e excluir.
 */
"use client";

import { Card, CardContent } from "@/components/ui/card";
import { Checkbox } from "@/components/ui/checkbox";
import { Button } from "@/components/ui/button";
import { Trash2, Pencil, CalendarIcon, Repeat } from "lucide-react";
import type { Task } from "@/types";
import { cn } from "@/lib/utils";
import { format } from "date-fns";

/**
 * Propriedades para o componente TaskCard.
 */
type TaskCardProps = {
  task: Task;                          // O objeto da tarefa a ser exibido.
  onToggleComplete: (id: string) => void; // Função para alternar o estado de conclusão.
  onDelete: (id: string) => void;       // Função para excluir a tarefa.
  onEdit: (task: Task) => void;        // Função para abrir o modo de edição da tarefa.
};

const recurringDaysMap: { [key: string]: string } = {
  SUN: 'DOM',
  MON: 'SEG',
  TUE: 'TER',
  WED: 'QUA',
  THU: 'QUI',
  FRI: 'SEX',
  SAT: 'SAB',
};


/**
 * Renderiza um cartão individual para uma tarefa, com controles para interagir com ela.
 * @param {TaskCardProps} props - As propriedades do componente.
 */
export function TaskCard({
  task,
  onToggleComplete,
  onDelete,
  onEdit,
}: TaskCardProps) {

  const formatDate = (date: any) => {
    if (!date) return null;
    // Firestore Timestamps need to be converted to JS Date objects
    const jsDate = date.toDate ? date.toDate() : new Date(date);
    return format(jsDate, "dd/MM/yyyy");
  };

  const startDate = formatDate(task.startDate);
  const endDate = formatDate(task.endDate);

  const recurringDaysText = task.recurringDays && task.recurringDays.length > 0
    ? task.recurringDays.map(day => recurringDaysMap[day]).join(', ')
    : null;
  
  return (
    <Card
      className={cn(
        "transition-all duration-300 hover:shadow-md",
        task.isCompleted ? "bg-card/50" : "bg-card"
      )}
    >
      <CardContent className="p-4 flex items-start gap-4">
        <Checkbox
          id={`task-${task.id}`}
          checked={task.isCompleted}
          onCheckedChange={() => onToggleComplete(task.id)}
          className="mt-1"
          aria-label={`Marcar tarefa "${task.title}" como ${task.isCompleted ? 'incompleta' : 'completa'}`}
        />
        <div className="flex-1 grid gap-1">
          <label
            htmlFor={`task-${task.id}`}
            className={cn(
              "font-medium cursor-pointer transition-colors",
              task.isCompleted && "line-through text-muted-foreground"
            )}
          >
            {task.title}
          </label>
          {task.description && (
            <p
              className={cn(
                "text-sm text-muted-foreground",
                task.isCompleted && "line-through"
              )}
            >
              {task.description}
            </p>
          )}
          <div className="flex flex-wrap items-center gap-x-4 gap-y-1 mt-1">
            {(startDate || endDate) && (
                <div className={cn("text-xs text-muted-foreground flex items-center gap-1", task.isCompleted && "line-through")}>
                <CalendarIcon className="h-3 w-3" />
                <span>{startDate}</span>
                {startDate && endDate && <span> - </span>}
                <span>{endDate}</span>
                </div>
            )}
            {recurringDaysText && (
                <div className={cn("text-xs text-muted-foreground flex items-center gap-1", task.isCompleted && "line-through")}>
                    <Repeat className="h-3 w-3" />
                    <span>{recurringDaysText}</span>
                </div>
            )}
           </div>
        </div>
        <div className="flex gap-2">
          <Button
            variant="ghost"
            size="icon"
            className="h-8 w-8"
            onClick={() => onEdit(task)}
            aria-label={`Editar tarefa "${task.title}"`}
          >
            <Pencil className="h-4 w-4" />
          </Button>
          <Button
            variant="ghost"
            size="icon"
            className="h-8 w-8 text-destructive/80 hover:text-destructive hover:bg-destructive/10"
            onClick={() => onDelete(task.id)}
            aria-label={`Excluir tarefa "${task.title}"`}
          >
            <Trash2 className="h-4 w-4" />
          </Button>
        </div>
      </CardContent>
    </Card>
  );
}
