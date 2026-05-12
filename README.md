# ClinicaVeterinaria
Proyecto fullstack

Integrantes: Francisco Vera y Angela Robles

#  Clínica Veterinaria - Arquitectura de Microservicios 🐾

##  Descripción del proyecto

Este proyecto consiste en una solución basada en arquitectura de microservicios para la gestión de una clínica veterinaria. El sistema permite administrar dueños, mascotas, veterinarios, consultas, pagos, procedimientos y otros elementos del dominio, aplicando separación de responsabilidades, persistencia con base de datos y comunicación entre servicios.

---

## Arquitectura del sistema

El proyecto está diseñado bajo el patrón:

- **Controller** → Manejo de solicitudes REST
- **Service** → Lógica de negocio
- **Repository** → Acceso a datos
- **DTO** → Transferencia de datos entre capas

Cada microservicio es independiente y responsable de una entidad del dominio.

---

##  Microservicios implementados

El sistema está compuesto por los siguientes microservicios:

- Microservicio de Dueños
- Microservicio de Mascotas
- Microservicio de Veterinarios
- Microservicio de Clínicas
- Microservicio de Consultas
- Microservicio de Pagos
- Microservicio de Procedimientos
- Microservicio de Especies
- Microservicio de Razas
- Microservicio de Ubicación (Región/Comuna)

---

##  Persistencia de datos

- Spring Data JPA + Hibernate
- Entidades con relaciones:
  - @OneToMany
  - @ManyToOne
- Base de datos MySQL
- Configuración en `application.properties`

---

##  Validaciones

- Uso de Bean Validation (JSR-380)
- Validación de DTOs
- Control de datos inválidos en endpoints

---

##  Manejo de errores

- Uso de ResponseEntity
- Códigos HTTP adecuados
- @ControllerAdvice para manejo global de excepciones
- Try/Catch en capa de servicio cuando es necesario

---

##  Comunicación entre microservicios

- Integración mediante WebClient / Feign Client
- Consumo de endpoints entre servicios
- Manejo de errores y timeouts

---

##  API REST

- Métodos HTTP:
  - GET
  - POST
  - PUT
  - DELETE
- Respuestas en formato JSON
- Endpoints organizados y semánticos

---

##  Logs

- Implementación con SLF4J
- Registro de:
  - creación de datos
  - errores
  - operaciones importantes del sistema

---

##  Ejecución del proyecto

1. Clonar repositorio
```bash
git clone https://github.com/Angelaroblesd/ClinicaVeterinaria.git
