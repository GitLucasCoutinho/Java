/**
 * @file Define os tipos de dados centrais para a aplicação.
 */
import { FieldValue } from 'firebase/firestore';

/**
 * Representa a estrutura de um objeto de Tarefa (Task).
 */
export type Task = {
  id: string;                      // ID único do documento no Firestore.
  title: string;                   // O título da tarefa.
  description: string;             // Descrição detalhada da tarefa.
  isCompleted: boolean;            // Status de conclusão da tarefa.
  category: string;                // Categoria à qual a tarefa pertence.
  createdAt?: FieldValue | Date;   // Timestamp de criação (pode ser do servidor ou cliente).
  updatedAt?: FieldValue | Date;   // Timestamp da última atualização.
  userId: string;                  // ID do usuário proprietário da tarefa.
  startDate?: FieldValue | Date;   // Data de início opcional.
  endDate?: FieldValue | Date;     // Data de término opcional.
};
