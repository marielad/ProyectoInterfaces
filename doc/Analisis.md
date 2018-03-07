# **ANÁLISIS DEL MOTOR**

Empezamos el análisis explicando como funcionan las clases principales del motor:

 - `Screen` : Clase que hereda de `StackPane` por lo que permite superponer elementos de diferentes paneles. También contiene un objeto de tipo `AnimationTimer`, con el que iniciar, pausar y parar las animaciones mostradas en pantalla a 60FPS. Además, esta clase carga los diferentes .FXML del juego; Y está pendiente de determinadas pulsaciones del teclado con las que realizar distintas acciones en el juego.

 - `Sprite`: Clase que hereda de `ImageView` a la que se le asigna una imagen mediante una URL y unos parámetros con el número de imágenes y columnas que contiene esta misma. Estos "Sprites" se colocarán directamente en los "Panes" de las distintas "Screens" de nuestro juego. Las clases que hereden de `Sprite` se animaran con objetos propios de esa clase de tipo `TranslateTransition`.

 - `Sound`: Clase que contiene un objeto de tipo `MediaPlayer` y distintas funciones que permiten reproducir, pausar, parar y silenciar un archivo de audio.

   ​

   ![FlappyMotorUML](http://i67.tinypic.com/14ig96f.png)