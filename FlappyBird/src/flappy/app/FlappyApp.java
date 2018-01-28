package flappy.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FlappyApp extends Application {
	
	public static Stage primaryStage;
	public static Scene scene;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		FlappyApp.primaryStage = primaryStage;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Scene getScene() {
		return scene;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
}