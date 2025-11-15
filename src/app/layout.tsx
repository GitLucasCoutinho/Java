/**
 * @file Define o layout raiz da aplicação.
 * Este componente envolve todas as páginas e inclui elementos globais como fontes,
 * provedores de contexto (Firebase) e componentes de UI (Toaster).
 */
import type {Metadata} from 'next';
import './globals.css';
import { Toaster } from "@/components/ui/toaster";
import { FirebaseClientProvider } from '@/firebase/client-provider';

/**
 * Metadados da página, como título e descrição.
 * Usado para SEO e para o navegador.
 */
export const metadata: Metadata = {
  title: 'TaskFlow',
  description: 'Um aplicativo de lista de tarefas simples e inteligente.',
};

/**
 * Componente RootLayout que define a estrutura HTML base para todas as páginas.
 * @param {object} props - As propriedades do componente.
 * @param {React.ReactNode} props.children - Os componentes filhos que serão renderizados dentro do layout.
 * @returns {JSX.Element} O layout raiz da aplicação.
 */
export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt-BR" className="h-full">
      <head>
        {/* Carregamento de fontes do Google Fonts */}
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossOrigin="anonymous" />
        <link href="https://fonts.googleapis.com/css2?family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet" />
      </head>
      <body className="font-body antialiased h-full">
        {/* Provedor do Firebase para disponibilizar a instância do Firebase para toda a aplicação cliente */}
        <FirebaseClientProvider>
          {children}
        </FirebaseClientProvider>
        {/* Componente para exibir notificações (toasts) */}
        <Toaster />
      </body>
    </html>
  );
}
