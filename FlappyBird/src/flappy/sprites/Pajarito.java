package flappy.sprites;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Pajarito extends Pane{

	//configuración del personaje
	
 	ImageView imagen;
 	int count = 3;
 	int columns = 3;
 	int offsetX = 0;
 	int offsetY = 0;
 	//tamaño del personaje
 	int width = 34;
 	int height = 24;
 	Duration duration = Duration.millis(200);
 	
 	
 	public boolean saltando = false;
 	TranslateTransition saltar;
    TranslateTransition caer;
 	RotateTransition rotarPajarito;
 	
 	int score = 0;
 	
 	public Sprite animacion;
 	
 	public Pajarito(ImageView imagen) {
		this.imagen = imagen;
		this.imagen.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animacion = new Sprite(imagen, Duration.millis(200),count, columns, offsetX, offsetY, width, height);
		getChildren().addAll(imagen);
	}
	
 	private void jump() {
 		rotarPajarito.setDuration(Duration.millis(100));
        rotarPajarito.setToAngle(-40);
        rotarPajarito.stop();
        rotarPajarito.play();
        saltar.setByY(-50);
        saltar.setCycleCount(1);
        saltando = true;
        caer.stop();
        saltar.stop();
        saltar.play();
        saltar.setOnFinished((finishedEvent) -> {
            rotarPajarito.setDuration(Duration.millis(500));
            rotarPajarito.setToAngle(40);
            rotarPajarito.stop();
            rotarPajarito.play();
            saltando = false;
            caer.play();
        });
	}
}
