package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Jorge Delgado, Mariela Dorta
 *
 */
public class Menu extends Background {
	
	@FXML
	private Pane paneNubes;

	@FXML
	private Button onePlayerButton, twoPlayerButton, highScoreButton, optionsButton, aboutButton, exitButton;

	public Menu() throws IOException {
		super("/flappy/view/MenuView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		onePlayerButton.setOnAction(e -> onePlayerButtonAction(e));
		twoPlayerButton.setOnAction(e -> twoPlayerButtonAction(e));
		highScoreButton.setOnAction(e -> highScoreButtonAction(e));
		aboutButton.setOnAction(e -> aboutButtonAction(e));
	}

	@Override
	public void start() {
		super.start();
		paneNubes.getChildren().add(nubes);
		menuMusic.playIndefinite();
		Platform.runLater(() -> onePlayerButton.requestFocus());
	}

	@Override
	public void stop() {
		super.stop();
		paneNubes.getChildren().remove(nubes);
	}

	@FXML
	void onePlayerButtonAction(ActionEvent event) {
		SelectCharacter seleccion;
		try {
			seleccion = new SelectCharacter();
			FlappyApp.goTo(seleccion);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void twoPlayerButtonAction(ActionEvent event) {
		SelectCharacterTwo seleccion;
		try {
			seleccion = new SelectCharacterTwo();
			FlappyApp.goTo(seleccion);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void highScoreButtonAction(ActionEvent event) {
		HighScore highScore;
		try {
			highScore = new HighScore();
			FlappyApp.goTo(highScore);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void optionsButtonAction(ActionEvent event) {
		FlappyApp.goTo(FlappyApp.opciones);
	}

	@FXML
	void aboutButtonAction(ActionEvent event) {
		FlappyApp.goTo(FlappyApp.acercaDe);
	}

	@FXML
	void exitButtonAction(ActionEvent event) {
		FlappyApp.baseDatos.closeConexion();
		FlappyApp.getPrimaryStage().close();
	}
	
}
