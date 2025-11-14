/**
 * @file Provedor do Firebase específico para o cliente.
 * Este componente garante que o Firebase seja inicializado apenas uma vez no lado do cliente,
 * evitando múltiplas inicializações que poderiam causar erros. Ele envolve o FirebaseProvider principal.
 */
'use client';

import React, { useMemo, type ReactNode, useEffect } from 'react';
import { FirebaseProvider } from '@/firebase/provider';
import { initializeFirebase } from '@/firebase';
import { handleRedirect } from './auth/handle-redirect';


/**
 * Propriedades para o FirebaseClientProvider.
 */
interface FirebaseClientProviderProps {
  children: ReactNode;
}

/**
 * Um provedor React que envolve o FirebaseProvider e garante que a inicialização do Firebase
 * ocorra apenas no lado do cliente e apenas uma vez.
 * @param {FirebaseClientProviderProps} props As propriedades do componente.
 * @returns {JSX.Element} O provedor do Firebase com os serviços inicializados.
 */
export function FirebaseClientProvider({ children }: FirebaseClientProviderProps) {
  // `useMemo` com um array de dependências vazio garante que `initializeFirebase` seja chamado apenas uma vez.
  const firebaseServices = useMemo(() => {
    return initializeFirebase();
  }, []); // O array vazio garante que a função só seja executada na montagem inicial.

  // O useEffect garante que `handleRedirect` seja chamado no lado do cliente após a inicialização.
  useEffect(() => {
    // Lida com o resultado do redirecionamento de login do Firebase.
    // Chamar esta função aqui garante que o resultado seja processado
    // o mais cedo possível no ciclo de vida do aplicativo no cliente, após a inicialização.
    handleRedirect();
  }, [firebaseServices]); // Depende dos serviços para garantir a ordem.

  return (
    <FirebaseProvider
      firebaseApp={firebaseServices.firebaseApp}
      auth={firebaseServices.auth}
      firestore={firebaseServices.firestore}
    >
      {children}
    </FirebaseProvider>
  );
}
