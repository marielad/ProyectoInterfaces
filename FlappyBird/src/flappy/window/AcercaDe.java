package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.sprites.Nubes;
import gamefx.Screen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AcercaDe extends Screen {

	@FXML
	private Pane paneAnimation;

	@FXML
	private BorderPane paneButtons;

	@FXML
	private Button volverButton;
	

	@FXML
    private Label textLabel;

	public AcercaDe() throws IOException {
		super("/flappy/view/AboutView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		volverButton.setOnAction(e -> volverButtonAction(e));
		
		textLabel.setText("Esta aplicación está desarrollada en el IDE Eclipse 4.7 (Oxygen). Los desarrolladores de esta\r\n" + 
				"aplicación, son tres estudiantes del ciclo superior de Desarrollo de Aplicaciones\r\n" +  
				"Multiplataforma (DAM) como trabajo final de la asignatura de Desarrollo de Interfaces.\r\n\n" + 
				"La aplicación está basada en el juego para móviles “Flappy Bird” del desarrollador vietnamita\r\n"+
				"Dong Nguyen, lanzada el 24 de mayo de 2013.\r\n\n" + 
				"Esta supone la versión 1.0 de esta aplicación de escritorio.\r\n\n" + 
				"Fecha de lanzamiento a x de febrero del año 2018.\r\n\n" + 
				"Creadores Mariela Dorta Díaz, Jorge Delgado Díaz, Daniel Paredes Sánchez.\r\n\n" + 
				"");

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