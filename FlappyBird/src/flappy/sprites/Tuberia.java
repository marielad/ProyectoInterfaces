package flappy.sprites;

import flappy.window.Screen;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class Tuberia extends Group {

	public double posicionHueco = Math.random() * (Screen.alto/3) + 200;
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

				Screen.animacionTubo = new AnimationTimer() {

					@Override
					public void handle(long now) {

						if (now - Screen.lastUpdate >= 1250000000) {

							if (Screen.decidirMovimiento) {
								Screen.decidirMovimiento = false;
							} else {
								Screen.decidirMovimiento = true;
							}

							Screen.lastUpdate = now;

						}

						if (Screen.decidirMovimiento) {
							setTranslateY(getTranslateY() + 0.70);
						} else {
							setTranslateY(getTranslateY() - 0.70);
						}

					}

				};

				Screen.animacionTubo.start();

			} else {
				
				Screen.animacionTubo = new AnimationTimer() {

					@Override
					public void handle(long now) {

						if (now - Screen.lastUpdate >= 1250000000) {

							if (!Screen.decidirMovimiento) {
								Screen.decidirMovimiento = true;
							} else {
								Screen.decidirMovimiento = false;
							}
							
							Screen.lastUpdate = now;
							
						}

						if (!Screen.decidirMovimiento) {
							setTranslateY(getTranslateY() + 0.70);
						} else {
							// velocidad vertical de los tubos
							setTranslateY(getTranslateY() - 0.70);
						}

					}

				};

				Screen.animacionTubo.start();
				
			}

		}

		getChildren().addAll(tubeUp.getSprite(), tubeDown.getSprite());

	}
}
