package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class AcercaDe extends Screen implements Initializable {

	@FXML
	private Pane paneAnimation;

	@FXML
	private BorderPane paneButtons;

	StackPane vistaAcerca;
	
	@FXML
    private Label textLabel;

	@FXML
	private Button volverButton;

	public AcercaDe() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/view/AboutView.fxml"));
		loader.setController(this);
		loader.load();

		vistaAcerca = loader.getRoot();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		volverButton.setOnAction(e -> volverButtonAction(e));

		for (int i = 0; i < listaNubes.size(); i++) {

			paneAnimation.getChildren().add(listaNubes.get(i).getSprite());

		}
		
		textLabel.setText("Esta aplicación está desarrollada en el IDE Eclipse 4.7 (Oxygen). Los desarrolladores de esta\r\n" + 
				"aplicación, son tres estudiantes del ciclo superior de Desarrollo de Aplicaciones\r\n" +  
				"Multiplataforma (DAM) como trabajo final de la asignatura de Desarrollo de Interfaces.\r\n\n" + 
				"La aplicación está basada en el juego para móviles “Flappy Bird” del desarrollador vietnamita\r\n"+
				"Dong Nguyen lanzada el 24 de mayo de 2013.\r\n\n" + 
				"Esta supone la versión 1.0 de esta aplicación de escritorio.\r\n\n" + 
				"Fecha de lanzamiento a x de febrero del año 2018.\r\n\n" + 
				"Creadores Mariela Dorta Díaz, Jorge Delgado Díaz, Daniel Paredes Sánchez.\r\n\n" + 
				"");

	}

	@FXML
	void volverButtonAction(ActionEvent event) {

		FlappyApp.scene.setRoot(FlappyApp.menuControl.getMenuView());
		
	}

	public StackPane getAcercaView() {
		return vistaAcerca;
	}

}