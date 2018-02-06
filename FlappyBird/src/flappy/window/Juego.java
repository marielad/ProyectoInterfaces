package flappy.window;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import flappy.app.FlappyApp;
import flappy.sound.Sound;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javazoom.jl.decoder.JavaLayerException;

public class Juego extends Screen implements Initializable {

	@FXML
	private Pane paneNubes, paneJuego, panePuntuacion;

	StackPane vistaJuego = new StackPane();
	
	InputStream juegoMP3 = getClass().getClassLoader().getResourceAsStream("flappy/sound/wily12.mp3");
	
	Sound musicaJuego;

	public Juego() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/view/GameView.fxml"));
		loader.setController(this);
		loader.load();

		vistaJuego = loader.getRoot();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			
			musicaJuego = new Sound(juegoMP3);
			musicaJuego.play();
			
		} catch (JavaLayerException e1) {
			
			e1.printStackTrace();
			
		}
		
		creacionTubos(paneJuego);

		for (int i = 0; i < listaNubes.size(); i++) {

			paneNubes.getChildren().add(listaNubes.get(i).getSprite());

		}
		
		FlappyApp.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				
				if (event.getCode() == KeyCode.ESCAPE) {
					
					if(FlappyApp.scene == vistaJuego.getScene()) {
						
						musicaJuego.stop();
						tubosLoop.stop();
						FlappyApp.scene.setRoot(FlappyApp.menuControl.getMenuView());
						
					}
					
				}
			}
		});

	}

	public StackPane getJuegoView() {
		return vistaJuego;
	}

}