# RoundRobinyLogsService-DockeryAWS

## Descripción

En este taller trabajaremos el depliegue de instancias de codigo ```java``` como 5 imagenes que estaran distribuidoras en contenedores ```docker``` entre estas 5 imagenes la primera es  ```RoundRobin``` el cual es un balanceador de cargas que se encuentra en el puerto ```35000```  y que distribuye las solicitudes en tres intancias de ```LogService``` que se llaman ```LogService1``` qeu se encuentra en el puerto ```35001```, ```LogService2``` que se encuentra en el puerto ```35002``` y ```LogService3``` que se encuentra en el puerto ```35003``` y estos se comunican una imagen que se instancio de mongoDB el cual contendra la instancia para poder hacer uso de nuestra base de datos; después todos estos contenedores se alojaran en un repositorio de ```Docker``` para que almacene todas las imagenes y posteriormente este contenedor se despliega en una maquina virtual creada en ```ÀWS```; cada petición realizada a ```RoundRobin``` es un mensaje que se envia a las diferentes instancias de ```LogService``` una petición para guardar en la base de datos ```MongoDB``` y después envia otra para obtener los ultimos 10 mensajes registrados; cada petición se envia a una instancia de ```LogService``` diferente

## Prerrequisitos

Para  elaborar este proyecto requeimos las siguientes tecnologias:
* [Maven](https://es.wikipedia.org/wiki/Maven): Herramienta la cual permite realizar la construción de proyectos, realizarles pruebas y otras funciones.
* [Git](https://es.wikipedia.org/wiki/Git): Software de control de versionamiento centralizado.
* [Docker](https://es.wikipedia.org/wiki/Docker_(software)): Es un proyecto de código abierto que automatiza el despliegue de aplicaciones dentro de contenedores de software, proporcionando una capa adicional de abstracción y automatización de virtualización de aplicaciones en múltiples sistemas operativos
 
También necesitamos tener instalado y habilitado el protocolo SSH en nuestro computador 
* [SSH](https://es.wikipedia.org/wiki/Secure_Shell): Es el nombre de un protocolo y del programa que lo implementa cuya principal función es el acceso remoto a un servidor por medio de un canal seguro en el que toda la información está cifrada

Para asegurar que el usuario cumple con todos los prerrequisitos para poder ejecutar el programa, es necesario disponer de un Shell o Símbolo del Sistema para ejecutar los siguientes comandos para comprobar que todos los programas están instalados correctamente, para así compilar y ejecutar tanto las pruebas como el programa correctamente.

```
docker --version
mvn --version
git --version
java --version
```

## Instalación

Para realizar la instalcaión de nuestro programa debemos ir a la linea de comandos de nuestro sistema operativo y hacer uso del siguiente comando
```
git clone https://github.com/RichardUG/RoundRobinyLogsService-DockeryAWS.git
```

## Ejecución

Para realizar la ejecución del programa debemos seguir los siguientes pasos:

```
cd RoundRobin
```

```
mvn clean install
```

```
mvn package
```

```
cd ..
```

```
cd LogService
```

```
mvn clean install
```

```
mvn package
```

```
cd ..
```

```
mvn clean install
```

```
mvn package
```

## Compilación y pruebas

Para compilar y verificar que nuestro proyecto se encuentre bien construido debemos seguir los siguientes pasos:

```
cd RoundRobin
```

```
mvn compile
```

```
mvn test
```

```
cd ..
```

```
cd LogService
```

```
mvn compile
```

```
mvn test
```

```
cd ..
```

```
mvn compile
```

```
mvn test
```

## Despliegue en locahost

Lo primero que debemos hacer tras construir nuestro proyecto mvn en nuestro computador es desplegar nuestras imagenes y contenedores, para lo cual en nuestra linea de comandos nos ubicaremos en la carpeta raiz de nuestro proyecto y haremos uso del siguiente comando:

```
docker-compose up -d
```

El cual al finalizar de hacer la construcción de las imagenes y los contenedores nos mostrara en consola el siguiente resultado

![](/img/build.PNG)

En consola podemos revisar que nuestras imagenes se hayan creado con el siguiente comando 

```
docker images
```

Que nos mostrara el siguiente resultado

![](/img/imagesConsole.PNG)

En consola también podemos revisar que nuestros contenedores se hayan creado con el siguiente comando 

```
docker ps
```

Que nos mostrara el siguiente resultado

![](/img/containersConsole.PNG)


Y si tenemos vista grafica de nuestro docker, en mi caso ```portainer``` también podemos ir a revisar si se encuentran creadas nuestras imagenes, que en este caso son las que se encuentran seleccionadas:

![](/img/images.PNG)

y también podemos revisar que nuestros contenedores esten creados, que son los que se encuentran seleccionados

![](/img/containers.PNG)

## Prueba en localHost

Para acceder a nuestro proyecto desplegado en localhost, consultamos la siguiente URL

<a href="https://github.com/RichardUG/RoundRobinyLogsService-DockeryAWS" target="_blank">example class="hljs-tag"></a>

