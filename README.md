# 🏷️ SmartLabel Hub - Sistema de Gestión de Balizajes Digitales

**SmartLabel Hub** es una solución integral diseñada para la modernización del retail, permitiendo la gestión centralizada y en tiempo real de etiquetas electrónicas (balizajes digitales). Este proyecto forma parte del desarrollo para el ciclo de **DAM (Desarrollo de Aplicaciones Multiplataforma)** con enfoque en escalabilidad e integración de sistemas.

---

## Arquitectura del Proyecto

El ecosistema está dividido en tres pilares fundamentales que permiten una gestión 360º de los precios:

- **💻 Back-end (Spring Boot):** El motor del sistema. Una API REST robusta que gestiona la base de datos MongoDB, permitiendo una gran flexibilidad en el manejo de catálogos de productos y metadatos de etiquetas.
- **📱 Mobile App (React Native):** Aplicación destinada a los operarios en tienda para el escaneo de productos y consulta rápida de stock a pie de estantería.
- **🖥️ Admin Dashboard (Angular 18+):** Panel de control centralizado diseñado para sustituir las interfaces de escritorio tradicionales por una solución web moderna y responsiva. Este módulo permite la administración integral del catálogo, facilitando la edición avanzada de atributos de producto y la monitorización en tiempo real del estado de sincronización con las balizas digitales.
- **Evolución Tecnológica:** Se ha sustituido el uso de _WindowBuilder (Java Swing)_ por **Angular 18+**. Esto permite una interfaz más ligera, escalable y acorde a los estándares actuales del sector tecnológico.

---

## 🛠️ Tecnologías Utilizadas

| Capa                  | Tecnología                                       |
| :-------------------- | :----------------------------------------------- |
| **Back-end**          | Java 21, Spring Boot 3.x, Spring Data JPA, MySQL |
| **Front-end Desktop** | Angular 18+, TypeScript, SCSS                    |
| **App Móvil**         | React Native, Expo SDK                           |
| **Herramientas**      | Eclipse IDE, VS Code, Git/GitHub, Bruno          |

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

## 📋 Estado del Proyecto (Sprint 2 - Actualizado)

Hemos completado la transición hacia una arquitectura Web moderna y funcional:

- [x] **Migración a Angular 18+:** Implementación completa del Dashboard administrativo utilizando **Signals** para una gestión de estado eficiente.
- [x] **UI/UX Avanzada:** Diseño de interfaz "Dark Mode" optimizada con Bootstrap 5, incluyendo layouts responsivos que adaptan la visualización de datos según el dispositivo.
- [x] **Gestión de Precios Dinámica:** Sistema de edición de precios (Referencia y Promo) con validación visual inmediata y sincronización con el servidor.
- [x] **Visualización de Recursos:** Integración de assets dinámicos (imágenes de producto y códigos de barras) dentro de las tablas de gestión.

---

## ⚙️ Instalación y Ejecución

### 1. Back-end

1. Importar el proyecto en Eclipse como _Existing Maven Project_.
2. Configurar el archivo `application.properties` con tus credenciales de MySQL.
3. Ejecutar como `Spring Boot App`. La API estará disponible en `http://localhost:8080`.

### 2. Front-end (Desktop)

1. Navegar a la carpeta `frontend-desktop`.
2. Ejecutar `npm install` para instalar dependencias.
3. Lanzar la aplicación con `ng serve`.

---

## 🎯 Próximos Pasos (Sprint 3)

### 1. Simulador de Balizas Vusion 4.2

- Desarrollo de un motor de renderizado en Angular que emule visualmente las etiquetas digitales **BWRY** (Black, White, Red, Yellow).
- Proyección dinámica de la descripción del producto, códigos QR y precios directamente sobre la "etiqueta virtual" para previsualizar el estado final.

### 2. Sincronización y Control (Dashboard)

- Implementación de indicadores visuales en el Dashboard para monitorizar en tiempo real si la información de la baliza coincide con la del servidor.
- Sistema de actualización manual desde el panel para forzar el refresco de datos en balizas desincronizadas.

### 3. Desarrollo de App de Vinculación (React Native)

- Creación de una aplicación móvil específica diseñada para la operativa de campo.
- Implementación de la lógica de "emparejamiento": Escaneo del código de barras del producto y del identificador de la baliza digital para establecer la vinculación directa en el sistema.

---

**Desarrollado por:** Víctor Gonzalez Aguayo - Estudiante de DAM.
