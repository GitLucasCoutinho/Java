'use server';

import { suggestTaskNames } from '@/ai/flows/ai-suggested-task-names';

export async function getAiSuggestions(userInput: string, taskCategory: string) {
  if (!userInput.trim()) {
    return { suggestions: [] };
  }
  
  try {
    const result = await suggestTaskNames({ userInput, taskCategory });
    return { suggestions: result.suggestedTaskNames };
  } catch (error) {
    console.error('Error getting AI suggestions:', error);
    return { error: 'Failed to get AI suggestions. Please try again.' };
  }
}
