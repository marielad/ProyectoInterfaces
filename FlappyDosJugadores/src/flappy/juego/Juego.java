package flappy.juego;

import java.io.IOException;
import java.util.ArrayList;

import flappy.animacion.MovimientoNube;
import flappy.app.GameApp;
import flappy.objeto.Tuberia;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class Juego {

	Tuberia tubo;

	GameApp main;

	BorderPane vistaJuego;

	MovimientoNube nubes;

	Timeline gameLoop;
	
	private static final double FPS_60 = 60;

	ArrayList<Tuberia> listaTubos = new ArrayList<>();

	public Juego() throws IOException {

		main = new GameApp();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/vista/GameView.fxml"));
		loader.setController(this);
		loader.load();

		vistaJuego = loader.getRoot();

		iniciarJuego();
	}

	private void iniciarJuego() {

		gameLoop = new Timeline(new KeyFrame(Duration.millis(1000 / FPS_60), new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				if (listaTubos.get(0).getTranslateX() <= -1000 / 11.4) {

					listaTubos.remove(0);

					tubo = new Tuberia();
					tubo.setTranslateX(listaTubos.get(listaTubos.size() - 1).getTranslateX() + (1000 / 4 + 10));
					tubo.getChildren().addAll(tubo.getTuboAnchoInf(), tubo.getTuboAnchoSup(), tubo.getTuboLargoInf(),
							tubo.getTuboLargoSup());
					
					listaTubos.add(tubo);
					vistaJuego.getChildren().add(tubo);

				}

				for (int i = 0; i < listaTubos.size(); i++) {
					listaTubos.get(i).setTranslateX(listaTubos.get(i).getTranslateX() - 2.5);
				}
			}
		}));

		gameLoop.setCycleCount(-1);
		inicializarJuego();
	}

	private void inicializarJuego() {

		vistaJuego.getChildren().clear();
		
		nubes = new MovimientoNube();

		vistaJuego.getChildren().addAll(nubes.getListaNubesRapidas());
		vistaJuego.getChildren().addAll(nubes.getListaNubesLentas());

		for (int i = 0; i < 5; i++) {
			
			tubo = new Tuberia();
			tubo.setTranslateX(i * (1000 / 4 + 10) + 400);
			tubo.getChildren().addAll(tubo.getTuboAnchoInf(), tubo.getTuboAnchoSup(), tubo.getTuboLargoInf(),
					tubo.getTuboLargoSup());
			
			listaTubos.add(tubo);
			vistaJuego.getChildren().addAll(tubo);
		}
		
		gameLoop.play();
	}

	public BorderPane getJuegoView() {
		return vistaJuego;
	}

}