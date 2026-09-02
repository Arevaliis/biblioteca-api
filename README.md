# 📚💻 Spring Boot Biblioteca API

## ⚙️ Descripción del proyecto

Este proyecto consiste en una API REST desarrollada con **Java y Spring Boot** para la gestión de una biblioteca. Permite administrar usuarios, perfiles, préstamos, libros y categorías, incorporando persistencia de datos, validación, gestión de errores y autenticación mediante JWT. El proyecto sigue una **arquitectura por capas**, separando la gestión de peticiones, la lógica de negocio y el acceso a datos.

---

## 🏗️ Arquitectura del sistema

### Capa Controller

* Gestión de peticiones HTTP
* Definición de endpoints REST
* Recepción y devolución de datos mediante DTOs

### Capa Service

* Implementación de la lógica de negocio
* Validación de reglas de negocio
* Coordinación entre controllers y repositories

### Capa Repository

* Acceso a la base de datos
* Implementación mediante Spring Data JPA
* Operaciones CRUD sobre las entidades

### Capa Security

* Autenticación mediante JWT
* Autorización mediante roles
* Protección de endpoints
* Encriptación de contraseñas mediante BCrypt

---

## 🗄️ Persistencia

La aplicación utiliza **MySQL** como base de datos y **JPA / Hibernate** para la persistencia.

Entidades principales:

* Usuario
* Perfil
* Préstamo
* Libro
* Categoría

Relaciones implementadas:

* `Usuario - Perfil` → OneToOne
* `Usuario - Préstamo` → OneToMany / ManyToOne
* `Libro - Categoría` → ManyToMany

---

## 🔐 Seguridad

La autenticación y autorización se implementan mediante:

* Spring Security
* JWT
* BCrypt
* Roles de usuario

El proceso de autenticación utiliza `UserDetailsService`, `AuthenticationManager` y un filtro personalizado para validar los tokens JWT.

---

## 🚨 Gestión de errores y validación

* Validación de datos mediante Bean Validation
* Gestión centralizada de excepciones
* Respuestas estructuradas de la API
* Separación entre errores de validación, negocio y persistencia

---

## 📖 Documentación

La API está documentada mediante **OpenAPI / Swagger**, permitiendo consultar y probar los endpoints disponibles.

---

## 🐳 Docker

La aplicación está preparada para ejecutarse mediante **Docker y Docker Compose**.

La configuración incluye:

* Dockerfile multi-stage
* Contenedor para Spring Boot
* Contenedor para MySQL
* Red interna entre servicios
* Variables de entorno
* Volumen persistente para MySQL
* Healthcheck de MySQL
* Dependencia de arranque entre servicios

Arquitectura:

```text
┌─────────────────────┐
│   Spring Boot API   │
│       :8080         │
└──────────┬──────────┘
           │
      Docker Network
           │
┌──────────▼──────────┐
│        MySQL        │
│       :3306         │
└──────────┬──────────┘
           │
      mysql-data
        Volume
```

---

## ⚙️ Configuración

Las credenciales y parámetros de conexión se gestionan mediante variables de entorno.

Crear un archivo `.env` a partir de `.env.example` y configurar los valores necesarios.

---

## ▶️ Ejecución

### Docker Compose

```bash
docker compose up --build
```

La API estará disponible en:

```text
http://localhost:8081
```

Swagger:

```text
http://localhost:8081/swagger-ui.html
```

Para detener los servicios:

```bash
docker compose down
```
---

## 💻 Tecnologías utilizadas

* Java 21
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* MySQL
* Spring Security
* JWT
* BCrypt
* Bean Validation
* OpenAPI / Swagger
* JUnit
* Mockito
* Maven
* Docker
* Docker Compose

---

## 🚦 Estado del proyecto

| Versión |      Estado      |
| ------: | :--------------: |
|     1.0 | 🚧 En desarrollo |

---

## 👤 Autoría

* [Jose Iglesias Arévalo](https://arevaliis.github.io/Portafolio)

---

## 📫 Contacto

* ✉️ [joseiglesiasarevalo@gmail.com](mailto:joseiglesiasarevalo@gmail.com)
* 💼 [LinkedIn](https://www.linkedin.com/in/jose-iglesias-ar%C3%A9valo-812860206/)
* 🐙 [GitHub](https://github.com/Arevaliis)

---

## 📄 Licencia

Este proyecto no tiene licencia.
