# **DISEÑO DE LA DEMO**

En este alpha en concreto, las distintas "Screens" que usamos comparten diversos elementos del juego, por lo tanto creamos una clase intermedia llamada `Background` que extiende de `Screen`. Las diferentes pantallas ("Screens") heredan de `Background`.

Tras su ejecución, el alpha muestra un `Background` principal `Menu` en la que aparecen seis botones que acceden a distintas pantallas: "One Player", "Two players", "HighScores", "Options", "About" y "Exit".

![N|menu](https://lh3.googleusercontent.com/-EBwbP-NX-gI/Wp8PVqZFBOI/AAAAAAAAAO0/bFoh3vEQB_wIYBvZlM6kEiiAhMP-eaFYwCL0BGAs/w530-d-h229-n-rw/menu.PNG)

 - **One Player:** Al pulsar este botón nos posiciona en `SelectCharacter`.
 - **Two Players:** Al pulsar este botón nos posiciona en `SelectCharacterTwoPlayers`.
 - **Highscores:** Al pulsar este botón nos posiciona en `HighScore`.
 - **Options:** Al pulsar este botón nos posiciona en `Options`.
 - **About:** Al pulsar este botón nos posiciona en `About`.
 - **Exit:** Al pulsar este botón nos cierra el juego.

**`SelectCharacter:`** permite seleccionar un pajaro e ingresar un nombre para crear un jugador y a continuación elegir entre una de estas dos opciones:

![N|selectone](https://lh3.googleusercontent.com/-n48IMwsH87o/Wp8QP7ZskYI/AAAAAAAAAQc/M5TbYiONgn84Zz1_c0AchlZ1C1ZRK2bwwCL0BGAs/w530-d-h229-n-rw/select.PNG)

- **Play:** Al pulsar este botón nos posiciona en `Game`.
- **Back:** Al pulsar este botón nos posiciona en `Menu`.

**`Game:`** se inicia el ciclo de la demo (`gameLoop`) y comienza la partida.

![N|game](https://lh3.googleusercontent.com/-MXCcI3ZBZ-U/Wp8QGtCbxZI/AAAAAAAAAQI/vfhvY7Ln-ukSw7DIc0D-adj0t6xRpmUJgCL0BGAs/w530-d-h228-n-rw/game.png)

**`SelectCharacterTwoPlayers:`** muestra el mismo contenido que `SelectCharacter`, pero en este caso, seleccionamos un pajaro e ingresamos un nombre para cada jugador (dos jugadores) que jugarán en la misma partida.

![N|selecttwo](https://lh3.googleusercontent.com/-7pa6t5JVDu4/Wp8QZtZi1gI/AAAAAAAAAQ0/etKdZq2Mz_ouXqkgz45j0iL4nLoh3AwcACL0BGAs/w530-d-h228-n-rw/select2.PNG)

**`HighScore:`** mediante una consulta a la base de datos del alpha, nos indicará las 10 mejores puntuaciones realizadas. También nos da la siguiente opción:

![N|high](https://lh3.googleusercontent.com/-qli4aROEKgI/Wp8P9pMW3tI/AAAAAAAAAPw/Wn3ZhIutGcwHagThnRyB8stamppgrS9aACL0BGAs/w530-d-h228-n-rw/high.PNG)

- **Generar informe:** Al pulsar este botón se genera un informe `JasperReports` con los datos de esa consulta.

**`Options:`** permite, mediante un botón, silenciar el sonido del alpha.

![N|options](https://lh3.googleusercontent.com/-xRW5oQZkaqE/Wp8PuB1OVMI/AAAAAAAAAPc/urfyv6Nvp60msVIkzW7IAXbbrzelMB2WgCL0BGAs/w530-d-h228-n-rw/opciones.PNG)

**`About:`** muestra información acerca del alpha.

![N|about](https://lh3.googleusercontent.com/-ogdJPVjPrBI/Wp8PkzzXnLI/AAAAAAAAAPI/q1OMAQREud8aKNjf3rC3VX_leSfb5y-CACL0BGAs/w530-d-h228-n-rw/about.PNG)


### Controles

 + `SPACE` o `↑`: Salto del  pájaro.
 + `M`: Silencia el sonido del alpha.
 + `ESCAPE`: Ir hacia atrás o pausar el alpha.
