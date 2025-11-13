import type {NextConfig} from 'next';

/**
 * @type {NextConfig}
 * Configuração para a aplicação Next.js.
 */
const nextConfig: NextConfig = {
  /* Opções de configuração aqui */
  
  // Ignora erros de TypeScript durante o processo de build. Útil para produção, mas use com cautela.
  typescript: {
    ignoreBuildErrors: true,
  },
  
  // Ignora erros do ESLint durante o processo de build.
  eslint: {
    ignoreDuringBuilds: true,
  },
  
  // Configuração para o componente `next/image`.
  // Define os domínios de onde as imagens podem ser carregadas de forma otimizada.
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'placehold.co',
        port: '',
        pathname: '/**',
      },
      {
        protocol: 'https',
        hostname: 'images.unsplash.com',
        port: '',
        pathname: '/**',
      },
      {
        protocol: 'https',
        hostname: 'picsum.photos',
        port: '',
        pathname: '/**',
      },
    ],
  },
};

export default nextConfig;
