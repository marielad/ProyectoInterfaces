package gamefx;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

/**
 * Clase que se encarga de manejar el AnimationTimer que
 * por defecto se ejecuta a 60FPS y que además se encarga
 * del manejo del teclado. Es la clase padre
 * 
 * @author Jorge Delgado, Mariela Dorta, Fran Vargas
 *
 */
public abstract class Screen extends StackPane implements Initializable {

	private AnimationTimer gameLoop;

	/**
	 * Constructor de Screen 
	 * @param url Url es la ruta del fxml que le queremos pasar
	 * @throws IOException
	 */
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

	/**
	 * Se utiliza para sobreescribir el gameLoop
	 * @param now
	 */
	protected void loop(long now) {}
	
	/**
	 * Se utiliza para darle funcionalidad a las teclas cuando se pulsan
	 * @param e
	 */
	protected void onKeyPressed(KeyEvent e) {}

	/**
	 * Comienza el gameloop
	 */
	public void start() {
		gameLoop.start();
		Platform.runLater(() -> requestFocus());
	}
	
	/**
	 * Para el gameloop
	 */
	public void stop() {
		gameLoop.stop();
	}
}
