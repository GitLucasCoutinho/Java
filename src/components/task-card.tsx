"use client";

import { Card, CardContent } from "@/components/ui/card";
import { Checkbox } from "@/components/ui/checkbox";
import { Button } from "@/components/ui/button";
import { Trash2, Pencil } from "lucide-react";
import type { Task } from "@/types";
import { cn } from "@/lib/utils";

type TaskCardProps = {
  task: Task;
  onToggleComplete: (id: string) => void;
  onDelete: (id: string) => void;
  onEdit: (task: Task) => void;
};

export function TaskCard({
  task,
  onToggleComplete,
  onDelete,
  onEdit,
}: TaskCardProps) {
  return (
    <Card
      className={cn(
        "transition-all duration-300 hover:shadow-md",
        task.completed ? "bg-card/50" : "bg-card"
      )}
    >
      <CardContent className="p-4 flex items-start gap-4">
        <Checkbox
          id={`task-${task.id}`}
          checked={task.completed}
          onCheckedChange={() => onToggleComplete(task.id)}
          className="mt-1"
          aria-label={`Mark task "${task.title}" as ${task.completed ? 'incomplete' : 'complete'}`}
        />
        <div className="flex-1 grid gap-1">
          <label
            htmlFor={`task-${task.id}`}
            className={cn(
              "font-medium cursor-pointer transition-colors",
              task.completed && "line-through text-muted-foreground"
            )}
          >
            {task.title}
          </label>
          {task.description && (
            <p
              className={cn(
                "text-sm text-muted-foreground",
                task.completed && "line-through"
              )}
            >
              {task.description}
            </p>
          )}
        </div>
        <div className="flex gap-2">
          <Button
            variant="ghost"
            size="icon"
            className="h-8 w-8"
            onClick={() => onEdit(task)}
            aria-label={`Edit task "${task.title}"`}
          >
            <Pencil className="h-4 w-4" />
          </Button>
          <Button
            variant="ghost"
            size="icon"
            className="h-8 w-8 text-destructive/80 hover:text-destructive hover:bg-destructive/10"
            onClick={() => onDelete(task.id)}
            aria-label={`Delete task "${task.title}"`}
          >
            <Trash2 className="h-4 w-4" />
          </Button>
        </div>
      </CardContent>
    </Card>
  );
}
