package flappy.estado;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import flappy.animacion.MovimientoNube;
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

public class Puntuacion implements Initializable{
	
	BorderPane view;
	Scene scene;
	Stage stage;
	ObservableList<String> datos =FXCollections.observableArrayList();
	MovimientoNube movimiento;
	
	@FXML
    private Label scoreTitle;

    @FXML
    private ListView<String> listScore;
    
	
	public Puntuacion() throws IOException{
		
		movimiento = new MovimientoNube(8);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/vista/PuntuacionView.fxml"));
		loader.setController(this);
		loader.load();
		
		view = loader.getRoot();
		
		view.getChildren().addAll(movimiento.getListaNubesLentas());
		
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
		
		Connection con = Conecta.hacerConexion();
		Statement s = con.createStatement();
		Statement s1 = con.createStatement();
		Statement s2 = con.createStatement();
		
		ResultSet rs = s.executeQuery("select nombre from puntuaciones group by nombre, puntos order by puntos DESC");
		ResultSet rs1 = s1.executeQuery("select puntos from puntuaciones group by nombre, puntos order by puntos DESC");
		ResultSet rs2 = s2.executeQuery("select count(*) from puntuaciones");
		
		rs2.next();
		tamanio = Integer.parseInt(rs2.getString(1));
		
		while (rs.next() && rs1.next() && contador != 11) {
				datos.add(""+contador+".-  "+rs.getString(1)+"   "+rs1.getString(1));
				contador++;
	
		}
		
		return datos;
	}

	public Stage getStage() {
		return stage;
	}

	
}
