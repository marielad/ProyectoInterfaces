package flappy.sprites;

import gamefx.Sprite;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Tuberia extends Group {

	private static final String TUBO_UP = "/flappy/resources/flappyTubeUp.png";
	private static final String TUBO_DOWN ="/flappy/resources/flappyTubeDown.png";
	
	private static final double DISTANCIA_MOVIMIENTO = - 70;
	
	public static boolean decidirMovimiento = false, animada = false;
	public static long lastUpdate = 0;
	
	private TranslateTransition animacion;
	
	private double posicionHueco;
	private double diferencia = posicionHueco;
	
	private Sprite tubeUp, tubeDown;

	public Tuberia(double screenWidth, double screenHeight, boolean animate, boolean rotate) {

		posicionHueco = Math.random() * (screenHeight / 3) + 200;
		diferencia = posicionHueco;		
		
		tubeUp = new Sprite(TUBO_UP);
		// Tamaño del hueco entre tubeUp y tubeDown
		tubeUp.setFitWidth(75);
		tubeUp.setFitHeight(320);
		tubeUp.setX(screenWidth);
		tubeUp.setY(diferencia - screenHeight);

		tubeDown = new Sprite(TUBO_DOWN);
		tubeDown.setFitWidth(75);
		tubeDown.setFitHeight(320);
		tubeDown.setX(screenWidth);
		tubeDown.setY(diferencia);


		if (rotate) {
			setRotationAxis(Rotate.Z_AXIS);
			setRotate(-15 + 30 * Math.random());
			setTranslateY(-40);
		}
	
		if (animate) {
			animacion = new TranslateTransition();
			animacion.setNode(this);
			animacion.setInterpolator(Interpolator.EASE_BOTH);
			animacion.setCycleCount(Animation.INDEFINITE);
			animacion.setByY(DISTANCIA_MOVIMIENTO);
			animacion.setAutoReverse(true);
			animacion.setDuration(Duration.seconds(1));
			animacion.playFromStart();
			
		}
		else {
			animacion = new TranslateTransition();
			animacion.setInterpolator(Interpolator.EASE_BOTH);
			animacion.setCycleCount(Animation.INDEFINITE);
			animacion.setByY(DISTANCIA_MOVIMIENTO);
			animacion.setAutoReverse(true);
			animacion.setDuration(Duration.seconds(0));
			animacion.playFromStart();
		}

		getChildren().addAll(tubeUp, tubeDown);
	}
	public void play() {
		animacion.play();
	}

	public void pause() {
		animacion.pause();
	}
	
	public void stop() {
		animacion.stop();
	}
}
