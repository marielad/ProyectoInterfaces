package gamefx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

public class Sprite extends ImageView {

	private ReadOnlyIntegerWrapper count;
	private ReadOnlyIntegerWrapper columns;
	private IntegerProperty imageIndex;
	private ObjectProperty<Shape> shape;
	private ReadOnlyDoubleWrapper width;
	private ReadOnlyDoubleWrapper height;

	public Sprite(String url) {
		this(url, 1, 1);
	}

	public Sprite(String url, int count, int columns) {
		super(url);
		this.width = new ReadOnlyDoubleWrapper(this, "width");
		this.height = new ReadOnlyDoubleWrapper(this, "height");
		this.count = new ReadOnlyIntegerWrapper(this, "count", count);
		this.columns = new ReadOnlyIntegerWrapper(this, "columns", columns);
		this.imageIndex = new SimpleIntegerProperty(this, "imageIndex", -1);
		this.imageIndex.addListener((o, ov, nv) -> {
			double x = (nv.intValue() % getColumns()) * getWidth();
			double y = (nv.intValue() / getColumns()) * getHeight();
			setViewport(new Rectangle2D(x, y, getWidth(), getHeight()));
		});
		this.width.bind(getImage().widthProperty().divide(columnsProperty()));
		this.height.bind(getImage().heightProperty().divide(countProperty().divide(columnsProperty())));
		this.shape = new SimpleObjectProperty<>(this, "shape");
		this.setImageIndex(0);
	}

	public final ReadOnlyIntegerProperty countProperty() {
		return this.count.getReadOnlyProperty();
	}

	public final int getCount() {
		return this.countProperty().get();
	}

	public final ReadOnlyIntegerProperty columnsProperty() {
		return this.columns.getReadOnlyProperty();
	}

	public final int getColumns() {
		return this.columnsProperty().get();
	}

	public final IntegerProperty imageIndexProperty() {
		return this.imageIndex;
	}

	public final int getImageIndex() {
		return this.imageIndexProperty().get();
	}

	public final void setImageIndex(final int imageIndex) {
		this.imageIndexProperty().set(imageIndex);
	}

	public final ObjectProperty<Shape> shapeProperty() {
		return this.shape;
	}

	public final Shape getShape() {
		return this.shapeProperty().get();
	}

	public final void setShape(final Shape shape) {
		this.shapeProperty().set(shape);
	}

	public final javafx.beans.property.ReadOnlyDoubleProperty widthProperty() {
		return this.width.getReadOnlyProperty();
	}

	public final double getWidth() {
		return this.widthProperty().get();
	}

	public final javafx.beans.property.ReadOnlyDoubleProperty heightProperty() {
		return this.height.getReadOnlyProperty();
	}

	public final double getHeight() {
		return this.heightProperty().get();
	}

}
