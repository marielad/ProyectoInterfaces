package flappy.sprites;

import flappy.window.Juego;
import javafx.scene.Group;

public class Tuberias extends Group {
	
	public Tuberias(double screenWidth, double screenHeight, int count) {
		super();		
		for (int i = 0; i < count; i++) {
			Tuberia tuberia = new Tuberia(screenWidth, screenHeight,false, false);
			tuberia.setTranslateX(i * (Juego.ESPACIO_ENTRE_TUBOS));
			tuberia.setTranslateZ(Juego.POSICIONZ_PAJARITO);
			getChildren().add(tuberia);
		}
	}
	
	public void play() {
		getChildren().stream().forEach(t -> ((Tuberia)t).play());
	}
	
	public void pause() {
		getChildren().stream().forEach(t -> ((Tuberia)t).pause());
	}
	
	public void stop() {
		getChildren().stream().forEach(t -> ((Tuberia)t).stop());
	}
}
