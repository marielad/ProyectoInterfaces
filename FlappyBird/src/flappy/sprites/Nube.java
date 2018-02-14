package flappy.sprites;

import flappy.window.Screen;
import javafx.scene.image.ImageView;

public class Nube extends Sprite {
	
	public Nube() {
		
		setSprite(new ImageView("/flappy/resources/flappyCloud.png"));
		setPositionX(Math.random() * Screen.ancho);
		setPositionY(Math.random() * Screen.alto - 40);
		setScaleX(Math.random() / 2.0 + 0.5);
		setScaleY(Math.random() / 2.0 + 0.5);
		setOpacity(0.5);
	}
	
}


