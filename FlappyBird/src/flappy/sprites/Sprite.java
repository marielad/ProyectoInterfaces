package flappy.sprites;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;

public class Sprite {

	private ObjectProperty<ImageView> sprite = new SimpleObjectProperty<>(this, "sprite");
	private DoubleProperty positionX = new SimpleDoubleProperty(this, "positionX");
	private DoubleProperty positionY = new SimpleDoubleProperty(this, "positionY");
	private DoubleProperty velocityX = new SimpleDoubleProperty(this, "velocityX");
	private DoubleProperty velocityY = new SimpleDoubleProperty(this, "velocityY");
	private DoubleProperty scaleX = new SimpleDoubleProperty(this, "scaleX");
	private DoubleProperty scaleY = new SimpleDoubleProperty(this, "scaleY");
	private DoubleProperty width = new SimpleDoubleProperty(this, "width");
	private DoubleProperty height = new SimpleDoubleProperty(this, "height");
	private DoubleProperty opacity = new SimpleDoubleProperty(this, "opacity");

	public final ObjectProperty<ImageView> spriteProperty() {
		return this.sprite;
	}

	public final ImageView getSprite() {
		return this.spriteProperty().get();
	}

	public final void setSprite(final ImageView sprite) {
		this.spriteProperty().set(sprite);
	}

	public final DoubleProperty positionXProperty() {
		return this.positionX;
	}

	public final double getPositionX() {
		return this.positionXProperty().get();
	}

	public final void setPositionX(final double positionX) {
		this.positionXProperty().set(positionX);
		getSprite().setX(positionX);
	}

	public final DoubleProperty positionYProperty() {
		return this.positionY;
	}

	public final double getPositionY() {
		return this.positionYProperty().get();
	}

	public final void setPositionY(final double positionY) {
		this.positionYProperty().set(positionY);
		getSprite().setY(positionY);
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

	public final DoubleProperty scaleXProperty() {
		return this.scaleX;
	}

	public final double getScaleX() {
		return this.scaleXProperty().get();
	}

	public final void setScaleX(final double scalex) {
		this.scaleXProperty().set(scalex);
		getSprite().setScaleX(scalex);
	}

	public final DoubleProperty scaleYProperty() {
		return this.scaleY;
	}

	public final double getScaleY() {
		return this.scaleYProperty().get();
	}

	public final void setScaleY(final double scaleY) {
		this.scaleYProperty().set(scaleY);
		getSprite().setScaleY(scaleY);
	}

	public final DoubleProperty widthProperty() {
		return this.width;
	}

	public final double getWidth() {
		return this.widthProperty().get();
	}

	public final void setWidth(final double width) {
		this.widthProperty().set(width);
		getSprite().setFitWidth(width);
	}

	public final DoubleProperty heightProperty() {
		return this.height;
	}

	public final double getHeight() {
		return this.heightProperty().get();
	}

	public final void setHeight(final double height) {
		this.heightProperty().set(height);
		getSprite().setFitHeight(height);
	}

	public final DoubleProperty opacityProperty() {
		return this.opacity;
	}

	public final double getOpacity() {
		return this.opacityProperty().get();
	}

	public final void setOpacity(final double opacity) {
		this.opacityProperty().set(opacity);
		getSprite().setOpacity(opacity);
	}
}
