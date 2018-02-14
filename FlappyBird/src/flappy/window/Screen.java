package flappy.window;

import java.util.ArrayList;

import flappy.sound.Reproductor;
import flappy.sprites.Nube;
import flappy.sprites.Tuberia;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public class Screen {

	public static long lastUpdate = 0;

	public static boolean decidirMovimiento = false;

	public static final int ancho = 1000;
	public static final int alto = 425;

	public static AnimationTimer nubesLoop, tubosLoop, animacionTubo;
	
	Reproductor musicaMenu = new Reproductor("bitVenture.mp3");
	Reproductor musicaJuego = new Reproductor("attackOnTitan.mp3");

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

					if (listaNubes.get(i).getSprite().getX() < - 175 * listaNubes.get(i).getSprite().getScaleX()) {
						//175 pixeles de reaparicion, y desaparicion
						listaNubes.get(i).getSprite().setX(listaNubes.get(i).getSprite().getX() + ancho
								+ 175 * listaNubes.get(i).getSprite().getScaleX());

					}
					//velocidad de las nubes
					listaNubes.get(i).getSprite().setX(listaNubes.get(i).getSprite().getX() - (i / 3 + 0.5));
					
				}

			}

		};

		nubesLoop.start();

	}

	public void creacionTubos(Pane juego) {

		listaTubos = new ArrayList<>();

		for (int i = 0; i < 5; i++) {

			Tuberia tubo = new Tuberia(false, false);
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
					Tuberia tubo;
					//añadir dificultades en el futuro menu de seleccion
					if (Math.random() < 0.4) {
						tubo = new Tuberia(true, false);
					} else if (Math.random() > 0.85) {
						tubo = new Tuberia(true, true);
					} else {
						tubo = new Tuberia(false, false);
					}

					tubo.setTranslateX(listaTubos.get(listaTubos.size() - 1).getTranslateX() + (ancho / 4));

					listaTubos.add(tubo);
					juego.getChildren().add(tubo);
					
				}

				for (int i = 0; i < listaTubos.size(); i++) {
					// velocidad horizontal de los tubos
					listaTubos.get(i).setTranslateX(listaTubos.get(i).getTranslateX() - 3);

				}

			}

		};

		tubosLoop.start();

	}
}
