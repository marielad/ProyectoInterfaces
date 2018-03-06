package gamefx;

import javafx.concurrent.Task;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 * La clase sound puede controlar todos los sonidos del videojuego
 * @author Jorge Delgado, Mariela Dorta, Fran Vargas
 *
 */

public class Sound {
	
	private MediaPlayer player;
	
	Task<MediaPlayer> tarea;

	/**
	 * Cargamos los sonidos a traves de la ruta
	 * @param ruta
	 */
	
	public Sound(String ruta) {
		String url = getClass().getResource(ruta).toExternalForm();
		player = new MediaPlayer(new Media(url));
	}

	/**
	 * Método que se utiliza para reproducir infinitamente la música
	 */
	
	public void playIndefinite() {
		tarea = new Task<MediaPlayer>() {
			protected MediaPlayer call() throws Exception {
				player.setCycleCount(MediaPlayer.INDEFINITE);
				player.play();
				return player;
			}
		};
		new Thread(tarea).start();
	}
	
	/**
	 * Método que se utiliza para reproducir solo una vez
	 */

	public void play() {
		tarea = new Task<MediaPlayer>() {
			protected MediaPlayer call() throws Exception {
				player.setCycleCount(1);
				player.play();
				return player;
			}
		};
		new Thread(tarea).start();
	}
	
	/**
	 * Método que se utiliza para pausar el sonido
	 */
	
	public void pause() {
		if(player.getStatus() == Status.PLAYING) {
			player.pause();
		}
	}
	
	/**
	 * Método que se utiliza para reanudar el sonido
	 */
	
	public void resume() {
		if(player.getStatus() == Status.PAUSED) {
			player.play();
		}
	}
	
	/**
	 * Método que se utiliza para parar el sonido
	 */

	public void stop() {
		tarea = new Task<MediaPlayer>() {
			protected MediaPlayer call() throws Exception {
				player.setCycleCount(1);
				player.stop();
				return player;
			}
		};
		new Thread(tarea).start();
	}
	
	/**
	 * Método que se utiliza para mutear el sonido
	 */

	public void mute(Boolean mutear) {
		player.setMute(mutear);
	}
	
	/**
	 * Método que se utiliza para comprobar si el sonido está muteado
	 */
	
	public boolean isMuted() {
		return player.isMute();
	}
}
