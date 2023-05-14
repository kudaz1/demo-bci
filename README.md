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
Agregar la dependencia de JPA y H2 a su archivo pom.xml para que Maven las descargue.
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

_Explica como ejecutar las pruebas automatizadas para este sistema_

### Analice las pruebas end-to-end 🔩

_Explica que verifican estas pruebas y por qué_

```
Da un ejemplo
```

### Y las pruebas de estilo de codificación ⌨️

_Explica que verifican estas pruebas y por qué_

```
Da un ejemplo
```

## Despliegue 📦

_Agrega notas adicionales sobre como hacer deploy_

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
