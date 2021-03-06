# **FlappyBird Alpha**

![GIF](https://media.giphy.com/media/69xvneEpKGyx4L1Du2/giphy.gif)

### Introducción

Este proyecto consiste en el diseño y estructura de un motor de videojuego en dos dimensiones. Este mismo contiene un alpha, en la que el jugador controla a un pájaro que vuela por la pantalla, y que debe pasar por el espacio que queda libre entre las tuberías, sin tocarse con estas, ya que tocarlas conllevaría el fin de la partida, y cuantas más tuberías consigas pasar, más puntos obtendrás. La pantalla da la sensación de desplazamiento lateral.

### Características

 - Capacidad para colocar imágenes en la pantalla.
 - Componente para animar las imágenes del juego.
 - Capacidad para reproducir sonidos.
 - Escrito básicamente en Java ([JavaFX][javafx], [.CSS][css], [.FXML][fxml]).
 - Proyecto Maven dos dependencias:
    - Generación de informes sobre el alpha mediante [JasperReports][jasper].
    - Conexión a base de datos embebida ([HSQLDB][hsql]).
 - Software completamente gratis.


### Cómo funciona

En primer lugar, la clase `Screen` se encarga de generar y animar a 60 imágenes por segundo las diferentes pantallas de nuestra demo.

Seguidamente, la clase `Sprite` asigna las propiedades de una imagen en concreto, dejándola preparada para su posterior uso en una pantalla determinada.

Por último, la clase `Sound` permite reproducir sonidos en las pantallas.

### Análisis y Diseño

 - [Análisis del motor de videojuego en dos dimensiones][ana]
 - [Diseño de la demo del proyecto][dis]
 - [Análisis de la demo][anaDemo]


### Desarrolladores

 - [Mariela Dorta Díaz][mariela]
 - [Jorge Delgado Díaz][jorge]
 - [Daniel Paredes Sánchez][dani]


[mariela]: <https://github.com/marielad>
[jorge]: <https://github.com/JorDelDiaz>
[dani]: <https://github.com/Daniwalls97>
[hsql]: <http://hsqldb.org>
[fxml]: <https://docs.oracle.com/javafx/2/fxml_get_started/why_use_fxml.htm#CHDCHIBE>
[css]: <https://www.w3.org/Style/CSS/>
[javafx]: <https://docs.oracle.com/javase/8/javase-clienttechnologies.htm>
[jasper]: <https://www.jaspersoft.com>
[ana]: <https://github.com/marielad/ProyectoInterfaces/blob/master/doc/Analisis.md>
[dis]: <https://github.com/marielad/ProyectoInterfaces/blob/master/doc/Diseño.md>
[anaDemo]: <https://github.com/marielad/ProyectoInterfaces/blob/master/doc/AnalisisDemo.md>

