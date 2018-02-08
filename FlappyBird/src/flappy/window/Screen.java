package flappy.window;

import java.util.ArrayList;

import flappy.sprites.Nube;
import flappy.sprites.Tuberia;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

public class Screen {

	long lastUpdate = 0;

	boolean decidirMovimiento = false;

	public static final int ancho = 1000;
	public static final int alto = 400;

	public static AnimationTimer nubesLoop, tubosLoop, animacionTubo;

	public static ArrayList<Nube> listaNubes;
	public static ArrayList<Tuberia> listaTubos;

	public Screen() {

	}

	public void creacionNubes() {

		listaNubes = new ArrayList<>();

		for (int i = 0; i < 10; i++) {

			Nube nube = new Nube();
			listaNubes.add(nube);

		}

		nubesLoop = new AnimationTimer() {

			@Override
			public void handle(long timestamp) {

				for (int i = 0; i < listaNubes.size(); i++) {

					if (listaNubes.get(i).getSprite().getX() < -100 * listaNubes.get(i).getSprite().getScaleX()) {

						listaNubes.get(i).getSprite().setX(listaNubes.get(i).getSprite().getX() + ancho
								+ 100 * listaNubes.get(i).getSprite().getScaleX());

					}

					listaNubes.get(i).getSprite().setX(listaNubes.get(i).getSprite().getX() - (i / 3 + 0.75));

				}

			}

		};

		nubesLoop.start();

	}

	public void creacionTubos(Pane juego) {

		listaTubos = new ArrayList<>();

		for (int i = 0; i < 5; i++) {

			Tuberia tubo = new Tuberia();
			tubo.setTranslateX(i * (ancho / 4));
			listaTubos.add(tubo);
			juego.getChildren().add(tubo);

		}

		tubosLoop = new AnimationTimer() {

			@Override
			public void handle(long now) {

				if (listaTubos.get(0).getTranslateX() <= -ancho - 600) {

					listaTubos.remove(0);
					juego.getChildren().remove(0);

					Tuberia tubo = new Tuberia();
					tubo.setTranslateX(listaTubos.get(listaTubos.size() - 1).getTranslateX() + (ancho / 4));

					if ((Math.random() * 2) > 1.3) {

						tubo.setRotationAxis(Rotate.Z_AXIS);
						tubo.setRotate(-15 + 30 * Math.random());
						tubo.setTranslateY(-40);

					}

					if ((Math.random() * 2) > 0.1) {

						animacionTubo = new AnimationTimer() {

							@Override
							public void handle(long now) {

								if (now - lastUpdate >= 1000_000_000) {

									if (decidirMovimiento == false) {

										decidirMovimiento = true;

									} else if (decidirMovimiento == true) {

										decidirMovimiento = false;

									}

									lastUpdate = now;

								}

								if (decidirMovimiento == true) {

									tubo.setTranslateY(tubo.getTranslateY() + 0.75);

								}

								if (decidirMovimiento == false) {

									tubo.setTranslateY(tubo.getTranslateY() - 0.75);

								}

							}

						};

						animacionTubo.start();

					}

					if ((Math.random() * 2) > 0.1) {

						animacionTubo = new AnimationTimer() {

							@Override
							public void handle(long now) {

								if (now - lastUpdate >= 1000_000_000) {

									if (decidirMovimiento == false) {

										decidirMovimiento = true;

									} else if (decidirMovimiento == true) {

										decidirMovimiento = false;

									}

									lastUpdate = now;

								}

								if (decidirMovimiento == false) {

									tubo.setTranslateY(tubo.getTranslateY() + 0.75);

								}

								if (decidirMovimiento == true) {
									// velocidad vertical de los tubos
									tubo.setTranslateY(tubo.getTranslateY() - 0.75);

								}

							}

						};

						animacionTubo.start();

					}

					listaTubos.add(tubo);
					juego.getChildren().add(tubo);
					
				}

				for (int i = 0; i < listaTubos.size(); i++) {

					// velocidad horizontal de los tubos
					listaTubos.get(i).setTranslateX(listaTubos.get(i).getTranslateX() - 5);

				}

			}

		};

		tubosLoop.start();

	}
}
