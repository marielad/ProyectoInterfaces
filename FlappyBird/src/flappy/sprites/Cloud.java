package flappy.sprites;

import flappy.app.FlappyApp;
import gamefx.Sprite;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

/**
 * 
 * @author Jorge Delgado, Mariela Dorta, Fran Vargas
 *
 */

public class Cloud extends Sprite {
	
	private static final String NUBE = "/flappy/resources/flappyCloud.png";
	
 	private TranslateTransition mover;

 	/**
 	 * Constructor de nubes 
 	 * @param duration se le pasa para darle una duración a cada nube
 	 */
 	
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
	
	/**
	 * mover() es un método que inicializa las transiciones de cada nube dándole una velocidad diferente gracias al Duration	
	 */
	
	public void mover() {
		mover.playFrom(Duration.seconds(Math.random() * mover.getDuration().toSeconds()));
	}
	
	/**
	 * pause() es un método que pausa las transiciones de las nubes	
	 */
	
	public void pause() {
		mover.pause();
	}
	
	/**
	 * resume() es un método que reanuda las transiciones de las nubes	
	 */
	
	public void resume() {
		mover.play();
	}
}	


