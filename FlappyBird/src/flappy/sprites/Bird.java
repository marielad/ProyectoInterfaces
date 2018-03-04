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
/**
 * 
 * @author Jorge Delgado, Mariela Dorta
 *
 */
public class Bird extends Sprite {
	
	private static final String PAJARITO = "/flappy/resources/RedBird.png";
 	
 	private boolean saltando = false, pausado = false;
 	
 	private TranslateTransition saltar, caer;
 	private RotateTransition rotarPajarito;
 	private Timeline aleteo;
 	
 	private Ellipse shape;
 	
 	//Pruebas Jorge
	public static IntegerProperty score = new SimpleIntegerProperty(0);
	public static IntegerProperty scoreTwo = new SimpleIntegerProperty(0);

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
 	
	/**
	 * Constructor por defecto
	 */
 	public Bird() {
 		this(PAJARITO);
 	}
	
 	/**
 	 * jump() es un m�todo que se utiliza para hacer que el pajarito salte
 	 */
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
 	
	/**
	 * isSaltando es un m�todo para saber si el pajarito est� saltando o no
	 * @return es un Boolean 
	 */
 	public boolean isSaltando() {
		return saltando;
	}
 	
 	/**
 	 * isPausado() es un m�todo que dice si el pajarito esta pausado o no
 	 * @return devuelve un Boolean
 	 */
 	public boolean isPausado() {
		return pausado;
	}
 	
	/**
	 *  start() es un m�todo que inicializa las transiciones del pajarito	
	 */
 	public void start() {
 		caer.play();
 		aleteo.playFromStart();
 		pausado = false;
 	}
 	
 	/**
	 *  pause() es un m�todo que pausa las transiciones del pajarito	
	 */
 	public void pause() {
 		caer.pause();
 		saltar.pause();
 		aleteo.pause();
 		pausado = true;
 	}
 	
 	/**
	 *  resume() es un m�todo que reanuda las transiciones del pajarito	
	 */
 	public void resume() {
 		caer.play();
 		aleteo.play();
 		pausado = false;
 	}
 	
 	/**
	 *  stop() es un m�todo que para las transiciones del pajarito	
	 */
 	public void stop() {
 		caer.stop();
 		aleteo.stop();
 		pausado = true;
 	}
 	
 	/**
 	 * aleteo() es un m�todo que inicializa la transici�n infinita del pajarito volando
 	 */
 	public void aleteo() {
 		aleteo.playFromStart();
 	}

 	/**
 	 * getScore es un getter del IntegerProperty score
 	 * @return score
 	 */
	public final IntegerProperty getScore() {
		return score;
	}

	/**
	 * setScore es un setter del IntegerProperty score
	 * @param score
	 */
	public final void setScore(final int score) {
		Bird.score.set(score);
	}
	
	public final IntegerProperty getScoreTwo() {
		return scoreTwo;
	}

	public final void setScoreTwo(final int score) {
		Bird.scoreTwo.set(score);
	}
 	
}
