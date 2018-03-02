# **DISEÑO DE LA DEMO**

En esta demo en concreto, las distintas "Screens" que usamos comparten diversos elementos de la demo, por lo tanto creamos una clase intermedia llamada `Background` que extiende de `Screen`. Las diferentes pantallas ("Screens") heredan de `Background`.

Tras su ejecución, la demo muestra un `Background` principal `Menu` en la que aparecen seis botones que acceden a distintas pantallas: "One Player", "Two players", "HighScores", "Options", "About" y "Exit".

 - **One Player:** Al pulsar este botón nos posiciona en `SelectCharacter`.
 - **Two Players:** Al pulsar este botón nos posiciona en `SelectCharacterTwoPlayers`.
 - **Highscores:** Al pulsar este botón nos posiciona en `HighScore`.
 - **Options:** Al pulsar este botón nos posiciona en `Options`.
 - **About:** Al pulsar este botón nos posiciona en `About`.
 - **Exit:** Al pulsar este botón nos cierra la demo.

**`SelectCharacter:`** permite seleccionar un pajaro e ingresar un nombre para crear un jugador y a continuación elegir entre una de estas dos opciones:

- **Play:** Al pulsar este botón nos posiciona en `Game`.
- **Back:** Al pulsar este botón nos posiciona en `Menu`.

**`Game:`** se inicia el ciclo de la demo (`gameLoop`) y comienza la partida.

**`SelectCharacterTwoPlayers:`** muestra el mismo contenido que `SelectCharacter`, pero en este caso, seleccionamos un pajaro e ingresamos un nombre para cada jugador (dos jugadores) que jugarán en la misma partida.

**`HighScore:`** mediante una consulta a la base de datos de la demo, nos indicará las 10 mejores puntuaciones realizadas. También nos da la siguiente opción:

- **Generar informe:** Al pulsar este botón se genera un informe `JasperReports` con los datos de esa consulta.

**`Options:`** permite, mediante un botón, silenciar el sonido de la demo.

**`About:`** muestra información acerca de la demo.

### Capturas

![N|Flappy](https://lh3.googleusercontent.com/-SwWj2EJxtcE/Wpibwy2b1AI/AAAAAAAAAKc/PRPsmwYIIIsB9uVKuQR-F29TT-RF5o-qwCL0BGAs/w530-d-h229-rw/onePlayer.gif)
![N|Flappy](https://lh3.googleusercontent.com/-fHJJcEEoMjs/Wpib_8dtdoI/AAAAAAAAAKs/vXL9QCVLlxEAbbxvnTERPcYy3VtUYV-PgCJoC/w530-h229-rw/options.gif)
![N|Flappy](https://lh3.googleusercontent.com/-AsYZLCqpmv8/WpicS_fdivI/AAAAAAAAAK8/C3hinjIFFEw-ubAg02Xjwh0AImBTwctSQCL0BGAs/w530-d-h229-rw/about.gif)


### Controles

 + `SPACE` o `↑`: Salto del  pájaro.
 + `M`: Silencia el sonido de la demo.
 + `ESCAPE`: Ir hacia atrás o pausar la demo.