package flappy.components;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class Sprite{
	private ObjectProperty<Image> sprite = new SimpleObjectProperty<>(this, "sprite");
    private DoubleProperty setScaleX = new SimpleDoubleProperty(this, "x");
    private DoubleProperty setScaleY = new SimpleDoubleProperty(this, "y");
    private DoubleProperty velocityX = new SimpleDoubleProperty(this, "x");
    private DoubleProperty velocityY = new SimpleDoubleProperty(this, "x");
    private DoubleProperty width = new SimpleDoubleProperty(this, "width");
    private DoubleProperty height = new SimpleDoubleProperty(this, "x");

	public final ObjectProperty<Image> spriteProperty() {
		return this.sprite;
	}
	
	public final Image getImage() {
		return this.spriteProperty().get();
	}
	
	public final void setImage(final Image image) {
		this.spriteProperty().set(image);
		width.set(image.getWidth());
		height.set(image.getHeight());
	}

	public final DoubleProperty setScaleXProperty() {
		return this.setScaleX;
	}

	public final double getSetScaleX() {
		return this.setScaleXProperty().get();
	}

	public final void setSetScaleX(final double setScaleX) {
		this.setScaleXProperty().set(setScaleX);
	}

	public final DoubleProperty setScaleYProperty() {
		return this.setScaleY;
	}

	public final double getSetScaleY() {
		return this.setScaleYProperty().get();
	}

	public final void setSetScaleY(final double setScaleY) {
		this.setScaleYProperty().set(setScaleY);
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
