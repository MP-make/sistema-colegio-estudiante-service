# estudiante-service

Microservicio de estudiantes y apoderados del sistema colegio (arquitectura SOA).

## Stack

- Java 17
- Spring Boot 3.5.16
- Maven (wrapper incluido)
- Spring Data JPA
- Lombok
- MySQL Driver

## Configuracion

| Propiedad | Valor |
|---|---|
| Puerto | `8082` |
| Base de datos | `db_estudiantes` (localhost:3306) |
| Usuario | `root` |
| Contrasena | `root` |

La configuracion vive en `src/main/resources/application.properties`.

## Autonomia de datos (SOA)

Este servicio es dueno exclusivo de los datos de alumnos y apoderados. La tabla
`estudiantes` **no tiene llaves foraneas ni relaciones** hacia el servicio de
matriculas. El servicio de matricula debe consumir este microservicio por HTTP
usando el endpoint por DNI.

## Ejecutar

```bash
chmod +x ./mvnw
./mvnw clean spring-boot:run
```

## Endpoints

| Metodo | Ruta | Descripcion |
|---|---|---|
| GET | `/api/v1/estudiantes` | Lista todos los estudiantes |
| GET | `/api/v1/estudiantes/{id}` | Busca por ID primario |
| GET | `/api/v1/estudiantes/dni/{dni}` | Busca por DNI (consumido por matricula) |
| POST | `/api/v1/estudiantes` | Registra un estudiante con validaciones `@Valid` |

### Ejemplo de POST

```bash
curl -i -X POST http://localhost:8082/api/v1/estudiantes \
  -H "Content-Type: application/json" \
  -d '{
    "dni": "12345678",
    "nombres": "Ana Maria",
    "apellidoPaterno": "Quispe",
    "apellidoMaterno": "Rojas",
    "fechaNacimiento": "2012-05-14",
    "genero": "FEMENINO",
    "direccion": "Av. Los Alamos 123, Lima",
    "apoderadoDni": "87654321",
    "apoderadoNombreCompleto": "Carlos Quispe Ramos",
    "apoderadoParentesco": "Padre",
    "apoderadoTelefono": "987654321",
    "apoderadoEmail": "carlos.quispe@example.com"
  }'
```

### Codigos de respuesta

- `201 Created`: estudiante registrado.
- `400 Bad Request`: datos invalidos (incluye detalle por campo).
- `404 Not Found`: no existe estudiante con ese ID o DNI.
- `409 Conflict`: DNI ya registrado.
