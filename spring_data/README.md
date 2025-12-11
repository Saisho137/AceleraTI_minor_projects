# Spring Data - Sistema de Gestión Educativa

Sistema de gestión educativa desarrollado con Spring Boot 3.x, implementando arquitectura hexagonal, Domain-Driven Design y mejores prácticas de desarrollo.

## 📋 Tabla de Contenidos

- [Arquitectura](#-arquitectura)
- [Modelo de Datos](#-modelo-de-datos)
- [Tecnologías](#-tecnologías)
- [Requisitos Previos](#-requisitos-previos)
- [Configuración](#️-configuración)
- [Ejecución](#-ejecución)
- [API Endpoints](#-api-endpoints)
- [Estructura del Proyecto](#️-estructura-del-proyecto)
- [Mejores Prácticas](#-mejores-prácticas-implementadas)

## Arquitectura

El proyecto implementa **Arquitectura Hexagonal (Ports & Adapters)** con los siguientes principios:

- **Clean Architecture**: Separación clara de responsabilidades
- **SOLID Principles**: Código mantenible y escalable
- **Dependency Inversion**: El dominio no depende de la infraestructura

## Modelo de Datos

### Diagrama Entidad-Relación

```
┌─────────────────────┐
│       USERS         │
├─────────────────────┤
│ PK  id              │
│     name            │
│ UK  email           │
│     role (ENUM)     │
│   - TEACHER         │
│   - STUDENT         │
└──────────┬──────────┘
           │
           │ 1:N (teacher)
           │
           ▼
┌─────────────────────┐         ┌─────────────────────┐
│      COURSES        │         │   ENROLLMENTS       │
├─────────────────────┤         ├─────────────────────┤
│ PK  id              │ 1:N     │ PK  student_id      │
│     name            ├────────▶│ PK  course_id       │
│     description     │         │ FK  student_id      │
│ FK  teacher_id      │◀────────│ FK  course_id       │
└──────────┬──────────┘         │     grade           │
           │                    └──────────┬──────────┘
           │                               │
           │ 1:N                           │ N:1
           │                               │
           ▼                               ▼
┌─────────────────────┐         ┌─────────────────────┐
│       TASKS         │         │       USERS         │
├─────────────────────┤         │    (STUDENT)        │
│ PK  id              │         └─────────────────────┘
│     title           │
│     description     │
│     published_at    │
│ FK  course_id       │
└─────────────────────┘
```

### Descripción de Entidades

#### **Users** (Usuarios)
- Almacena tanto estudiantes como docentes
- `role`: Discriminador de tipo (TEACHER/STUDENT)
- `email`: Único en el sistema

#### **Courses** (Cursos)
- Cada curso pertenece a un docente
- Relación 1:N con Tasks y Enrollments
- Cascade ALL con orphanRemoval para dependencias

#### **Enrollments** (Matriculaciones)
- Tabla de relación N:M entre Students y Courses
- Clave compuesta: (student_id, course_id)
- Almacena la nota del estudiante (0.0 - 5.0)

#### **Tasks** (Tareas/Anuncios)
- Tareas o anuncios publicados en un curso
- Ordenadas por fecha de publicación

### Índices de Base de Datos

```sql
-- Users
CREATE UNIQUE INDEX idx_user_email ON users(email);

-- Courses
CREATE INDEX idx_course_teacher ON courses(teacher_id);

-- Enrollments
CREATE INDEX idx_enrollment_student ON enrollments(student_id);
CREATE INDEX idx_enrollment_course ON enrollments(course_id);

-- Tasks
CREATE INDEX idx_task_course ON tasks(course_id);
CREATE INDEX idx_task_published ON tasks(published_at);
```

## 🚀 Tecnologías

### Backend
- **Java 21** - Lenguaje de programación
- **Spring Boot 3.x** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **Hibernate** - ORM
- **Jakarta Validation** - Validaciones

### Base de Datos
- **PostgreSQL+** - Base de datos relacional
- **HikariCP** - Connection pooling

### Herramientas
- **Lombok** - Reducción de boilerplate
- **Gradle** - Gestión de dependencias
- **SonarQube** - Análisis de código

## 📡 API Endpoints

Todos los endpoints están versionados bajo `/api/v1`

### 📝 Enrollments (Matriculaciones)

#### Registrar o actualizar nota de estudiante
```http
POST /api/v1/enrollments/register-grade
Content-Type: application/json

{
  "studentId": 4,
  "courseId": 1,
  "grade": 4.5
}
```

#### Listar estudiantes de un curso con notas
```http
GET /api/v1/enrollments/course/{courseId}/students
```

### 📚 Courses (Cursos)

#### Listar cursos de un docente
```http
GET /api/v1/courses/teacher/{teacherId}
```

### 📋 Tasks (Tareas/Anuncios)

#### Listar tareas de un curso
```http
GET /api/v1/tasks/course/{courseId}
```

### 🎓 Students (Estudiantes)

#### Listar matriculaciones de un estudiante
```http
GET /api/v1/students/{studentId}/enrollments
```

### ⚠️ Manejo de Errores

Todos los endpoints retornan errores en el siguiente formato:

```json
{
  "status": 404,
  "message": "Course not found with id: 999",
  "timestamp": "2024-01-20T10:30:00"
}
```

## 🗂️ Estructura del Proyecto

```
src/main/java/com/arca/spring_data/
├── domain/                           # 🟢 CAPA DE DOMINIO (Core Business)
│   ├── constants/                   # Constantes de mensajes y valores
│   │   └── ErrorMessages.java
│   ├── enums/                       # Enumeraciones del dominio
│   │   └── Role.java                 # TEACHER, STUDENT
│   ├── exception/                   # Excepciones de negocio
│   │   ├── BusinessRuleException.java
│   │   ├── InvalidGradeException.java
│   │   └── ResourceNotFoundException.java
│   ├── model/                       # Modelos de dominio (POJOs)
│   │   ├── User.java
│   │   ├── Course.java
│   │   ├── Enrollment.java
│   │   └── Task.java
│   ├── port/                        # Puertos (interfaces)
│   │   ├── UserRepository.java
│   │   ├── CourseRepository.java
│   │   ├── EnrollmentRepository.java
│   │   └── TaskRepository.java
│   └── usecase/                     # Casos de uso (lógica de negocio)
│       ├── RegisterStudentGradeUseCase.java
│       ├── GetCourseStudentsWithGradesUseCase.java
│       ├── GetTeacherCoursesUseCase.java
│       ├── GetCourseTasksUseCase.java
│       └── impl/                    # Implementaciones
│           ├── RegisterStudentGradeUseCaseImpl.java
│           ├── GetCourseStudentsWithGradesUseCaseImpl.java
│           ├── GetTeacherCoursesUseCaseImpl.java
│           └── GetCourseTasksUseCaseImpl.java
│
├── infrastructure/                  # 🔵 CAPA DE INFRAESTRUCTURA
│   ├── adapter/
│   │   ├── persistence/            # Adaptador de persistencia (JPA)
│   │   │   ├── mapper/             # Mappers: Domain ↔ JPA Entity
│   │   │   │   ├── UserMapper.java
│   │   │   │   ├── CourseMapper.java
│   │   │   │   ├── EnrollmentMapper.java
│   │   │   │   └── TaskMapper.java
│   │   │   ├── model/              # Entidades JPA
│   │   │   │   ├── UserEntity.java
│   │   │   │   ├── CourseEntity.java
│   │   │   │   ├── EnrollmentEntity.java
│   │   │   │   ├── EnrollmentId.java      # Clave compuesta
│   │   │   │   └── TaskEntity.java
│   │   │   ├── repository/         # Repositorios JPA (Spring Data)
│   │   │   │   ├── UserJpaRepository.java
│   │   │   │   ├── CourseJpaRepository.java
│   │   │   │   ├── EnrollmentJpaRepository.java
│   │   │   │   └── TaskJpaRepository.java
│   │   │   └── service/            # Implementación de puertos
│   │   │       ├── UserRepositoryAdapter.java
│   │   │       ├── CourseRepositoryAdapter.java
│   │   │       ├── EnrollmentRepositoryAdapter.java
│   │   │       └── TaskRepositoryAdapter.java
│   │   │
│   │   └── web/                    # Adaptador web (REST API)
│   │       ├── dto/                # DTOs de request/response
│   │       │   ├── RegisterGradeRequest.java
│   │       │   ├── EnrollmentResponse.java
│   │       │   ├── CourseResponse.java
│   │       │   ├── TaskResponse.java
│   │       │   ├── StudentGradeResponse.java
│   │       │   └── ErrorResponse.java
│   │       ├── exception/          # Manejadores de excepciones
│   │       │   └── GlobalExceptionHandler.java
│   │       ├── mapper/             # Mappers: Domain ↔ DTO
│   │       │   ├── EnrollmentDtoMapper.java
│   │       │   ├── CourseDtoMapper.java
│   │       │   └── TaskDtoMapper.java
│   │       ├── EnrollmentController.java
│   │       ├── CourseController.java
│   │       ├── TaskController.java
│   │       └── StudentController.java
│   │
│   └── config/                     # Configuraciones de Spring
│       ├── UseCaseConfig.java          # Beans de casos de uso
│
└── SpringDataApplication.java      # Clase principal

src/main/resources/
├── application.yml                # Configuración principal
└── mocks.sql                       # Datos de prueba
```

### Flujo de Datos

```
Cliente HTTP
    │
    ▼
[🌐 Controller] → Recibe Request, valida DTO
    │
    ▼
[📦 DTO Mapper] → Convierte DTO a Domain Model
    │
    ▼
[🎯 Use Case] → Ejecuta lógica de negocio
    │
    ▼
[🔌 Port/Repository] → Interface del dominio
    │
    ▼
[🔧 Adapter] → Implementa el puerto
    │
    ▼
[🗃️ JPA Mapper] → Convierte Domain a Entity
    │
    ▼
[💾 JPA Repository] → Persiste en BD
    │
    ▼
PostgreSQL
```

## 🔒 Mejores Prácticas Implementadas

### Arquitectura y Diseño
- ✅ **Arquitectura Hexagonal** - Separación clara entre dominio e infraestructura
- ✅ **Domain-Driven Design** - Lógica de negocio en el dominio
- ✅ **Dependency Inversion** - El dominio no depende de frameworks
- ✅ **Use Cases** - Lógica de aplicación encapsulada
- ✅ **Ports & Adapters** - Interfaces para abstraer dependencias externas

### API y Web
- ✅ **Versionamiento de API** - `/api/v1` para control de versiones
- ✅ **DTOs** - Separación entre modelos de dominio y API
- ✅ **Validaciones Jakarta** - Validación de requests con anotaciones
- ✅ **CORS configurado** - Permite peticiones desde frontend
- ✅ **Manejo global de excepciones** - Respuestas de error consistentes
- ✅ **HTTP Status correctos** - 200, 201, 400, 404, 500

### Base de Datos y Persistencia
- ✅ **JPA con Hibernate** - ORM para mapeo objeto-relacional
- ✅ **Lazy Loading** - Carga perezosa en relaciones (@FetchType.LAZY)
- ✅ **Índices optimizados** - Índices en columnas de búsqueda frecuente
- ✅ **Clave compuesta** - EnrollmentId con @EmbeddedId
- ✅ **Cascade y Orphan Removal** - Gestión automática de dependencias
- ✅ **Connection Pooling** - HikariCP para gestión de conexiones
- ✅ **Transacciones** - @Transactional con readOnly optimizado

### Código Limpio
- ✅ **Lombok** - Reducción de boilerplate (@Data, @AllArgsConstructor)
- ✅ **Streams API** - `.toList()` en lugar de `Collectors.toList()`
- ✅ **Constantes centralizadas** - ErrorMessages para mensajes
- ✅ **Sin comentarios innecesarios** - Código autodocumentado
- ✅ **Naming conventions** - Nombres descriptivos y consistentes
- ✅ **@Serial annotation** - Para serialVersionUID (Java 14+)

### Configuración
- ✅ **Perfiles de Spring** - dev, prod con configuraciones específicas
- ✅ **Variables de entorno** - Configuración externalizada
- ✅ **application.yaml** - Configuración centralizada
- ✅ **Beans de configuración** - UseCaseConfig, WebConfig

### Calidad de Código
- ✅ **SonarQube compliant** - Sin code smells críticos
- ✅ **Equals/HashCode correctos** - @EqualsAndHashCode en entidades JPA
- ✅ **ToString sin ciclos** - @ToString(exclude) para relaciones
- ✅ **Serializable con @Serial** - Para excepciones y claves compuestas

## 📚 Recursos Adicionales

### Documentación
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/)

### Herramientas Recomendadas
- **IntelliJ IDEA** - IDE recomendado
- **Postman** - Para probar endpoints
- **DBeaver** - Cliente de PostgreSQL
- **SonarLint** - Análisis de código en tiempo real

## 👥 Contribuciones

Este proyecto sigue las mejores prácticas de:
- Clean Code (Robert C. Martin)
- Domain-Driven Design (Eric Evans)
- Hexagonal Architecture (Alistair Cockburn)

## 📝 Licencia

Este proyecto es parte de **AceleraTI** - Programa de formación en tecnologías de la información.

---

**Desarrollado con** ❤️ **usando Spring Boot y Arquitectura Hexagonal**
