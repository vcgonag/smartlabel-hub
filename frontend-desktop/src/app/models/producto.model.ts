export interface Producto {
  id: number; //Codigo de barras
  nombre: string;
  marca: string;
  categoria: string;
  peso: string;
  precioReferencia: number; // Antes era precio
  precioPromo: number; // El nuevo atributo
  descripcion: string;
  //mime: string;
  imagenPNG: string;
  imagenBarCodePNG: string;
  sincronizado: boolean;
  vinculado: boolean;
}
