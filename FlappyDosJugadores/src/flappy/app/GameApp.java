package flappy.app;

import java.io.IOException;

import flappy.menu.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameApp extends Application {
	
	public static Stage primaryStage;
	public static Scene scene;
	private Menu menuControl;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		GameApp.primaryStage = primaryStage;
		menuControl = new Menu();
		
		scene = new Scene(menuControl.getVistaMenu(), 1000, 400);
		scene.getStylesheets().add(getClass().getResource("/flappy/css/style.css").toExternalForm());
		
		primaryStage.getIcons().add(new Image("/flappy/recurso/flappyIcon.png"));
		primaryStage.setTitle("Flappy Bird");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
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