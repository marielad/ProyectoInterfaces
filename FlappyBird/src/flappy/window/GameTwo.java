package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.sound.Sounds;
import flappy.sprites.Score;
import flappy.sprites.Bird;
import flappy.sprites.Birds;
import flappy.sprites.Explosion;
import flappy.sprites.Tube;
import flappy.sprites.Tubes;
import gamefx.Sound;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 * Clase que se encarga de gestionar la partida para dos jugadores
 * @author Jorge Delgado, Mariela Dorta
 *
 */

public class GameTwo extends Background {

	public static final int POSICIONX_PAJARITO = 200;
	public static final int POSICIONY_PAJARITO = 75;
	public static final int POSICIONZ_PAJARITO = 75;
	public static final int ESPACIO_ENTRE_TUBOS = 200;
	
	private Sound pointSound = Sounds.POINT;
	
	private Tubes tuberias;
	private Bird pajarito, pajaritoTwo;
	private Explosion explosion, explosionTwo;
	private Score puntuacion, puntuacionTwo;
	
	Ellipse shapeAux = new Ellipse();
	Ellipse shapeAuxTwo = new Ellipse();

	private Boolean pausado = false, explode = false, explodeTwo= false;
	private StringProperty puntuacionTexto, puntuacionTextoTwo, nombreTexto, nombreTextoTwo;
	
	@FXML
    private VBox buttonsBox, overBox;
	
	@FXML
	private Label scoreLabel;

    @FXML
    private Button resumeButton, muteGameButton, exitButton, tryAgainButton, exitButton1;

	@FXML
	private Pane paneNubes, paneJuego, panePuntuacion;
	
	@FXML
	private BorderPane panePausa, paneOver;

