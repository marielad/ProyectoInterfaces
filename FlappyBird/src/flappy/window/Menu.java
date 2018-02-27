package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.sound.Sonidos;
import flappy.sprites.Nubes;
import gamefx.Screen;
import gamefx.Sound;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Menu extends Screen {

	private Sound musica;
	private Nubes nubes;

	@FXML
	private Pane animationPane;

	@FXML
	private Button onePlayerButton, twoPlayerButton, highScoreButton, optionsButton, aboutButton, exitButton;

	public Menu() throws IOException {
		super("/flappy/view/MenuView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		musica = Sonidos.MENU;
		
		onePlayerButton.setOnAction(e -> onePlayerButtonAction(e));
		twoPlayerButton.setOnAction(e -> twoPlayerButtonAction(e));
		highScoreButton.setOnAction(e -> highScoreButtonAction(e));
		aboutButton.setOnAction(e -> aboutButtonAction(e));
		
		nubes = new Nubes(30);
		animationPane.getChildren().add(nubes);
	}

	@Override
	public void start() {
		super.start();
		nubes.mover();
		musica.play();
		Platform.runLater(() -> onePlayerButton.requestFocus());
	}

	@Override
	public void stop() {
		super.stop();
		musica.stop();
	}

	@FXML
	void onePlayerButtonAction(ActionEvent event) {
		FlappyApp.irA(FlappyApp.juego);
	}

	@FXML
	void twoPlayerButtonAction(ActionEvent event) {

	}

	@FXML
	void highScoreButtonAction(ActionEvent event) {
		FlappyApp.irA(FlappyApp.mejores);
	}

	@FXML
	void optionsButtonAction(ActionEvent event) {
		FlappyApp.irA(FlappyApp.opciones);
	}

	@FXML
	void aboutButtonAction(ActionEvent event) {
		FlappyApp.irA(FlappyApp.acercaDe);
	}

	@FXML
	void exitButtonAction(ActionEvent event) {
		FlappyApp.getPrimaryStage().close();
	}
	
	@Override
	protected void onKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.M)) {
			if (!musica.isMuted()) {
				musica.mute(true);
			}else {
				musica.mute(false);
			}
		}
	}
}
