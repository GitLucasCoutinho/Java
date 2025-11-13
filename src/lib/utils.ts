/**
 * @file Utilitário para combinar e mesclar classes do Tailwind CSS.
 * Usa `clsx` para combinar classes condicionalmente e `tailwind-merge` para
 * resolver conflitos de classes do Tailwind de forma inteligente.
 */
import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"

/**
 * Combina múltiplos nomes de classe em uma única string, resolvendo conflitos do Tailwind.
 * @param {...ClassValue[]} inputs - Nomes de classe a serem combinados. Podem ser strings, objetos ou arrays.
 * @returns {string} Uma string de nomes de classe mesclada e otimizada.
 */
export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}
