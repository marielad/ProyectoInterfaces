package flappy.window;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.sound.Sound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javazoom.jl.decoder.JavaLayerException;

public class Menu extends Screen implements Initializable {

	@FXML
	private Pane paneAnimation;

	@FXML
	private BorderPane paneButtons;

	StackPane vistaMenu;

	@FXML
	private Button onePlayerButton, twoPlayerButton, highScoreButton, aboutButton, exitButton;

	InputStream menuMP3 = getClass().getClassLoader().getResourceAsStream("flappy/sound/shootingStars.mp3");
	
	Sound musicaMenu;
	
	public Menu() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/view/MenuView.fxml"));
		loader.setController(this);
		loader.load();

		vistaMenu = loader.getRoot();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		onePlayerButton.setOnAction(e -> onePlayerButtonAction(e));
		twoPlayerButton.setOnAction(e -> twoPlayerButtonAction(e));
		highScoreButton.setOnAction(e -> highScoreButtonAction(e));
		aboutButton.setOnAction(e -> aboutButtonAction(e));
		
		try {
			
			musicaMenu = new Sound(menuMP3);
			musicaMenu.play();
			
		} catch (JavaLayerException e1) {
			
			e1.printStackTrace();
			
		}

		creacionNubes();

	}

	@FXML
	void onePlayerButtonAction(ActionEvent event) {
		
		musicaMenu.stop();

		try {

			Juego nuevoJuego = new Juego();
			FlappyApp.scene.setRoot(nuevoJuego.getJuegoView());

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	@FXML
	void twoPlayerButtonAction(ActionEvent event) {

	}

	@FXML
	void highScoreButtonAction(ActionEvent event) {

		try {

			MejoresPuntuaciones nuevoPuntuacion = new MejoresPuntuaciones();
			FlappyApp.scene.setRoot(nuevoPuntuacion.getPuntuacionView());

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	@FXML
	void aboutButtonAction(ActionEvent event) {

		try {

			AcercaDe nuevoAcerca = new AcercaDe();
			FlappyApp.scene.setRoot(nuevoAcerca.getAcercaView());

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	@FXML
	void exitButtonAction(ActionEvent event) {
		FlappyApp.getPrimaryStage().close();
	}

	public StackPane getMenuView() {

		for (int i = 0; i < listaNubes.size(); i++) {

			paneAnimation.getChildren().add(listaNubes.get(i).getSprite());

		}

		return vistaMenu;
	}

}
