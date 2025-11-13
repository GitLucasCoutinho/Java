'use client';

import { firebaseConfig } from '@/firebase/config';
import { initializeApp, getApp, FirebaseApp } from 'firebase/app';
import { getAuth, Auth } from 'firebase/auth';
import { getFirestore, Firestore } from 'firebase/firestore';

// IMPORTANTE: NÃO MODIFIQUE ESTA FUNÇÃO
let firebaseApp: FirebaseApp;
let auth: Auth;
let firestore: Firestore;

/**
 * Inicializa o Firebase e os serviços principais (Auth, Firestore).
 * Esta função lida com a inicialização idempotente para evitar erros de "app já existe".
 * @returns Um objeto contendo as instâncias dos serviços do Firebase.
 */
export function initializeFirebase() {
  try {
    // Tenta obter o app Firebase já existente.
    firebaseApp = getApp();
  } catch (e) {
    // Se nenhum app existir, inicializa um novo.
    firebaseApp = initializeApp(firebaseConfig);
  }

  auth = getAuth(firebaseApp);
  firestore = getFirestore(firebaseApp);

  return {
    firebaseApp,
    auth,
    firestore,
  };
}


export * from './provider';
export * from './client-provider';
export * from './firestore/use-collection';
export * from './firestore/use-doc';
export * from './non-blocking-updates';
export * from './non-blocking-login';
export * from './errors';
export * from './error-emitter';
