/**
 * @file Hook React para detectar se a viewport atual corresponde a um dispositivo móvel.
 * A detecção é baseada em um ponto de quebra (breakpoint) de largura fixa.
 */
import * as React from "react"

// Define a largura máxima em pixels para ser considerada 'móvel'.
const MOBILE_BREAKPOINT = 768

/**
 * Um hook que retorna `true` se a largura da janela for menor que o `MOBILE_BREAKPOINT`.
 * @returns {boolean} `true` se a viewport for de tamanho móvel, `false` caso contrário.
 */
export function useIsMobile() {
  const [isMobile, setIsMobile] = React.useState<boolean | undefined>(undefined)

  React.useEffect(() => {
    // Usa a API `matchMedia` do navegador para observar mudanças na largura da viewport.
    const mql = window.matchMedia(`(max-width: ${MOBILE_BREAKPOINT - 1}px)`)
    
    // Função para atualizar o estado quando a condição de mídia muda.
    const onChange = () => {
      setIsMobile(window.innerWidth < MOBILE_BREAKPOINT)
    }

    // Adiciona o ouvinte.
    mql.addEventListener("change", onChange)
    
    // Define o estado inicial na montagem do componente.
    setIsMobile(window.innerWidth < MOBILE_BREAKPOINT)

    // Limpeza: remove o ouvinte quando o componente é desmontado.
    return () => mql.removeEventListener("change", onChange)
  }, [])

  return !!isMobile
}
