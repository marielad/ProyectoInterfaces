package flappy.components;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class SpritePane extends BorderPane implements Initializable {

	private ListProperty<Sprite> spriteList = new SimpleListProperty<>(this, "spriteList",
			FXCollections.observableArrayList());
	
	public SpritePane() {
		initialize(null, null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		spriteList.addListener(new ListChangeListener<Sprite>() {
		      public void onChanged(Change<? extends Sprite> c) {
		        while (c.next()) {
		          for (Sprite s : c.getAddedSubList()) {
		            SpriteView sv = new SpriteView();
		            sv.setSprite(s);          
		            SpritePane.this.getChildren().add(sv);
		          }
		        }
		      }
		    });
		
	}
	
	public final ListProperty<Sprite> spriteListProperty() {
		return this.spriteList;
	}

	public final ObservableList<Sprite> getSpriteList() {
		return this.spriteListProperty().get();
	}

	public final void setSpriteList(final ObservableList<Sprite> spriteList) {
		this.spriteListProperty().set(spriteList);
	}
}
