## SISTEMA DE GESTION DE ALQUILER DE VEHICULOS
![Descripción de la imagen](https://old.lucentinnovation.com/cdn/shop/articles/292208577_790554755728901_9100568040808362362_n.jpg?v=1657705755)

Este proyecto es una aplicación REST desarrollada con Java Spring Boot que implementa un sistema de gestión para el alquiler de vehículos. Permite a los usuarios realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre vehículos, alquileres y clientes.

## Características principales

- Gestión de vehículos: Agregar, editar, eliminar y consultar vehículos disponibles.
- Gestión de alquileres: Crear, modificar, cancelar y consultar alquileres de vehículos.
- Gestión de clientes: Registrar, actualizar y consultar información de clientes.
- Autenticación y autorización de usuarios.
- Implementación de buenas prácticas y patrones de diseño.

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Bases de datos relacionales MySQL
- Maven
- Spring Security
## Instrucciones de instalación

1. Clonar este repositorio: 
```git clone https://github.com/devbryan02/project-psw-back.git ```
2. Configurar la conexión a la base de datos en el archivo `application.properties`.
3. Ejecutar el comando `mvn clean install` para compilar el proyecto.
4. Ejecutar la aplicación con el comando `mvn spring-boot:run`.

## Documentación de la API
Todos los vehiculos: `GET /vechicle` <br/>
Reservas de un usuario por nombre: `GET /user/bookings?username=nombreEjemplo`

Proyecto front: https://github.com/boya155/project-alquiler
