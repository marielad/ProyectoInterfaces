package gamefx;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public abstract class Screen extends StackPane implements Initializable {

	private AnimationTimer gameLoop;

	public Screen(String url) throws IOException {
		gameLoop = new AnimationTimer() {
			public void handle(long now) {
				loop(now);
			}
		};
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
		loader.setController(this);
		loader.setRoot(this);
		loader.load();
		
		sceneProperty().addListener((o, ov, nv) -> {
			if (nv != null) start();
			else stop();
		});
		
		setOnKeyPressed(e -> onKeyPressed(e));		
	}

	protected void loop(long now) {}
	protected void onKeyPressed(KeyEvent e) {}

	public void start() {
		gameLoop.start();
		Platform.runLater(() -> requestFocus());
	}
	
	public void stop() {
		gameLoop.stop();
	}
}
