package flappy.objeto;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class Tuberia extends Group {
	
	Rectangle tuboAnchoSup, tuboAnchoInf, tuboLargoSup, tuboLargoInf;

	double separacionVertial = 120;
	double centroOscilacion;
	int rotateOffset = 0;
	int frames = 0;
	
	Stop[] colors = new Stop[]{
			new Stop(0, Color.LIGHTGREEN),
			new Stop(1, Color.DARKGREEN)
			};
	
	LinearGradient gradiente = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, colors);
	Color colorTubo = new Color(84 / 255.0, 56 / 255.0, 71 / 255.0, 1.0);
	
	SimpleDoubleProperty ejeY = new SimpleDoubleProperty(0);
	SimpleDoubleProperty ancho = new SimpleDoubleProperty(0);
	SimpleDoubleProperty alto = new SimpleDoubleProperty(0);
	
	public Tuberia() {
		
        if (Math.random() < 0.4) {
//            tube = new Tubos(y, root, true, false);
        } else if (Math.random() > 0.85) {
//            tube = new Tubos(y, root, true, true);
        } else {
//            tube = new Tubos(y, root, false, false);
        }
        
    	ejeY.set(400 * Math.random() / 2.0);
    	tuboLargoSup = new Rectangle();
        centroOscilacion = ejeY.get();
        
        ancho.set(1000);
        alto.set(400);
        tuboLargoSup.widthProperty().bind(ancho.divide(12.3));
        tuboLargoSup.heightProperty().bind(ejeY);
        tuboLargoSup.setX(2.5);
        
        tuboAnchoSup = new Rectangle();
        tuboAnchoSup.widthProperty().bind(ancho.divide(11.4));
        tuboAnchoSup.heightProperty().bind(alto.divide(12));
        tuboAnchoSup.yProperty().bind(ejeY);
        
        tuboAnchoInf = new Rectangle();
        tuboAnchoInf.widthProperty().bind(ancho.divide(11.4));
        tuboAnchoInf.heightProperty().bind(alto.divide(12));
        tuboAnchoInf.yProperty().bind(ejeY.add(separacionVertial).add(alto.divide(12)));
        
        tuboLargoInf = new Rectangle();
        tuboLargoInf.widthProperty().bind(ancho.divide(12.3));
        tuboLargoInf.heightProperty().bind(alto.add(-separacionVertial - 50 + rotateOffset).subtract(ejeY));
        tuboLargoInf.setX(2.5);
        tuboLargoInf.yProperty().bind(ejeY.add(separacionVertial).add(alto.divide(6)));
        
        tuboLargoSup.setFill(gradiente);
        tuboAnchoSup.setFill(gradiente);
        tuboAnchoInf.setFill(gradiente);
        tuboLargoInf.setFill(gradiente);
        
        tuboLargoSup.setStroke(colorTubo);
        tuboAnchoSup.setStroke(colorTubo);
        tuboAnchoInf.setStroke(colorTubo);
        tuboLargoInf.setStroke(colorTubo);
		
	}
	
	public Rectangle getTuboAnchoSup() {
		return tuboAnchoSup;
	}

	public Rectangle getTuboAnchoInf() {
		return tuboAnchoInf;
	}

	public Rectangle getTuboLargoSup() {
		return tuboLargoSup;
	}

	public Rectangle getTuboLargoInf() {
		return tuboLargoInf;
	}
	
}