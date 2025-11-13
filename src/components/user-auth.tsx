/**
 * @file Componente de autenticação do usuário.
 * Gerencia o fluxo de login com Google e o logout.
 * Exibe um botão de "Entrar" se o usuário não estiver logado,
 * ou um menu de avatar com a opção "Sair" se estiver logado.
 */
"use client";

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
import { useSidebar } from "./ui/sidebar";

/**
 * Componente que lida com a interface de autenticação do usuário.
 */
export function UserAuth() {
  const { user, auth } = useFirebase();
  const { state } = useSidebar();

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
    if (state === 'collapsed') {
        return (
            <Button variant="ghost" size="icon" onClick={handleGoogleSignIn}>
                <GoogleIcon className="h-5 w-5" />
            </Button>
        );
    }
    return (
      <Button variant="outline" onClick={handleGoogleSignIn}>
        <GoogleIcon className="mr-2 h-4 w-4" />
        Entrar com Google
      </Button>
    );
  }

  // Define a inicial do usuário para o Avatar de fallback.
  const userInitial = user.displayName ? user.displayName.charAt(0) : <User className="h-4 w-4" />;
  
  if (state === 'collapsed') {
    return (
        <DropdownMenu>
            <DropdownMenuTrigger asChild>
                <button>
                    <Avatar className="h-8 w-8">
                        <AvatarImage src={user.photoURL ?? ""} alt={user.displayName ?? ""} />
                        <AvatarFallback>{userInitial}</AvatarFallback>
                    </Avatar>
                </button>
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
    )
  }


  // Se o usuário estiver logado, exibe o menu dropdown com suas informações.
  return (
    <div className="flex items-center gap-4 w-full">
        <Avatar className="h-8 w-8">
            <AvatarImage src={user.photoURL ?? ""} alt={user.displayName ?? ""} />
            <AvatarFallback>{userInitial}</AvatarFallback>
        </Avatar>
        <div className="flex-1 truncate">
            <p className="text-sm font-medium leading-none truncate">
                {user.displayName}
            </p>
            <p className="text-xs leading-none text-muted-foreground truncate">
                {user.email}
            </p>
        </div>
        <Button variant="ghost" size="icon" className="h-8 w-8" onClick={handleSignOut}>
            <LogOut />
        </Button>
    </div>
  );
}
