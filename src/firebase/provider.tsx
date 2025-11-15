/**
 * @file Provedor de contexto do Firebase para a aplicação.
 * Inicializa o Firebase e fornece o contexto para os componentes filhos.
 * Garante que a lógica de autenticação e redirecionamento seja tratada
 * corretamente em toda a aplicação.
 */
'use client';

import { createContext, useContext, useMemo, useEffect, useState, ReactNode } from 'react';
import { getApps, initializeApp, type FirebaseApp } from 'firebase/app';
import { getAuth, onAuthStateChanged, type User } from 'firebase/auth';
import { getFirestore, type Firestore } from 'firebase/firestore';
import { firebaseConfig } from './config';
import { handleRedirect } from './auth/handle-redirect';

/**
 * Interface que define a estrutura do contexto do Firebase.
 */
interface FirebaseContextValue {
  app: FirebaseApp;
  auth: ReturnType<typeof getAuth>;
  firestore: Firestore;
  user: User | null;
  isAuthLoading: boolean;
}

// Cria o contexto do Firebase com um valor inicial nulo.
const FirebaseContext = createContext<FirebaseContextValue | null>(null);

/**
 * Provedor de contexto que inicializa o Firebase e disponibiliza
 * para os componentes filhos.
 * @param {object} props - Propriedades do componente.
 * @param {React.ReactNode} props.children - Componentes filhos que terão acesso ao contexto.
 */
export function FirebaseProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<User | null>(null);
  const [isAuthLoading, setIsAuthLoading] = useState(true);

  // Memoiza a inicialização do Firebase para garantir que ocorra apenas uma vez.
  const firebaseServices = useMemo(() => {
    const apps = getApps();
    const app = apps.length === 0 ? initializeApp(firebaseConfig) : apps[0];
    const auth = getAuth(app);
    const firestore = getFirestore(app);
    return { app, auth, firestore };
  }, []);

  // Efeito para monitorar o estado de autenticação e processar o redirecionamento.
  useEffect(() => {
    // Processa o resultado do redirecionamento de login.
    handleRedirect().catch(console.error);

    // Observador para o estado de autenticação.
    const unsubscribe = onAuthStateChanged(firebaseServices.auth, (user) => {
      setUser(user);
      setIsAuthLoading(false);
    });

    // Limpa o observador quando o componente é desmontado.
    return () => unsubscribe();
  }, [firebaseServices.auth]);

  // Memoiza o valor do contexto para otimizar o desempenho.
  const contextValue = useMemo(
    () => ({
      ...firebaseServices,
      user,
      isAuthLoading,
    }),
    [firebaseServices, user, isAuthLoading]
  );

  return (
    <FirebaseContext.Provider value={contextValue}>
      {children}
    </FirebaseContext.Provider>
  );
}

/**
 * Hook para usar o memoized context do Firebase.
 * Permite o acesso à instância do Firebase de forma otimizada.
 * @returns {FirebaseContextValue} O valor do contexto do Firebase.
 */
export const useMemoFirebase = () => {
  const context = useContext(FirebaseContext);
  if (!context) {
    throw new Error('useMemoFirebase must be used within a FirebaseProvider');
  }
  return context;
};
