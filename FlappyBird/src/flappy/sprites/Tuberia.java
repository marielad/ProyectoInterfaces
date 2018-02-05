package flappy.sprites;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Tuberia extends Group {
	
	public Tuberia() {
		
		Sprite tubeUp = new Sprite();
		tubeUp.setSprite(new ImageView("/flappy/resources/flappyTubeUp.png"));
		tubeUp.setPositionX(200);
		tubeUp.setPositionY(-200);	
		
		Sprite tubeDown = new Sprite();
		tubeDown.setSprite(new ImageView("/flappy/resources/flappyTubeDown.png"));
		tubeDown.setPositionX(200);
		tubeDown.setPositionY(220);
		
		getChildren().addAll(tubeUp.getSprite(), tubeDown.getSprite());
		
	}
}
