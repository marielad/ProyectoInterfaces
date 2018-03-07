# **DISEÑO DE LA DEMO**

En este alpha en concreto, las distintas "Screens" que usamos comparten diversos elementos del juego, por lo tanto creamos una clase intermedia llamada `Background` que extiende de `Screen`. Las diferentes pantallas ("Screens") heredan de `Background`.

Tras su ejecución, el alpha muestra un `Background` principal `Menu` en la que aparecen seis botones que acceden a distintas pantallas: "One Player", "Two players", "HighScores", "Options", "About" y "Exit".

  ![Menu](https://github.com/marielad/ProyectoInterfaces/blob/master/doc/Menu.PNG)

 - **One Player:** Al pulsar este botón nos posiciona en `SelectCharacter`.
 - **Two Players:** Al pulsar este botón nos posiciona en `SelectCharacterTwo`.
 - **Highscores:** Al pulsar este botón nos posiciona en `HighScore`.
 - **Options:** Al pulsar este botón nos posiciona en `Options`.
 - **About:** Al pulsar este botón nos posiciona en `About`.
 - **Exit:** Al pulsar este botón nos cierra el juego.

**`SelectCharacter:`** permite seleccionar un pajaro e ingresar un nombre para crear un jugador y a continuación elegir entre una de estas dos opciones:

  ![OnePlayer](https://github.com/marielad/ProyectoInterfaces/blob/master/doc/OnePlayer.PNG)

- **Play:** Al pulsar este botón nos posiciona en `Game`.
- **Back:** Al pulsar este botón nos posiciona en `Menu`.

**`Game:`** se inicia el ciclo del juego (`gameLoop`) y comienza la partida.

**`SelectCharacterTwo:`** muestra el mismo contenido que `SelectCharacter`, pero en este caso, seleccionamos un pajaro e ingresamos un nombre para cada jugador (dos jugadores) que jugarán en la misma partida.

  ![TwoPlayer](https://github.com/marielad/ProyectoInterfaces/blob/master/doc/TwoPlayer.PNG)

**`HighScore:`** mediante una consulta a la base de datos del alpha, nos indicará las 10 mejores puntuaciones realizadas. También nos da la siguiente opción:

  ![Score](https://github.com/marielad/ProyectoInterfaces/blob/master/doc/Scores.PNG)

- **Generar informe:** Al pulsar este botón se genera un informe `JasperReports` con los datos de esa consulta.

**`Options:`** permite, mediante un botón, silenciar el sonido del alpha.

  ![Options](https://github.com/marielad/ProyectoInterfaces/blob/master/doc/Options.PNG)

**`About:`** muestra información acerca del alpha.

  ![About](https://github.com/marielad/ProyectoInterfaces/blob/master/doc/About.PNG)

### Controles

 + `SPACE` o `↑`: Salto del  pájaro.
 + `M`: Silencia el sonido del alpha.
 + `ESCAPE`: Ir hacia atrás o pausar el alpha.
