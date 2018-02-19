package flappy.sprites;

import flappy.window.Screen;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class Tuberia extends Group {

	private static final double DISTANCIA_MOVIMIENTO = 0.7;
	private static final long NANOSEGUNDOS = 1250000000;
	public static boolean decidirMovimiento = false;
	public static long lastUpdate = 0;
	public static AnimationTimer animacionTubo;
	
	public double posicionHueco = Math.random() * (Screen.alto / 3) + 200;
	public double diferencia = posicionHueco;
	public Sprite tubeUp, tubeDown;

	public Tuberia(boolean animate, boolean rotate) {

		tubeUp = new Sprite();
		tubeUp.setSprite(new ImageView("/flappy/resources/flappyTubeUp.png"));
		// Tamaño del hueco entre tubeUp y tubeDown
		tubeUp.setPositionY(diferencia - Screen.alto);
		tubeUp.setPositionX(1400);

		tubeDown = new Sprite();
		tubeDown.setSprite(new ImageView("/flappy/resources/flappyTubeDown.png"));
		tubeDown.setPositionY(diferencia);
		tubeDown.setPositionX(1400);

		if (rotate) {
			setRotationAxis(Rotate.Z_AXIS);
			setRotate(-15 + 30 * Math.random());
			setTranslateY(-40);
		}

		if (animate) {
			if ((Math.random() * 2) > 1.3) {
				animacionTubo = new AnimationTimer() {
					@Override
					public void handle(long now) {
						if (now - lastUpdate >= NANOSEGUNDOS) {
							if (decidirMovimiento) {
								decidirMovimiento = false;
							} else {
								decidirMovimiento = true;
							}
							lastUpdate = now;
						}
						if (decidirMovimiento) {
							setTranslateY(getTranslateY() + DISTANCIA_MOVIMIENTO);
							
						} else {
							setTranslateY(getTranslateY() - DISTANCIA_MOVIMIENTO);
						}
					}
				};

				animacionTubo.start();

			} else {
				animacionTubo = new AnimationTimer() {
					@Override
					public void handle(long now) {
						if (now - lastUpdate >= NANOSEGUNDOS) {
							if (!decidirMovimiento) {
								decidirMovimiento = true;
							} else {
								decidirMovimiento = false;
							}
							lastUpdate = now;
						}
						if (!decidirMovimiento) {
							setTranslateY(getTranslateY() + DISTANCIA_MOVIMIENTO);
						} else {
							// velocidad vertical de los tubos
							setTranslateY(getTranslateY() - DISTANCIA_MOVIMIENTO);
						}
					}
				};
				animacionTubo.start();
			}
		}

		
		getChildren().addAll(tubeUp.getSprite(), tubeDown.getSprite());
	}
}
