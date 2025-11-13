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
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Plus } from "lucide-react";
import type { Task } from "@/types";
import { useEffect } from "react";

/**
 * Esquema de validação para o formulário de adicionar tarefa.
 */
const formSchema = z.object({
  title: z.string().min(2, "O título deve ter pelo menos 2 caracteres."),
  description: z.string().optional(),
  category: z.string().min(1, "Por favor, selecione uma categoria."),
});

/**
 * Propriedades para o componente AddTaskForm.
 */
type AddTaskFormProps = {
  onAddTask: (taskData: Omit<Task, "id" | "isCompleted" | "userId">) => void;
  defaultCategory: string;
};

// Categorias de tarefas fixas para o seletor.
const taskCategories = ["Pessoal", "Trabalho", "Compras", "Recados", "Estudo"];

/**
 * Um formulário encapsulado em um Card para adicionar novas tarefas à lista.
 * @param {AddTaskFormProps} props - Propriedades do componente.
 */
export function AddTaskForm({ onAddTask, defaultCategory }: AddTaskFormProps) {
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      title: "",
      description: "",
      category: defaultCategory,
    },
  });

  useEffect(() => {
    form.setValue('category', defaultCategory);
  }, [defaultCategory, form]);

  /**
   * Função chamada ao submeter o formulário.
   * @param {z.infer<typeof formSchema>} values - Os valores do formulário validados.
   */
  function onSubmit(values: z.infer<typeof formSchema>) {
    onAddTask({ title: values.title, description: values.description || "", category: values.category });
    form.reset({ title: "", description: "", category: values.category });
  }

  return (
    <Card className="bg-card/80 backdrop-blur-sm border-border/50 shadow-lg">
      <CardHeader>
        <CardTitle className="flex items-center gap-2">
          <Plus className="h-5 w-5" />
          Adicionar Nova Tarefa
        </CardTitle>
      </CardHeader>
      <CardContent>
        <Form {...form}>
          <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
              <div className="md:col-span-2">
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
              </div>
              <div>
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
              </div>
            </div>
            
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
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <Button type="submit" className="w-full md:w-auto bg-primary hover:bg-primary/90 text-primary-foreground">Adicionar Tarefa</Button>
          </form>
        </Form>
      </CardContent>
    </Card>
  );
}
