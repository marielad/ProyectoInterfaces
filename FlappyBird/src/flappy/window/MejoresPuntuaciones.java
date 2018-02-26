package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import gamefx.Screen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import methods.CargarFicheros;

public class MejoresPuntuaciones extends Screen {

	@FXML
	private Pane paneAnimation, paneScore;

	@FXML
	private BorderPane paneButtons;

	StackPane vistaPuntuacion;

	@FXML
	private ListView<String> listScore; 
	
	@FXML
	private Button volverButton;

	
	public MejoresPuntuaciones() throws IOException {
		super("/flappy/view/HighView.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		volverButton.setOnAction(e -> volverButtonAction(e));
		
		try {
			listScore.setItems(datosMySql());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

	@SuppressWarnings("unused")
	public ObservableList<String> datosMySql() throws Exception {
		
		ObservableList<String> datos = FXCollections.observableArrayList();
		
		int tamanioLista = 0;
		int contador = 1;
		int tamanio = 0;

		Conexion conn = new Conexion();
		Statement stmt = conn.getConexion().createStatement();
		stmt.executeQuery(CargarFicheros.fileToString("script/registros.sql"));

		try {

			ResultSet rstSet = stmt.executeQuery("SELECT * FROM puntuaciones ORDER BY puntos DESC");

			while (rstSet.next() && tamanioLista != 10) {

				int id = rstSet.getInt(1);
				String nomb = rstSet.getString(2);
				int puntos = rstSet.getInt(3);

				datos.add("" + contador + ".-  " + nomb + "   " + puntos);
				contador++;
				tamanioLista++;

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		conn.closeConexion();
		return datos;
	}

}
