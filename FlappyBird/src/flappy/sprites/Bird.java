package flappy.sprites;

import flappy.app.FlappyApp;
import gamefx.Sprite;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public class Bird extends Sprite {
	
	private static final String PAJARITO = "/flappy/resources/redBird.png";
 	
 	private boolean saltando = false, pausado = false;
 	
 	private TranslateTransition saltar, caer;
 	private RotateTransition rotarPajarito;
 	private Timeline aleteo;
 	
 	private Ellipse shape;
 	
 	//Pruebas Jorge
	public static IntegerProperty score = new SimpleIntegerProperty(0);

	public Bird(String url) {
 		super(url, 3, 3);
 		
		KeyFrame ini = new KeyFrame(Duration.millis(0), new KeyValue(imageIndexProperty(), 0));
        KeyFrame fin = new KeyFrame(Duration.millis(100), new KeyValue(imageIndexProperty(), 2));

 		aleteo = new Timeline(ini, fin);
 		aleteo.setAutoReverse(true);
 		aleteo.setCycleCount(Timeline.INDEFINITE);
 		
 		saltar = new TranslateTransition();
 		saltar.setNode(this);
 		saltar.setInterpolator(Interpolator.EASE_OUT);
 		saltar.setDuration(Duration.seconds(0.30));
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
 		
 		//He quitado 1 seg de retardo en la caida del pajaro
 		caer = new TranslateTransition();
 		caer.setNode(this);
 		caer.setCycleCount(TranslateTransition.INDEFINITE);
 		caer.setByY(FlappyApp.ALTO);
 		caer.setInterpolator(Interpolator.EASE_IN);
 		caer.setDuration(Duration.seconds(2));
 		
 		shape = new Ellipse();
		shape.centerXProperty().bind(translateXProperty().add(widthProperty().divide(2)));
		shape.centerYProperty().bind(translateYProperty().add(heightProperty().divide(2)));
		shape.rotateProperty().bind(rotateProperty());
		shape.radiusXProperty().bind(widthProperty().divide(2));
		shape.radiusYProperty().bind(heightProperty().divide(2));
		shape.setVisible(false);
		setShape(shape);

	}
 	
 	public Bird() {
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
 		saltar.pause();
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
 	
 	public void aleteo() {
 		aleteo.playFromStart();
 	}

	public final IntegerProperty getScore() {
		return score;
	}

	public final void setScore(final int score) {
		Bird.score.set(score);
	}
 	
}
