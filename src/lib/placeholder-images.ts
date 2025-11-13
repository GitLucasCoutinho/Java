/**
 * @file Exporta dados de imagens de placeholder.
 * Lê o arquivo JSON e o disponibiliza para a aplicação.
 */
import data from './placeholder-images.json';

/**
 * Define a estrutura de um objeto de imagem de placeholder.
 */
export type ImagePlaceholder = {
  id: string;
  description: string;
  imageUrl: string;
  imageHint: string;
};

/**
* Array de imagens de placeholder carregado do arquivo JSON.
*/
export const PlaceHolderImages: ImagePlaceholder[] = data.placeholderImages;
