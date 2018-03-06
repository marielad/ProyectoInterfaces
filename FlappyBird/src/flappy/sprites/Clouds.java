package flappy.sprites;

import javafx.scene.Group;
import javafx.util.Duration;

/**
 * 
 * @author Jorge Delgado, Mariela Dorta, Fran Vargas
 *
 */

public class Clouds extends Group {
	
	/**
	 * Constructor del grupo de nubes
	 * @param count
	 */
	
	public Clouds(int count) {
		super();
		for (int i = 0; i < count; i++) {
			Duration duration = Duration.seconds(Math.random() * 5 + 5);
			getChildren().add(new Cloud(duration));
		}
	}
	
	/**
	 *  mover() es un método que ejecuta el método mover() de cada nube del group	
	 */
	
	public void mover() {
		getChildren().stream().forEach(n -> ((Cloud)n).mover());
	}
	
	/**
	 *  pause() es un método que ejecuta el método pause() de cada nube del group	
	 */
	
	public void pause() {
		getChildren().stream().forEach(n -> ((Cloud)n).pause());
	}
	
	/**
	 *  resume() es un método que ejecuta el método resume() de cada nube del group	
	 */
	
	public void resume() {
		getChildren().stream().forEach(n -> ((Cloud)n).resume());
	}
	
}
