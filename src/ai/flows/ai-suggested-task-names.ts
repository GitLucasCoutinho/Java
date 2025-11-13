// Use server directive.
'use server';

/**
 * @fileOverview AI-powered task name suggestions flow.
 *
 * This file defines a Genkit flow that suggests task names based on user input and task category.
 *
 * @exports suggestTaskNames - Function to trigger the task name suggestion flow.
 * @exports SuggestTaskNamesInput - The input type for the suggestTaskNames function.
 * @exports SuggestTaskNamesOutput - The output type for the suggestTaskNames function.
 */

import {ai} from '@/ai/genkit';
import {z} from 'genkit';

// Define the input schema for the flow
const SuggestTaskNamesInputSchema = z.object({
  userInput: z.string().describe('The user input describing the task.'),
  taskCategory: z.string().describe('The category of the task (e.g., work, personal, shopping).'),
});
export type SuggestTaskNamesInput = z.infer<typeof SuggestTaskNamesInputSchema>;

// Define the output schema for the flow
const SuggestTaskNamesOutputSchema = z.object({
  suggestedTaskNames: z.array(
    z.string().describe('An AI-powered suggestion for the task name.')
  ).describe('A list of suggested task names.'),
});
export type SuggestTaskNamesOutput = z.infer<typeof SuggestTaskNamesOutputSchema>;

// Exported function to trigger the flow
export async function suggestTaskNames(input: SuggestTaskNamesInput): Promise<SuggestTaskNamesOutput> {
  return suggestTaskNamesFlow(input);
}

// Define the prompt
const suggestTaskNamesPrompt = ai.definePrompt({
  name: 'suggestTaskNamesPrompt',
  input: {schema: SuggestTaskNamesInputSchema},
  output: {schema: SuggestTaskNamesOutputSchema},
  prompt: `You are a helpful AI assistant that suggests task names based on user input and task category.

  User Input: {{{userInput}}}
  Task Category: {{{taskCategory}}}

  Suggest 3 relevant and creative task names. Return the suggestions as a JSON array of strings.
  Do not include any descriptions, only the task names.
  Example: ["Buy groceries", "Pay bills", "Schedule appointment"]
  `,
});

// Define the flow
const suggestTaskNamesFlow = ai.defineFlow(
  {
    name: 'suggestTaskNamesFlow',
    inputSchema: SuggestTaskNamesInputSchema,
    outputSchema: SuggestTaskNamesOutputSchema,
  },
  async input => {
    const {output} = await suggestTaskNamesPrompt(input);
    return output!;
  }
);
