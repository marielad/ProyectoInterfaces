package flappy.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.flappybird.GameOver;
import dad.flappybird.Tubos;
import flappy.app.FlappyApp;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class Juego extends Screen implements Initializable {

	@FXML
	private Pane paneNubes, paneJuego, panePuntuacion;

	StackPane vistaJuego = new StackPane();

	public Juego() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/view/GameView.fxml"));
			loader.setController(this);
			loader.load();
			vistaJuego = loader.getRoot();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		musicaJuego.play();
		creacionJuego(paneJuego);
		for (int i = 0; i < listaNubes.size(); i++) {
			paneNubes.getChildren().add(listaNubes.get(i).getSprite());
		}
		
		FlappyApp.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					if(FlappyApp.scene == vistaJuego.getScene()) {
						musicaJuego.stop();
						tubosLoop.stop();
						FlappyApp.scene.setRoot(FlappyApp.menuControl.getMenuView());
					}
				}
			}
		});
	}

//    private void checkCollisions() {
//    	
//        boolean intersection = !(p1.getElements().isEmpty()
//                && p2.getElements().isEmpty()
//                && p3.getElements().isEmpty()
//                && p4.getElements().isEmpty());
//        if (pajarito.getLimites().getCenterY() + pajarito.getLimites().getRadiusY() > alto || pajarito.getLimites().getCenterY() - pajarito.getLimites().getRadiusY() < 0) {
//            intersection = true;
//        }
//        if (intersection) {
//            GameOver gameOverLabel = new GameOver(ancho / 2, alto / 2);
//            puntuacionMasAlta = puntuacionMasAlta < puntuacion ? puntuacion : puntuacionMasAlta;
//            gameOverLabel.setText("Tap to retry. Score: " + puntuacion + "\n\tHighScore: " + puntuacionMasAlta);
//            saveHighScore();
//            root.getChildren().add(gameOverLabel);
//            root.getChildren().get(1).setOpacity(0);
//            gameOver = true;
//            gameLoop.stop();
//        }
//    }
	
	public StackPane getJuegoView() {
		return vistaJuego;
	}

}