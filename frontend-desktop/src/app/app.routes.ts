import { Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { BalizasComponent } from './pages/balizas/balizas.component';
import { AddProductoComponent } from './pages/add-producto/add-producto.component';
import { ConfiguracionComponent } from './pages/configuracion/configuracion.component';

export const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'balizas', component: BalizasComponent },
  { path: 'add-producto', component: AddProductoComponent },
  { path: 'configuracion', component: ConfiguracionComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' }, // Ruta por defecto
  { path: '**', redirectTo: '/dashboard' } // Por si escriben cualquier cosa
];