/**
 * @file Componente de autenticação do usuário.
 * Gerencia o fluxo de login com Google e o logout.
 * Exibe um botão de "Entrar" se o usuário não estiver logado,
 * ou um menu de avatar com a opção "Sair" se estiver logado.
 */
"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import {
  GoogleAuthProvider,
  signInWithRedirect,
  signOut,
} from "firebase/auth";
import { useFirebase } from "@/firebase";
import { Button } from "@/components/ui/button";
import { GoogleIcon } from "@/components/icons";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { LogOut, User } from "lucide-react";

/**
 * Componente que lida com a interface de autenticação do usuário.
 */
export function UserAuth() {
  const { user, auth } = useFirebase();
  const router = useRouter();

  // Força a revalidação da página quando o estado do usuário muda.
  // Isso é crucial para corrigir problemas de cache do Next.js com o redirecionamento do Firebase.
  useEffect(() => {
    if (user) {
      router.refresh();
    }
  }, [user, router]);


  /**
   * Inicia o fluxo de login com o Google usando redirecionamento.
   */
  const handleGoogleSignIn = () => {
    if (!auth) return;
    const provider = new GoogleAuthProvider();
    signInWithRedirect(auth, provider);
  };

  /**
   * Realiza o logout do usuário.
   */
  const handleSignOut = () => {
    if (!auth) return;
    signOut(auth);
  };

  // Se não houver usuário, exibe o botão de login.
  if (!user) {
    return (
      <Button variant="outline" onClick={handleGoogleSignIn}>
        <GoogleIcon className="mr-2 h-4 w-4" />
        Entrar com Google
      </Button>
    );
  }

  // Define a inicial do usuário para o Avatar de fallback.
  const userInitial = user.displayName ? user.displayName.charAt(0) : <User className="h-4 w-4" />;
  
  // Se o usuário estiver logado, exibe o menu dropdown com suas informações.
  return (
    <DropdownMenu>
        <DropdownMenuTrigger asChild>
            <Button variant="ghost" className="relative h-8 w-8 rounded-full">
                <Avatar className="h-8 w-8">
                    <AvatarImage src={user.photoURL ?? ""} alt={user.displayName ?? ""} />
                    <AvatarFallback>{userInitial}</AvatarFallback>
                </Avatar>
            </Button>
        </DropdownMenuTrigger>
        <DropdownMenuContent className="w-56" align="end" forceMount>
            <DropdownMenuLabel className="font-normal">
            <div className="flex flex-col space-y-1">
                <p className="text-sm font-medium leading-none">
                {user.displayName}
                </p>
                <p className="text-xs leading-none text-muted-foreground">
                {user.email}
                </p>
            </div>
            </DropdownMenuLabel>
            <DropdownMenuSeparator />
            <DropdownMenuItem onClick={handleSignOut}>
            <LogOut className="mr-2 h-4 w-4" />
            <span>Sair</span>
            </DropdownMenuItem>
        </DropdownMenuContent>
    </DropdownMenu>
  );
}
