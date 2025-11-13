/**
 * @file Componente de formulário para adicionar uma nova tarefa.
 * Utiliza react-hook-form para gerenciamento de estado do formulário e Zod para validação.
 */
"use client";

import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Form, FormControl, FormDescription, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover";
import { Calendar } from "@/components/ui/calendar";
import { CalendarIcon } from "lucide-react";
import { cn } from "@/lib/utils";
import { format } from "date-fns";
import type { Task } from "@/types";
import { useEffect } from "react";
import { DaySelector } from "./day-selector";

/**
 * Esquema de validação para o formulário de adicionar tarefa.
 */
const formSchema = z.object({
  title: z.string().min(2, "O título deve ter pelo menos 2 caracteres."),
  description: z.string().optional(),
  category: z.string().min(1, "Por favor, selecione uma categoria."),
  recurringDays: z.array(z.string()).optional(),
  startDate: z.date().optional(),
  endDate: z.date().optional(),
});

/**
 * Propriedades para o componente AddTaskForm.
 */
type AddTaskFormProps = {
  onAddTask: (taskData: Omit<Task, "id" | "isCompleted" | "userId">) => void;
  onDone?: () => void;
  defaultCategory?: string;
};

// Categorias de tarefas fixas para o seletor.
const taskCategories = ["Pessoal", "Trabalho", "Compras", "Recados", "Estudo"];

/**
 * Um formulário para adicionar novas tarefas à lista.
 * @param {AddTaskFormProps} props - Propriedades do componente.
 */
export function AddTaskForm({ onAddTask, onDone, defaultCategory }: AddTaskFormProps) {
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      title: "",
      description: "",
      category: defaultCategory || "Pessoal",
      recurringDays: [],
    },
  });

  useEffect(() => {
    if(defaultCategory) {
        form.setValue('category', defaultCategory);
    }
  }, [defaultCategory, form]);

  /**
   * Função chamada ao submeter o formulário.
   * @param {z.infer<typeof formSchema>} values - Os valores do formulário validados.
   */
  function onSubmit(values: z.infer<typeof formSchema>) {
    onAddTask({ 
        title: values.title, 
        description: values.description || "", 
        category: values.category,
        recurringDays: values.recurringDays,
        startDate: values.startDate,
        endDate: values.endDate,
    });
    form.reset();
    onDone?.();
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
        <div className="space-y-4">
            <FormField
              control={form.control}
              name="title"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Título da Tarefa</FormLabel>
                  <FormControl>
                    <Input placeholder="ex: Comprar mantimentos para a semana" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="category"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Categoria</FormLabel>
                  <Select onValueChange={field.onChange} value={field.value} defaultValue={field.value}>
                    <FormControl>
                      <SelectTrigger>
                        <SelectValue placeholder="Selecione uma categoria" />
                      </SelectTrigger>
                    </FormControl>
                    <SelectContent>
                      {taskCategories.map((category) => (
                        <SelectItem key={category} value={category}>
                          {category}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                  <FormMessage />
                </FormItem>
              )}
            />
             <FormField
              control={form.control}
              name="description"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Descrição (Opcional)</FormLabel>
                  <FormControl>
                    <Textarea
                      placeholder="Adicione mais detalhes sobre a tarefa..."
                      className="resize-none"
                      {...field}
                      value={field.value ?? ''}
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="recurringDays"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Repetir nos dias (Opcional)</FormLabel>
                    <FormControl>
                        <DaySelector value={field.value || []} onValueChange={field.onChange} />
                    </FormControl>
                  <FormDescription>
                    Selecione os dias para tarefas recorrentes.
                  </FormDescription>
                  <FormMessage />
                </FormItem>
              )}
            />
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <FormField
                control={form.control}
                name="startDate"
                render={({ field }) => (
                    <FormItem className="flex flex-col">
                    <FormLabel>Data de Início (Opcional)</FormLabel>
                    <Popover>
                        <PopoverTrigger asChild>
                        <FormControl>
                            <Button
                            variant={"outline"}
                            className={cn(
                                "w-full pl-3 text-left font-normal",
                                !field.value && "text-muted-foreground"
                            )}
                            >
                            {field.value ? (
                                format(field.value, "PPP")
                            ) : (
                                <span>Escolha uma data</span>
                            )}
                            <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                            </Button>
                        </FormControl>
                        </PopoverTrigger>
                        <PopoverContent className="w-auto p-0" align="start">
                        <Calendar
                            mode="single"
                            selected={field.value}
                            onSelect={field.onChange}
                            initialFocus
                        />
                        </PopoverContent>
                    </Popover>
                    <FormMessage />
                    </FormItem>
                )}
                />
                 <FormField
                control={form.control}
                name="endDate"
                render={({ field }) => (
                    <FormItem className="flex flex-col">
                    <FormLabel>Data de Fim (Opcional)</FormLabel>
                    <Popover>
                        <PopoverTrigger asChild>
                        <FormControl>
                            <Button
                            variant={"outline"}
                            className={cn(
                                "w-full pl-3 text-left font-normal",
                                !field.value && "text-muted-foreground"
                            )}
                            >
                            {field.value ? (
                                format(field.value, "PPP")
                            ) : (
                                <span>Escolha uma data</span>
                            )}
                            <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                            </Button>
                        </FormControl>
                        </PopoverTrigger>
                        <PopoverContent className="w-auto p-0" align="start">
                        <Calendar
                            mode="single"
                            selected={field.value}
                            onSelect={field.onChange}
                            initialFocus
                        />
                        </PopoverContent>
                    </Popover>
                    <FormMessage />
                    </FormItem>
                )}
                />
            </div>
        </div>
        <Button type="submit" className="w-full bg-primary hover:bg-primary/90 text-primary-foreground">Adicionar Tarefa</Button>
      </form>
    </Form>
  );
}
