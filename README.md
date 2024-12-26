
# Proyecto Final Con Spring Boot

Este proyecto es una API RESTful para gestionar usuarios, clientes, ventas y devoluciones. La API está desarrollada utilizando Java con la tecnología Spring Boot, proporcionando un conjunto de endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre los recursos mencionados. Está diseñada para facilitar la integración y el manejo eficiente de las operaciones comerciales dentro de un sistema de ventas.
## Tabla de Contenidos
- [Configurar el Proyecto y ejecutar de manera local](#configurar-el-proyecto-y-ejecutar-de-manera-local)
  - [1. Clonar el Repositorio de GitHub](#1-clonar-el-repositorio-de-github)
  - [2. Configuración de la Base de Datos](#2-configuración-de-la-base-de-datos)
  - [3. Configuración de Variables de Entorno](#3-configuración-de-variables-de-entorno)
  - [4. Ejecutar el Proyecto](#4-ejecutar-el-proyecto)
- [Ejecutar el proyecto y ejecutar en Docker de forma local](#ejecutar-el-proyecto-y-ejecutar-en-docker-de-forma-local)
  - [1. Clonar el Repositorio de GitHub](#1-clonar-el-repositorio-de-github-1)
  - [2. Crear el contenedor en Docker](#2-crear-el-contenedor-en-docker)
- [Ejemplos de Cómo Probar los Endpoints](#ejemplos-de-cómo-probar-los-endpoints)
- [Acceso a la Documentación Swagger](#acceso-a-la-documentación-swagger)
- [Cómo Probar los Endpoints](#cómo-probar-los-endpoints)
- [CRUD de usuarios (clientes)](#crud-de-usuarios-clientes)
## Configurar el Proyecto y ejecutar de manera local
### 1. Clonar el Repositorio de GitHub
Ejecuta el siguiente comando para clonar el proyecto desde GitHub:
```bash
git clone https://github.com/gitmariosalazar/spring-boot-project.git
```
Una vez clonado, navega al directorio del proyecto y ábrelo con IntelliJ IDEA o el editor de tu preferencia.
### 2. Configuración de la Base de Datos
Para este proyecto, necesitas tener instalado PostgreSQL o MySQL.
Si usas MySQL, sigue los pasos a continuación para crear la base de datos:
1. Accede a MySQL desde la terminal ejecutando:
```sql
mysqlsh -u root -p
```
2.	Cambia al modo SQL ingresando:
```sql
\sql
```
3.	Crea la base de datos con el siguiente comando:
```sql
create database database_name;
```
### 3.	Configuración de Variables de Entorno
Crea las siguientes variables de entorno para el proyecto:
•	`SB_PORT`: El puerto por el que se comunica la aplicación (se recomienda usar el puerto 8080).
•	`SB_DBURL`: La URL de conexión a la base de datos.
•	`SB_USERDB`: El nombre de usuario de la base de datos.
•	`SB_DBPASSWORD`: La contraseña de la base de datos.
Configura estas variables en el archivo de propiedades del proyecto (`application.properties`) de la siguiente manera:
```properties
server.port=${SB_PORT} # Ejemplo: 8080
spring.datasource.url=${SB_DBURL} # Ejemplo: jdbc:mysql://localhost:3306/database_name
spring.datasource.username=${SB_USERDB} # Ejemplo: root
spring.datasource.password=${SB_DBPASSWORD} # Ejemplo: contraseña
```

### 4.	Ejecutar el Proyecto
Con todas las configuraciones realizadas, ejecuta el proyecto. Una vez en funcionamiento, puedes acceder a la documentación de los endpoints en el siguiente enlace: http://localhost:8080/swagger-ui/index.html
## Ejecutar el proyecto y ejecutar en Docker de forma local
### 1.	Clonar el Repositorio de GitHub
Ejecuta el siguiente comando para clonar el proyecto desde GitHub:
git clone https://github.com/gitmariosalazar/spring-boot-project.git
### 2.	Crear el contenedor en Docker
Una vez que hayas clonado el proyecto, navega al directorio del proyecto desde la terminal y ejecuta el comando correspondiente a tu sistema operativo para construir y ejecutar el contenedor:
- **Linux 🐧:**
```bash
sudo docker-compose up –build -d
```
- **Windows 🪟:**
```bash
docker-compose up –build -d
```
El parámetro `--build` asegura que se reconstruya la imagen del contenedor, mientras que -d ejecuta los servicios en segundo plano.


## Ejemplos de Cómo Probar los Endpoints

La API cuenta con una documentación interactiva generada con Swagger que te permite probar fácilmente los endpoints.

## Acceso a la Documentación Swagger

1. **Inicia la aplicación** siguiendo los pasos de configuración y ejecución descritos anteriormente.

2. **Accede a Swagger** abriendo tu navegador web y visitando la siguiente URL: http://localhost:8080/swagger-ui/index.html
> Reemplaza `8080` con el puerto en el que se esté ejecutando tu aplicación si has configurado uno diferente.

## Cómo Probar los Endpoints

Una vez que estés en la interfaz de Swagger, sigue estos pasos para probar los endpoints de la API:

1. **Navega por la lista de endpoints disponibles**. Verás una lista completa de todos los endpoints agrupados por categorías o recursos.

2. **Selecciona un endpoint** que desees probar haciendo clic sobre él. Esto expandirá la sección y mostrará detalles como el método HTTP (GET, POST, PUT, DELETE), parámetros requeridos.

3. **Introduce los parámetros necesarios** (si los hay) en los campos correspondientes.

4. Haz clic en el botón `Try it out` para enviar la solicitud y ver la respuesta directamente en la interfaz.

## **Endpoints**

### **Obtener el token**

- Generar Token
    - Endpoint: `http://localhost:8080/api/auth/get-token`
    - Método: `POST`
- Para obtener el token:
    - Registra un usuario en el sistema.
    - Genera el token enviando el correo electrónico y la contraseña del usuario.

#### Detalles de los campos
| Campo     | Tipo    | Descripción                                         | Requerido |
|-----------|---------|-----------------------------------------------------|-----------|
| `email`   | `String`| Correo electrónico del usuario. Ejemplo: `mariosalazar.ms.10@gmail.com` | Sí        |
| `password`| `String`| Contraseña del usuario. Ejemplo: `password-mario`   | Sí        |

#### Ejemplo de Solicitud

```json
{
  "email": "mariosalazar.ms.10@gmail.com",
  "password": "password-mario"
}
```
#### Uso del Token
Una vez que obtengas el token, cópialo y pégalo para autorizar el uso de los demás endpoints.

----

## CRUD de usuarios (clientes)
### 1. Crear usuario o cliente (`POST`)
- Crear usuario o cliente
    - **Endpoint:** `http://localhost:8080/api/users`
    - **Método:** `POST`
#### Detalles de los campos

| **Campo**         | **Tipo**    | **Descripción**                                | **Requerido** |
|--------------------|------------|-----------------------------------------------|---------------|
| `identification`  | `String`   | Identificación única del cliente (DNI).       | Sí            |
| `firstname`       | `String`   | Nombre del cliente.                           | Sí            |
| `lastname`        | `String`   | Apellido del cliente.                         | Sí            |
| `email`           | `String`   | Email del cliente.                            | Sí            |
| `address`         | `String`   | Dirección del cliente.                        | Sí            |
| `phone`           | `String`   | Número de teléfono del cliente.               | Sí            |
| `password`        | `String`   | Clave secreta del cliente.                    | Sí            |

#### Ejemplo de Solicitud

```json
{
  "identification": "1003938477",
  "firstname": "Mario",
  "lastname": "Salazar",
  "email": "mariosalazar.ms.10@gmail.com",
  "address": "El Tejar - Ibarra",
  "phone": "0994532438",
  "password": "password-mario"
}
```

### 2. Obtener la lista de usuarios (`GET`)
- Traer lista de usuarios
    - **Endpoint:** `http://localhost:8080/api/users`
    - **Método:** `GET`

### 3. Traer un usuario en particular por `id` (`GET`)
- Buscar usuario por `id`
    - **Endpoint:** `http://localhost:8080/api/users/by-id/{id}`
    - **Método:** `GET`

#### Detalles del parámetro

| **Parámetro** | **Tipo**  | **Ubicación** | **Descripción**                       |
|---------------|-----------|---------------|---------------------------------------|
| `id`          | `Integer` | `Path`        | El ID del cliente a buscar. Ej.: `1` |

### 4. Traer un usuario en particular por `email` (`GET`)
- Buscar usuario por `email`
    - **Endpoint:** `http://localhost:8080/api/users/by-email/{email}`
    - **Método:** `GET`

#### Detalles del parámetro

| **Parámetro** | **Tipo**  | **Ubicación** | **Descripción**                       |
|---------------|-----------|---------------|---------------------------------------|
| `email`          | `String` | `Path`        | El email del cliente a buscar. Ej.: `mariosalazar.ms.10@gmail.com` |

### 5. Eliminar un usuario en particular por `id` (`DELETE`)
- Buscar usuario por `id`
    - **Endpoint:** `http://localhost:8080/api/users/{id}`
    - **Método:** `DELETE`

#### Detalles del parámetro

| **Parámetro** | **Tipo**  | **Ubicación** | **Descripción**                       |
|---------------|-----------|---------------|---------------------------------------|
| `id`          | `Integer` | `Path`        | El ID del cliente a eliminar. Ej.: `1` |

### 6. Actualizar un usuario en particular por `id` (`PUT`)
- Actualizar usuario por `id`
    - **Endpoint:** `http://localhost:8080/api/users/{id}`
    - **Método:** `PUT`

#### Detalles de la solicitud

| **Campo**         | **Tipo**    | **Descripción**                                | **Requerido** |
|--------------------|------------|-----------------------------------------------|---------------|
| `identification`  | `String`   | Identificación única del cliente (DNI).       | Sí            |
| `firstname`       | `String`   | Nombre del cliente.                           | Sí            |
| `lastname`        | `String`   | Apellido del cliente.                         | Sí            |
| `email`           | `String`   | Email del cliente.                            | Sí            |
| `address`         | `String`   | Dirección del cliente.                        | Sí            |
| `phone`           | `String`   | Número de teléfono del cliente.               | Sí            |
| `password`        | `String`   | Clave secreta del cliente.                    | Sí            |

#### Detalles del parámetro

| **Parámetro** | **Tipo**  | **Ubicación** | **Descripción**                       |
|---------------|-----------|---------------|---------------------------------------|
| `id`          | `Integer` | `Path`        | El ID del cliente a actualizar. Ej.: `1` |

#### Ejemplo de Solicitud

```json
{
  "identification": "1003938477",
  "firstname": "Mario",
  "lastname": "Salazar",
  "email": "mariosalazar.ms.10@gmail.com",
  "address": "El Tejar - Ibarra",
  "phone": "0994532438",
  "password": "password-mario"
}
```

---

## CRUD de Productos
### 1. Crear producto (`POST`)
- Agregar un nuevo producto
    - **Endpoint:** `http://localhost:8080/api/products`
    - **Método:** `POST`
#### Detalles de los campos

| **Campo**        | **Tipo**       | **Descripción**                                     | **Requerido** |
|-------------------|---------------|-----------------------------------------------------|---------------|
| `code`           | `String`      | Código único del producto.                         | Sí            |
| `name`           | `String`      | Nombre del producto.                               | Sí            |
| `description`    | `String`      | Descripción del producto.                          | Opcional      |
| `mark`           | `String`      | Marca del producto.                                | Sí            |
| `supplierPrice`  | `BigDecimal`  | Precio de compra del producto (proveedor).         | Sí            |
| `quantity`       | `Integer`     | Cantidad de productos a ingresar.                 | Sí            |
| `iva`            | `BigDecimal`  | Valor de IVA del producto.                        | Sí            |

#### Ejemplo de Solicitud

```json
{
  "code": "PT001TECH",
  "name": "Mouse keyboard",
  "description": "This mouse is black",
  "mark": "SONY",
  "supplierPrice": 12.36,
  "iva": 12,
  "quantity": 4
}
```

### 2. Obtener la lista de productos (`GET`)
- Traer lista de usuarios
    - **Endpoint:** `http://localhost:8080/api/products`
    - **Método:** `GET`

### 3. Traer un producto en particular por `id` (`GET`)
- Buscar producto por `id`
    - **Endpoint:** `http://localhost:8080/api/products/by-id/{id}`
    - **Método:** `GET`

#### Detalles del parámetro

| **Parámetro** | **Tipo**  | **Ubicación** | **Descripción**                       |
|---------------|-----------|---------------|---------------------------------------|
| `id`          | `Integer` | `Path`        | El ID del producto a buscar. Ej.: `1` |

### 4. Traer un producto en particular por `code` (`GET`)
- Buscar usuario por `code`
    - **Endpoint:** `http://localhost:8080/api/products/by-code/{code}`
    - **Método:** `GET`

#### Detalles del parámetro

| **Parámetro** | **Tipo**  | **Ubicación** | **Descripción**                       |
|---------------|-----------|---------------|---------------------------------------|
| `code`          | `String` | `Path`        | El código del producto a buscar. Ej.: `PT001TECH` |

### 5. Eliminar un producto en particular por `id` (`DELETE`)
- Buscar usuario por `id`
    - **Endpoint:** `http://localhost:8080/api/products/{id}`
    - **Método:** `DELETE`

#### Detalles del parámetro

| **Parámetro** | **Tipo**  | **Ubicación** | **Descripción**                       |
|---------------|-----------|---------------|---------------------------------------|
| `id`          | `Integer` | `Path`        | El id de un producto a eliminar. Ej.: `1` |

### 6. Actualizar un producto en particular por `code` (`PUT`)
- Actualizar usuario por `code`
    - **Endpoint:** `http://localhost:8080/api/products/{code}`
    - **Método:** `PUT`

#### Detalles de la solicitud


| **Campo**        | **Tipo**       | **Descripción**                                     | **Requerido** |
|-------------------|---------------|-----------------------------------------------------|---------------|
| `code`           | `String`      | Código único del producto.                         | Sí            |
| `name`           | `String`      | Nombre del producto.                               | Sí            |
| `description`    | `String`      | Descripción del producto.                          | Opcional      |
| `mark`           | `String`      | Marca del producto.                                | Sí            |
| `supplierPrice`  | `BigDecimal`  | Precio de compra del producto (proveedor).         | Sí            |
| `quantity`       | `Integer`     | Cantidad de productos a ingresar.                 | Sí            |
| `iva`            | `BigDecimal`  | Valor de IVA del producto.                        | Sí            |

#### Detalles del parámetro

| **Parámetro** | **Tipo**  | **Ubicación** | **Descripción**                       |
|---------------|-----------|---------------|---------------------------------------|
| `code`          | `String` | `Path`        | El código del producto a actualizar. Ej.: `PT001TECH` |

#### Ejemplo de Solicitud

```json
{
  "code": "PT001TECH",
  "name": "Mouse keyboard",
  "description": "This mouse is black",
  "mark": "SONY",
  "supplierPrice": 12.36,
  "iva": 12,
  "quantity": 4
}
```

---

## CRUD de Ventas
### 1. Crear venta (`POST`)
- Agregar una nuevo venta
    - **Endpoint:** `http://localhost:8080/api/selling`
    - **Método:** `POST`

#### Modelo `SellingItemsRequest`

| **Campo**   | **Tipo**  | **Descripción**                                   | **Requerido** |
|-------------|-----------|---------------------------------------------------|---------------|
| `product`   | `Product` | Objeto que representa el producto del ítem.       | Sí            |
| `quantity`  | `String`  | Cantidad de productos del ítem.                   | Sí            |



#### Modelo `User` (Debe existir en la base de datos)

| **Campo**          | **Tipo**   | **Descripción**                            | **Requerido** |
|---------------------|------------|--------------------------------------------|---------------|
| `id`               | `Integer`  | Id del cliente.                            | Sí            |
| `identification`    | `String`   | Identificación única del cliente (DNI).    | Sí            |
| `firstname`         | `String`   | Nombre del cliente.                        | Sí            |
| `lastname`          | `String`   | Apellido del cliente.                      | Sí            |
| `email`             | `String`   | Email del cliente.                         | Sí            |
| `address`           | `String`   | Dirección del cliente.                     | Sí            |
| `phone`             | `String`   | Número de teléfono del cliente.            | Sí            |
| `password`          | `String`   | Clave secreta del cliente.                 | Sí            |


#### Modelo `Product` (Debe existir en la base de datos)

| **Campo**             | **Tipo**      | **Descripción**                                     | **Requerido** |
|------------------------|---------------|-----------------------------------------------------|---------------|
| `id`                  | `Integer`     | Id del producto.                                   | Sí            |
| `code`                | `String`      | Código único del producto.                         | Sí            |
| `name`                | `String`      | Nombre del producto.                               | Sí            |
| `description`         | `String`      | Descripción del producto.                          | Opcional      |
| `mark`                | `String`      | Marca del producto.                                | Sí            |
| `supplierPrice`       | `BigDecimal`  | Precio de compra del producto (proveedor).         | Sí            |
| `quantity`            | `Integer`     | Cantidad de productos a ingresar.                 | Sí            |
| `iva`                 | `BigDecimal`  | Valor de IVA del producto.                        | Sí            |
| `percentageIncrement` | `BigDecimal`  | Porcentaje de incremento para la venta al público. | Sí            |
| `publicPrice`         | `BigDecimal`  | Precio de venta al público.                        | Sí            |


#### Modelo `SellingRequest`

| **Campo**             | **Tipo**               | **Descripción**                                           | **Requerido** |
|------------------------|------------------------|-----------------------------------------------------------|---------------|
| `sellingItemsRequest` | `List<SellingItemsRequest>` | Lista de objetos que representa los productos a vender.    | Sí            |
| `client`              | `User`                | Objeto que representa el cliente asociado con la venta.    | Sí            |

## Ejemplo de Solicitud Completo

```json
{
  "sellingItemsRequests": [
    {
      "product": {
        "id": 1,
        "code": "PT001TECH",
        "name": "Mouse keyboard",
        "description": "This mouse is black",
        "mark": "SONY",
        "supplierPrice": 12.36,
        "iva": 12,
        "percentageIncrement": 35,
        "publicPrice": 18.69,
        "quantity": 10
      },
      "quantity": 5
    },
    {
      "product": {
        "id": 2,
        "code": "PT002TECH",
        "name": "Laptop Dell Inspiron 5567 16 GB RAM",
        "description": "This computer is on stock",
        "mark": "DELL",
        "supplierPrice": 1102.57,
        "iva": 12,
        "percentageIncrement": 35,
        "publicPrice": 1667.09,
        "quantity": 15
      },
      "quantity": 2
    }
  ],
  "client": {
    "id": 15,
    "identification": "1003938471",
    "firstname": "Mario Andres",
    "lastname": "Salazar Anrango",
    "email": "mariosalazar.ms.10@gmail.com",
    "address": "El Tejar - Ibarra",
    "phone": "099456325"
  }
}
```

### 2. Obtener la lista de ventas (`GET`)
- Traer lista de usuarios
    - **Endpoint:** `http://localhost:8080/api/products`
    - **Método:** `GET`

### 3. Traer una venta en particular por `id` (`GET`)
- Buscar producto por `id`
    - **Endpoint:** `http://localhost:8080/api/selling/{id}`
    - **Método:** `GET`

#### Detalles del parámetro

| **Parámetro** | **Tipo**  | **Ubicación** | **Descripción**                       |
|---------------|-----------|---------------|---------------------------------------|
| `id`          | `Integer` | `Path`        | El ID de la venta a buscar. Ej.: `1` |

### 4. Eliminar una venta en particular por `id` (`DELETE`)
- Buscar usuario por `id`
    - **Endpoint:** `http://localhost:8080/api/selling/{id}`
    - **Método:** `DELETE`

#### Detalles del parámetro

| **Parámetro** | **Tipo**  | **Ubicación** | **Descripción**                       |
|---------------|-----------|---------------|---------------------------------------|
| `id`          | `Integer` | `Path`        | El id de la venta a eliminar. Ej.: `1` |

### 5. Actualizar un producto en particular por `id` (`PUT`)
- Actualizar usuario por `id`
    - **Endpoint:** `http://localhost:8080/api/selling/{id}`
    - **Método:** `PUT`


#### Modelo `SellingItemsRequest`

| **Campo**   | **Tipo**  | **Descripción**                                   | **Requerido** |
|-------------|-----------|---------------------------------------------------|---------------|
| `product`   | `Product` | Objeto que representa el producto del ítem.       | Sí            |
| `quantity`  | `String`  | Cantidad de productos del ítem.                   | Sí            |



#### Modelo `User` (Debe existir en la base de datos)

| **Campo**          | **Tipo**   | **Descripción**                            | **Requerido** |
|---------------------|------------|--------------------------------------------|---------------|
| `id`               | `Integer`  | Id del cliente.                            | Sí            |
| `identification`    | `String`   | Identificación única del cliente (DNI).    | Sí            |
| `firstname`         | `String`   | Nombre del cliente.                        | Sí            |
| `lastname`          | `String`   | Apellido del cliente.                      | Sí            |
| `email`             | `String`   | Email del cliente.                         | Sí            |
| `address`           | `String`   | Dirección del cliente.                     | Sí            |
| `phone`             | `String`   | Número de teléfono del cliente.            | Sí            |
| `password`          | `String`   | Clave secreta del cliente.                 | Sí            |


#### Modelo `Product` (Debe existir en la base de datos)

| **Campo**             | **Tipo**      | **Descripción**                                     | **Requerido** |
|------------------------|---------------|-----------------------------------------------------|---------------|
| `id`                  | `Integer`     | Id del producto.                                   | Sí            |
| `code`                | `String`      | Código único del producto.                         | Sí            |
| `name`                | `String`      | Nombre del producto.                               | Sí            |
| `description`         | `String`      | Descripción del producto.                          | Opcional      |
| `mark`                | `String`      | Marca del producto.                                | Sí            |
| `supplierPrice`       | `BigDecimal`  | Precio de compra del producto (proveedor).         | Sí            |
| `quantity`            | `Integer`     | Cantidad de productos a ingresar.                 | Sí            |
| `iva`                 | `BigDecimal`  | Valor de IVA del producto.                        | Sí            |
| `percentageIncrement` | `BigDecimal`  | Porcentaje de incremento para la venta al público. | Sí            |
| `publicPrice`         | `BigDecimal`  | Precio de venta al público.                        | Sí            |


#### Modelo `SellingRequest`

| **Campo**             | **Tipo**               | **Descripción**                                           | **Requerido** |
|------------------------|------------------------|-----------------------------------------------------------|---------------|
| `sellingItemsRequest` | `List<SellingItemsRequest>` | Lista de objetos que representa los productos a vender.    | Sí            |
| `client`              | `User`                | Objeto que representa el cliente asociado con la venta.    | Sí            |

#### Ejemplo de Solicitud Completo

```json
{
  "sellingItemsRequests": [
    {
      "product": {
        "id": 1,
        "code": "PT001TECH",
        "name": "Mouse keyboard",
        "description": "This mouse is black",
        "mark": "SONY",
        "supplierPrice": 12.36,
        "iva": 12,
        "percentageIncrement": 35,
        "publicPrice": 18.69,
        "quantity": 10
      },
      "quantity": 5
    },
    {
      "product": {
        "id": 2,
        "code": "PT002TECH",
        "name": "Laptop Dell Inspiron 5567 16 GB RAM",
        "description": "This computer is on stock",
        "mark": "DELL",
        "supplierPrice": 1102.57,
        "iva": 12,
        "percentageIncrement": 35,
        "publicPrice": 1667.09,
        "quantity": 15
      },
      "quantity": 2
    }
  ],
  "client": {
    "id": 15,
    "identification": "1003938471",
    "firstname": "Mario Andres",
    "lastname": "Salazar Anrango",
    "email": "mariosalazar.ms.10@gmail.com",
    "address": "El Tejar - Ibarra",
    "phone": "099456325"
  }
}
```

---

## CRUD de Devoluciones

### 1. **Creación de devolución**
- **Método HTTP**: `POST`
- **Endpoint**: `http://localhost:8080/api/returns`
- **Descripción**: Registra una devolución en la base de datos. Al registrarse, la venta relacionada cambia su estado a "anulado", y los productos se devuelven al stock.
- **Modelo de Request (`returnRequest`)**:

#### Campos:
| **Campo**   | **Tipo**   | **Descripción**                                           | **Requerido** |
|-------------|------------|-----------------------------------------------------------|---------------|
| `selling`   | `Object`   | Objeto que representa la venta asociada con la devolución. | Sí            |
| `reason`    | `String`   | Razón o motivo de la devolución.                          | Sí            |

**Modelo `selling` (Debe ser un dato existente en la base de datos):**

| **Campo**        | **Tipo**    | **Descripción**                                           | **Requerido** |
|------------------|-------------|-----------------------------------------------------------|---------------|
| `id`             | `Integer`   | Identificador único de la venta.                          | Sí            |
| `sellingCode`    | `String`    | Código único de la venta.                                 | Sí            |
| `sellingDate`    | `String`    | Fecha de la venta en formato `YYYY-MM-DD`.                | Sí            |
| `subtotal`       | `BigDecimal`| Subtotal de la venta antes de IVA.                        | Sí            |
| `iva`            | `BigDecimal`| Monto de IVA aplicado.                                    | Sí            |
| `total`          | `BigDecimal`| Total de la venta (subtotal + IVA).                       | Sí            |
| `sellingItems`   | `Array`     | Lista de los ítems vendidos.                              | Sí            |
| `client`         | `Object`    | Información del cliente asociado con la venta.            | Sí            |
| `status`         | `String`    | Estado de la venta.                                       | Sí            |

**Modelo `sellingItems` (Debe ser un dato existente en la base de datos):**

| **Campo**        | **Tipo**    | **Descripción**                                           | **Requerido** |
|------------------|-------------|-----------------------------------------------------------|---------------|
| `id`             | `Integer`   | Identificador único del ítem.                             | Sí            |
| `product`        | `Object`    | Objeto que representa el producto vendido.                | Sí            |
| `selling`        | `String`    | Código de la venta asociada.                              | Sí            |
| `quantity`       | `Integer`   | Cantidad de productos vendidos.                           | Sí            |
| `unitPrice`      | `String`    | Precio unitario del producto.                             | Sí            |
| `subtotal`       | `BigDecimal`| Subtotal antes de IVA.                                    | Sí            |
| `iva`            | `BigDecimal`| Monto de IVA aplicado.                                    | Sí            |
| `total`          | `BigDecimal`| Total (subtotal + IVA).                                   | Sí            |

**Modelo `client` (Debe ser un dato existente en la base de datos):**

| **Campo**        | **Tipo**    | **Descripción**                                           | **Requerido** |
|------------------|-------------|-----------------------------------------------------------|---------------|
| `id`             | `Integer`   | Identificador del cliente.                                | Sí            |
| `identification` | `String`    | Identificación única del cliente (DNI).                   | Sí            |
| `firstname`      | `String`    | Nombre del cliente.                                       | Sí            |
| `lastname`       | `String`    | Apellido del cliente.                                     | Sí            |
| `email`          | `String`    | Email del cliente.                                        | Sí            |
| `address`        | `String`    | Dirección del cliente.                                    | Sí            |
| `phone`          | `String`    | Teléfono del cliente.                                     | Sí            |
| `password`       | `String`    | Clave secreta del cliente.                                | Sí            |

#### Ejemplo de Solicitud Completa:

```json
{
  "selling": {
    "id": 1,
    "sellingCode": "09c81052-4f33-47a2-859f-47d034743c4e",
    "sellingDate": "2024-12-19",
    "subtotal": 3060.39,
    "iva": 367.24,
    "total": 3427.63,
    "sellingItems": [
      {
        "id": 1,
        "product": {
          "id": 1,
          "code": "PT001TECH",
          "name": "Mouse keyboard",
          "description": "This mouse is black",
          "mark": "SONY",
          "supplierPrice": 12.36,
          "iva": 12,
          "percentageIncrement": 35,
          "publicPrice": 18.69,
          "quantity": 4
        },
        "selling": null,
        "quantity": 5,
        "unitPrice": 18.69,
        "iva": 10.01,
        "subtotal": 83.44,
        "totalPrice": 93.45
      },
      {
        "id": 2,
        "product": {
          "id": 2,
          "code": "PT002TECH",
          "name": "Laptop Dell Inspiron 5567 16 GB RAM",
          "description": "This computer is on stock",
          "mark": "DELL",
          "supplierPrice": 1102.57,
          "iva": 12,
          "percentageIncrement": 35,
          "publicPrice": 1667.09,
          "quantity": 151
        },
        "selling": null,
        "quantity": 2,
        "unitPrice": 1667.09,
        "iva": 357.23,
        "subtotal": 2976.95,
        "totalPrice": 3334.18
      }
    ],
    "client": {
      "id": 2,
      "identification": "1003938410",
      "firstname": "Lizbeth",
      "lastname": "Ruales",
      "email": "lizbeth@gmail.com",
      "address": "El Tejar - Ibarra",
      "phone": "0994532438",
      "password": "$2a$10$.mdnj1PZ.124SicT2LJubO5GRxgwKjXPhvkKs1MeguF56p.4cOZwu",
      "token": ""
    },
    "status": "delivered"
  },
  "reason": "string"
}
```

## 2. Lista completa de devoluciones

- **Método HTTP**: GET
- **Endpoint**: `http://localhost:8080/api/returns`
- **Descripción**: Devuelve una lista de todas las devoluciones registradas.

## 3. Traer una devolución en particular por ID

- **Método HTTP**: GET
- **Endpoint**: `http://localhost:8080/api/returns/{id}`
- **Descripción**: Trae una devolución específica según el ID proporcionado.

#### Parámetro:
| Campo   | Tipo   | Ubicación | Descripción                                       |
|---------|--------|-----------|---------------------------------------------------|
| id      | Integer| Path      | El ID de la devolución a buscar. Ej.: 1          |


## 4. Eliminación de devolución

- **Método HTTP**: DELETE
- **Endpoint**: `http://localhost:8080/api/returns/{id}`
- **Descripción**: Elimina una devolución específica de acuerdo con su ID.

#### Parámetro:
| Campo   | Tipo   | Ubicación | Descripción                                       |
|---------|--------|-----------|---------------------------------------------------|
| id      | Integer| Path      | El código de la devolución a eliminar. Ej.: 1     |


## 5. Edición de devolución (Anulación)

- **Método HTTP**: PUT
- **Endpoint**: `http://localhost:8080/api/returns/{id}`
- **Descripción**: Anula una devolución, cambiando su estado a "anulado". También actualiza la venta y el stock de productos.

### Parámetro:
| Campo   | Tipo   | Ubicación | Descripción                                       |
|---------|--------|-----------|---------------------------------------------------|
| id      | Integer| Path      | El código de la devolución a actualizar. Ej.: 1        |

---


## Obtener todos los productos cuya cantidad disponible (`quantity`) sea menor a 5

- **Método HTTP**: `GET`
- **Endpoint**: `http://localhost:8080/api/products/warning_stock`
- **Descripción**: Devuelve todos los productos cuya cantidad disponible sea menor a 5.

---

## Obtener la lista de productos de una determinada venta

- **Método HTTP**: `GET`
- **Endpoint**: `http://localhost:8080/api/sellings/products/{id}`
- **Descripción**: Devuelve la lista de productos vendidos en una venta específica.

#### Parámetro:
| Campo   | Tipo   | Ubicación | Descripción                                       |
|---------|--------|-----------|---------------------------------------------------|
| `id`      | `Integer`| `Path`      | El código de la venta para ver la lista de productos vendidos. Ej.: `1`  |

---

## Obtener la sumatoria del monto y también cantidad total de ventas de un determinado día

- **Método HTTP**: GET
- **Endpoint**: `http://localhost:8080/api/sellings/{selling_date}`
- **Descripción**: Devuelve la sumatoria del monto total y la cantidad total de ventas realizadas en una fecha específica.

#### Parámetro:
| Campo        | Tipo   | Ubicación | Descripción                                 |
|--------------|--------|-----------|---------------------------------------------|
| `sellingDate`  | `String` | `Path`      | Fecha de la venta en formato `YYYY-MM-DD`     |

---

## Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el apellido del cliente de la venta con el monto más alto de todas

- **Método HTTP**: `GET`
- **Endpoint**: `http://localhost:8080/api/selling/greater-selling`
- **Descripción**: Devuelve los detalles de la venta con el monto más alto, incluyendo el código de venta, el total, la cantidad de productos, el nombre y apellido del cliente.

---

## Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el apellido del cliente de la venta con el monto más bajo de todas

- **Método HTTP**: `GET`
- **Endpoint**: `http://localhost:8080/api/selling/smaller-selling`
- **Descripción**: Devuelve los detalles de la venta con el monto más bajo, incluyendo el código de venta, el total, la cantidad de productos, el nombre y apellido del cliente.

---

## Obtener la lista de productos que aún no se han vendido por primera vez

- **Método HTTP**: `GET`
- **Endpoint**: `http://localhost:8080/api/selling/unpurchased-products`
- **Descripción**: Devuelve la lista de productos que aún no han sido vendidos ni una sola vez.
