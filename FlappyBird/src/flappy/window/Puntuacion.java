package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import flappy.bd.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import methods.CargarFicheros;

public class Puntuacion implements Initializable{
	
	BorderPane view;
	Scene scene;
	Stage stage;
	//Lista de strings donde se guardan los datos de las puntuaciones
	ObservableList<String> datos =FXCollections.observableArrayList();
	
	@FXML
    private Label scoreTitle;

    @FXML
    private ListView<String> listScore;
    
	
	public Puntuacion() throws IOException{
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/view/HighView.fxml"));
		loader.setController(this);
		loader.load();
		
		view = loader.getRoot();
		
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			listScore.setItems(datosMySql());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public BorderPane getView() {
		return view;
	}

	public Scene getScene() {
		return scene;
	}
	
	@SuppressWarnings("unused")
	public ObservableList<String> datosMySql() throws Exception {
		int contador = 1;
		int tamanio = 0;
		
		Connection con = Conexion.conectarHSQL();
		
		Statement stmt = con.createStatement();
		stmt.executeQuery(CargarFicheros.fileToString("script/registros.sql"));
		
		try {

			ResultSet rstSet = stmt.executeQuery("SELECT * FROM puntuaciones ORDER BY puntos DESC");

			while (rstSet.next()) {

				int id = rstSet.getInt(1);
				String nomb = rstSet.getString(2);
				int puntos = rstSet.getInt(3);
				
				datos.add(""+contador+".-  "+nomb+"   "+puntos);
				contador++;

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return datos;
	}

	public Stage getStage() {
		return stage;
	}

	
}
