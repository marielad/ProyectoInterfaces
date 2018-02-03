package flappy.window;

import java.util.ArrayList;

import flappy.sprites.Nube;
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
	public static Timeline gameLoop;

	public static ArrayList<Nube> listaNubes;

	public Screen() {
		
		creacionNubes();

		gameLoop = new Timeline(new KeyFrame(Duration.millis(1000 / FPS_60), new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				
				for (int i = 0; i < listaNubes.size(); i++) {
					
					if (listaNubes.get(i).getSpriteImage().getX() < -(listaNubes.get(i).getWidth()+100)
							* listaNubes.get(i).getSpriteImage().getScaleX()) {

						listaNubes.get(i).getSpriteImage().setX(listaNubes.get(i).getSpriteImage().getX() + ancho
								+ (listaNubes.get(i).getWidth()+100) * listaNubes.get(i).getSpriteImage().getScaleX());

					}
					
					listaNubes.get(i).getSpriteImage().setX(listaNubes.get(i).getSpriteImage().getX() - (i/3+0.5));
					
				}
				
			}

		}));

		gameLoop.setCycleCount(-1);
		gameLoop.play();
		
	}
	
	public void creacionNubes() {
		
		listaNubes = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			
			Nube nube = new Nube();
			listaNubes.add(nube);
			
		}
		
	}

}
