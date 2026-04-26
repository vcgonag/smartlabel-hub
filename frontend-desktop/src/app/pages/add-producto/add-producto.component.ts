import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Producto } from '../../models/producto.model';
import { ProductoAPIService } from '../../services/producto-api.service';

@Component({
  selector: 'app-add-producto',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './add-producto.component.html',
  styleUrl: './add-producto.component.scss',
})
export class AddProductoComponent {
  public nuevoProducto: Producto = {
    id: 0,
    nombre: '',
    marca: '',
    categoria: '',
    peso: '',
    precioReferencia: 0,
    precioPromo: 0,
    descripcion: '',
    //mime: '',
    imagenPNG: '',
    imagenBarCodePNG: '',
    sincronizado: false,
    vinculado: false,
  };

  constructor(private productoAPIService: ProductoAPIService) {}

  crearProducto() {
    // Pasamos a la forma de "Objeto Observador" para que no salga tachado
    this.productoAPIService.crearProductoPOST(this.nuevoProducto).subscribe({
      next: (response: string) => {
        console.log('Respuesta del servidor:', response);
        this.resetearFormulario();
      },
      error: (error) => {
        console.error('Error al crear el producto:', error);
      },
    });
  }

  resetearFormulario() {
    this.nuevoProducto = {
      id: 0,
      nombre: '',
      marca: '',
      categoria: '',
      peso: '',
      precioReferencia: 0,
      precioPromo: 0,
      descripcion: '',
      //mime: '',
      imagenPNG: '',
      imagenBarCodePNG: '',
      sincronizado: false,
      vinculado: false,
    };
  }
}
