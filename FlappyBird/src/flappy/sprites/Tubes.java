package flappy.sprites;

import flappy.window.Game;
import javafx.scene.Group;

/**
 * 
 * @author Jorge Delgado, Mariela Dorta, Fran Vargas
 *
 */
public class Tubes extends Group {
	/**
	 * Constructor Tubes
	 * @param screenWidth 
	 * @param screenHeight
	 * @param count N�mero de tuber�as que queremos en el group
	 */
	public Tubes(double screenWidth, double screenHeight, int count) {
		super();
		for (int i = 0; i < count; i++) {
			Tube tuberia = new Tube(screenWidth, screenHeight,false, false);
			tuberia.setTranslateX(i * (Game.ESPACIO_ENTRE_TUBOS));
			tuberia.setTranslateZ(Game.POSICIONZ_PAJARITO);
			getChildren().addAll(tuberia);
		}
	}
	
	/**
	 *  play() es un m�todo que ejecuta el m�todo play() de cada tuber�a del group	
	 */
	public void play() {
		getChildren().stream().filter(t -> t instanceof Tube).forEach(t -> ((Tube)t).play());
	}
	
	/**
	 *  pause() es un m�todo que ejecuta el m�todo pause() de cada tuber�a del group	
	 */
	public void pause() {
		getChildren().stream().filter(t -> t instanceof Tube).forEach(t -> ((Tube)t).pause());
	}
	
	/**
	 *  stop() es un m�todo que ejecuta el m�todo stop() de cada tuber�a del group	
	 */
	public void stop() {
		getChildren().stream().filter(t -> t instanceof Tube).forEach(t -> ((Tube)t).stop());
	}
	
}
