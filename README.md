
# e-Commerce Gapsi - Backend en Spring Boot

Este proyecto es el backend de una aplicación de gestión de proveedores para una tienda, desarrollado con Spring Boot. Proporciona una API RESTful que interactúa con el frontend desarrollado en React.

## Características

- API REST para gestionar proveedores (CRD: Crear, Leer, Eliminar).
- Endpoints para manejar proveedores y sus datos (nombre, razón social, dirección).
- Soporte para paginación en la lista de proveedores.
- Implementación de validaciones en el backend(Nombre de proveedor no se repita).

## Requisitos previos

Para instalar y ejecutar este proyecto, asegúrate de tener instalado lo siguiente:

- [Java 22](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)

## Instalación

1. **Clona este repositorio en tu máquina local:**

   ```bash
   git clone https://github.com/siegfriedgm/providers_java.git
   cd providers_java
   ```

2. **Instala las dependencias usando Maven:**

   ```bash
   mvn install
   ```

3. **Configuración de la base de datos**

   El proyecto utiliza una base de datos basada en un archivo JSON el cual se encuentra en /resources.

   ```properties
   # src/main/resources/application.properties
   spring.application.name=providers

   app.feature.version=10.0
   app.feature.candidate.name=Bienvenido Candidato GPJ
   app.feature.candidate.image=

   #Localización de BD
   app.db.location=dbjson/bd.json
      ```

4. **Configurar el servidor en el archivo `application.properties`:**

   Por defecto, el servidor Spring Boot estará en el puerto `8080`. Si deseas cambiarlo, edita el archivo `src/main/resources/application.properties` y agrega lo siguiente:

   ```properties
   # Cambiar puerto del servidor
   server.port=8081
   ```

## Ejecución del Proyecto

### Modo de Desarrollo

Para iniciar el proyecto en modo de desarrollo, ejecuta:

```bash
mvn spring-boot:run
```

Esto iniciará la aplicación en `http://localhost:8080`, donde podrás acceder a los endpoints de la API.

### Endpoints de la API

Los siguientes son algunos de los endpoints disponibles en la API para gestionar proveedores:

1. **Obtener la lista de proveedores (paginada):**

   ```http
   GET /providers/paging
   ```

   Parámetros (paginación):
   - `page`: Número de página (por defecto es 0)
   - `size`: Número de elementos por página (por defecto es 10)

2. **Obtener la lista de proveedores:**

   ```http
   GET /providers
   ```

3. **Crear un nuevo proveedor:**

   ```http
   POST /providers
   ```

   Cuerpo de la solicitud:

   ```json
   {
     "providerName": "Proveedor A",
     "providerSocialName": "Razón Social A",
     "providerAddress": "Dirección A"
   }
   ```

4. **Eliminar un proveedor:**

   ```http
   DELETE /providers/{id}
   ```
## Dependencias Principales

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Web](https://spring.io/projects/spring-web)
- [Lombok](https://projectlombok.org/)

## Contribuciones

Si deseas contribuir a este proyecto, siéntete libre de hacer un fork y enviar tus pull requests. Las sugerencias y mejoras son bienvenidas.

## Licencia

Este proyecto está bajo la licencia MIT.
