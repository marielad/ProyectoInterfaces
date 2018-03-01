package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.sprites.Bird;
import flappy.sprites.Score;
import flappy.sprites.Tube;
import flappy.sprites.Tubes;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

public class Game extends Background {

	public static final int POSICIONX_PAJARITO = 200;
	public static final int POSICIONY_PAJARITO = 75;
	public static final int POSICIONZ_PAJARITO = 75;
	public static final int ESPACIO_ENTRE_TUBOS = 200;

	private Bird pajarito;
	private Tubes tuberias;
	private Score puntuacion;

	private Boolean pausado = false;
	//Me da que esto es una changada
	private StringProperty puntuacionTexto;
	
	@FXML
    private VBox buttonsBox;

    @FXML
    private Button resumeButton, optionsButton, exitButton;

	@FXML
	private Pane paneNubes, paneJuego, panePuntuacion;

	public Game() throws IOException {
		super("/flappy/view/GameView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonsBox.setVisible(false);
		
		resumeButton.setOnAction(e -> resumeButtonAction(e));
		optionsButton.setOnAction(e -> optionsButtonAction(e));
		exitButton.setOnAction(e -> exitButtonAction(e));
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
	}
	
	@FXML
    void exitButtonAction(ActionEvent event) {
    	resume();
		stop();
		FlappyApp.irA(FlappyApp.menu);
		buttonsBox.setVisible(false);
    }

	@FXML
    void optionsButtonAction(ActionEvent event) {
    	
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
		//Parte de la changada inicial
		pajarito = SelectCharacter.pajarito;
		pajarito.setTranslateX(100);
		pajarito.setTranslateY(200);
		puntuacion = new Score();
		puntuacionTexto = new SimpleStringProperty(this, "puntuacionTexto", "SCORE:");
		panePuntuacion.getChildren().add(puntuacion);
		puntuacion.getPuntuacion().textProperty().bind(puntuacionTexto.concat(pajarito.getScore().asString()));
		tuberias = new Tubes(FlappyApp.ANCHO, FlappyApp.ALTO, 7);
		paneJuego.getChildren().addAll(tuberias, pajarito, pajarito.getShape());
		tuberias.play();
		musicaJuego.play();
		pajarito.start();
	}
	
	@Override
	public void stop() {
		super.stop();
		musicaJuego.stop();
		pajarito.stop();
		tuberias.stop();
		paneJuego.getChildren().remove(tuberias);
		paneJuego.getChildren().remove(pajarito);
		paneNubes.getChildren().remove(nubes);
		panePuntuacion.getChildren().remove(puntuacion);
	}
	
	private void pause() {
		super.stop();
		musicaJuego.pause();
		tuberias.pause();
		pajarito.pause();
		nubes.pause();
		pausado = true;
		
		System.out.println(pajarito.getShape().getBoundsInParent());
	}
	
	private void resume() {
		super.start();
		musicaJuego.resume();
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
				
				tuberias.getChildren().addAll(tuberia);
				tuberias.getChildren().remove(0);
			}
			
			for (Node tuberia : tuberias.getChildren()) {
				tuberia.setTranslateX(tuberia.getTranslateX() - 3);
			}
		}
		
		checkCollision();
	}

	public void checkCollision() {
		for (Node node : tuberias.getChildren()) {
			
			if (node instanceof Tube) { 
				Tube tuberia = (Tube) node;
				Shape intersection = Shape.intersect(tuberia.getShape(), pajarito.getShape());
				if (intersection.getBoundsInLocal().getWidth() != -1) {
					System.out.println("zasca del pajarito!!!");
					gameOver();
				}
				
			}
			
		}
	}
	
	private void gameOver() {
		pause();
	}
	
}