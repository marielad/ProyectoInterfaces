package flappy.objeto;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Nube extends ImageView {

    public Nube() {
    	
        setImage(new Image(Nube.class.getResourceAsStream("/flappy/recurso/flappyCloud.png")));
        setScaleX(Math.random() / 3.5 + 0.5);
        setScaleY(Math.random() / 3.5 + 0.5);
        setOpacity(0.5);
        
    }
	
}