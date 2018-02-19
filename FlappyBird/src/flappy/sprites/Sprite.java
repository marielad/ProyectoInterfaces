package flappy.sprites;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Sprite extends Transition{

	private ObjectProperty<ImageView> sprite = new SimpleObjectProperty<>(this, "sprite");
	private DoubleProperty positionX = new SimpleDoubleProperty(this, "positionX");
	private DoubleProperty positionY = new SimpleDoubleProperty(this, "positionY");
	private DoubleProperty offsetX = new SimpleDoubleProperty(this, "offsetX");
	private DoubleProperty offsetY = new SimpleDoubleProperty(this, "offsetY");
	private DoubleProperty translateX = new SimpleDoubleProperty(this, "translateX");
	private DoubleProperty translateY = new SimpleDoubleProperty(this, "translateY");
	private DoubleProperty scaleX = new SimpleDoubleProperty(this, "scaleX");
	private DoubleProperty scaleY = new SimpleDoubleProperty(this, "scaleY");
	private DoubleProperty width = new SimpleDoubleProperty(this, "width");
	private DoubleProperty height = new SimpleDoubleProperty(this, "height");
	private DoubleProperty count = new SimpleDoubleProperty(this, "count");
	private DoubleProperty columns = new SimpleDoubleProperty(this, "columns");
	private DoubleProperty opacity = new SimpleDoubleProperty(this, "opacity");

	private double lastIndex;

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
	
	public final DoubleProperty offsetXProperty() {
		return this.offsetX;
	}
	
	public final double getOffsetX() {
		return this.offsetXProperty().get();
	}
	
	public final void setOffsetX(final double offsetX) {
		this.offsetXProperty().set(offsetX);
	}

	public final DoubleProperty offsetYProperty() {
		return this.offsetY;
	}

	public final double getOffsetY() {
		return this.offsetYProperty().get();
	}
	
	public final void setOffsetY(final double offsetY) {
		this.offsetYProperty().set(offsetY);
	}

	public final DoubleProperty translateXProperty() {
		return this.translateX;
	}

	public final double getTranslateX() {
		return this.translateXProperty().get();
	}

	public final void setTranslateX(final double translateX) {
		this.translateXProperty().set(translateX);
		getSprite().setTranslateX(translateX);
	}

	public final DoubleProperty translateYProperty() {
		return this.translateY;
	}

	public final double getTranslateY() {
		return this.translateYProperty().get();
	}
	
	public final void setTranslateY(final double translateY) {
		this.translateYProperty().set(translateY);
		getSprite().setTranslateY(translateY);
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
	
	public final DoubleProperty countProperty() {
		return this.count;
	}
	
	public final double getCount() {
		return this.countProperty().get();
	}

	public final void setCount(final double count) {
		this.countProperty().set(count);
	}
	
	public final DoubleProperty columnsProperty() {
		return this.columns;
	}

	public final double getColumns() {
		return this.columnsProperty().get();
	}

	public final void setColumns(final double columns) {
		this.columnsProperty().set(columns);
	}
	
	public Rectangle2D getBounds(){
        return new Rectangle2D(getPositionX(),getPositionX(),getWidth(),getHeight());
    }

    public boolean intersects(Sprite a, Sprite b){
        return a.getBounds().intersects(b.getBounds());
    }
    
    public Sprite() {}
    
    public Sprite(ImageView imageView,Duration duration,int count,int columns,double offsetX, double offsetY,double width,double height) {
    	this.sprite =  new SimpleObjectProperty<>(imageView);
        this.count= new SimpleDoubleProperty(count);
        this.columns= new SimpleDoubleProperty(columns);
        this.offsetX= new SimpleDoubleProperty(offsetX);
        this.offsetY= new SimpleDoubleProperty(offsetY);
        this.width= new SimpleDoubleProperty(width);
        this.height= new SimpleDoubleProperty(height);
   
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
        setCycleCount(Animation.INDEFINITE);
        this.getSprite().setViewport(new Rectangle2D(offsetX,offsetY,width,height));
    }
    
	@Override
	protected void interpolate(double frac) {
		final double index = Math.min(Math.floor(frac * getCount()), getCount()- 1);
        if (index != lastIndex) {
            final double x = (index % getColumns()) * getWidth()  + getOffsetX();
            final double y = (index / getColumns()) * getHeight() + getOffsetY();
            getSprite().setViewport(new Rectangle2D(getPositionX(), getPositionY(), getWidth(), getHeight()));
            lastIndex = index;
        }
	}  
}
