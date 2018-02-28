package flappy.sprites;

import flappy.window.Game;
import javafx.scene.Group;

public class Tubes extends Group {
	
	public Tubes(double screenWidth, double screenHeight, int count) {
		super();
		for (int i = 0; i < count; i++) {
			Tube tuberia = new Tube(screenWidth, screenHeight,false, false);
			tuberia.setTranslateX(i * (Game.ESPACIO_ENTRE_TUBOS));
			tuberia.setTranslateZ(Game.POSICIONZ_PAJARITO);
			getChildren().add(tuberia);
		}
	}
	
	public void play() {
		getChildren().stream().forEach(t -> ((Tube)t).play());
	}
	
	public void pause() {
		getChildren().stream().forEach(t -> ((Tube)t).pause());
	}
	
	public void stop() {
		getChildren().stream().forEach(t -> ((Tube)t).stop());
	}
	
}
