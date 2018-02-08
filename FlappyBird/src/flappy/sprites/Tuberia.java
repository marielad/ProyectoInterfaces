package flappy.sprites;

import flappy.window.Screen;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Tuberia extends Group {
	
	public double posicionHueco = (Screen.alto+50) * Math.random() / 2;
	public double diferencia = 100+Screen.alto/6+posicionHueco;
	public double tamanioHueco = 20;
	public Sprite tubeUp, tubeDown;

	public Tuberia() {

		tubeUp = new Sprite();
		tubeUp.setSprite(new ImageView("/flappy/resources/flappyTubeUp.png"));
		//Tamaño del hueco entre tubeUp y tubeDown
		tubeUp.setPositionY(diferencia-Screen.alto-tamanioHueco);
		tubeUp.setPositionX(1400);

		tubeDown = new Sprite();
		tubeDown.setSprite(new ImageView("/flappy/resources/flappyTubeDown.png"));
		tubeDown.setPositionY(diferencia);
		tubeDown.setPositionX(1400);

		getChildren().addAll(tubeUp.getSprite(), tubeDown.getSprite());

	}
}
