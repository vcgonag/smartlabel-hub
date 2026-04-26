import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Producto } from '../models/producto.model';
import { tap, Observable } from 'rxjs';

interface ApiResponse {
  productos: Producto[];
}

@Injectable({
  providedIn: 'root',
})
export class ProductoAPIService {
  private apiUrl = 'http://localhost:8080/productos/listarProductos';

  constructor(private http: HttpClient) {}

  getProductos() {
    return this.http.get<ApiResponse>(this.apiUrl).pipe(
      tap((response) => {
        console.log('Respuesta del servidor:', response);
      }),
    );
  }

  actualizarProductoPOST(producto: Producto): Observable<string> {
    const url = 'http://localhost:8080/productos/update';
    // HttpClient.post detecta automáticamente que es un objeto y lo convierte a JSON
    return this.http.post<string>(url, producto, {
      responseType: 'text' as 'json',
    });
  }

  crearProductoPOST(producto: Producto): Observable<string> {
    const url = 'http://localhost:8080/productos/create';
    return this.http.post<string>(url, producto, {
      responseType: 'text' as 'json',
    });
  }

  borrarProductoPOST(productoId: string | number) {
    const url = 'http://localhost:8080/productos/delete';
    return this.http.post<string>(url, productoId, {
      responseType: 'text' as 'json',
    });
  }
}
