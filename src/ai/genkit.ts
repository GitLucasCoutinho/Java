/**
 * @file Arquivo de configuração central para o Genkit.
 * Inicializa e exporta a instância principal do Genkit AI, configurando os plugins necessários.
 */
import {genkit} from 'genkit';
import {googleAI} from '@genkit-ai/google-genai';

/**
 * Instância principal do Genkit (ai).
 * Configurada com o plugin do Google AI para acessar modelos como o Gemini.
 * O modelo padrão definido é 'googleai/gemini-2.5-flash'.
 */
export const ai = genkit({
  plugins: [googleAI()],
  model: 'googleai/gemini-2.5-flash',
});
