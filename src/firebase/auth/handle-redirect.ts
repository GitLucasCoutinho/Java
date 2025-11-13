/**
 * @file Lida com a lógica de redirecionamento de login do Firebase de forma não bloqueante.
 * Este arquivo exporta uma função que chama `getRedirectResult` para processar
 * credenciais do usuário após um redirecionamento de um provedor de autenticação como o Google,
 * sem interromper o fluxo principal da aplicação.
 */
'use client';

import { getAuth, getRedirectResult, type UserCredential } from 'firebase/auth';
import { initializeFirebase } from '@/firebase';

// Uma promessa que resolve com as credenciais do usuário ou nulo.
// Isso armazena o resultado para que não precisemos chamar getRedirectResult várias vezes.
let redirectResultPromise: Promise<UserCredential | null> | null = null;

/**
 * Inicia o processo de obtenção do resultado do redirecionamento do Firebase Auth.
 * Esta função é projetada para ser chamada incondicionalmente no nível superior
 * do seu aplicativo (por exemplo, no layout raiz). Ela garante que a chamada para
 * `getRedirectResult` aconteça apenas uma vez.
 */
export function handleRedirect(): void {
  if (typeof window !== 'undefined' && !redirectResultPromise) {
    // Inicializa o Firebase para garantir que a instância de autenticação esteja disponível.
    initializeFirebase();
    // Obtém a instância de autenticação.
    const auth = getAuth();
    // Chama getRedirectResult e armazena a promessa.
    redirectResultPromise = getRedirectResult(auth);
  }
}
