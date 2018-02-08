package flappy.app;

import java.io.IOException;

import flappy.window.Menu;
import flappy.window.Screen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FlappyApp extends Application {

	public static Stage primaryStage;
	public static Scene scene;
	public static Menu menuControl;

	@Override
	public void start(Stage primaryStage) throws IOException {

		FlappyApp.primaryStage = primaryStage;
		menuControl = new Menu();

		scene = new Scene(menuControl.getMenuView(), Screen.ancho, Screen.alto);
		Font.loadFont(FlappyApp.class.getResource("/flappy/style/Flappy-Bird.ttf").toExternalForm(), 0);
		scene.getStylesheets().add(getClass().getResource("/flappy/style/estilo.css").toExternalForm());

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

}