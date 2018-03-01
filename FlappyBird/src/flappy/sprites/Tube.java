package flappy.sprites;

import gamefx.Sprite;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
	
	private Sprite topTube, bottomTube;
		
	public Tube(double screenWidth, double screenHeight, boolean animate, boolean rotate) {

		posicionHueco = new SimpleDoubleProperty(this, "posicionHueco");
		posicionHueco.set(Math.random() * (screenHeight / 3) + 200);
		diferencia = posicionHueco.get();		

		topTube = new Sprite(TUBO_UP);
		topTube.setFitWidth(75);
		topTube.setFitHeight(400);
		topTube.setX(screenWidth);
		topTube.setY(diferencia - screenHeight * 1.2);

		bottomTube = new Sprite(TUBO_DOWN);
		bottomTube.setFitWidth(75);
		bottomTube.setFitHeight(400);
		bottomTube.setX(screenWidth);
		bottomTube.setY(diferencia);
		
		Rectangle topTubeShape = new Rectangle();
		topTubeShape.widthProperty().bind(topTube.fitWidthProperty());
		topTubeShape.heightProperty().bind(topTube.fitHeightProperty());
		topTubeShape.xProperty().bind(topTube.xProperty());
		topTubeShape.yProperty().bind(topTube.yProperty());
		topTubeShape.setVisible(false);
		topTube.setShape(topTubeShape);

		Rectangle bottomTubeShape = new Rectangle();
		bottomTubeShape.widthProperty().bind(bottomTube.fitWidthProperty());
		bottomTubeShape.heightProperty().bind(bottomTube.fitHeightProperty());
		bottomTubeShape.xProperty().bind(bottomTube.xProperty());
		bottomTubeShape.yProperty().bind(bottomTube.yProperty());
		bottomTubeShape.setVisible(false);
		bottomTube.setShape(bottomTubeShape);

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

		getChildren().addAll(topTube, bottomTube, topTube.getShape(), bottomTube.getShape());
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
	
	public Shape getShape() {
		return Shape.union(topTube.getShape(), bottomTube.getShape());
	}
	
}
