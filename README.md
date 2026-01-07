# Sistema de Gestión para Cadena de Farmacias

Sistema de gestión desarrollado con arquitectura de microservicios para administrar catálogo de medicamentos, inventario por sucursal, ventas, clientes y reportes.

## 🏗️ Arquitectura

El sistema está compuesto por 5 microservicios independientes:

1. **Catalog-Service** (Puerto 8081) - Gestión del catálogo de medicamentos
2. **Customer-Service** (Puerto 8082) - Gestión de clientes y prescripciones médicas
3. **Inventory-Service** (Puerto 8083) - Gestión de inventario por sucursal
4. **Sales-Service** (Puerto 8084) - Gestión de ventas
5. **Reports-Service** (Puerto 8085) - Generación de reportes de negocio

## 🛠️ Tecnologías

- **Backend:** Java 17 + Spring Boot 4.0.0
- **Build Tool:** Gradle
- **Base de Datos:** MySQL 8.0 (una por microservicio)
- **Contenedorización:** Docker + Docker Compose
- **Arquitectura:** Microservicios con database-per-service

## 📋 Requisitos Previos

- Java 17 o superior
- Gradle 8.5 o superior
- Docker y Docker Compose
- MySQL 8.0 (si ejecutas localmente sin Docker)

## 🚀 Inicio Rápido

### Opción 1: Usando Docker Compose (Recomendado)

```bash
# Construir y levantar todos los servicios
docker-compose up -d

# Ver logs
docker-compose logs -f

# Detener servicios
docker-compose down
```

### Opción 2: Ejecutar Localmente

1. **Configurar bases de datos MySQL:**
   - Crear las bases de datos: `catalog_db`, `customer_db`, `inventory_db`, `sales_db`
   - Actualizar `application.properties` en cada servicio con las credenciales correctas

2. **Ejecutar cada microservicio:**
```bash
# Catalog-Service
cd Catalog-Service
./gradlew bootRun

# Customer-Service (en otra terminal)
cd Customer-Service
./gradlew bootRun

# Inventory-Service (en otra terminal)
cd Inventory-Service
./gradlew bootRun

# Sales-Service (en otra terminal)
cd Sales-Service
./gradlew bootRun

# Reports-Service (en otra terminal)
cd Reports-Service
./gradlew bootRun
```

## 📚 Documentación de APIs

### Catalog-Service

- `GET /api/medicamentos` - Listar todos los medicamentos
- `GET /api/medicamentos/{id}` - Obtener medicamento por ID
- `POST /api/medicamentos` - Crear medicamento
- `PUT /api/medicamentos/{id}` - Actualizar medicamento
- `DELETE /api/medicamentos/{id}` - Eliminar medicamento
- `GET /api/medicamentos/categoria/{categoria}` - Filtrar por categoría
- `GET /api/medicamentos/buscar?nombre={nombre}` - Buscar por nombre

### Customer-Service

- `GET /api/clientes` - Listar todos los clientes
- `GET /api/clientes/{id}` - Obtener cliente por ID
- `GET /api/clientes/cedula/{cedula}` - Obtener cliente por cédula
- `POST /api/clientes` - Crear cliente
- `PUT /api/clientes/{id}` - Actualizar cliente
- `DELETE /api/clientes/{id}` - Eliminar cliente

- `GET /api/prescripciones` - Listar todas las prescripciones
- `GET /api/prescripciones/{id}` - Obtener prescripción por ID
- `GET /api/prescripciones/cliente/{clienteId}` - Prescripciones de un cliente
- `POST /api/prescripciones` - Crear prescripción
- `PUT /api/prescripciones/{id}` - Actualizar prescripción
- `DELETE /api/prescripciones/{id}` - Eliminar prescripción

### Inventory-Service

- `GET /api/sucursales` - Listar todas las sucursales
- `GET /api/sucursales/{id}` - Obtener sucursal por ID
- `POST /api/sucursales` - Crear sucursal
- `PUT /api/sucursales/{id}` - Actualizar sucursal
- `DELETE /api/sucursales/{id}` - Eliminar sucursal

- `GET /api/inventarios` - Listar todos los inventarios
- `GET /api/inventarios/{id}` - Obtener inventario por ID
- `GET /api/inventarios/sucursal/{sucursalId}` - Inventario de una sucursal
- `GET /api/inventarios/stock-bajo` - Productos con stock bajo
- `POST /api/inventarios` - Crear inventario
- `PATCH /api/inventarios/{id}/reducir?cantidad={cantidad}` - Reducir stock

### Sales-Service

- `GET /api/ventas` - Listar todas las ventas
- `GET /api/ventas/{id}` - Obtener venta por ID
- `GET /api/ventas/sucursal/{sucursalId}` - Ventas de una sucursal
- `GET /api/ventas/cliente/{clienteId}` - Ventas de un cliente
- `POST /api/ventas` - Crear venta
- `DELETE /api/ventas/{id}` - Eliminar venta

### Reports-Service

- `GET /api/reportes/ventas/sucursal/{sucursalId}?fechaInicio={fecha}&fechaFin={fecha}` - Reporte de ventas
- `GET /api/reportes/inventario/sucursal/{sucursalId}` - Reporte de inventario

## 🐳 Dockerización

Para más detalles sobre cómo dockerizar y subir a Docker Hub, consulta [DOCKER_GUIDE.md](DOCKER_GUIDE.md)

### Comandos principales:

```bash
# Construir imágenes
docker-compose build

# Subir a Docker Hub (después de hacer login)
docker login
docker push tu-usuario/catalog-service:1.0.0
# ... (repetir para cada servicio)

# Levantar servicios
docker-compose up -d
```

## 📁 Estructura del Proyecto

```
PROYECTO MICROSERVICIOS/
├── Catalog-Service/
│   ├── src/
│   │   └── main/
│   │       ├── java/.../catalogservice/
│   │       │   ├── model/
│   │       │   ├── repository/
│   │       │   ├── service/
│   │       │   ├── controller/
│   │       │   ├── dto/
│   │       │   └── exception/
│   │       └── resources/
│   ├── Dockerfile
│   └── build.gradle
├── Customer-Service/
├── Inventory-Service/
├── Sales-Service/
├── Reports-Service/
├── docker-compose.yml
└── DOCKER_GUIDE.md
```

## 🔧 Configuración

Cada microservicio tiene su propio archivo `application.properties` con:
- Puerto del servidor
- Configuración de base de datos
- Configuración JPA/Hibernate

Las bases de datos se crean automáticamente al iniciar los servicios.

## 🧪 Pruebas

```bash
# Ejecutar tests de un servicio
cd Catalog-Service
./gradlew test

# Ejecutar todos los tests
./gradlew test
```

## 📝 Notas

- Cada microservicio tiene su propia base de datos (database-per-service)
- Los servicios se comunican mediante REST APIs
- Reports-Service consume datos de los otros servicios
- CORS está habilitado para permitir conexiones desde el frontend

## 👥 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto es parte de un trabajo académico.






