package gamefx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {

	private ReadOnlyIntegerWrapper count;
	private ReadOnlyIntegerWrapper columns;
	private IntegerProperty imageIndex;

	public Sprite(String url) {
		this(url, 1, 1);
	}

	public Sprite(String url, int count, int columns) {
		super(url);
		this.count = new ReadOnlyIntegerWrapper(this, "count", count);
		this.columns = new ReadOnlyIntegerWrapper(this, "columns", columns);
		this.imageIndex = new SimpleIntegerProperty(this, "imageIndex", -1);
		this.imageIndex.addListener((o, ov, nv) -> {
			double width = getImage().getWidth() / getColumns();  
			double height = getImage().getHeight() / (getCount() / getColumns());
			double x = (nv.intValue() % getColumns()) * width;
			double y = (nv.intValue() / getColumns()) * height;
			setViewport(new Rectangle2D(x, y, width, height));
		});
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
	
}
