package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import flappy.sprites.Tuberia;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Juego extends Screen implements Initializable {
	
    @FXML
    private Pane paneNubes, paneJuego, panePuntuacion;
    
    StackPane vistaJuego;
    
    public static Timeline tubosLoop;
    
	public static ArrayList<Tuberia> listaTubos;
   
    
	public Juego() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/view/GameView.fxml"));
		loader.setController(this);
		loader.load();

		vistaJuego = loader.getRoot();

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		loopTubos();
		
		for (int i = 0; i < listaNubes.size(); i++) {
			
			paneNubes.getChildren().add(listaNubes.get(i).getSprite());
		
		}
		
	}
	
	public void loopTubos() {
		
		listaTubos = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			
			Tuberia tubo = new Tuberia();
			tubo.setTranslateX(i * (ancho / 4));
			listaTubos.add(tubo);
			
		}
		
		tubosLoop = new Timeline(new KeyFrame(Duration.millis(1000 / FPS_60), new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				if (listaTubos.get(0).getTranslateX() <= - ancho / 12.3) {
					
					listaTubos.remove(0);
					
					Tuberia tubo = new Tuberia();
					tubo.setTranslateX(listaTubos.get(listaTubos.size() - 1).getTranslateX() + (ancho / 4));
					listaTubos.add(tubo);
					
					paneJuego.getChildren().add(tubo);
				}
				
				for (int i = 0; i < listaTubos.size(); i++) {
					
					listaTubos.get(i).setTranslateX(listaTubos.get(i).getTranslateX() - 3.5);
					
				}
				
			}
			
		}));
		
		tubosLoop.setCycleCount(-1);
		tubosLoop.play();
		
	}
	
    public StackPane getJuegoView() {
		return vistaJuego;
	}
	
}