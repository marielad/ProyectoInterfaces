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
		tarea = new Task<MediaPlayer>() {
			protected MediaPlayer call() throws Exception {
				String url = getClass().getResource(ruta).toExternalForm();
				return player = new MediaPlayer(new Media(url));
			}
		};
		new Thread(tarea).start();
	}

	/**
	 * Método que se utiliza para reproducir infinitamente la música
	 */
	
	public void playIndefinite() {
		player.setCycleCount(MediaPlayer.INDEFINITE);
		player.play();
	}

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
	
	public void pause() {
		if(player.getStatus() == Status.PLAYING) {
			player.pause();
		}
	}
	
	public void resume() {
		if(player.getStatus() == Status.PAUSED) {
			player.play();
		}
	}

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

	public void mute(Boolean mutear) {
		player.setMute(mutear);
	}
	
	public boolean isMuted() {
		return player.isMute();
	}
}
