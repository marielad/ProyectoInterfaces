package flappy.sprites;

import flappy.components.Sprite;
import flappy.window.Screen;
import javafx.scene.image.ImageView;

public class Nube extends Sprite {
	
	public Nube() {
		
		setSpriteImage(new ImageView("/flappy/resources/flappyCloud.png"));
		getSpriteImage().setX(Math.random() * Screen.ancho);
		getSpriteImage().setY(Math.random() * Screen.alto - 40);
		getSpriteImage().setScaleX(Math.random() / 2.0 + 0.5);
		getSpriteImage().setScaleY(Math.random() / 2.0 + 0.5);
		getSpriteImage().setOpacity(0.5);
		
	}
	
}
