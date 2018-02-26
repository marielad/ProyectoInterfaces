package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.sound.Sonidos;
import flappy.sprites.Nubes;
import flappy.sprites.Pajarito;
import flappy.sprites.Tuberia;
import flappy.sprites.Tuberias;
import gamefx.Screen;
import gamefx.Sound;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Juego extends Screen {

	public static final int POSICIONX_PAJARITO = 200;
	public static final int POSICIONY_PAJARITO = 75;
	public static final int POSICIONZ_PAJARITO = 75;
	public static final int ESPACIO_ENTRE_TUBOS = 200;

	private Pajarito pajarito;
	private Nubes nubes;
	private Tuberias tuberias;

	private Sound musica = Sonidos.JUEGO;

	private Boolean pausado = false;
	
	@FXML
	private Pane paneNubes, paneJuego, panePuntuacion;

	public Juego() throws IOException {
		super("/flappy/view/GameView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		nubes = new Nubes(30);
		paneNubes.getChildren().add(nubes);
		
		pajarito = new Pajarito();
		pajarito.setTranslateX(100);
		paneJuego.getChildren().add(pajarito);

	}
	
	@Override
	protected void onKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ESCAPE)) {
			stop();
			FlappyApp.irA(FlappyApp.menu);
		}
		if (e.getCode().equals(KeyCode.SPACE) || e.getCode().equals(KeyCode.UP)) {
			if (!pajarito.isPausado()) {
				pajarito.jump();
			}
		}
		if (e.getCode().equals(KeyCode.M)) {
			if (!musica.isMuted()) {
				musica.mute(true);
			}else {
				musica.mute(false);
			}
		}
		if (e.getCode().equals(KeyCode.P)) {	
			if (!pausado) {
				pause();
			}else {
				resume();
			}
		}
	}
	
	@Override
	public void start() {
		super.start();

		tuberias = new Tuberias(FlappyApp.ANCHO, FlappyApp.ALTO, 7);
		paneJuego.getChildren().add(tuberias);

		tuberias.play();
		musica.play();
		nubes.mover();
		pajarito.start();
	}
	
	@Override
	public void stop() {
		super.stop();
		musica.stop();
		paneJuego.getChildren().remove(tuberias);		
	}
	
	private void pause() {
		super.stop();
		musica.pause();
		tuberias.pause();
		pajarito.pause();
		nubes.pause();
		pausado = true;
	}
	
	private void resume() {
		super.start();
		musica.resume();
		tuberias.play();
		pajarito.resume();
		nubes.resume();
		pausado = false;
	}

	protected void loop(long now) {
		if (!tuberias.getChildren().isEmpty()) {

			if (tuberias.getChildren().get(0).getTranslateX() <= -getWidth() - ESPACIO_ENTRE_TUBOS - 75) {
				Tuberia tuberia;

				if (Math.random() < 0.25) {
					tuberia = new Tuberia(getWidth(), getHeight(),false, true);
				} else if (Math.random() > 0.75) {
					if (Math.random() > 0.5) {
						tuberia = new Tuberia(getWidth(), getHeight(),true, false);
					}else {
						tuberia = new Tuberia(getWidth(), getHeight(),true, true);
					}
				} else {
					tuberia = new Tuberia(getWidth(), getHeight(),false, false);
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

		// Colisión Tubos
		checkCollision();
		
	}

	public void checkCollision() {

	}
}