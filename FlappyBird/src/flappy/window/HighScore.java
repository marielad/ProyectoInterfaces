package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Jorge Delgado, Mariela Dorta, Daniel Paredes
 *
 */
public class HighScore extends Background {

	@FXML
	private Pane paneNubes, paneScore;

	@FXML
	private BorderPane paneButtons;
	
    @FXML
    private ListView<String> listScore;

	@FXML
	private Button volverButton, reportButton;

	public HighScore() throws IOException {
		super("/flappy/view/HighView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		volverButton.setOnAction(e -> volverButtonAction(e));
		reportButton.setOnAction(e -> onReportButtonAction(e));
		
		FlappyApp.baseDatos.cargarPuntuaciones();
		listScore.setItems(FlappyApp.baseDatos.datos);
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
    void onReportButtonAction(ActionEvent event) {
    	FlappyApp.baseDatos.cargarJasper();
    }
	
	@FXML
	void volverButtonAction(ActionEvent event) {
		FlappyApp.irA(FlappyApp.menu);
	}


}
