-- ============================================
-- SCRIPT DE DATOS MOCK PARA SISTEMA EDUCATIVO
-- Schema: ejercicio_jpa
-- ============================================

-- ============================================
-- 1. USUARIOS (Docentes y Estudiantes)
-- ============================================

-- Docentes
INSERT INTO ejercicio_jpa.users (id, name, email, role) VALUES
                                                            (1, 'María García', 'maria.garcia@colegio.edu', 'TEACHER'),
                                                            (2, 'Carlos Rodríguez', 'carlos.rodriguez@colegio.edu', 'TEACHER'),
                                                            (3, 'Ana Martínez', 'ana.martinez@colegio.edu', 'TEACHER');

-- Estudiantes
INSERT INTO ejercicio_jpa.users (id, name, email, role) VALUES
                                                            (4, 'Juan Pérez', 'juan.perez@estudiante.edu', 'STUDENT'),
                                                            (5, 'Laura Gómez', 'laura.gomez@estudiante.edu', 'STUDENT'),
                                                            (6, 'Pedro Sánchez', 'pedro.sanchez@estudiante.edu', 'STUDENT'),
                                                            (7, 'Sofía López', 'sofia.lopez@estudiante.edu', 'STUDENT'),
                                                            (8, 'Diego Torres', 'diego.torres@estudiante.edu', 'STUDENT'),
                                                            (9, 'Valentina Ruiz', 'valentina.ruiz@estudiante.edu', 'STUDENT'),
                                                            (10, 'Andrés Morales', 'andres.morales@estudiante.edu', 'STUDENT');

SELECT setval('ejercicio_jpa.users_id_seq', 10, true);

-- ============================================
-- 2. CURSOS
-- ============================================

INSERT INTO ejercicio_jpa.courses (id, name, description, teacher_id) VALUES
                                                                          (1, 'Matemáticas Avanzadas', 'Curso de cálculo diferencial e integral para estudiantes de grado 11', 1),
                                                                          (2, 'Física Cuántica', 'Introducción a los principios fundamentales de la física cuántica', 1),
                                                                          (3, 'Programación en Java', 'Fundamentos de programación orientada a objetos con Java', 2),
                                                                          (4, 'Base de Datos', 'Diseño y gestión de bases de datos relacionales con PostgreSQL', 2),
                                                                          (5, 'Literatura Colombiana', 'Análisis de obras literarias colombianas del siglo XX', 3),
                                                                          (6, 'Historia Universal', 'Recorrido por los eventos históricos más importantes de la humanidad', 3);

SELECT setval('ejercicio_jpa.courses_id_seq', 6, true);

-- ============================================
-- 3. MATRICULACIONES (ENROLLMENTS)
-- ============================================

-- Curso: Matemáticas Avanzadas (ID: 1)
INSERT INTO ejercicio_jpa.enrollments (student_id, course_id, grade) VALUES
                                                                         (4, 1, 4.5),  -- Juan Pérez
                                                                         (5, 1, 4.8),  -- Laura Gómez
                                                                         (6, 1, 3.9),  -- Pedro Sánchez
                                                                         (7, 1, 4.2),  -- Sofía López
                                                                         (8, 1, 3.5);  -- Diego Torres

-- Curso: Física Cuántica (ID: 2)
INSERT INTO ejercicio_jpa.enrollments (student_id, course_id, grade) VALUES
                                                                         (4, 2, 4.0),  -- Juan Pérez
                                                                         (5, 2, 4.7),  -- Laura Gómez
                                                                         (9, 2, 3.8);  -- Valentina Ruiz

-- Curso: Programación en Java (ID: 3)
INSERT INTO ejercicio_jpa.enrollments (student_id, course_id, grade) VALUES
                                                                         (4, 3, 5.0),  -- Juan Pérez
                                                                         (6, 3, 4.3),  -- Pedro Sánchez
                                                                         (8, 3, 4.6),  -- Diego Torres
                                                                         (9, 3, 4.9),  -- Valentina Ruiz
                                                                         (10, 3, 4.1); -- Andrés Morales

-- Curso: Base de Datos (ID: 4)
INSERT INTO ejercicio_jpa.enrollments (student_id, course_id, grade) VALUES
                                                                         (5, 4, 4.4),  -- Laura Gómez
                                                                         (7, 4, 3.7),  -- Sofía López
                                                                         (10, 4, 4.0); -- Andrés Morales

-- Curso: Literatura Colombiana (ID: 5)
INSERT INTO ejercicio_jpa.enrollments (student_id, course_id, grade) VALUES
                                                                         (4, 5, 3.8),  -- Juan Pérez
                                                                         (6, 5, 4.1),  -- Pedro Sánchez
                                                                         (7, 5, 4.5);  -- Sofía López

