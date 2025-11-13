/**
 * @file Componente ouvinte para erros de permissão do Firebase.
 * Este componente invisível escuta eventos globais de 'permission-error' e
 * lança o erro para ser capturado pelo `global-error.tsx` do Next.js,
 * exibindo uma sobreposição de erro rica em contexto durante o desenvolvimento.
 */
'use client';

import { useState, useEffect } from 'react';
import { errorEmitter } from '@/firebase/error-emitter';
import { FirestorePermissionError } from '@/firebase/errors';

/**
 * Um componente invisível que escuta eventos 'permission-error' emitidos globalmente.
 * Ele lança qualquer erro recebido para ser capturado pela barreira de erro global do Next.js.
 * @returns {null} Este componente não renderiza nada.
 */
export function FirebaseErrorListener() {
  // Estado para armazenar o erro recebido.
  const [error, setError] = useState<FirestorePermissionError | null>(null);

  useEffect(() => {
    // Callback para lidar com o erro quando o evento é emitido.
    const handleError = (error: FirestorePermissionError) => {
      // Define o erro no estado para acionar uma nova renderização.
      setError(error);
    };

    // Inscreve-se no evento 'permission-error'.
    errorEmitter.on('permission-error', handleError);

    // Remove a inscrição no desmonte do componente para evitar vazamentos de memória.
    return () => {
      errorEmitter.off('permission-error', handleError);
    };
  }, []);

  // Em uma nova renderização, se um erro existir no estado, lança-o.
  if (error) {
    throw error;
  }

  // Este componente não renderiza nada na UI.
  return null;
}
