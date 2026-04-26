import { Component, OnInit, signal } from '@angular/core';
import { ProductoAPIService } from '../../services/producto-api.service';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormControl } from '@angular/forms';

import { Producto } from './../../models/producto.model';

interface ApiResponse {
  productos: Producto[];
}

declare var bootstrap: any;

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
})
export class DashboardComponent implements OnInit {
  //public listaProductos: Producto[] = [];
  public listaProductos = signal<Producto[]>([]);
  public listaProdcutosSearch = signal<Producto[]>([]);
  public searchControl = new FormControl('');
  public productoSeleccionado: Producto = {
    id: 0,
    nombre: '',
    marca: '',
    categoria: '',
    peso: '',
    precioReferencia: 0,
    precioPromo: 0,
    descripcion: '',
    imagenPNG: '',
    imagenBarCodePNG: '',
    sincronizado: false,
    vinculado: false,
  };
  public mensajeExito: string | null = null;

  constructor(private productoAPIService: ProductoAPIService) {
    this.searchControl.valueChanges.subscribe((value) => {
      console.log('Valor de búsqueda:', value);
      const prod = value?.toLowerCase() || '';

      this.listaProdcutosSearch.set(
        this.listaProductos().filter(
          (productos: Producto) =>
            productos.nombre.toLowerCase().includes(prod) || productos.id.toString().includes(prod),
        ),
      );
      console.log('lista productos buscados,', this.listaProdcutosSearch());
    });
  }

  ngOnInit(): void {
    this.productoAPIService.getProductos().subscribe((response: ApiResponse) => {
      console.log('Productos obtenidos en Dashboard:', response.productos);
      this.listaProductos.set(response.productos);
      this.listaProdcutosSearch.set(response.productos);
    });
  }

  seleccionarProducto(producto: Producto) {
    console.log('Producto seleccionado:', producto);
    this.productoSeleccionado = { ...producto }; // Tu lógica actual

    // ABRIR EL MODAL MANUALMENTE
    const modalElement = document.getElementById('modalEditarProducto');
    if (modalElement) {
      const modalInstance = new bootstrap.Modal(modalElement);
      modalInstance.show();
    }
  }

  eliminarProducto(event: Event, prodctoId: string | number) {
    event.stopPropagation(); // Frenamos el clic aquí
    this.productoAPIService.borrarProductoPOST(prodctoId).subscribe({
      next: (resp) => {
        console.log(resp);
        // Filtramos los signals para que desaparezca de la vista sin recargar
        this.listaProductos.update((prods) => prods.filter((p) => p.id !== prodctoId));
        this.listaProdcutosSearch.update((prods) => prods.filter((p) => p.id !== prodctoId));
      },
      error: (err) => console.error('Error al borrar', err),
    });
  }

  actualizarProducto() {
    this.productoAPIService.actualizarProductoPOST(this.productoSeleccionado).subscribe({
      next: (response: string) => {
        // 1. Mostrar el mensaje de éxito
        this.mensajeExito = response;

        // 2. Esperar un poco para que el usuario lea "Producto actualizado"
        // y luego cerrar el modal automáticamente
        setTimeout(() => {
          const modalElement = document.getElementById('modalEditarProducto');
          if (modalElement) {
            const modalInstance = bootstrap.Modal.getInstance(modalElement);
            if (modalInstance) {
              modalInstance.hide(); // <--- Aquí se cierra el modal
            }
          }
          this.mensajeExito = null; // Limpiamos el mensaje para la próxima vez
        }, 2000); // 2 segundos de cortesía
      },
      error: (err) => console.error('Error al actualizar:', err),
    });
  }
}
