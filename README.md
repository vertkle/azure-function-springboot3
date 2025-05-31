# Azure Functions con Spring Boot 3

## Descripción

Este proyecto es una integración de **Azure Functions** con **Spring Boot 3** utilizando **Java** y **Maven**. Permite crear funciones serverless en Azure mientras se aprovechan las capacidades del framework Spring Boot para la configuración y gestión de dependencias.

## Características

- **Azure Functions**: Implementación de funciones serverless.
- **Spring Boot 3**: Framework para la creación de aplicaciones Java.
- **H2 Database**: Base de datos en memoria para pruebas.
- **Hibernate**: Configuración de JPA para persistencia.
- **Maven**: Gestión de dependencias y construcción del proyecto.
- **Docker | Docker Compose**: Contenerización de la aplicación y despliegue local.

## Configuración

### Archivos importantes

1. **`local.settings.json`**: Configuración local para Azure Functions.
   ```json
   {
     "IsEncrypted": false,
     "Values": {
       "FUNCTIONS_WORKER_RUNTIME": "java",
       "AzureWebJobsStorage": "reemplazar_cadena_de_conexión_aquí"
     }
   }
2. **`host.json`**: Configuración del host de Azure Functions.
   ```json
   {
     "version": "2.0",
     "extensionBundle": {
       "id": "Microsoft.Azure.Functions.ExtensionBundle",
       "version": "[4.*, 5.0.0)"
     }
   }

## Desplegar function en local
Para desplegar y probar la función localmente, sigue estos pasos:
- Asegúrate de tener instalado **Docker** y **Docker Compose**.
- Agrega tu cadena de conexión de Azure Storage en enviroment `AzureWebJobsStorage` en el archivo `docker-compose`.
- Ejecuta el siguiente comando en la raíz del proyecto:
  ```bash
  docker-compose up --build
  ```
- Esto construirá la imagen de Docker y levantará el contenedor con la aplicación Spring Boot y Azure Functions.