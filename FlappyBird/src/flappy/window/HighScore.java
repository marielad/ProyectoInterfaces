package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.database.ScoreDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class HighScore extends Background {

	@FXML
	private Pane paneNubes, paneScore;

	@FXML
	private BorderPane paneButtons;
	
    @FXML
    private ListView<String> listScore;

	@FXML
	private Button volverButton;

	public HighScore() throws IOException {
		super("/flappy/view/HighView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		volverButton.setOnAction(e -> volverButtonAction(e));
		
		try {
			listScore.setItems(cargarDatos());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<String> cargarDatos() throws Exception {
		
		ObservableList<String> datos = FXCollections.observableArrayList();
		
		int contador = 1;

		ScoreDB conn = new ScoreDB();
		Statement stmt = conn.getConexion().createStatement();

		try {

			ResultSet rstSet = stmt.executeQuery("SELECT TOP 10 Nombre, Puntos FROM Puntuaciones ORDER BY Puntos DESC");

			while (rstSet.next()) {

				String nomb = rstSet.getString(1);
				int puntos = rstSet.getInt(2);

				datos.add(contador + ".- " + nomb + " " + puntos);
				contador++;
				
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		conn.closeConexion();
		return datos;
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
		FlappyApp.irA(FlappyApp.menu);
	}


}
