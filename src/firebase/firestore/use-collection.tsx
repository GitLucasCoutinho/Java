/**
 * @file Hook React para se inscrever a uma coleção ou consulta do Firestore em tempo real.
 * Retorna os dados, o estado de carregamento e quaisquer erros.
 */
'use client';

import { useState, useEffect } from 'react';
import {
  Query,
  onSnapshot,
  DocumentData,
  FirestoreError,
  QuerySnapshot,
  CollectionReference,
} from 'firebase/firestore';
import { errorEmitter } from '@/firebase/error-emitter';
import { FirestorePermissionError } from '@/firebase/errors';

/** Tipo utilitário para adicionar um campo 'id' a um tipo T. */
export type WithId<T> = T & { id: string };

/**
 * Interface para o valor de retorno do hook `useCollection`.
 * @template T Tipo dos dados do documento.
 */
export interface UseCollectionResult<T> {
  data: WithId<T>[] | null; // Dados do documento com ID, ou nulo.
  isLoading: boolean;       // Verdadeiro se estiver carregando.
  error: FirestoreError | Error | null; // Objeto de erro, ou nulo.
}

/*
  Implementação interna da Query, usada para obter o caminho da consulta de forma segura.
  https://github.com/firebase/firebase-js-sdk/blob/c5f08a9bc5da0d2b0207802c972d53724ccef055/packages/firestore/src/lite-api/reference.ts#L143
*/
export interface InternalQuery extends Query<DocumentData> {
  _query: {
    path: {
      canonicalString(): string;
      toString(): string;
    }
  }
}

/**
 * Hook React para se inscrever a uma coleção ou consulta do Firestore em tempo real.
 * IMPORTANTE! Você DEVE memoizar a referência/consulta de entrada usando `useMemoFirebase` para evitar renderizações infinitas.
 * 
 * @template T Tipo opcional para os dados do documento.
 * @param {CollectionReference<DocumentData> | Query<DocumentData> | null | undefined} memoizedTargetRefOrQuery -
 * A CollectionReference ou Query do Firestore. O hook aguarda se for nulo/indefinido.
 * @returns {UseCollectionResult<T>} Objeto com dados, estado de carregamento e erro.
 */
export function useCollection<T = any>(
    memoizedTargetRefOrQuery: ((CollectionReference<DocumentData> | Query<DocumentData>) & {__memo?: boolean})  | null | undefined,
): UseCollectionResult<T> {
  type ResultItemType = WithId<T>;
  type StateDataType = ResultItemType[] | null;

  const [data, setData] = useState<StateDataType>(null);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [error, setError] = useState<FirestoreError | Error | null>(null);

  useEffect(() => {
    // Se a referência/consulta não for fornecida, reseta o estado.
    if (!memoizedTargetRefOrQuery) {
      setData(null);
      setIsLoading(false);
      setError(null);
      return;
    }

    setIsLoading(true);
    setError(null);

    // Cria o ouvinte em tempo real do Firestore.
    const unsubscribe = onSnapshot(
      memoizedTargetRefOrQuery,
      (snapshot: QuerySnapshot<DocumentData>) => {
        const results: ResultItemType[] = [];
        for (const doc of snapshot.docs) {
          results.push({ ...(doc.data() as T), id: doc.id });
        }
        setData(results);
        setError(null);
        setIsLoading(false);
      },
      (error: FirestoreError) => {
        // Em caso de erro (ex: permissão negada), cria um erro contextual.
        const path: string =
          memoizedTargetRefOrQuery.type === 'collection'
            ? (memoizedTargetRefOrQuery as CollectionReference).path
            : (memoizedTargetRefOrQuery as unknown as InternalQuery)._query.path.canonicalString()

        const contextualError = new FirestorePermissionError({
          operation: 'list',
          path,
        })

        setError(contextualError);
        setData(null);
        setIsLoading(false);

        // Emite o erro globalmente para que possa ser capturado pelo ErrorListener.
        errorEmitter.emit('permission-error', contextualError);
      }
    );

    // Limpeza: remove o ouvinte quando o componente é desmontado.
    return () => unsubscribe();
  }, [memoizedTargetRefOrQuery]); // Reexecuta o efeito se a referência/consulta mudar.
  
  // Lança um erro em desenvolvimento se a entrada não for memoizada, para evitar bugs.
  if(memoizedTargetRefOrQuery && !memoizedTargetRefOrQuery.__memo) {
    throw new Error(memoizedTargetRefOrQuery + ' não foi devidamente memoizado usando useMemoFirebase');
  }

  return { data, isLoading, error };
}
