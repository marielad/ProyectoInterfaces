package flappy.components;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class SpriteView extends ImageView implements Initializable {

	@FXML
	private ImageView spriteView;

	private ObjectProperty<Sprite> sprite = new SimpleObjectProperty<>(this, "sprite");

	public SpriteView() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/flappy/view/SpriteView.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.sprite.addListener((o, ov, nv) -> {
			if (nv != null) {
				spriteView.imageProperty().bind(nv.spriteProperty());
			}
		});
	}

	public final ObjectProperty<Sprite> spriteProperty() {
		return this.sprite;
	}

	public final Sprite getSprite() {
		return this.spriteProperty().get();
	}

	public final void setSprite(final Sprite sprite) {
		this.spriteProperty().set(sprite);
	}
}
