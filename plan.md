# Plan de Desarrollo — Fusionar PDFs (Android Kotlin)

## 1. Resumen del proyecto

**Fusionar PDFs** es una aplicación Android desarrollada en Kotlin que permite:

* Seleccionar múltiples archivos PDF.
* Reordenarlos mediante drag & drop.
* Fusionarlos en un único documento.
* Guardar el PDF resultante en una ubicación elegida por el usuario.
* Compartir o abrir el PDF generado.
* Mantener un historial local de fusiones realizadas.

### Objetivos del MVP

* Selección múltiple de PDFs.
* Reordenamiento de archivos.
* Fusión de documentos.
* Exportación del PDF resultante.
* Compartir el resultado.

---

# 2. Arquitectura del proyecto

## Arquitectura general

Se utilizará una variante ligera de Clean Architecture basada en MVVM.

```text
UI (Compose Screens)
        ↓
ViewModels
        ↓
Use Cases
        ↓
Repositories
        ↓
PDF APIs / Android APIs
```

## Principios a respetar

### Separación de responsabilidades

* La UI no contiene lógica de negocio.
* Los ViewModels gestionan únicamente estado y eventos.
* Los UseCases contienen la lógica de negocio.
* Los Repositories encapsulan el acceso a archivos y librerías PDF.

### Estado

Utilizar:

```kotlin
StateFlow
```

para exponer estados a la UI.

### Navegación

Utilizar:

```kotlin
Navigation Compose
```

con navegación tipada mediante rutas centralizadas.

---

# 3. Tecnologías y librerías

## UI

```gradle
androidx.compose.material3
androidx.navigation:navigation-compose
androidx.lifecycle:lifecycle-viewmodel-compose
```

## Estado y asincronía

```gradle
kotlinx-coroutines-android
androidx.lifecycle:lifecycle-runtime-compose
```

## Persistencia

```gradle
androidx.datastore:datastore-preferences
```

## Manipulación de PDFs

```gradle
com.tom-roush:pdfbox-android
```

## Reordenamiento Drag & Drop

```gradle
sh.calvin.reorderable:reorderable
```

## Inyección de dependencias (recomendado)

```gradle
com.google.dagger:hilt-android
androidx.hilt:hilt-navigation-compose
```

---

# 4. Estructura de paquetes

```text
com.app.fusionarpdfs

├── core
│   ├── navigation
│   ├── theme
│   ├── extensions
│   ├── utils
│   └── constants
│
├── data
│   ├── repository
│   ├── datasource
│   └── preferences
│
├── domain
│   ├── model
│   ├── repository
│   └── usecase
│
├── presentation
│   ├── home
│   ├── reorder
│   ├── preview
│   ├── progress
│   ├── result
│   ├── history
│   └── settings
│
└── di
```

---

# 5. Diseño visual

## Estilo

La aplicación debe transmitir:

* Rapidez
* Simplicidad
* Productividad
* Confiabilidad

Características:

* Diseño minimalista.
* Material 3.
* Espacios amplios.
* Animaciones suaves.
* Navegación simple.

---

## Paleta de colores

| Uso              | Color   |
| ---------------- | ------- |
| Primario         | #2563EB |
| Primario oscuro  | #1E40AF |
| Secundario       | #10B981 |
| Fondo            | #F8FAFC |
| Superficie       | #FFFFFF |
| Texto principal  | #0F172A |
| Texto secundario | #64748B |
| Error            | #EF4444 |
| Bordes           | #E2E8F0 |

---

## Iconografía

Material Symbols.

| Acción              | Icono          |
| ------------------- | -------------- |
| Agregar PDF         | picture_as_pdf |
| Seleccionar archivo | upload_file    |
| Reordenar           | drag_indicator |
| Eliminar            | delete         |
| Fusionar            | call_merge     |
| Guardar             | save           |
| Compartir           | share          |
| Historial           | history        |
| Configuración       | settings       |
| Éxito               | check_circle   |
| Error               | error_outline  |

---

# 6. Modelo de datos

## PdfFileItem

```kotlin
data class PdfFileItem(
    val id: String,
    val uri: Uri,
    val name: String,
    val sizeBytes: Long,
    val order: Int
)
```

---

## MergeHistoryItem

```kotlin
data class MergeHistoryItem(
    val id: String,
    val fileName: String,
    val fileUri: String,
    val fileSizeBytes: Long,
    val createdAt: Long
)
```

---

# 7. Pantallas

## 7.1 Home / Fusionar PDFs

### Objetivo

Punto de entrada principal.

### Vistas

```text
TopAppBar
Lista de PDFs seleccionados
Estado vacío
FAB / Botón seleccionar PDFs
Botón continuar
```

### Funcionalidades

* Selección múltiple de PDFs.
* Mostrar nombre y tamaño.
* Eliminar PDF individual.
* Limpiar selección completa.
* Validar mínimo 2 PDFs.

### Navegación

```text
Home → Reordenar
Home → Historial
Home → Configuración
```

---

## 7.2 Reordenar PDFs

### Objetivo

Permitir definir el orden final de fusión.

### Vistas

```text
TopAppBar
Lista reorderable
Botón Vista Previa
```

### Funcionalidades

* Drag & Drop.
* Numeración visible.
* Eliminar elementos.
* Reordenar mediante gestos.

### Navegación

```text
Reordenar → Home
Reordenar → Vista previa
```

---

## 7.3 Vista previa / Confirmación

