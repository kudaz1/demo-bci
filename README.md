# Ejercicio de Java

_Desarrolle una aplicación que exponga una API RESTful de creación de usuarios._

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

### Pre-requisitos 📋

_Que cosas necesitas para instalar el software y como instalarlas_
```
● Banco de datos en memoria. Ejemplo: HSQLDB o H2. (En este caso se uso H2)
● Proceso de build vía Gradle o Maven. (En este caso se uso Maven)
● Persistencia con JPA. Ejemplo: EclipseLink, Hibernate u OpenJPA. (En este caso se uso Hibernate)
● Framework SpringBoot.
● Java 8+ (En este caso se uso java 17)

```

### Instalación 🔧

_Para desarrollar esta aplicación, se pueden seguir los siguientes pasos:_

_Paso 1_

```
Crear un nuevo proyecto en Spring Boot usando Maven. Puede usar el sitio web oficial de Spring Boot para generar un proyecto con las dependencias necesarias.
```

_Paso 2_

```
Agregar la dependencia de JPA y H2 a su archivo pom.xml para que Maven las descargue. Como tambien estamos dandole seguridad a la API y estamos generando un token, debemos agregar las siguientes dependenncias.

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
</dependency>
```

_Paso 3_

```
Configurar la conexión de la base de datos H2. Spring Boot tiene soporte incorporado para la base de datos H2, lo que significa que solo necesita agregar algunas líneas de configuración en el archivo application.properties.
```
_Paso 4_

```
Crear una entidad de usuario en Java que represente a un usuario en la base de datos. Esto se hace usando la anotación @Entity de JPA.
```

_Paso 5_

```
Crear una interfaz de repositorio de usuario que extienda JpaRepository. Esto proporcionará los métodos CRUD necesarios para interactuar con la base de datos.
```

_Paso 6_

```
Crear un controlador REST que maneje las solicitudes HTTP de los clientes. Este controlador debe tener métodos para crear un nuevo usuario, obtener una lista de todos los usuarios y obtener un usuario por su ID
```

_Paso 7_

```
Probar la API utilizando una herramienta de prueba de API como Postman o Insomnia.
```
_NOTAS_

```
Al usar como banco de memoria H2, no existe un script de creacion de la Base de Datos sino que esta se crea automaticamente al momento de levantar el servidor tomcat en donde va a correr nuestra aplicacion, y automaticamente genera los scripts de las tablas.
```

## Ejecutando las pruebas ⚙️

### Colección de Postman ⌨️

