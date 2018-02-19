package flappy.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class Reproductor {

	MediaPlayer player;

	public Reproductor(String ruta) {
			
		player = new MediaPlayer(new Media(this.getClass().getResource(ruta).toExternalForm()));
		
		player.setCycleCount(MediaPlayer.INDEFINITE);

	}

	public void play() {
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

	public void mute() {
		player.setMute(true);
	}
}
