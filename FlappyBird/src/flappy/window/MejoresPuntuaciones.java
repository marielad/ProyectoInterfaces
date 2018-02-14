package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import methods.CargarFicheros;

public class MejoresPuntuaciones extends Screen implements Initializable {

	@FXML
	private Pane paneAnimation;

	@FXML
	private BorderPane paneButtons;

	@FXML
	private ListView<String> listScore;

	StackPane vistaPuntuacion;

	private ObservableList<String> datos = FXCollections.observableArrayList();

	@FXML
	private Button volverButton;

	public MejoresPuntuaciones() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/view/HighView.fxml"));
		loader.setController(this);
		loader.load();

		vistaPuntuacion = loader.getRoot();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		volverButton.setOnAction(e -> volverButtonAction(e));

		for (int i = 0; i < listaNubes.size(); i++) {

			paneAnimation.getChildren().add(listaNubes.get(i).getSprite());

		}

		try {
			listScore.setItems(datosMySql());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void volverButtonAction(ActionEvent event) {

		FlappyApp.scene.setRoot(FlappyApp.menuControl.getMenuView());

	}

	public StackPane getPuntuacionView() {
		return vistaPuntuacion;
	}

	@SuppressWarnings("unused")
	public ObservableList<String> datosMySql() throws Exception {
		int tamanioLista = 0;
		int contador = 1;
		int tamanio = 0;

		Connection con = Conexion.conectarHSQL();

		Statement stmt = con.createStatement();
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

		return datos;
	}

}
