package flappy.window;

import java.util.ArrayList;

import flappy.sound.Reproductor;
import flappy.sprites.Nube;
import flappy.sprites.Pajarito;
import flappy.sprites.Tuberia;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Screen{


	public static final int ancho = 1000;
	public static final int alto = 425;
	
	public static final int POSICIONX_PAJARITO = 200;
	public static final int POSICIONY_PAJARITO = 75;
	public static final int POSICIONZ_PAJARITO = 75;
	
	public static final int ESPACIO_NUBES_DERECHA = 175;
	public static final int ESPACIO_NUBES_IZQUIERDA = 250;
	
	public static final int ESPACIO_TUBOS_DERECHA = 600;
	public static final int ESPACIO_TUBOS_IZQUIERDA = 800;
	
	public static AnimationTimer nubesLoop, tubosLoop;
	
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

					if (listaNubes.get(i).getSprite().getX() < - ESPACIO_NUBES_IZQUIERDA * listaNubes.get(i).getSprite().getScaleX()) {
						//175 pixeles de reaparicion, y desaparicion
						listaNubes.get(i).getSprite().setX(listaNubes.get(i).getSprite().getX() + ancho
								+ ESPACIO_NUBES_DERECHA * listaNubes.get(i).getSprite().getScaleX());

					}
					//velocidad de las nubes
					listaNubes.get(i).getSprite().setX(listaNubes.get(i).getSprite().getX() - (i / 3 + 0.5));
				}
			}
		};

		nubesLoop.start();

	}

	public void creacionJuego(Pane juego) {

		listaTubos = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {

			Tuberia tubo = new Tuberia(false, false);
			tubo.setTranslateX(i * (ancho / 4));
			tubo.setTranslateZ(POSICIONZ_PAJARITO);
			listaTubos.add(tubo);
			juego.getChildren().add(tubo);

		}

		tubosLoop = new AnimationTimer() {

			@Override
			public void handle(long now) {

				Pajarito pajaro = new Pajarito(new ImageView(new Image(getClass().getResourceAsStream("/flappy/resources/yellowBird.png"))));
				pajaro.animacion.setPositionY(POSICIONX_PAJARITO);
				pajaro.animacion.setPositionX(POSICIONY_PAJARITO);
				pajaro.animacion.getSprite().setTranslateZ(POSICIONY_PAJARITO);
				pajaro.animacion.setCycleCount(Animation.INDEFINITE);
				pajaro.animacion.play();
				juego.getChildren().add(pajaro);

				if (listaTubos.get(0).getTranslateX() < -ancho - ESPACIO_TUBOS_DERECHA ) {	
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
					juego.getChildren().addAll(tubo);
					
					if (listaTubos.get(0).getTranslateX() < -ancho - ESPACIO_TUBOS_IZQUIERDA ) {
						listaTubos.remove(0);
						juego.getChildren().remove(listaTubos.get(0));
					}
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
