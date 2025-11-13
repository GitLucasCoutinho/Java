'use client';
import {
  Auth, // Import Auth type for type hinting
  signInAnonymously,
  createUserWithEmailAndPassword,
  signInWithEmailAndPassword,
  GoogleAuthProvider,
  signInWithRedirect,
  getRedirectResult,
  // Assume getAuth and app are initialized elsewhere
} from 'firebase/auth';

/** Initiate anonymous sign-in (non-blocking). */
export function initiateAnonymousSignIn(authInstance: Auth): void {
  // CRITICAL: Call signInAnonymously directly. Do NOT use 'await signInAnonymously(...)'.
  signInAnonymously(authInstance);
  // Code continues immediately. Auth state change is handled by onAuthStateChanged listener.
}

/** Initiate Google sign-in via redirect. */
export function initiateGoogleSignInRedirect(authInstance: Auth): void {
  const provider = new GoogleAuthProvider();
  signInWithRedirect(authInstance, provider);
}

/** Handles the result from a redirect operation. Should be called on page load. */
export function handleRedirectResult(authInstance: Auth): void {
  getRedirectResult(authInstance)
    .then((result) => {
      // User is signed in. The onAuthStateChanged listener will handle the user object.
      // You can get the Google Access Token from result.credential if needed.
    })
    .catch((error) => {
      // Handle Errors here.
      console.error("Google sign-in redirect error:", error);
    });
}


/** Initiate email/password sign-up (non-blocking). */
export function initiateEmailSignUp(authInstance: Auth, email: string, password: string): void {
  // CRITICAL: Call createUserWithEmailAndPassword directly. Do NOT use 'await createUserWithEmailAndPassword(...)'.
  createUserWithEmailAndPassword(authInstance, email, password);
  // Code continues immediately. Auth state change is handled by onAuthStateChanged listener.
}

/** Initiate email/password sign-in (non-blocking). */
export function initiateEmailSignIn(authInstance: Auth, email: string, password: string): void {
  // CRITICAL: Call signInWithEmailAndPassword directly. Do NOT use 'await signInWithEmailAndPassword(...)'.
  signInWithEmailAndPassword(authInstance, email, password);
  // Code continues immediately. Auth state change is handled by onAuthStateChanged listener.
}
