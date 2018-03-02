package gamefx;

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
		player.setCycleCount(MediaPlayer.INDEFINITE);
		player.play();
	}

	public void play() {
		player.setCycleCount(1);
		player.play();
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
		player.stop();
	}

	public void mute(Boolean mutear) {
		player.setMute(mutear);
	}
	
	public boolean isMuted() {
		return player.isMute();
	}
}
