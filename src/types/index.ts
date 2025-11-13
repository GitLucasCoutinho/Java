import { FieldValue } from 'firebase/firestore';

export type Task = {
  id: string;
  title: string;
  description: string;
  isCompleted: boolean;
  category: string;
  createdAt?: FieldValue | Date;
  updatedAt?: FieldValue | Date;
  userId: string;
};
