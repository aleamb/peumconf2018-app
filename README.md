# peumconf2018-spring-boot-app

Repositorio de la aplicación de ejemplo Spring Boot para la PEUM Conf 2018.

Se trata de una aplicación JEE simple que usé en la [Conferencia PEUM 2018](https://www.programaresunamierda.com/2018/07/peumconf-2018.html) para mostrar el uso de Spring Boot. 
Está desarrollada en Java 8, usando Maven como gestor de proyecto. En la conferencia usé eclipse ccmo IDE Eclipse Oxygen y Sistema Operativo Windows 10

La aplicación es extremadamente simple. Consiste en una sola página Web donde se muestra un listado de incidencias que genera el backend en tiempo real y un botón que activa su resolución.
Esta mecánica tan simple se usa para cubrir ejemplos básicos de:

- Organización de bibliotecas de depedencias para desarrollo de software en Windows 10 usando la herramienta [scoop](https://scoop.sh/)
- Organización de un proyecto [Spring Boot](https://spring.io/projects/spring-boot)
- Uso de un proyecto Spring Boot en [Eclipse](https://www.eclipse.org/)
- Uso de los aspectos básicos de Spring Boot como Configuraciones, Controladores, vistas con [Thymeleaf-](https://www.thymeleaf.org/)
- Uso de STOMP sobre WebSockets.
- Capa de datos mediante JPA y repositorios, usando base de datos [H2](http://www.h2database.com)
- Arquitectura de servicios.
- Controladores REST básicos.

Fuera de tecnologías Spring Boot, el Frontend usa [Vue 2.X](https://vuejs.org/) como framework frontend y [Bulma](https://bulma.io/) como framewrok CSS. El front ha sido generado usando vue-cli, usando ES6 y [Webpack.](https://webpack.js.org/)

Se ha utilizado como plantilla Web [https://github.com/dansup/bulma-templates/blob/master/templates/forum.html](https://github.com/dansup/bulma-templates/blob/master/templates/forum.html)

## Compilación

### Prerrequisitos

1. JDK 8
2. Nodejs 6.X
3. Maven 3.X

### Generación de la aplicación

1. Clonar repositorio

```
git clone https://github.com/aleamb/peumconf2018-app.git
```

2. Generar frontend

- Instalar dependencias

``` 
npm install
```
- Construir frontend

``` 
npm run build
```

3. Compilar Backend

```
mvn package
```

## Ejecución

Una vez compilado y generdos tanto frontend como backend, puede ejecutarse con:

```
java -jar .\target\peumconf2018-spring-boot-app-0.0.1-SNAPSHOT.jar


```

Abrir un navegador web y conectarse a http://localhost:8080

Para parar la aplicación, usar Ctrl-C

## Desarrollo

Existe la posibilidad de cargar el front de forma aparte y poder modificar sus ficheros en caliente. Para ello, arrancar la aplicación y en otra consola ejecutar 

```
npm run dev

```

# Licencia

Esta aplicación se encuentra bajo licencia MIT. 
El autor da permiso para reproducir, redistribuir y modificar este software sin restricciones.

