package flappy.app;

import java.io.IOException;

import flappy.window.About;
import flappy.window.Game;
import flappy.window.HighScore;
import flappy.window.Menu;
import flappy.window.Options;
import flappy.window.SelectCharacter;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FlappyApp extends Application {
	
	public static final int ANCHO = 1000;
	public static final int ALTO = 425;

	public static Stage primaryStage;
	private static Scene scene;

	public static Menu menu;
	public static About acercaDe;
	public static Game juego;
	public static HighScore mejores;
	public static Options opciones;
	public static SelectCharacter seleccion;

	@Override
	public void start(Stage primaryStage) throws IOException {
		FlappyApp.primaryStage = primaryStage;
		
		menu = new Menu();
		acercaDe = new About();
		juego = new Game();
		mejores = new HighScore();
		opciones = new Options();
		seleccion = new SelectCharacter();

		scene = new Scene(menu, ANCHO, ALTO);
		scene.getStylesheets().add(getClass().getResource("/flappy/style/style.css").toExternalForm());

		primaryStage.getIcons().add(new Image("/flappy/resources/flappyIcon.png"));
		primaryStage.setTitle("Flappy Bird");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void irA(Parent root) {
		scene.setRoot(root);
	}

}