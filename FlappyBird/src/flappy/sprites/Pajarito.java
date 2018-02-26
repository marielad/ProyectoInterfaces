package flappy.sprites;

import flappy.app.FlappyApp;
import gamefx.Sprite;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Pajarito extends Sprite {
	
	private static final String PAJARITO = "/flappy/resources/redBird.png";
 	
 	private boolean saltando = false, pausado = false;
 	
 	private TranslateTransition saltar, caer;
 	private RotateTransition rotarPajarito;
 	private Timeline aleteo;
 	
 	int score = 0;
 	
 	public Pajarito(String url) {
 		super(url, 3, 3);
 		
		KeyFrame ini = new KeyFrame(Duration.millis(0), new KeyValue(imageIndexProperty(), 0));
        KeyFrame fin = new KeyFrame(Duration.millis(100), new KeyValue(imageIndexProperty(), 2));

 		aleteo = new Timeline(ini, fin);
 		aleteo.setAutoReverse(true);
 		aleteo.setCycleCount(Timeline.INDEFINITE);//Preguntar a Fran
 		
 		saltar = new TranslateTransition();
 		saltar.setNode(this);
 		saltar.setInterpolator(Interpolator.EASE_OUT);
 		saltar.setCycleCount(1);
        saltar.setByY(-50);
        saltar.setOnFinished((finishedEvent) -> {
            rotarPajarito.setDuration(Duration.millis(100));
            rotarPajarito.setToAngle(20);
            rotarPajarito.stop();
            rotarPajarito.play();
            saltando = false;
            caer.play();
        });
        
 		rotarPajarito = new RotateTransition();
 		rotarPajarito.setNode(this);
 		rotarPajarito.setCycleCount(1);
 		
 		caer = new TranslateTransition();
 		caer.setNode(this);
 		caer.setCycleCount(TranslateTransition.INDEFINITE);
 		caer.setByY(FlappyApp.ALTO);
 		caer.setInterpolator(Interpolator.EASE_IN);
 		caer.setDuration(Duration.seconds(3));

	}
 	
 	public Pajarito() {
 		this(PAJARITO);
 	}
	
	public void jump() {
        caer.stop();
        rotarPajarito.stop();
        saltar.stop();
        
 		rotarPajarito.setDuration(Duration.millis(50));
        rotarPajarito.setToAngle(-20);
        rotarPajarito.play();
            
        saltar.play();

        saltando = true;
	}
 	
 	public boolean isSaltando() {
		return saltando;
	}
 	
 	public boolean isPausado() {
		return pausado;
	}
 	
 	public void start() {
 		caer.play();
 		aleteo.playFromStart();
 		pausado = false;
 	}
 	
 	public void pause() {
 		caer.pause();
 		aleteo.pause();
 		pausado = true;
 	}
 	
 	public void resume() {
 		caer.play();
 		aleteo.play();
 		pausado = false;
 	}
 	
 	public void stop() {
 		caer.stop();
 		aleteo.stop();
 	}
 	
}
