/**
 * @file Provedor de contexto React para os serviços do Firebase.
 * Este componente disponibiliza as instâncias do Firebase (app, auth, firestore)
 * e o estado de autenticação do usuário para todos os componentes filhos.
 */
'use client';

import React, { DependencyList, createContext, useContext, ReactNode, useMemo, useState, useEffect } from 'react';
import { FirebaseApp } from 'firebase/app';
import { Firestore } from 'firebase/firestore';
import { Auth, User, onAuthStateChanged } from 'firebase/auth';
import { FirebaseErrorListener } from '@/components/FirebaseErrorListener'

/**
 * Propriedades para o componente FirebaseProvider.
 */
interface FirebaseProviderProps {
  children: ReactNode;
  firebaseApp: FirebaseApp;
  firestore: Firestore;
  auth: Auth;
}

/**
 * Estado interno para a autenticação do usuário.
 */
interface UserAuthState {
  user: User | null;
  isUserLoading: boolean;
  userError: Error | null;
}

/**
 * Estado combinado para o contexto do Firebase.
 */
export interface FirebaseContextState {
  areServicesAvailable: boolean; // Verdadeiro se os serviços principais estiverem disponíveis.
  firebaseApp: FirebaseApp | null;
  firestore: Firestore | null;
  auth: Auth | null;
  // Estado de autenticação do usuário
  user: User | null;
  isUserLoading: boolean; // Verdadeiro durante a verificação inicial de autenticação.
  userError: Error | null; // Erro do ouvinte de autenticação.
}

/**
 * Tipo de retorno para o hook `useFirebase()`.
 */
export interface FirebaseServicesAndUser {
  firebaseApp: FirebaseApp;
  firestore: Firestore;
  auth: Auth;
  user: User | null;
  isUserLoading: boolean;
  userError: Error | null;
}

/**
 * Tipo de retorno para o hook `useUser()`.
 */
export interface UserHookResult {
  user: User | null;
  isUserLoading: boolean;
  userError: Error | null;
}

// Criação do Contexto React para o Firebase.
export const FirebaseContext = createContext<FirebaseContextState | undefined>(undefined);

/**
 * Provedor que gerencia e fornece os serviços do Firebase e o estado de autenticação do usuário.
 */
export const FirebaseProvider: React.FC<FirebaseProviderProps> = ({
  children,
  firebaseApp,
  firestore,
  auth,
}) => {
  const [userAuthState, setUserAuthState] = useState<UserAuthState>({
    user: null,
    isUserLoading: true, // Inicia como carregando até o primeiro evento de autenticação.
    userError: null,
  });

  // Efeito para se inscrever nas mudanças de estado de autenticação do Firebase.
  useEffect(() => {
    if (!auth) {
      setUserAuthState({ user: null, isUserLoading: false, userError: new Error("Serviço de autenticação não fornecido.") });
      return;
    }

    const unsubscribe = onAuthStateChanged(
      auth,
      (firebaseUser) => { // Estado de autenticação determinado.
        setUserAuthState({ user: firebaseUser, isUserLoading: false, userError: null });
      },
      (error) => { // Erro no ouvinte de autenticação.
        console.error("FirebaseProvider: erro no onAuthStateChanged:", error);
        setUserAuthState({ user: null, isUserLoading: false, userError: error });
      }
    );
    return () => unsubscribe(); // Limpeza na desmontagem.
  }, [auth]); // Depende da instância de autenticação.

  // Memoiza o valor do contexto para evitar renderizações desnecessárias.
  const contextValue = useMemo((): FirebaseContextState => {
    const servicesAvailable = !!(firebaseApp && firestore && auth);
    return {
      areServicesAvailable: servicesAvailable,
      firebaseApp: servicesAvailable ? firebaseApp : null,
      firestore: servicesAvailable ? firestore : null,
      auth: servicesAvailable ? auth : null,
      ...userAuthState,
    };
  }, [firebaseApp, firestore, auth, userAuthState]);

  return (
    <FirebaseContext.Provider value={contextValue}>
      <FirebaseErrorListener />
      {children}
    </FirebaseContext.Provider>
  );
};

/**
 * Hook para acessar os serviços principais do Firebase e o estado de autenticação.
 * Lança um erro se usado fora de um FirebaseProvider ou se os serviços não estiverem disponíveis.
 */
export const useFirebase = (): FirebaseServicesAndUser => {
  const context = useContext(FirebaseContext);

  if (context === undefined) {
    throw new Error('useFirebase deve ser usado dentro de um FirebaseProvider.');
  }

  if (!context.areServicesAvailable || !context.firebaseApp || !context.firestore || !context.auth) {
    throw new Error('Serviços principais do Firebase não disponíveis. Verifique as props do FirebaseProvider.');
  }

  return {
    firebaseApp: context.firebaseApp,
    firestore: context.firestore,
    auth: context.auth,
    user: context.user,
    isUserLoading: context.isUserLoading,
    userError: context.userError,
  };
};

/** Hook para acessar a instância do Firebase Auth. */
export const useAuth = (): Auth => {
  const { auth } = useFirebase();
  return auth;
};

/** Hook para acessar a instância do Firestore. */
export const useFirestore = (): Firestore => {
  const { firestore } = useFirebase();
  return firestore;
};

/** Hook para acessar a instância do Firebase App. */
export const useFirebaseApp = (): FirebaseApp => {
  const { firebaseApp } = useFirebase();
  return firebaseApp;
};

// Tipo auxiliar para marcar um objeto como memoizado.
type MemoFirebase <T> = T & {__memo?: boolean};

/**
 * Um wrapper em torno do `useMemo` do React que adiciona uma flag de marcação.
 * Usado para garantir que as referências/consultas do Firestore passadas para os hooks `useCollection`/`useDoc`
 * sejam devidamente memoizadas, evitando loops de renderização infinitos.
 * @param factory A função que cria o valor a ser memoizado.
 * @param deps A lista de dependências.
 * @returns O valor memoizado.
 */
export function useMemoFirebase<T>(factory: () => T, deps: DependencyList): T | (MemoFirebase<T>) {
  const memoized = useMemo(factory, deps);
  
  if(typeof memoized !== 'object' || memoized === null) return memoized;
  // Adiciona uma propriedade não enumerável para marcar o objeto como memoizado.
  (memoized as MemoFirebase<T>).__memo = true;
  
  return memoized;
}

/**
 * Hook específico para acessar o estado do usuário autenticado.
 * @returns {UserHookResult} Objeto com `user`, `isUserLoading`, `userError`.
 */
export const useUser = (): UserHookResult => {
  const { user, isUserLoading, userError } = useFirebase();
  return { user, isUserLoading, userError };
};
