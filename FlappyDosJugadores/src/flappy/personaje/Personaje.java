package flappy.personaje;

import com.sun.javafx.geom.Rectangle;

import flappy.animacion.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;;

public class Personaje {
	//configuración del personaje
	
	 	ImageView imagen;
	 	int count = 3;
	 	int columns = 3;
	 	int offsetX = 0;
	 	int offsetY = 0;
	 	int width = 32;
	 	int height = 50;
	 	int score = 0;
	 	
	 	Rectangle removeRect = null;
	 	SpriteAnimation animacion;
	 	
	 	public Personaje(ImageView imagen) {
			this.imagen = imagen;
			this.imagen.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
			animacion = new SpriteAnimation(imagen, Duration.millis(200), count, columns, offsetX, offsetY, width, height);	
		}
	 	

}
