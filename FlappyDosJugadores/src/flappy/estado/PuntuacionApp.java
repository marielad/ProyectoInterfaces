package flappy.estado;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PuntuacionApp extends Application {
	
	public static Stage primaryStage;
	public static Scene scene;
	private Puntuacion controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		PuntuacionApp.primaryStage = primaryStage;
		controller = new Puntuacion();
		
		scene = new Scene(controller.getView(), 600, 400);
		scene.getStylesheets().add(getClass().getResource("/flappy/css/PuntuacionStyle.css").toExternalForm());
		
		primaryStage.getIcons().add(new Image("/flappy/recurso/flappyIcon.png"));
		primaryStage.setTitle("Highscores");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}
	
	public Scene getScene() {
		scene.getStylesheets().clear();
		return scene;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	

}
