package flappy.sprites;

import java.util.ArrayList;

import flappy.window.Screen;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

public class Nube extends Sprite {
	
	public static AnimationTimer nubesLoop;
	public static ArrayList<Nube> listaNubes;
	
	public Nube() {
		
		setSprite(new ImageView("/flappy/resources/flappyCloud.png"));
		setPositionX(Math.random() * Screen.ancho);
		setPositionY(Math.random() * Screen.alto - 40);
		setScaleX(Math.random() / 2.0 + 0.5);
		setScaleY(Math.random() / 2.0 + 0.5);
		setOpacity(0.5);
		setTranslateX(100);
	}	
		
}	