-- Curso: Historia Universal (ID: 6)
INSERT INTO ejercicio_jpa.enrollments (student_id, course_id, grade) VALUES
                                                                         (8, 6, 3.9),  -- Diego Torres
                                                                         (9, 6, 4.2),  -- Valentina Ruiz
                                                                         (10, 6, 3.6); -- Andrés Morales

-- ============================================
-- 4. TAREAS/ANUNCIOS (TASKS)
-- ============================================

-- Tareas de Matemáticas Avanzadas (Curso ID: 1)
INSERT INTO ejercicio_jpa.tasks (id, course_id, title, description, published_at) VALUES
                                                                                      (1, 1, 'Taller de Derivadas', 'Resolver los ejercicios 1-20 del capítulo 3. Fecha de entrega: próxima semana.', '2024-01-15 10:00:00'),
                                                                                      (2, 1, 'Examen Parcial', 'El examen parcial será el viernes 26 de enero. Temas: límites, derivadas y aplicaciones.', '2024-01-20 14:30:00'),
                                                                                      (3, 1, 'Proyecto Final', 'Aplicación de integrales en problemas de la vida real. Trabajo en grupos de 3 personas.', '2024-01-25 09:00:00');

-- Tareas de Física Cuántica (Curso ID: 2)
INSERT INTO ejercicio_jpa.tasks (id, course_id, title, description, published_at) VALUES
                                                                                      (4, 2, 'Lectura: Principio de Incertidumbre', 'Leer el capítulo 5 del libro de texto y preparar un resumen de 2 páginas.', '2024-01-16 11:00:00'),
                                                                                      (5, 2, 'Laboratorio Virtual', 'Completar la simulación del experimento de doble rendija en el laboratorio virtual.', '2024-01-22 15:00:00');

-- Tareas de Programación en Java (Curso ID: 3)
INSERT INTO ejercicio_jpa.tasks (id, course_id, title, description, published_at) VALUES
                                                                                      (6, 3, 'Ejercicios de POO', 'Implementar las clases del diagrama UML proporcionado. Incluir herencia y polimorfismo.', '2024-01-17 08:30:00'),
                                                                                      (7, 3, 'Proyecto: Sistema de Biblioteca', 'Desarrollar un sistema de gestión de biblioteca con Java. Entrega en 2 semanas.', '2024-01-18 10:00:00'),
                                                                                      (8, 3, 'Quiz de Colecciones', 'Quiz sobre ArrayList, HashMap y LinkedList. Será el próximo martes.', '2024-01-23 13:00:00');

-- Tareas de Base de Datos (Curso ID: 4)
INSERT INTO ejercicio_jpa.tasks (id, course_id, title, description, published_at) VALUES
                                                                                      (9, 4, 'Diseño de Modelo ER', 'Crear el modelo entidad-relación para un sistema de ventas online.', '2024-01-19 09:00:00'),
                                                                                      (10, 4, 'Consultas SQL Avanzadas', 'Resolver los 15 ejercicios de JOINs y subconsultas del material de clase.', '2024-01-24 11:30:00');

-- Tareas de Literatura Colombiana (Curso ID: 5)
INSERT INTO ejercicio_jpa.tasks (id, course_id, title, description, published_at) VALUES
                                                                                      (11, 5, 'Análisis: Cien Años de Soledad', 'Ensayo de 5 páginas sobre el realismo mágico en la obra de García Márquez.', '2024-01-21 10:00:00'),
                                                                                      (12, 5, 'Exposición Oral', 'Preparar presentación de 10 minutos sobre un autor colombiano del siglo XX.', '2024-01-26 14:00:00');

-- Tareas de Historia Universal (Curso ID: 6)
INSERT INTO ejercicio_jpa.tasks (id, course_id, title, description, published_at) VALUES
                                                                                      (13, 6, 'Línea de Tiempo: Revolución Industrial', 'Crear una línea de tiempo detallada con los eventos más importantes.', '2024-01-20 12:00:00'),
                                                                                      (14, 6, 'Debate: Causas de la Segunda Guerra Mundial', 'Preparar argumentos para el debate en clase. Grupos asignados.', '2024-01-25 15:30:00');
SELECT setval('ejercicio_jpa.tasks_id_seq', 14, true);

-- Verificación
SELECT 'Users' as tabla, COUNT(*) as total FROM ejercicio_jpa.users
UNION ALL SELECT 'Courses', COUNT(*) FROM ejercicio_jpa.courses
UNION ALL SELECT 'Enrollments', COUNT(*) FROM ejercicio_jpa.enrollments
UNION ALL SELECT 'Tasks', COUNT(*) FROM ejercicio_jpa.tasks;
