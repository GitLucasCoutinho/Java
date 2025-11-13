"use client";

import { useState } from "react";
import { useForm, useWatch } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Plus, Sparkles, Loader2 } from "lucide-react";
import type { Task } from "@/types";
import { getAiSuggestions } from "@/app/actions";
import { useToast } from "@/hooks/use-toast";

const formSchema = z.object({
  title: z.string().min(2, "Title must be at least 2 characters."),
  description: z.string().optional(),
  category: z.string().min(1, "Please select a category."),
});

type AddTaskFormProps = {
  onAddTask: (taskData: Omit<Task, "id" | "completed">) => void;
};

const taskCategories = ["Personal", "Work", "Shopping", "Errands", "Study"];

export function AddTaskForm({ onAddTask }: AddTaskFormProps) {
  const [suggestions, setSuggestions] = useState<string[]>([]);
  const [isSuggesting, setIsSuggesting] = useState(false);
  const { toast } = useToast();

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      title: "",
      description: "",
      category: "Personal",
    },
  });

  const watchedTitle = useWatch({ control: form.control, name: "title" });
  const watchedCategory = useWatch({ control: form.control, name: "category" });

  const handleGetSuggestions = async () => {
    setIsSuggesting(true);
    setSuggestions([]);
    const result = await getAiSuggestions(watchedTitle, watchedCategory);
    if (result.error) {
      toast({
        variant: "destructive",
        title: "Suggestion Error",
        description: result.error,
      });
    } else if (result.suggestions) {
      setSuggestions(result.suggestions);
    }
    setIsSuggesting(false);
  };

  function onSubmit(values: z.infer<typeof formSchema>) {
    onAddTask({ title: values.title, description: values.description || "" });
    form.reset();
    setSuggestions([]);
  }

  return (
    <Card className="bg-card/80 backdrop-blur-sm border-border/50 shadow-lg">
      <CardHeader>
        <CardTitle className="flex items-center gap-2">
          <Plus className="h-5 w-5" />
          Add a New Task
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
                      <FormLabel>Task Title</FormLabel>
                      <FormControl>
                        <Input placeholder="e.g., Buy groceries for the week" {...field} />
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
                      <FormLabel>Category</FormLabel>
                      <Select onValueChange={field.onChange} defaultValue={field.value}>
                        <FormControl>
                          <SelectTrigger>
                            <SelectValue placeholder="Select a category" />
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
            
            <Button
              type="button"
              variant="outline"
              size="sm"
              onClick={handleGetSuggestions}
              disabled={isSuggesting || !watchedTitle}
            >
              {isSuggesting ? (
                <Loader2 className="mr-2 h-4 w-4 animate-spin" />
              ) : (
                <Sparkles className="mr-2 h-4 w-4" />
              )}
              Smart Suggest Titles
            </Button>
            
            {suggestions.length > 0 && (
              <div className="flex flex-wrap gap-2">
                {suggestions.map((suggestion, index) => (
                  <Button
                    key={index}
                    type="button"
                    variant="ghost"
                    size="sm"
                    className="bg-accent/50 hover:bg-accent"
                    onClick={() => {
                      form.setValue("title", suggestion);
                      setSuggestions([]);
                    }}
                  >
                    {suggestion}
                  </Button>
                ))}
              </div>
            )}
            
            <FormField
              control={form.control}
              name="description"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Description (Optional)</FormLabel>
                  <FormControl>
                    <Textarea
                      placeholder="Add more details about the task..."
                      className="resize-none"
                      {...field}
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <Button type="submit" className="w-full md:w-auto bg-primary hover:bg-primary/90 text-primary-foreground">Add Task</Button>
          </form>
        </Form>
      </CardContent>
    </Card>
  );
}
