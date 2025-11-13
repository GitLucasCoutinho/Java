/**
 * @file Hook React para se inscrever a um único documento do Firestore em tempo real.
 * Retorna os dados do documento, o estado de carregamento e quaisquer erros.
 */
'use client';
    
import { useState, useEffect } from 'react';
import {
  DocumentReference,
  onSnapshot,
  DocumentData,
  FirestoreError,
  DocumentSnapshot,
} from 'firebase/firestore';
import { errorEmitter } from '@/firebase/error-emitter';
import { FirestorePermissionError } from '@/firebase/errors';

/** Tipo utilitário para adicionar um campo 'id' a um tipo T. */
type WithId<T> = T & { id: string };

/**
 * Interface para o valor de retorno do hook `useDoc`.
 * @template T Tipo dos dados do documento.
 */
export interface UseDocResult<T> {
  data: WithId<T> | null; // Dados do documento com ID, ou nulo se não existir ou erro.
  isLoading: boolean;       // Verdadeiro se estiver carregando.
  error: FirestoreError | Error | null; // Objeto de erro, ou nulo.
}

/**
 * Hook React para se inscrever a um único documento do Firestore em tempo real.
 * IMPORTANTE! Você DEVE memoizar a referência de entrada usando `useMemoFirebase` para evitar renderizações infinitas.
 *
 * @template T Tipo opcional para os dados do documento.
 * @param {DocumentReference<DocumentData> | null | undefined} memoizedDocRef -
 * A DocumentReference do Firestore. O hook aguarda se for nulo/indefinido.
 * @returns {UseDocResult<T>} Objeto com dados, estado de carregamento e erro.
 */
export function useDoc<T = any>(
  memoizedDocRef: DocumentReference<DocumentData> | null | undefined,
): UseDocResult<T> {
  type StateDataType = WithId<T> | null;

  const [data, setData] = useState<StateDataType>(null);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [error, setError] = useState<FirestoreError | Error | null>(null);

  useEffect(() => {
    // Se a referência não for fornecida, reseta o estado.
    if (!memoizedDocRef) {
      setData(null);
      setIsLoading(false);
      setError(null);
      return;
    }

    setIsLoading(true);
    setError(null);

    // Cria o ouvinte em tempo real do Firestore para o documento.
    const unsubscribe = onSnapshot(
      memoizedDocRef,
      (snapshot: DocumentSnapshot<DocumentData>) => {
        if (snapshot.exists()) {
          // Se o documento existir, atualiza o estado com seus dados.
          setData({ ...(snapshot.data() as T), id: snapshot.id });
        } else {
          // Se o documento não existir, define os dados como nulos.
          setData(null);
        }
        setError(null);
        setIsLoading(false);
      },
      (error: FirestoreError) => {
        // Em caso de erro (ex: permissão negada), cria um erro contextual.
        const contextualError = new FirestorePermissionError({
          operation: 'get',
          path: memoizedDocRef.path,
        })

        setError(contextualError);
        setData(null);
        setIsLoading(false);

        // Emite o erro globalmente.
        errorEmitter.emit('permission-error', contextualError);
      }
    );

    // Limpeza: remove o ouvinte quando o componente é desmontado.
    return () => unsubscribe();
  }, [memoizedDocRef]); // Reexecuta o efeito se a referência do documento mudar.

  return { data, isLoading, error };
}
