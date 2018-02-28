package flappy.sprites;

import flappy.app.FlappyApp;
import gamefx.Sprite;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Cloud extends Sprite {
	
	private static final String NUBE = "/flappy/resources/flappyCloud.png";
	
 	private TranslateTransition mover;

	public Cloud(Duration duration) {
		super(NUBE);
		setLayoutY(Math.random() * FlappyApp.ALTO);			
		setScaleX(Math.random() / 2.0 + 0.5);
		setScaleY(Math.random() / 2.0 + 0.5);
		setOpacity(0.5);
		
		mover = new TranslateTransition(duration, this);
		mover.setInterpolator(Interpolator.LINEAR);
		mover.setFromX(FlappyApp.ANCHO);
		mover.setToX(-getImage().getWidth());
		mover.setDuration(duration);
		mover.setOnFinished(e -> {
			mover.playFromStart();
		});
		
	}
	
	public void mover() {
		mover.playFrom(Duration.seconds(Math.random() * mover.getDuration().toSeconds()));
	}
	
	public void pause() {
		mover.pause();
	}
	
	public void resume() {
		mover.play();
	}
}	


