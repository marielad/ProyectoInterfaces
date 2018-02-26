package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import gamefx.Screen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Opciones extends Screen {

	@FXML
	private Pane paneAnimation;

	@FXML
	private BorderPane paneButtons;

	@FXML
	private Button volverButton, muteButton;

	public Opciones() throws IOException {
		super("/flappy/view/OptionView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		muteButton.setOnAction(e -> muteButtonAction(e));
		volverButton.setOnAction(e -> volverButtonAction(e));

		
	}
	
    @FXML
    void muteButtonAction(ActionEvent event) {
    	
//    	musica.mute(true);
//    	musicaJuego.mute(true);
    	
    }

    @Override
	protected void onKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ESCAPE)) {
			stop();
			FlappyApp.irA(FlappyApp.menu);
		}
	}
    
	@FXML
	void volverButtonAction(ActionEvent event) {
		FlappyApp.irA(FlappyApp.menu);
	}

}