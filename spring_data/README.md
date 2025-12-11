# Spring Data - Sistema de Gestión Educativa

Sistema de gestión educativa desarrollado con Spring Boot, implementando arquitectura hexagonal y mejores prácticas.

## 🏗️ Arquitectura

- **Arquitectura Hexagonal (Ports & Adapters)**
- **Domain-Driven Design (DDD)**
- **Clean Architecture**

## 🚀 Tecnologías

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Lombok
- Jakarta Validation

## 📋 Requisitos Previos

- JDK 17 o superior
- PostgreSQL 12+
- Maven 3.6+

## ⚙️ Configuración

1. Clonar el repositorio
2. Copiar `.env.example` a `.env` y configurar variables
3. Crear base de datos PostgreSQL:
```sql
CREATE DATABASE "Acelera_TI";
CREATE SCHEMA ejercicio_jpa;
```

## 🏃 Ejecución

### Desarrollo
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Producción
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## 📡 API Endpoints

### Enrollments (Matriculaciones)
- `POST /api/v1/enrollments/register-grade` - Registrar nota de estudiante
- `GET /api/v1/enrollments/course/{courseId}/students` - Obtener estudiantes de un curso

### Courses (Cursos)
- `GET /api/v1/courses/teacher/{teacherId}` - Obtener cursos de un docente

### Tasks (Tareas)
- `GET /api/v1/tasks/course/{courseId}` - Obtener tareas de un curso

### Students (Estudiantes)
- `GET /api/v1/students/{studentId}/enrollments` - Obtener matriculaciones de un estudiante

## 🗂️ Estructura del Proyecto

```
src/main/java/com/arca/spring_data/
├── domain/                      # Capa de dominio
│   ├── constants/              # Constantes del dominio
│   ├── enums/                  # Enumeraciones
│   ├── exception/              # Excepciones de negocio
│   ├── model/                  # Modelos de dominio
│   ├── port/                   # Puertos (interfaces)
│   └── usecase/                # Casos de uso
├── infrastructure/             # Capa de infraestructura
│   ├── adapter/
│   │   ├── persistence/       # Adaptadores de persistencia
│   │   │   ├── mapper/        # Mappers JPA
│   │   │   ├── model/         # Entidades JPA
│   │   │   ├── repository/    # Repositorios JPA
│   │   │   └── service/       # Implementación de puertos
│   │   └── web/               # Adaptadores web
│   │       ├── dto/           # DTOs
│   │       ├── exception/     # Manejadores de excepciones
│   │       └── mapper/        # Mappers DTO
│   └── config/                # Configuraciones
└── SpringDataApplication.java
```

## 🔒 Mejores Prácticas Implementadas

- ✅ Versionamiento de API (v1)
- ✅ Validaciones en entidades JPA
- ✅ Índices en base de datos
- ✅ Transacciones con @Transactional
- ✅ Lazy loading en relaciones
- ✅ Variables de entorno para configuración
- ✅ Perfiles de Spring (dev, prod)
- ✅ CORS configurado
- ✅ Manejo global de excepciones
- ✅ Constantes para mensajes
- ✅ Connection pooling (HikariCP)
- ✅ Separación de capas (Hexagonal)

## 📝 Licencia

Este proyecto es parte de AceleraTI.
