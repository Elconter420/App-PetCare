# 🐾 PetCare App – Plataforma Integral para el Cuidado de Mascotas

> Aplicación desarrollada en **Kotlin** con conexión a base de datos **PostgreSQL**, diseñada para conectar a dueños de mascotas con veterinarios y profesionales del cuidado animal.

---

## 🧩 Descripción del Proyecto

**PetCare** es una aplicación móvil que permite a los usuarios:
- Registrar sus mascotas y acceder a sus historiales médicos.
- Agendar citas veterinarias, de peluquería, guardería y transporte.
- Suscribirse a planes Free o Premium según sus necesidades.

Por otro lado, los **veterinarios y cuidadores** pueden:
- Administrar sus citas, aceptar o reprogramar servicios.
- Subir historias clínicas y mantener seguimiento de pacientes.
- Gestionar pagos, membresías y visibilidad profesional en la plataforma.

El objetivo de este proyecto es **automatizar y centralizar los servicios veterinarios y de cuidado animal**, ofreciendo una experiencia digital moderna, confiable y fácil de usar.

---

## ⚙️ Tecnologías Utilizadas

### 🔹 Backend
- **Kotlin (Ktor Framework)** – Lógica del servidor y controladores REST.
- **Exposed ORM** – Manejo de la base de datos con mapeo de objetos.
- **PostgreSQL** – Base de datos relacional principal.
- **JWT / Cookies Auth** – Autenticación de usuarios y manejo de roles (usuarios, veterinarios, administradores).

### 🔹 Frontend
- **Kotlin Multiplatform / Android XML (Jetpack Compose opcional)**
- **HTML, CSS, JavaScript** (para versión web)
- **Figma** – Diseño UI/UX previo al desarrollo.

### 🔹 Herramientas y Entorno
- **IntelliJ IDEA / Android Studio**
- **Docker** – Contenerización del backend y la base de datos.
- **Gradle** – Sistema de construcción del proyecto.
- **Git & GitHub** – Control de versiones y colaboración.

---

## 🧠 Arquitectura del Proyecto

El proyecto sigue una arquitectura **limpia (Clean Architecture)**, separando la lógica en capas:

📦 com.petcare
┣ 📂 data # Conexión a BD, modelos y repositorios
┣ 📂 domain # Lógica de negocio y entidades
┣ 📂 presentation # Controladores, endpoints y vistas
┣ 📂 utils # Clases de ayuda, validadores, etc.
┗ 📜 Application.kt # Punto de entrada del servidor

## 🗄️ Base de Datos – PostgreSQL

Tablas principales:
- **usuarios** → datos de inicio de sesión, rol y autenticación.
- **mascotas** → información individual de cada mascota.
- **veterinarios** → datos de perfil profesional.
- **citas** → relación entre usuarios, veterinarios y servicios.
- **pagos** → historial de transacciones y membresías.

ORM: **Exposed SQL DSL** → para manipular datos con tipado seguro y sin SQL plano.

---

## 🔐 Autenticación y Roles

Sistema de autenticación mediante **JWT (JSON Web Token)** o **cookies seguras**, con manejo de roles:
- 👤 **Usuario** – puede registrar mascotas, agendar servicios, consultar historial.
- 🩺 **Veterinario / Profesional** – gestiona pacientes, citas y reportes.
- 🧑‍💼 **Administrador** – controla usuarios, planes y reportes generales.

---
## 💚 Autores
- 👩‍💻 Maryury Villa
- 👩‍💻 Karen Duque
- 👩‍💻 Melissa Espitia
- 🧑‍💻 Alejandro Moreno
- 🧑‍💻 Jhojan Bueno
- 🧑‍💻 Juan Contreras