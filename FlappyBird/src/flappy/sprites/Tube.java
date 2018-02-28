package flappy.sprites;

import gamefx.Sprite;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Tube extends Group {

	private static final String TUBO_UP = "/flappy/resources/flappyTubeUp.png";
	private static final String TUBO_DOWN ="/flappy/resources/flappyTubeDown.png";
	
	private static final double DISTANCIA_MOVIMIENTO = - 70;
	
	public static boolean animada = false;
	public static long lastUpdate = 0;
	
	private TranslateTransition animacion;
	
	private double diferencia;
	private DoubleProperty posicionHueco;
	
	Rectangle collisionsTubeUp, collisionsTubeDown;
	
	Sprite tubeUp, tubeDown;
	
	public Tube(double screenWidth, double screenHeight, boolean animate, boolean rotate) {

		posicionHueco = new SimpleDoubleProperty(this, "posicionHueco");
		posicionHueco.set(Math.random() * (screenHeight / 3) + 200);
		diferencia = posicionHueco.get();		
		
		tubeUp = new Sprite(TUBO_UP);
		// Tamaño del hueco entre tubeUp y tubeDown
		tubeUp.setFitWidth(75);
		tubeUp.setFitHeight(400);
		tubeUp.setX(screenWidth);
		tubeUp.setY(diferencia - screenHeight * 1.2);

		tubeDown = new Sprite(TUBO_DOWN);
		tubeDown.setFitWidth(75);
		tubeDown.setFitHeight(400);
		tubeDown.setX(screenWidth);
		tubeDown.setY(diferencia);
		
		collisionsTubeUp = new Rectangle();
		collisionsTubeUp.widthProperty().bind(tubeUp.fitWidthProperty());
		collisionsTubeUp.heightProperty().bind(tubeUp.fitHeightProperty());
		collisionsTubeUp.yProperty().bind(posicionHueco);
		
		collisionsTubeDown = new Rectangle();
		collisionsTubeDown.widthProperty().bind(tubeDown.fitWidthProperty());
		collisionsTubeDown.heightProperty().bind(tubeDown.fitHeightProperty());
		collisionsTubeDown.yProperty().bind(posicionHueco.add(120).add(tubeUp.fitHeightProperty()));

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
	
	public Rectangle getCollisionsTubeUp() {
		return collisionsTubeUp;
	}
	
	public Bounds getCollisionsTubeDown() {
		return collisionsTubeDown.getLayoutBounds();
	}
	
}
