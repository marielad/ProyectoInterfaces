package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.sound.Sounds;
import flappy.sprites.Score;
import flappy.sprites.Bird;
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
 * 
 * @author Jorge Delgado, Mariela Dorta, Fran Vargas
 *
 */

public class Game extends Background {

	public static final int POSICIONX_PAJARITO = 200;
	public static final int POSICIONY_PAJARITO = 75;
	public static final int POSICIONZ_PAJARITO = 75;
	public static final int ESPACIO_ENTRE_TUBOS = 200;

	private Sound pointSound = Sounds.POINT;
	
	private Bird pajarito;
	private Explosion explosion;
	private Tubes tuberias;
	private Score puntuacion;
	private Ellipse shapeAux = new Ellipse();
	
	private Boolean pausado = false;
	private StringProperty puntuacionTexto, nombreTexto;
	
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

	public Game() throws IOException {
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
	
	@Override
	protected void onKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ESCAPE)) {
			if (!pausado) {
				pause();
				buttonsBox.setVisible(true);
			}
		}
		if (e.getCode().equals(KeyCode.SPACE) || e.getCode().equals(KeyCode.UP)) {
			if (!pajarito.isPausado()) {
				pajarito.jump();
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
	
	@FXML
    void exitButtonAction(ActionEvent event) {
    	resume();
		stop();
		FlappyApp.goTo(FlappyApp.menu);
		buttonsBox.setVisible(false);
		overBox.setVisible(false);
    }

	@FXML
    void muteGameButtonAction(ActionEvent event) {
		if (!gameMusic.isMuted()) {
			gameMusic.mute(true);
		}else {
			gameMusic.mute(false);
		}
    }
	
	@FXML
    void tryAgainButtonAction(ActionEvent event) {
		panePausa.setDisable(false);
		overBox.setVisible(false);
		resume();
		stop();
		start();
    }

	@FXML
    void resumeButtonAction(ActionEvent event) {
		resume();
		buttonsBox.setVisible(false);
    }
	
	@Override
	public void start() {
		super.start();
		paneNubes.getChildren().add(nubes);
		
		pajarito = SelectCharacter.pajarito;
		pajarito.setTranslateX(100);
		pajarito.setTranslateY(200);  
		
		puntuacion = new Score();
		puntuacion.setTranslateX(873);
		puntuacion.setTranslateY(5);
		nombreTexto = SelectCharacter.nombre;		
		puntuacionTexto = new SimpleStringProperty(this, "puntuacionTexto", "SCORE:");
		panePuntuacion.getChildren().add(puntuacion);
		puntuacion.getPuntuacion().textProperty().bind(puntuacionTexto.concat(pajarito.getScore().asString()));
		
		tuberias = new Tubes(FlappyApp.ANCHO, FlappyApp.ALTO, 7);
		paneJuego.getChildren().addAll(tuberias, pajarito);
		
		tuberias.play();
		gameMusic.playIndefinite();
		pajarito.start();
	}
	
	@Override
	public void stop() {
		super.stop();
		pajarito.setScore(0);
		gameMusic.stop();
		pajarito.stop();
		pajarito.rotateProperty().set(0);
		tuberias.stop();
		paneJuego.getChildren().remove(tuberias);
		paneJuego.getChildren().remove(pajarito);
		paneJuego.getChildren().remove(explosion);
		paneNubes.getChildren().remove(nubes);
		panePuntuacion.getChildren().remove(puntuacion);
		pausado = false;
	}
	
	private void pause() {
		super.stop();
		gameMusic.pause();
		tuberias.pause();
		pajarito.pause();
		nubes.pause();
		pausado = true;
	}
	
	private void resume() {
		super.start();
		gameMusic.resume();
		tuberias.play();
		pajarito.resume();
		nubes.resume();
		pausado = false;
	}

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

	public void checkCollisions() {
		for (Node node : tuberias.getChildren()) {
			
			shapeAux.centerXProperty().bind(pajarito.translateXProperty().add(pajarito.widthProperty().divide(2)));
	        shapeAux.centerYProperty().bind(pajarito.translateYProperty().add(pajarito.heightProperty().divide(2)));
	        shapeAux.rotateProperty().bind(pajarito.rotateProperty());
	        shapeAux.setRadiusX(1);
	        shapeAux.setRadiusY(1);
	        shapeAux.setVisible(true);
	        
			if (node instanceof Tube) { 
				Tube tuberia = (Tube) node;
				Shape intersection = Shape.intersect(tuberia.getShape(), pajarito.getShape());
				if (pajarito.getTranslateY() >= getHeight() || pajarito.getTranslateY() <= 0 || intersection.getBoundsInLocal().getWidth() != -1) {
					if (!pausado) {
						pause();
						gameOver();
					}
				}
				
		        Shape intersectionTwo = Shape.intersect(tuberia.getMiddleShape(), shapeAux);
		        if (intersectionTwo.getBoundsInLocal().getWidth() != -1) {
		        	pointSound.stop();
		        	pajarito.setScore(pajarito.getScore().get()+1);
		        	pointSound.play();
		        }
			}
		}
	}
	
	private void gameOver() {
		explosion = new Explosion();
		explosion.setTranslateX(pajarito.getTranslateX()-32);
		explosion.setTranslateY(pajarito.getTranslateY()-32);
		
		paneJuego.getChildren().add(explosion);
		paneJuego.getChildren().remove(pajarito);
		explosion.explode();
		
		
		scoreLabel.textProperty().bind(nombreTexto.concat(" ").concat(puntuacionTexto.concat(pajarito.getScore().asString())));
		panePausa.setDisable(true);
		overBox.setVisible(true);
		
		FlappyApp.baseDatos.insertarTabla(nombreTexto.get(), pajarito.getScore().get());
		
	}
	
}