* [Api Creacion Ussuario para BCI](https://raw.githubusercontent.com/kudaz1/demo-bci/master/Demo-BCI.postman_collection.json) - Colección de Postman para probar la API.

### Swagger ⌨️

* [Demo-BCI](https://app.swaggerhub.com/apis/CARLOSCCABELLOFLORES/demo-bci/1.0.0) - Documentación en OpenAPI.

_Para desarrollar esta aplicación, se pueden seguir los siguientes pasos:_

### Crear Usuario 🔩

_Paso 1_

```
Abra Postman y cree una nueva solicitud haciendo clic en el botón "Nuevo" en la esquina superior izquierda.
```

_Paso 2_

```
Seleccione el método HTTP que desea probar (por ejemplo, POST para crear un usuario).
```

_Paso 3_

```
Ingrese la URL de la API RESTful ("http://localhost:8080/api/v1/user/create")
```

_Paso 4_

```
Seleccione la pestaña "Body" y elija "raw" como tipo de cuerpo. (En este caso el tipo es unn JSON)
```

_Paso 5_

```

Escriba los datos del usuario en formato JSON. Por ejemplo:

{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}

```

_Paso 6_

```
Haga clic en el botón "Send" para enviar la solicitud al servidor.
```

_Paso 7_

```
Verifique la respuesta del servidor en la sección "Response" de Postman. Si todo está bien, debería recibir un código de estado HTTP 200 (OK) y los datos del usuario creado.

Ejemplo de mensaje de Respuesta del servicio

{
    "message": "User Created.",
    "name": "carlos",
    "email": "carlos@falabella.cl",
    "password": "$2a$10$rZ8mtAOirwz.RUuut0dRkOJTJr2TmhNNGY8DUc6SzsP4Z8AiIM9ee",
    "phones": [
        {
            "id": 0,
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ],
    "created": "2023-05-14T21:15:55.650+00:00",
    "modified": null,
    "last_login": "2023-05-14T21:15:55.650+00:00",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJsb3NAZmFsYWJlbGxhLmNsIiwiaWF0IjoxNjg0MDk4OTU1LCJleHAiOjE2ODQxMDAzOTV9.7s_uT9_guLEPjkek_Xw3U_BdORG7fOPDf9irx4nzILI",
    "isactive": true,
    "id": "74d0ef4e-d889-4d6b-ae36-7c9b933c8628"
}

```

### Actualizar Usuario ⌨️

_Paso 1_

```
Abra Postman y cree una nueva solicitud haciendo clic en el botón "Nuevo" en la esquina superior izquierda.
```

_Paso 2_

```
Seleccione el método HTTP que desea probar (por ejemplo, PUT para actualizar un usuario).
```

_Paso 3_

```
Ingrese la URL de la API RESTful ("http://localhost:8080/api/v1/userSession/{userID}}/update") ("Donde userId es igual al id del usuario que previamente fue creado o que este en el sistema")

```

_Paso 3_

```
En la pestaña de Authorization, debemos seleccionar la opcion de Bearer Token y colocar en el 
recuadro el token el JWT token que te devolvio la API al momento de crear el usuario, 
con esto se valida que el usuario que quiera actualizar sus datos, 
solo pueda actualizar sus propios datos y no los de otro usuario (usuario de la session).

```

_Paso 4_

```

Escriba los datos del usuario en formato JSON. Por ejemplo:

Aca mandamos los campos que deseamos actualizar.

{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}

```

_Paso 5_

```
Haga clic en el botón "Send" para enviar la solicitud al servidor.
```

_Paso 6_

```
Verifique la respuesta del servidor en la sección "Response" de Postman. Si todo está bien, debería recibir un código de estado HTTP 200 (OK) y los datos del usuario creado.

Ejemplo de mensaje de Respuesta del servicio

{
    "message": "Updated User",
    "name": "Juan Rodriguez3",
    "email": "carlos@falabella.cl",
    "password": "$2a$10$7tCz.FG4yWLMJMVTi/ildewmRB6VjtUxk6Ohy.B.pRy8lXxh1bwiG",
    "phones": [
        {
            "id": 0,
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ],
    "created": "2023-05-14T21:15:55.650+00:00",
    "modified": "2023-05-14T21:17:32.784+00:00",
    "last_login": "2023-05-14T21:17:32.784+00:00",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJsb3NAZmFsYWJlbGxhLmNsIiwiaWF0IjoxNjg0MDk5MDUyLCJleHAiOjE2ODQxMDA0OTJ9.DR2ZJoDHizV81xWYswQfUHV1xnvhzbm-x6yhbPlip2U",
    "isactive": true,
    "id": "74d0ef4e-d889-4d6b-ae36-7c9b933c8628"
}

```

### Colección de Postman ⌨️

* [Api Creacion Ussuario para BCI](https://raw.githubusercontent.com/kudaz1/demo-bci/master/Demo-BCI.postman_collection.json) - Colección de Postman para probar la API.

## Diagrama de Solución 🚀

![Diagrama de Solución](./DiagramaSolucion.jpg)

## Construido con 🛠️

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Spring Initializr](https://start.spring.io/) - Creador de proyectos sprintboot con maven en el lenguaje de java.
* [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/#section=windows) - Entorno de desarrollo integrado para el desarrollo de programas informáticos.
* [Postman API Platform](https://www.jetbrains.com/idea/download/#section=windows) - Plataforma API para que los desarrolladores diseñen, construyan, prueben e iteren sus API.
* [GitHub](https://github.com/) - Es una forja para alojar proyectos utilizando el sistema de control de versiones.

## Versionado 📌

Usamos [GitHub](https://github.com/) para el versionado. Para todas las versiones disponibles, mira los [demo-bci](https://github.com/kudaz1/demo-bci).

## Autores ✒️

_Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios_

* **Carlos Cabello** - *Desarrollador* - [ccabello]([https://github.com/villanuevand](https://github.com/kudaz1))

---
⌨️ con ❤️ por [ccabello](https://github.com/kudaz1) 😊
