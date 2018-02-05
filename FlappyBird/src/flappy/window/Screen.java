package flappy.window;

import java.util.ArrayList;

import flappy.sprites.Nube;
import flappy.sprites.Tuberia;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.util.Duration;

public class Screen {

	public static final int FPS_60 = 60;
	public static final int ancho = 1000;
	public static final int alto = 400;
	public static Group surfaceGroup;
	public static Timeline nubesLoop, gameLoop;

	public static ArrayList<Nube> listaNubes;
	public static ArrayList<Tuberia> listaTubos;

	public Screen() {
		
	}
	
	public void creacionNubes() {
		
		listaNubes = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			
			Nube nube = new Nube();
			listaNubes.add(nube);
			
		}
	
		nubesLoop = new Timeline(new KeyFrame(Duration.millis(1000 / FPS_60), new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				
				for (int i = 0; i < listaNubes.size(); i++) {
					
					if (listaNubes.get(i).getSprite().getX() < - 100 * listaNubes.get(i).getSprite().getScaleX()) {

						listaNubes.get(i).getSprite().setX(listaNubes.get(i).getSprite().getX() + ancho
								+ 100 * listaNubes.get(i).getSprite().getScaleX());

					}
					
					listaNubes.get(i).getSprite().setX(listaNubes.get(i).getSprite().getX() - (i/4+0.75));
					
				}
				
			}

		}));

		nubesLoop.setCycleCount(-1);
		nubesLoop.play();
		
	}
	
	public void creacionTubos() {
		
		listaTubos = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			
			Tuberia tubo = new Tuberia();
			listaTubos.add(tubo);
			
		}
		
	}

}
