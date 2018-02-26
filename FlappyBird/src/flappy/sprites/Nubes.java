package flappy.sprites;

import javafx.scene.Group;
import javafx.util.Duration;

public class Nubes extends Group {
	
	public Nubes(int count) {
		super();
		for (int i = 0; i < count; i++) {
			Duration duration = Duration.seconds(Math.random() * 5 + 5);
			getChildren().add(new Nube(duration));
		}
	}
	
	public void mover() {
		getChildren().stream().forEach(n -> ((Nube)n).mover());
	}
	
	public void pause() {
		getChildren().stream().forEach(n -> ((Nube)n).pause());
	}
	
	public void resume() {
		getChildren().stream().forEach(n -> ((Nube)n).resume());
	}
	
}
