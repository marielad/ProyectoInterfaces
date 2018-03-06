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

/**
 * 
 * @author Jorge Delgado, Mariela Dorta, Fran Vargas
 *
 */

public class Tube extends Group {

	private static final String TUBO_UP = "/flappy/resources/flappyTubeUp.png";
	private static final String TUBO_DOWN ="/flappy/resources/flappyTubeDown.png";
	
	private static final double DISTANCIA_MOVIMIENTO = - 70;
	
	public static boolean animada = false;
	public static long lastUpdate = 0;
	
	private TranslateTransition animacion;
	
	private double diferencia;
	private DoubleProperty posicionHueco;
	
	private Sprite topTube, bottomTube, middleTube;
		
	/**
	 * Constructor
	 * @param screenWidth Le pasamos el ancho de la pantalla
	 * @param screenHeight Le pasamos el alto de la pantalla
	 * @param animate Es un boolean que indica si se quiere animar el tubo verticalmente
	 * @param rotate Es un boolean que indica si se quiere rotar el tubo
	 */
	
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
		
		middleTube = new Sprite(TUBO_DOWN);
	    middleTube.setFitWidth(1);
	    middleTube.setFitHeight(425);
	    middleTube.setX(screenWidth+37.5);
	    middleTube.setY(0);
	    middleTube.setOpacity(0);
		
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
		

	    Rectangle middleTubeShape = new Rectangle();
	    middleTubeShape.widthProperty().bind(middleTube.fitWidthProperty());
	    middleTubeShape.heightProperty().bind(middleTube.fitHeightProperty());
	    middleTubeShape.xProperty().bind(middleTube.xProperty());
	    middleTubeShape.yProperty().bind(middleTube.yProperty());
	    middleTubeShape.setVisible(false);
	    middleTube.setShape(middleTubeShape);

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

		getChildren().addAll(topTube, bottomTube, middleTube, topTube.getShape(), bottomTube.getShape(), middleTube.getShape());
	}
	
	/**
	 * play() es un método que inicia las transiciones de las tuber�as
	 */
	
	public void play() {
		animacion.play();
	}

	/**
	 * pause() es un método que pausa las transiciones de las tuber�as
	 */
	
	public void pause() {
		animacion.pause();
	}
	
	/**
	 * stop() es un método que para las transiciones de las tuber�as
	 */
	
	public void stop() {
		animacion.stop();
	}
	
	/**
	 * Método que devuelve el Shape de los tubos
	 * @return Shape.union(topTube.getShape(), bottomTube.getShape());
	 */
	
	public Shape getShape() {
		return Shape.union(topTube.getShape(), bottomTube.getShape());
	}
	
	/**
	 * Método que devuelve el Shape del tubo auxiliar que se utiliza para las puntuaciones
	 * @return middleTube.getShape();
	 */
	
	public Shape getMiddleShape() {
	    return middleTube.getShape();
	  }

}
