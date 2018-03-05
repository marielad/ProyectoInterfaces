package flappy.sprites;

import flappy.sound.Sounds;
import gamefx.Sound;
import gamefx.Sprite;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.util.Duration;

public class Explosion extends Sprite {
	
	Task<Void> tarea;

	private static final String EXPLOSION = "/flappy/resources/explosion.png";
	private Timeline explosion;
	private Sound deathSound = Sounds.DEATH;
	
	public Explosion(String url) {
		super(url, 81, 9);

		KeyFrame ini = new KeyFrame(Duration.millis(0), new KeyValue(imageIndexProperty(), 0));
        KeyFrame fin = new KeyFrame(Duration.millis(1000), new KeyValue(imageIndexProperty(), 81));
	
        explosion = new Timeline(ini, fin);
 		explosion.setCycleCount(1);
	}
	
	public Explosion() {
		this(EXPLOSION);
	}
	
	public void explode() {
		deathSound.stop();
		explosion.playFromStart();
		deathSound.play();
 	}
}
