# **Análisis del Alpha**

El objetivo del alpha, consistirá en ir volando verticalmente en la pantalla con el pájaro, pasando entre los tubos, sin colisionar con ellos. Cada vez que el pájaro pase por el medio de dos tubos, el marcador de puntuaciones sumará un punto. Si chocas contra un tubo, automáticamente pierdes.

A continuación se mostrará una lista con los componentes del alpha:

 - **Pájaro:** Es el personaje del jugador que lleva como avatar un pájaro que aletea constantemente; Mediante la tecla `SPACE` salta verticalmente y por defecto tiende a caer, y cada vez que salta o cae, su pico rota para crear el efecto de subir o bajar. Con estos saltos se puede esquivar las diferentes tuberías con las que nos encontramos.
 - **Tubo:** Elemento del alpha que se mueve horizontalmente de derecha a izquierda y se generá constantemente en el `Screen` a modo de obstáculo que debemos de superar, en este caso, pasando por el hueco que queda entre este tubo. Los tubos pueden rotarse y moverse verticalmente añadiendo cierta difcultad extra, en cierto modo, podríamos decir que es el enemigo al que debemos hacer frente.
 - **Nube:** Están presentes durante todo el alpha y forman parte del `Background` que comparten todas las pantallas del juego. Las nubes, posicionadas aletoriamente, se mueven horizontalmente de derecha a izquierda a diferentes velocidades. Por lo tanto, se trata de un componente meramente estético.
 - **Puntuación:** Marcador del progreso actual de la partida que cada vez que el pájaro pasa entre medio de los tubos, éste incrementa su valor en uno. Puede encontrarse en alguna de las equinas superiores de la pantalla del juego.
 - **Explosión:** Cuando el pájaro colisiona contra un tubo aparéce esta animación para dar feedback al usuario.


