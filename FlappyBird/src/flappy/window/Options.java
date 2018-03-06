package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Clase que contiene las opciones del juego
 * @author Jorge Delgado, Mariela Dorta
 *
 */
public class Options extends Background {

	@FXML
	private Pane paneNubes;

	@FXML
	private BorderPane paneButtons;

	@FXML
	private Button volverButton, muteButton;

	public Options() throws IOException {
		super("/flappy/view/OptionView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		muteButton.setOnAction(e -> muteButtonAction(e));
		volverButton.setOnAction(e -> volverButtonAction(e));
	}
	
	@Override
	public void start() {
		super.start();
		paneNubes.getChildren().add(nubes);
	}

	@Override
	public void stop() {
		super.stop();
		paneNubes.getChildren().remove(nubes);
	}
	
    @FXML
    void muteButtonAction(ActionEvent event) {
		if (!menuMusic.isMuted() || !gameMusic.isMuted()) {
			menuMusic.mute(true);
			gameMusic.mute(true);
		}else {
			menuMusic.mute(false);
			gameMusic.mute(false);
		}
    }
    
	@FXML
	void volverButtonAction(ActionEvent event) {
		FlappyApp.goTo(FlappyApp.menu);
	}

}