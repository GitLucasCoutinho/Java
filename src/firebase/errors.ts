/**
 * @file Define uma classe de erro personalizada, `FirestorePermissionError`.
 * Esta classe formata um erro de permissão do Firestore de uma maneira que imita
 * o objeto `request` das Regras de Segurança, fornecendo um contexto rico para depuração,
 * especialmente útil quando consumido por uma LLM ou exibido em uma sobreposição de erro.
 */
'use client';
import { getAuth, type User } from 'firebase/auth';

/**
 * Contexto da regra de segurança para uma operação do Firestore.
 */
type SecurityRuleContext = {
  path: string;
  operation: 'get' | 'list' | 'create' | 'update' | 'delete' | 'write';
  requestResourceData?: any;
};

/**
 * Interface para o token de autenticação do Firebase.
 */
interface FirebaseAuthToken {
  name: string | null;
  email: string | null;
  email_verified: boolean;
  phone_number: string | null;
  sub: string;
  firebase: {
    identities: Record<string, string[]>;
    sign_in_provider: string;
    tenant: string | null;
  };
}

/**
 * Interface para o objeto de autenticação do Firebase.
 */
interface FirebaseAuthObject {
  uid: string;
  token: FirebaseAuthToken;
}

/**
 * Interface para a requisição da regra de segurança.
 */
interface SecurityRuleRequest {
  auth: FirebaseAuthObject | null;
  method: string;
  path: string;
  resource?: {
    data: any;
  };
}

/**
 * Constrói um objeto de autenticação compatível com as regras de segurança a partir do usuário do Firebase.
 * @param {User | null} currentUser O usuário do Firebase atualmente autenticado.
 * @returns {FirebaseAuthObject | null} Um objeto que espelha `request.auth` nas regras de segurança, ou nulo.
 */
function buildAuthObject(currentUser: User | null): FirebaseAuthObject | null {
  if (!currentUser) {
    return null;
  }

  const token: FirebaseAuthToken = {
    name: currentUser.displayName,
    email: currentUser.email,
    email_verified: currentUser.emailVerified,
    phone_number: currentUser.phoneNumber,
    sub: currentUser.uid,
    firebase: {
      identities: currentUser.providerData.reduce((acc, p) => {
        if (p.providerId) {
          acc[p.providerId] = [p.uid];
        }
        return acc;
      }, {} as Record<string, string[]>),
      sign_in_provider: currentUser.providerData[0]?.providerId || 'custom',
      tenant: currentUser.tenantId,
    },
  };

  return {
    uid: currentUser.uid,
    token: token,
  };
}

/**
 * Constrói o objeto de requisição simulado completo para a mensagem de erro.
 * @param {SecurityRuleContext} context O contexto da operação falha do Firestore.
 * @returns {SecurityRuleRequest} Um objeto de requisição estruturado.
 */
function buildRequestObject(context: SecurityRuleContext): SecurityRuleRequest {
  let authObject: FirebaseAuthObject | null = null;
  try {
    // Tenta obter o usuário atual com segurança.
    const firebaseAuth = getAuth();
    const currentUser = firebaseAuth.currentUser;
    if (currentUser) {
      authObject = buildAuthObject(currentUser);
    }
  } catch {
    // Captura erros se o app Firebase ainda não estiver inicializado.
  }

  return {
    auth: authObject,
    method: context.operation,
    path: `/databases/(default)/documents/${context.path}`,
    resource: context.requestResourceData ? { data: context.requestResourceData } : undefined,
  };
}

/**
 * Constrói a mensagem de erro final e formatada.
 * @param {SecurityRuleRequest} requestObject O objeto de requisição simulado.
 * @returns {string} Uma string contendo a mensagem de erro e o payload JSON.
 */
function buildErrorMessage(requestObject: SecurityRuleRequest): string {
  return `Missing or insufficient permissions: The following request was denied by Firestore Security Rules:
${JSON.stringify(requestObject, null, 2)}`;
}

/**
 * Uma classe de erro personalizada projetada para depuração.
 * Ela estrutura as informações do erro para imitar o objeto `request`
 * disponível nas Regras de Segurança do Firestore.
 */
export class FirestorePermissionError extends Error {
  public readonly request: SecurityRuleRequest;

  constructor(context: SecurityRuleContext) {
    const requestObject = buildRequestObject(context);
    super(buildErrorMessage(requestObject));
    this.name = 'FirebaseError';
    this.request = requestObject;
  }
}