### Objetivo

Confirmar configuración final.

### Vistas

```text
Resumen de PDFs
Campo nombre de archivo
Ubicación de guardado
Botón Fusionar
```

### Funcionalidades

* Editar nombre final.
* Seleccionar ubicación de guardado.
* Validaciones previas.

### Navegación

```text
Vista previa → Reordenar
Vista previa → Progreso
```

---

## 7.4 Progreso de fusión

### Objetivo

Mostrar el proceso de unión.

### Vistas

```text
ProgressIndicator
Estado actual
Información de progreso
```

### Funcionalidades

* Ejecución en background.
* Manejo de errores.
* Bloqueo de navegación accidental.

### Navegación

```text
Progreso → Resultado
```

---

## 7.5 Resultado

### Objetivo

Mostrar éxito de la operación.

### Vistas

```text
Icono éxito
Información del PDF generado
Botones de acción
```

### Funcionalidades

* Abrir PDF.
* Compartir PDF.
* Crear nueva fusión.

### Navegación

```text
Resultado → Home
Resultado → Historial
```

---

## 7.6 Historial

### Objetivo

Consultar PDFs generados anteriormente.

### Vistas

```text
Listado de fusiones
```

### Funcionalidades

* Abrir PDF.
* Compartir PDF.
* Eliminar registro.
* Limpiar historial.

### Navegación

```text
Historial → Resultado
Historial → Home
```

---

## 7.7 Configuración

### Objetivo

Gestionar preferencias de la aplicación.

### Vistas

```text
Tema
Nombre por defecto
Confirmaciones
Opciones legales
```

### Funcionalidades

#### Apariencia

* Claro
* Oscuro
* Sistema

#### Preferencias

* Nombre por defecto del PDF.
* Confirmación antes de fusionar.

#### Datos

* Limpiar historial.

#### Información

* Política de privacidad.
* Licencias open source.
* Versión de la app.

### Navegación

```text
Configuración → Home
```

---

# 8. Flujo principal de usuario

```text
Home
    ↓
Seleccionar PDFs
    ↓
Reordenar PDFs
    ↓
Vista previa
    ↓
Fusionar
    ↓
Resultado
    ↓
Abrir / Compartir
```

---

# 9. Manejo de errores

## Casos contemplados

### Archivos

* PDF corrupto.
* PDF protegido.
* PDF vacío.
* PDF inaccesible.

### Almacenamiento

* Sin permisos.
* Espacio insuficiente.
* Error de escritura.

### Aplicación

* Excepciones inesperadas.
* Cancelación del proceso.

---

# 10. Estrategia de testing

## Unit Tests

### UseCases

* MergePdfUseCase
* SaveHistoryUseCase
* ValidatePdfSelectionUseCase

### ViewModels

* HomeViewModel
* ReorderViewModel
* PreviewViewModel

---

## Pruebas manuales

### PDFs pequeños

```text
2-5 archivos
```

### PDFs medianos

```text
20-50 archivos
```

### PDFs grandes

```text
100+ MB
```

### Casos extremos

```text
PDF protegido
PDF corrupto
Archivo inexistente
```

---

# 11. Roadmap de Pull Requests

## PR-01 — Bootstrap del proyecto

### Objetivo

Crear la base técnica.

### Incluye

* Proyecto Compose.
* Material 3.
* Tema.
* Navegación.
* Hilt.
* Estructura de carpetas.

---

## PR-02 — Dominio y modelos

### Incluye

* Models.
* Repositories.
* UseCases vacíos.
* Contratos de navegación.

---

## PR-03 — Selección de PDFs

### Incluye

* SAF.
* OpenMultipleDocuments.
* Validaciones.
* Gestión de URIs.

---

## PR-04 — Pantalla Home

### Incluye

* Estado vacío.
* Lista de PDFs.
* Acciones de selección.
* Navegación.

---

## PR-05 — Reordenamiento Drag & Drop

### Incluye

* Lista reorderable.
* Actualización de orden.
* Eliminación de PDFs.

---

## PR-06 — Vista previa

### Incluye

* Nombre de salida.
* Selección de ubicación.
* Validaciones.

---

## PR-07 — Motor de fusión PDF

### Incluye

* PdfBox-Android.
* Repository real.
* MergePdfUseCase.
* Gestión de errores.

---

## PR-08 — Pantalla de progreso

### Incluye

* Estados Loading.
* Progreso.
* Cancelación.

---

## PR-09 — Pantalla de resultado

### Incluye

* Abrir PDF.
* Compartir PDF.
* Reiniciar flujo.

---

## PR-10 — Historial

### Incluye

* Persistencia local.
* Pantalla historial.
* Gestión de registros.

---

## PR-11 — Configuración

### Incluye

* DataStore.
* Tema.
* Preferencias.
* Información legal.

---

## PR-12 — Manejo avanzado de errores

### Incluye

* Dialogs.
* Snackbars.
* Casos extremos.

---

## PR-13 — Pulido UX/UI

### Incluye

* Animaciones.
* Accesibilidad.
* Modo oscuro.
* Optimización visual.

---

## PR-14 — Testing

### Incluye

* Unit Tests.
* Cobertura de ViewModels.
* Cobertura de UseCases.

---

## PR-15 — Release Play Store

### Incluye

* Icono final.
* Feature Graphic.
* Screenshots.
* Política de privacidad.
* Optimización de tamaño.
* Firma de release.
* Publicación.
