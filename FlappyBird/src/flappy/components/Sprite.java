package flappy.components;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;

public class Sprite {
	
	private ObjectProperty<ImageView> spriteImage = new SimpleObjectProperty<>(this, "spriteImage");
	private DoubleProperty velocityX = new SimpleDoubleProperty(this, "x");
	private DoubleProperty velocityY = new SimpleDoubleProperty(this, "x");
	private DoubleProperty width = new SimpleDoubleProperty(this, "width");
	private DoubleProperty height = new SimpleDoubleProperty(this, "x");
	
	public final ObjectProperty<ImageView> spriteImageProperty() {
		return this.spriteImage;
	}
	
	public final ImageView getSpriteImage() {
		return this.spriteImageProperty().get();
	}
	
	public final void setSpriteImage(final ImageView spriteImage) {
		this.spriteImageProperty().set(spriteImage);
	}
	
	public final DoubleProperty velocityXProperty() {
		return this.velocityX;
	}
	
	public final double getVelocityX() {
		return this.velocityXProperty().get();
	}
	
	public final void setVelocityX(final double velocityX) {
		this.velocityXProperty().set(velocityX);
	}
	
	public final DoubleProperty velocityYProperty() {
		return this.velocityY;
	}
	
	public final double getVelocityY() {
		return this.velocityYProperty().get();
	}
	
	public final void setVelocityY(final double velocityY) {
		this.velocityYProperty().set(velocityY);
	}
	
	public final DoubleProperty widthProperty() {
		return this.width;
	}
	
	public final double getWidth() {
		return this.widthProperty().get();
	}
	
	public final void setWidth(final double width) {
		this.widthProperty().set(width);
	}
	
	public final DoubleProperty heightProperty() {
		return this.height;
	}
	
	public final double getHeight() {
		return this.heightProperty().get();
	}
	
	public final void setHeight(final double height) {
		this.heightProperty().set(height);
	}

}
