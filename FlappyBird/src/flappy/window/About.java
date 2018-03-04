package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Jorge Delgado, Mariela Dorta, Daniel Paredes
 *
 */
public class About extends Background {

	@FXML
	private Pane paneNubes;

	@FXML
	private BorderPane paneButtons;

	@FXML
	private Button volverButton;

    @FXML
    private TextArea acercaDeText;

	public About() throws IOException {
		super("/flappy/view/AboutView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		volverButton.setOnAction(e -> volverButtonAction(e));
		
		acercaDeText.setText("Esta aplicación está desarrollada en el IDE Eclipse 4.7 (Oxygen). Los desarrolladores de esta\r\n" + 
				"aplicación, son tres estudiantes del ciclo superior de Desarrollo de Aplicaciones\r\n" +  
				"Multiplataforma (DAM) como trabajo final de la asignatura de Desarrollo de Interfaces.\r\n\n" + 
				"La aplicación está basada en el juego para móviles 'Flappy Bird' del desarrollador vietnamita\r\n"+
				"Dong Nguyen, lanzada el 24 de mayo de 2013.\r\n\n" + 
				"Esta supone la versión 1.0 de esta aplicación de escritorio.\r\n\n" + 
				"Fecha de lanzamiento a 2 de febrero del año 2018.\r\n\n" + 
				"Creadores Jorge Delgado Díaz, Mariela Dorta Díaz, Daniel Paredes Sánchez.");
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
	void volverButtonAction(ActionEvent event) {
		FlappyApp.goTo(FlappyApp.menu);
	}

}