	public GameTwo() throws IOException {
		super("/flappy/view/GameView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonsBox.setVisible(false);
		overBox.setVisible(false);
		
		tryAgainButton.setOnAction(e -> tryAgainButtonAction(e));
		resumeButton.setOnAction(e -> resumeButtonAction(e));
		muteGameButton.setOnAction(e -> muteGameButtonAction(e));
		exitButton.setOnAction(e -> exitButtonAction(e));
		exitButton1.setOnAction(e -> exitButtonAction(e));
	}
	
	/**
	 * Función que se encarga de disparas distintos eventos cuando seleccionamos
	 * una determinada tecla
	 */
	
	@Override
	protected void onKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ESCAPE)) {
			if (!pausado) {
				pause();
				buttonsBox.setVisible(true);
			}
		}
		if (e.getCode().equals(KeyCode.UP)) {
			if (!pajarito.isPausado()) {
				pajarito.jump();
			}
		}
		if (e.getCode().equals(KeyCode.SPACE)) {
			if (!pajaritoTwo.isPausado()) {
				pajaritoTwo.jump();
			}
		}
		if (e.getCode().equals(KeyCode.M)) {
			if (!gameMusic.isMuted()) {
				gameMusic.mute(true);
			} else {
				gameMusic.mute(false);
			}
		}
	}
	
	/**
	 * Función que al dispararse nos lleva al menu del juego
	 * @param event
	 */
	
	@FXML
    void exitButtonAction(ActionEvent event) {
    	resume();
		stop();
		FlappyApp.goTo(FlappyApp.menu);
		buttonsBox.setVisible(false);
		overBox.setVisible(false);
    }
	
	/**
	 * Función que al dispararse mutea o desmutea la música del juego
	 * @param event
	 */

	@FXML
    void muteGameButtonAction(ActionEvent event) {
		if (!menuMusic.isMuted() || !gameMusic.isMuted()) {
			gameMusic.mute(true);
		}else {
			gameMusic.mute(false);
		}
    }
	
	/**
	 * Función que al dispararse vuelve a cargar una nueva partida
	 * con los avatares y nombres escogidos
	 * @param event
	 */
	
	@FXML
    void tryAgainButtonAction(ActionEvent event) {
		panePausa.setDisable(false);
		overBox.setVisible(false);
		explode = false;
		explodeTwo = false;
		resume();
		stop();
		start();
    }
	
	/**
	 * Función que al dispararse reanuda el juego
	 * @param event
	 */

	@FXML
    void resumeButtonAction(ActionEvent event) {
		resume();
		buttonsBox.setVisible(false);
    }
	
	/**
	 * En start() cogemos los pajaros seleccionados y los posicionamos,
	 * agregamos los Sprites puntuacion al Pane, creamos las 6 tuberías 
	 * iniciales y comenzamos las animaciones
	 */
	
	@Override
	public void start() {
		super.start();
		paneNubes.getChildren().add(nubes);
		pajarito = SelectCharacterTwo.pajarito;
		pajarito.setTranslateX(100);
		pajarito.setTranslateY(280);
		
		pajaritoTwo = SelectCharacterTwo.pajaritoArriba;
		pajaritoTwo.setTranslateX(100);
		pajaritoTwo.setTranslateY(220);
		
		puntuacion = new Score();
		puntuacion.setTranslateX(873);
		puntuacion.setTranslateY(5);
		nombreTexto = SelectCharacterTwo.nombre;		
		puntuacionTexto = new SimpleStringProperty(this, "puntuacionTexto", "SCORE:");
		panePuntuacion.getChildren().add(puntuacion);
		puntuacion.getPuntuacion().textProperty().bind(puntuacionTexto.concat(pajarito.getScore().asString()));
		
		puntuacionTwo = new Score();
		puntuacionTwo.setTranslateX(5);
		puntuacionTwo.setTranslateY(5);
		nombreTextoTwo = SelectCharacterTwo.nombreArriba;		
		puntuacionTextoTwo = new SimpleStringProperty(this, "puntuacionTexto2", "SCORE:");
		panePuntuacion.getChildren().add(puntuacionTwo);
		puntuacionTwo.getPuntuacion().textProperty().bind(puntuacionTextoTwo.concat(pajaritoTwo.getScoreTwo().asString()));
		
		shapeAux = new Ellipse();
		shapeAuxTwo = new Ellipse();
		
		tuberias = new Tubes(FlappyApp.ANCHO, FlappyApp.ALTO, 7);
		paneJuego.getChildren().addAll(tuberias, pajarito, pajaritoTwo);
		
		tuberias.play();
		gameMusic.playIndefinite();
		pajarito.start();
		pajaritoTwo.start();
	}
	
	/**
	 * En stop() paramos todos los recursos añadidos al Game()
	 * como la música, los pajaros, las puntuaciones...
	 */
	
	@Override
	public void stop() {
		super.stop();
		pajarito.setScore(0);
		pajaritoTwo.setScoreTwo(0);
		gameMusic.stop();
		pajarito.stop();
		pajarito.rotateProperty().set(0);
		pajaritoTwo.stop();
		pajaritoTwo.rotateProperty().set(0);
		tuberias.stop();
		paneJuego.getChildren().remove(tuberias);
		paneJuego.getChildren().remove(pajarito);
		paneJuego.getChildren().remove(pajaritoTwo);
		paneJuego.getChildren().remove(explosion);
		paneJuego.getChildren().remove(explosionTwo);
		paneNubes.getChildren().remove(nubes);
		panePuntuacion.getChildren().remove(puntuacion);
		panePuntuacion.getChildren().remove(puntuacionTwo);
		pausado = false;
	}
	
	/**
	 * pause() pausa el juego
	 */
	
	private void pause() {
		super.stop();
		gameMusic.pause();
		tuberias.pause();
		pajarito.pause();
		pajaritoTwo.pause();
		nubes.pause();
		pausado = true;
	}
	
	/**
	 * resume() lo reanuda
	 */
	
	private void resume() {
		super.start();
		gameMusic.resume();
		tuberias.play();
		pajarito.resume();
		pajaritoTwo.resume();
		nubes.resume();
		pausado = false;
	}
	
	/**
	 * En esta función se borran y generan tuberías, y se llama
	 * a checkCollisions() indefinidamente
	 */

	protected void loop(long now) {
		if (!tuberias.getChildren().isEmpty()) {

			if (tuberias.getChildren().get(0).getTranslateX() <= -getWidth() - ESPACIO_ENTRE_TUBOS - 75) {
				Tube tuberia;

				if (Math.random() < 0.25) {
					tuberia = new Tube(getWidth(), getHeight(),false, true);
				} else if (Math.random() > 0.75) {
					if (Math.random() > 0.5) {
						tuberia = new Tube(getWidth(), getHeight(),true, false);
					} else {
						tuberia = new Tube(getWidth(), getHeight(),true, true);
					}
				} else {
					tuberia = new Tube(getWidth(), getHeight(),false, false);
				}
				tuberia.setTranslateX(tuberias.getChildren().get(tuberias.getChildren().size() - 1).getTranslateX() + (ESPACIO_ENTRE_TUBOS));
				tuberia.setTranslateZ(POSICIONZ_PAJARITO);
				
				tuberias.getChildren().add(tuberia);
				tuberias.getChildren().remove(0);
			}
			
			for (Node tuberia : tuberias.getChildren()) {
				tuberia.setTranslateX(tuberia.getTranslateX() - 3);
			}
			
		}
		checkCollisions();
	}
	
	/**
	 * Función que comprueba si hemos chocado o no, y si hemos obtenido un punto.
	 * En dos jugadores también se encarga de reproducir la explosión al perder
	 * unos de los dos jugadores
	 */

	public void checkCollisions() {
		for (Node node : tuberias.getChildren()) {
			if (node instanceof Tube) { 
				Tube tuberia = (Tube) node;
				Shape intersectionTube = Shape.intersect(tuberia.getShape(), pajarito.getShape());
				Shape intersectionTubeTwo = Shape.intersect(tuberia.getShape(), pajaritoTwo.getShape());
				
				if(shapeAux != null) {
					shapeAux.centerXProperty().bind(pajarito.translateXProperty().add(pajarito.widthProperty().divide(2)));
			        shapeAux.centerYProperty().bind(pajarito.translateYProperty().add(pajarito.heightProperty().divide(2)));
			        shapeAux.rotateProperty().bind(pajarito.rotateProperty());
			        shapeAux.setRadiusX(1);
			        shapeAux.setRadiusY(1);
			        shapeAux.setVisible(false);
					Shape intersectionScore = Shape.intersect(tuberia.getMiddleShape(), shapeAux);
			        if (intersectionScore.getBoundsInLocal().getWidth() != -1) {
			        	pointSound.stop();
			        	this.pajarito.setScore(this.pajarito.getScore().get()+1);
			        	pointSound.play();
			        }
				}
		        
				if(shapeAuxTwo != null) {
					shapeAuxTwo.centerXProperty().bind(pajaritoTwo.translateXProperty().add(pajaritoTwo.widthProperty().divide(2)));
					shapeAuxTwo.centerYProperty().bind(pajaritoTwo.translateYProperty().add(pajaritoTwo.heightProperty().divide(2)));
					shapeAuxTwo.rotateProperty().bind(pajaritoTwo.rotateProperty());
					shapeAuxTwo.setRadiusX(1);
					shapeAuxTwo.setRadiusY(1);
					shapeAuxTwo.setVisible(false);
			        Shape intersectionScoreTwo = Shape.intersect(tuberia.getMiddleShape(), shapeAuxTwo);
			        if (intersectionScoreTwo.getBoundsInLocal().getWidth() != -1) {
			        	pointSound.stop();
			        	this.pajaritoTwo.setScoreTwo(this.pajaritoTwo.getScoreTwo().get()+1);
			        	pointSound.play();
			        }
				}
				if(pajarito!=Birds.FRANBIRD) {
					if (pajarito.getTranslateY() >= getHeight() || pajarito.getTranslateY() <= 0 || intersectionTube.getBoundsInLocal().getWidth() != -1) {
						if (!pausado) {
							if (!explode) {
								explosion = new Explosion();
								explosion.setTranslateX(pajarito.getTranslateX()-32);
								explosion.setTranslateY(pajarito.getTranslateY()-32);
								
								paneJuego.getChildren().add(explosion);
								paneJuego.getChildren().remove(pajarito);
								explosion.explode();
								explode = true;
							}
	
							pajarito.stop();
							shapeAux = null;
						}
					}
				}
				if(pajaritoTwo!=Birds.FRANBIRD2) {
					if (pajaritoTwo.getTranslateY() >= getHeight() || pajaritoTwo.getTranslateY() <= 0 || intersectionTubeTwo.getBoundsInLocal().getWidth() != -1) {
						if (!pausado) {
							if (!explodeTwo) {
								explosionTwo = new Explosion();
								explosionTwo.setTranslateX(pajaritoTwo.getTranslateX()-32);
								explosionTwo.setTranslateY(pajaritoTwo.getTranslateY()-32);
								
								paneJuego.getChildren().add(explosionTwo);
								paneJuego.getChildren().remove(pajaritoTwo);
								explosionTwo.explode();
								explodeTwo = true;
							}
							
							pajaritoTwo.stop();
							shapeAuxTwo = null;
						}
					}
				}
				if (pajarito.isPausado() && pajaritoTwo.isPausado()) {
					if (!pausado) {
						pause();
						gameOver();
					}
				}
			}
		}
	}
	
	/**
	 * En caso de que anteriormete hayamos chocado, se desencadena gameOver()
	 * que nos mostrará un menu de reintento con nuestros nombres
	 * y puntuaciones, e inserta estos datos en la base de datos.
	 */
	
	private void gameOver() {
		scoreLabel.textProperty().bind(nombreTexto.concat(" ").concat(puntuacionTexto.concat(pajarito.getScore().asString()).concat("  |  ")
				.concat(nombreTextoTwo.concat(" ").concat(puntuacionTextoTwo.concat(pajaritoTwo.getScoreTwo().asString()
				)))));
		
		panePausa.setDisable(true);
		overBox.setVisible(true);
		
		FlappyApp.baseDatos.insertarTabla(nombreTexto.get(), pajarito.getScore().get());
		FlappyApp.baseDatos.insertarTabla(nombreTextoTwo.get(), pajaritoTwo.getScoreTwo().get());

	}
	
}