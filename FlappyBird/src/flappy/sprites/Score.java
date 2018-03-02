package flappy.sprites;

import gamefx.Sprite;
import javafx.scene.Group;
import javafx.scene.control.Label;

/**
 * 
 * @author Jorge Delgado, Mariela Dorta
 *
 */
public class Score extends Group {
	
	Label puntuacion;

	private Sprite cuadroPuntuacion;
	private static final String PUNTUACION = "/flappy/resources/scoreBG.png";
	
	/**
	 * Constructor por defecto de Score que consta de un Sprite y un label
	 */
 	public Score() {
 		cuadroPuntuacion = new Sprite(PUNTUACION);
 		cuadroPuntuacion.setOpacity(0.5);
 		puntuacion = new Label();
 		puntuacion.setTranslateX(10);
 		puntuacion.setTranslateY(10);
 		
 		getChildren().addAll(cuadroPuntuacion, puntuacion);
 	}
 	
 	/**
 	 * Método que devuelve la label del group
 	 * @return
 	 */
	public Label getPuntuacion() {
		return puntuacion;
	}

}
