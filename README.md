# Sistema de Gestión de Citas Médicas

Este proyecto es una API RESTful desarrollada en Java y Spring Boot para la gestión de citas de una clínica médica. Está diseñado utilizando principios estrictos de Arquitectura Hexagonal (Puertos y Adaptadores) y Diseño Guiado por el Dominio (DDD), garantizando que las reglas de negocio permanezcan aisladas de las dependencias tecnológicas.

## Arquitectura y Diseño

El proyecto se divide en tres capas principales para mantener un bajo acoplamiento y una alta cohesión:

* **Domain (El Búnker):** Contiene la lógica central del negocio. Aquí viven las entidades principales (como `Cita`) y los validadores de reglas de negocio. Esta capa no tiene dependencias de frameworks externos ni bases de datos.
* **Application:** Define los Casos de Uso del sistema (la orquestación de operaciones) y los Puertos de salida (interfaces) que definen los contratos que la infraestructura debe cumplir.
* **Infrastructure:** Contiene los detalles de implementación. Aquí se encuentran los Controladores REST (adaptadores de entrada), la persistencia con PostgreSQL y Spring Data JPA (adaptadores de salida), y la configuración de inyección de dependencias.

## Stack Tecnológico

* Java 17+
* Spring Boot 3
* PostgreSQL
* Spring Data JPA
* Maven

## Reglas de Negocio (Domain Rules)

El núcleo de la aplicación protege la integridad de los datos a través de las siguientes reglas de negocio:

**Implementadas:**
* Validación de horario laboral: No se permite agendar ni modificar citas fuera del horario de operación (apertura a las 06:00 hrs).

**En Backlog (Próximas iteraciones):**
* Bloqueo de agenda durante el horario de comida (14:00 - 16:00 hrs).
* Duración dinámica de la consulta basada en la especialidad médica.
* Cancelación automática de citas por retrasos mayores a 15 minutos (mediante procesos programados).
* Gestión de recursos compartidos (ej. validación de disponibilidad de equipos médicos).

## Instalación y Ejecución

1.  Clona este repositorio en tu máquina local.
2.  Asegúrate de tener una instancia de PostgreSQL ejecutándose.
3.  Crea una base de datos llamada `clinica_db`.
4.  Configura tus credenciales de base de datos en el archivo `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/clinica_db
    spring.datasource.username=TU_USUARIO_AQUI
    spring.datasource.password=TU_PASSWORD_AQUI
    spring.jpa.hibernate.ddl-auto=update
    ```

5.  Ejecuta la aplicación desde la raíz del proyecto usando Maven:

    ```bash
    ./mvnw spring-boot:run
    ```

## Endpoints de la API

La API expone las siguientes operaciones bajo el prefijo `/api/citas`:

* `POST /`: Crea una nueva cita. Requiere payload en formato JSON (`nombrePaciente`, `fechaHora`).
* `GET /`: Obtiene la lista completa de citas agendadas.
* `PUT /{id}`: Modifica los datos de una cita existente.
* `DELETE /{id}`: Cancela y elimina una cita del sistema.

Nota: En caso de violar una regla de negocio del dominio, la API responderá de manera uniforme con un código HTTP 400 (Bad Request) y el mensaje de error correspondiente.