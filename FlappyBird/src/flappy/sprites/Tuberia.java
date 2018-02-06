package flappy.sprites;

import flappy.window.Screen;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Tuberia extends Group {
	
	double posicionHueco = (Screen.alto) * Math.random() / 2;
	double diferencia = 80+Screen.alto/6+posicionHueco;

	public Tuberia() {

		Sprite tubeUp = new Sprite();
		tubeUp.setSprite(new ImageView("/flappy/resources/flappyTubeUp.png"));
		tubeUp.setPositionY(diferencia-Screen.alto-20);

		Sprite tubeDown = new Sprite();
		tubeDown.setSprite(new ImageView("/flappy/resources/flappyTubeDown.png"));
		tubeDown.setPositionY(diferencia);

		getChildren().addAll(tubeUp.getSprite(), tubeDown.getSprite());

	}
}
