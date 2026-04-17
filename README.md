# 🏷️ SmartLabel Hub - Sistema de Gestión de Balizajes Digitales

**SmartLabel Hub** es una solución integral diseñada para la modernización del retail, permitiendo la gestión centralizada y en tiempo real de etiquetas electrónicas (balizajes digitales). Este proyecto forma parte del desarrollo para el ciclo de **DAM (Desarrollo de Aplicaciones Multiplataforma)** con enfoque en escalabilidad e integración de sistemas.

---

## Arquitectura del Proyecto

El ecosistema está dividido en tres pilares fundamentales que permiten una gestión 360º de los precios:

* **💻 Back-end (Spring Boot):** El motor del sistema. Una API REST robusta que gestiona la base de datos MongoDB, permitiendo una gran flexibilidad en el manejo de catálogos de productos y metadatos de etiquetas.
* **📱 Mobile App (React Native):** Aplicación destinada a los operarios en tienda para el escaneo de productos y consulta rápida de stock a pie de estantería.
* **Evolución Tecnológica:** Se ha sustituido el uso de *WindowBuilder (Java Swing)* por **Angular 18+**. Esto permite una interfaz más ligera, escalable y acorde a los estándares actuales del sector tecnológico.

---

## 🛠️ Tecnologías Utilizadas

| Capa | Tecnología |
| :--- | :--- |
| **Back-end** | Java 21, Spring Boot 3.x, Spring Data JPA, MySQL |
| **Front-end Desktop** | Angular 18+, TypeScript, SCSS |
| **App Móvil** | React Native, Expo SDK |
| **Herramientas** | Eclipse IDE, VS Code, Git/GitHub, Bruno |

---

## 📦 Gestión de Recursos y Assets

El sistema gestiona productos complejos que incluyen información logística y recursos visuales generados dinámicamente:
- **Barcodes:** Generación automática de códigos de barras en formato `.png`.
- **Imágenes de Producto:** Vinculación de imágenes reales mediante rutas dinámicas en el JSON.
- **Formato de datos:** Estructura NoSQL optimizada para lectura rápida.

## 📋 Estado del Proyecto (Sprint 1)

Actualmente, el proyecto se encuentra en su primera fase de entrega, cumpliendo los siguientes hitos:
- [x] Configuración del entorno de base de datos relacional.
- [x] Desarrollo de los Endpoints principales de la API (CRUD de productos).
- [x] Prototipo funcional de la interfaz móvil para visualización de balizas.
- [x] Estructura base del panel administrativo en Angular.

---

## ⚙️ Instalación y Ejecución

### 1. Back-end
1. Importar el proyecto en Eclipse como *Existing Maven Project*.
2. Configurar el archivo `application.properties` con tus credenciales de MySQL.
3. Ejecutar como `Spring Boot App`. La API estará disponible en `http://localhost:8080`.

### 2. Front-end (Desktop)
1. Navegar a la carpeta `frontend-desktop`.
2. Ejecutar `npm install` para instalar dependencias.
3. Lanzar la aplicación con `ng serve`.

---

## 🎯 Próximos Pasos (Sprint 2)

### 1. Dashboard Administrativo (Angular)
- Creación de la interfaz de escritorio avanzada sustituyendo por completo los componentes antiguos de Java Swing.

### 2. Simulador de Balizas Vusion 4.2
- Desarrollo de un panel de visualización en Angular que emule el aspecto de las etiquetas digitales **BWRY** (Black, White, Red, Yellow).
- Renderizado dinámico de la información del producto (precio, oferta, descripción) sobre la baliza simulada.

### 3. Sincronización Móvil Avanzada
- Evolución de la App en React Native para permitir la **vinculación**: Escaneo de un producto real y asignación inmediata a una de las balizas simuladas.

---
**Desarrollado por:** Víctor G. - Estudiante de DAM & Especialización en IA.