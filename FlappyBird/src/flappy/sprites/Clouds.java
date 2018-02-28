package flappy.sprites;

import javafx.scene.Group;
import javafx.util.Duration;

public class Clouds extends Group {
	
	public Clouds(int count) {
		super();
		for (int i = 0; i < count; i++) {
			Duration duration = Duration.seconds(Math.random() * 5 + 5);
			getChildren().add(new Cloud(duration));
		}
	}
	
	public void mover() {
		getChildren().stream().forEach(n -> ((Cloud)n).mover());
	}
	
	public void pause() {
		getChildren().stream().forEach(n -> ((Cloud)n).pause());
	}
	
	public void resume() {
		getChildren().stream().forEach(n -> ((Cloud)n).resume());
	}
	
}
