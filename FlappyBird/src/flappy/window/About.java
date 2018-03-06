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
 * Clase que muestra el About de nuestra aplicación
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
		
		acercaDeText.setText("Versión alpha de escritorio para el proyecto final de Desarollo de Interfaces.\r\n\n" + 
				"Fecha de lanzamiento: 7 de marzo de 2018.\r\n\n" + 
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