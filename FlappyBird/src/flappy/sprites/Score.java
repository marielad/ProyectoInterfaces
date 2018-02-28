package flappy.sprites;

import gamefx.Sprite;
import javafx.scene.Group;
import javafx.scene.control.Label;

public class Score extends Group {
	
	Label puntuacion;

	private Sprite cuadroPuntuacion;
	private static final String PUNTUACION = "/flappy/resources/scoreBG.png";
	
 	public Score() {
 		cuadroPuntuacion = new Sprite(PUNTUACION);
 		cuadroPuntuacion.setOpacity(0.5);
 		puntuacion = new Label();
 		puntuacion.setTranslateX(10);
 		puntuacion.setTranslateY(10);
		setTranslateX(873);
		setTranslateY(5);
 		
 		getChildren().addAll(cuadroPuntuacion, puntuacion);
 	}
 	
	public Label getPuntuacion() {
		return puntuacion;
	}

}
