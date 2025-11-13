/**
 * @file Ponto de entrada central para funcionalidades do Firebase.
 * Este arquivo lida com a inicialização idempotente do Firebase e exporta
 * os principais serviços e hooks para uso em toda a aplicação.
 */
'use client';

import { firebaseConfig } from '@/firebase/config';
import { initializeApp, getApp, FirebaseApp, getApps } from 'firebase/app';
import { getAuth, Auth } from 'firebase/auth';
import { getFirestore, Firestore } from 'firebase/firestore';

// Variáveis para armazenar as instâncias dos serviços do Firebase.
let firebaseApp: FirebaseApp;
let auth: Auth;
let firestore: Firestore;

/**
 * Inicializa o Firebase e os serviços principais (Auth, Firestore) de forma robusta.
 * Garante que a inicialização ocorra apenas uma vez (idempotente).
 * @returns Um objeto contendo as instâncias dos serviços do Firebase.
 */
export function initializeFirebase() {
  // Se nenhum app Firebase foi inicializado ainda, inicializa um.
  if (!getApps().length) {
    firebaseApp = initializeApp(firebaseConfig);
  } else {
    // Caso contrário, obtém a instância do app já existente.
    firebaseApp = getApp();
  }

  // Obtém as instâncias dos serviços de Autenticação e Firestore.
  auth = getAuth(firebaseApp);
  firestore = getFirestore(firebaseApp);

  return {
    firebaseApp,
    auth,
    firestore,
  };
}

// Re-exporta os provedores, hooks e utilitários para fácil importação em outros lugares.
export * from './provider';
export * from './client-provider';
export * from './firestore/use-collection';
export * from './firestore/use-doc';
export * from './errors';
export * from './error-emitter';