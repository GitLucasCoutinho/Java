/**
 * @file Lida com a lógica de redirecionamento de login do Firebase de forma não bloqueante.
 * Este arquivo exporta uma função que chama `getRedirectResult` para processar
 * credenciais do usuário após um redirecionamento de um provedor de autenticação como o Google,
 * sem interromper o fluxo principal da aplicação.
 */
'use client';

import { getAuth, getRedirectResult, type UserCredential } from 'firebase/auth';

/**
 * Inicia o processo de obtenção do resultado do redirecionamento do Firebase Auth.
 * Esta função é projetada para ser chamada incondicionalmente no nível superior
 * do seu aplicativo (por exemplo, no layout raiz). Ela garante que a chamada para
 * `getRedirectResult` aconteça apenas uma vez.
 */
export function handleRedirect(): Promise<UserCredential | null> {
  const auth = getAuth();
  return getRedirectResult(auth);
}
