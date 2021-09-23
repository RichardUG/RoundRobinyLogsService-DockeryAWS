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

![](/img/image.PNG)

y también podemos revisar que nuestros contenedores esten creados, que son los que se encuentran seleccionados

![](/img/containers.PNG)

## Prueba en localHost

Para acceder a nuestro proyecto desplegado en localhost, consultamos la siguiente URL

[http://localhost:35000](http://localhost:35000)

Que nos mostrara esta pagina

![](/img/localnomensaje.PNG)

Ahora podemos hacer la prueba ingresando dos mensajes

> ### Prueba1
> 
> Primer mensaje
> 
> ![](/img/prueba1localsinresultado.PNG)
> 
> Resultado primer mensaje
> 
> ![](/img/prueba1localconresultado.PNG)
> 
> ### Prueba2
> 
> Segundo mensaje
> 
> ![](/img/prueba2localsinresultado.PNG)
> 
> Resultado segundo mensaje
> 
> ![](/img/prueba2localconresultado.PNG)


## Despliegue en Docker Hub

Ahora iniciaremos el proceso para desplegar nuestras imagenes en ```AWS```, para inciar con esto lo primero que debemos hacer es crear un repositorio en ```Docker hub```, para subir nuestro proyecto en él, en mi caso este sera mi repositorio

* Nombre usuario: richardug
* Nombe repositorio: roundrobinylogservices-dockeryaws
* Link: [https://hub.docker.com/repository/docker/richardug/roundrobinylogservices-dockeryaw](https://hub.docker.com/repository/docker/richardug/roundrobinylogservices-dockeryaws)

Ahora crearemos nuevos tag para cada una de nuestras imagenes con el nombre de la nueva imagen como "Nombre usuario Docker"/"Nombre repositorio" es decir richardug/roundrobinylogservices-dockeryaws y con tag el nombre de cada recurso, que sera los siguientes comandos:

```
docker tag roundrobinylogservice-awsydocker_roundrobin:latest richardug/roundrobinylogservices-dockeryaws:roundrobin
```

```
docker tag roundrobinylogservice-awsydocker_logservice1:latest richardug/roundrobinylogservices-dockeryaws:logservice1
```

```
docker tag roundrobinylogservice-awsydocker_logservice2:latest richardug/roundrobinylogservices-dockeryaws:logservice2
```

```
docker tag roundrobinylogservice-awsydocker_logservice3:latest richardug/roundrobinylogservices-dockeryaws:logservice3
```

```
docker tag mongo:latest richardug/roundrobinylogservices-dockeryaws:mongo
```

Lo cual en consola lo veriamos así

![](/img/newtags.PNG)

Y tras ejecutar los  comandos podemos verificar que se hayan creado las imagenes con el siguiente comando

```
docker images
```

Y nos mostrara el siguiente resultado

![](/img/newtagsImages.PNG)

Ahora empujaremos las imagenes a nuestro repositorio de ```Docker hub```


```
docker push richardug/roundrobinylogservices-dockeryaws:roundrobin
```

```
docker push richardug/roundrobinylogservices-dockeryaws:logservice1
```

```
docker push richardug/roundrobinylogservices-dockeryaws:logservice2
```

```
docker push richardug/roundrobinylogservices-dockeryaws:logservice3
```

```
docker push richardug/roundrobinylogservices-dockeryaws:mongo
```

Podemos visualizar que las imagenes se hayan subido dirigiendonos a nuestro repositorio en ```Docker hub``` y nos debe mostrar algo así

![](/img/imagesDockerhub.PNG)


## Creación maquina en AWS

Antes de desplegar en ```AWS``` debemos tener un entorno en el cual desplegarlo, por los cual crearemos una maquina virtual EC2 con ambiente linux en la consola de ```AWS```; para hacer esto debemos dirigirnos a nuestra consola de ```AWS``` e iniciarla, nos daremos cuenta que estara iniciada al estar la luz verde enecendida al lado de donde dice AWS

![](/img/iniciaraws.PNG)

Tras encenderse la luz damos click en ```AWS``` y nos dirigira a la siguiente pestaña en donde seleccionaremos "Lance una maquina virtuall con EC2"

![](/img/ec2.PNG)

Ahora nos encontraremos en otra ventana que nos solicitara escoger el sistema operativo con el que se va a trabajar, elegiremos "Amazon Linux 2 AMI (HVM), SSD Volume Type" de 64 bits(x86)

![](/img/maquina.PNG)

Tras esto nos enviara a escoger las caracteristicas que queremos que tenga el sistema operativo, seleccionaremos la que tiene la etiqueta verde que dice "Apto para capa gratuita" y oprimimos la opción del boton azul "revisar y lanzar"

![](/img/tipo.PNG)

Después nos muestra las caracteristicas de la maquina y oprimimos en "lanzar"

![](/img/lanzar.PNG)

Y nos desplegara este menú, seleccionamos "Crear nuevo par de claves" de tipo "RSA" y le damos unn nombre, en este caso es "roundrobin" y las descargamos, tras la descarga se habilitara la opción "Lanzar instancias", a la cual le daremos clic

![](/img/claves.PNG)

Ahora seleccionamos nuestra instancia y en el menpu superior de "Acciones" elegimos la opción conectar

![](/img/conectar.PNG)

Adentro elegimos la opción ```ssh``` y copiamos la ruta de conexión en mi caso es esta:

```
ssh -i "roundrobin.pem" ec2-user@ec2-54-157-197-123.compute-1.amazonaws.com
```

Después en nuestro computador creamos una nueva carpeta

![](/img/nuevoec2.PNG)

Dentro de esa carpeta vamos a colocar el archivo de claves que descargamos de la consola de amazon

![](/img/claveec2.PNG)

Y desplegamos una consola en esta ubicación

![](/img/ubicacion.PNG)

En esta ruta escribimos la ruta de conexión ssh que obtuvimos anteriormente, con el cual accderemos a nuestra maquina EC2 desde la consola

```
ssh -i "roundrobin.pem" ec2-user@ec2-54-157-197-123.compute-1.amazonaws.com
```

Ahora pondremos el siguiente comando para actualizar el sistema a la versión mas reciente

```
sudo yum update -y
```

Después instalaremos docker en nuestro ambiente con el siguiente comando 

```
sudo yum install docker
```

Y asignaremos al usuario docker permisos de administrador para que no tengamos que hacer uso del comando sudo cada vez que ejecutemos un comando docker

```
sudo usermod -a -G docker ec2-user
```

Después de esto saldremos y volveremos a entrar para que los cambios se refresquen

```
exit
```

```
ssh -i "roundrobin.pem" ec2-user@ec2-54-157-197-123.compute-1.amazonaws.com
```

Tras volver a ingresar inicializamos el servicio docker

```
sudo service docker start
```

Y al consultar las imagenes nos daremos cuenta que no existe ninguna, por lo cual las debemos desplegar en nuestro ambiente de la maquina EC2

```
docker images
```

## Despliegue en AWS

Ahora que tenemos instalado nuestra maquina podemos desplegar nuestras imagenes docker en el, pero para eso necesitamos crear reglas de entrada para que la maquina pueda leer los puertos que necesitamos; por lo cual iremos a nuestra instancia desde la consola de amazón y nos dirigiremos a la pestaña de "seguridad" y oprimiremos en el link de "grupos de seguridad"

![](/img/seguridad.PNG)

Ahora en reglas de entrada seleccionaremos "edit inbound rules"

![](/img/reglasdeentrada.PNG)

Lo cual nos mostrara esta pestaña en la cual crearemos nuestras reglas oprimiendo en "agregar regla"

![](/img/agregar.PNG)






