/**
 * @file Implementa um emissor de eventos (pub/sub) fortemente tipado.
 * É usado para desacoplar a origem de um erro (ex: uma chamada do Firestore)
 * do seu tratamento (ex: um componente React que exibe uma sobreposição de erro).
 */
'use client';
import { FirestorePermissionError } from '@/firebase/errors';

/**
 * Define a forma de todos os eventos possíveis e seus tipos de payload correspondentes.
 * Centraliza as definições de eventos para segurança de tipos em toda a aplicação.
 */
export interface AppEvents {
  'permission-error': FirestorePermissionError;
}

// Um tipo genérico para uma função de callback.
type Callback<T> = (data: T) => void;

/**
 * Cria um emissor de eventos pub/sub fortemente tipado.
 * Usa um tipo genérico T que estende um registro de nomes de eventos para tipos de payload.
 * @returns Um objeto com métodos `on`, `off` e `emit`.
 */
function createEventEmitter<T extends Record<string, any>>() {
  // O objeto `events` armazena arrays de callbacks, indexados pelo nome do evento.
  const events: { [K in keyof T]?: Array<Callback<T[K]>> } = {};

  return {
    /**
     * Inscreve-se em um evento.
     * @param {K} eventName O nome do evento para se inscrever.
     * @param {Callback<T[K]>} callback A função a ser chamada quando o evento é emitido.
     */
    on<K extends keyof T>(eventName: K, callback: Callback<T[K]>) {
      if (!events[eventName]) {
        events[eventName] = [];
      }
      events[eventName]?.push(callback);
    },

    /**
     * Desinscreve-se de um evento.
     * @param {K} eventName O nome do evento para se desinscrever.
     * @param {Callback<T[K]>} callback O callback específico a ser removido.
     */
    off<K extends keyof T>(eventName: K, callback: Callback<T[K]>) {
      if (!events[eventName]) {
        return;
      }
      events[eventName] = events[eventName]?.filter(cb => cb !== callback);
    },

    /**
     * Publica um evento para todos os inscritos.
     * @param {K} eventName O nome do evento a ser emitido.
     * @param {T[K]} data O payload de dados que corresponde ao tipo do evento.
     */
    emit<K extends keyof T>(eventName: K, data: T[K]) {
      if (!events[eventName]) {
        return;
      }
      events[eventName]?.forEach(callback => callback(data));
    },
  };
}

// Cria e exporta uma instância singleton do emissor, tipada com nossa interface AppEvents.
export const errorEmitter = createEventEmitter<AppEvents>();
