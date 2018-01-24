package flappy.animacion;

import java.util.ArrayList;

import flappy.objeto.Nube;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class MovimientoNube {
	
	Timeline nubesRapidas, nubesLentas;
	
	private static final double FPS_60 = 60;
	
	ArrayList<Nube> listaNubesRapidas = new ArrayList<>();
	ArrayList<Nube> listaNubesLentas = new ArrayList<>();
	
	public MovimientoNube() {
		
		for (int i = 0; i < 4; i++) {
	    	
	    	Nube nube = new Nube();
	    	nube.setX(Math.random() * 1000);
	    	nube.setY((Math.random()*(450+(-50))+(-50)));
	    	listaNubesRapidas.add(nube);
	        
	    }
		
	    for (int i = 0; i < 4; i++) {
	    	
	    	Nube nube = new Nube();
	    	nube.setX(Math.random() * 1000);
	    	nube.setY((Math.random()*(450+(-50))+(-50)));
	    	listaNubesLentas.add(nube);
	        
	    }
		
		nubesRapidas = new Timeline(new KeyFrame(Duration.millis(1000 / FPS_60), new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
	            for (int i = 0; i < listaNubesRapidas.size(); i++) {
	            	
	                if (listaNubesRapidas.get(i).getX() < - listaNubesRapidas.get(i).getImage().getWidth() * listaNubesRapidas.get(i).getScaleX()) {
	                	listaNubesRapidas.get(i).setX(listaNubesRapidas.get(i).getX() + 1000 + listaNubesRapidas.get(i).getImage().getWidth() * listaNubesRapidas.get(i).getScaleX());
	                }
	                
	                listaNubesRapidas.get(i).setX(listaNubesRapidas.get(i).getX() - 1.5);
	   
	            }
	            
			}
			
		}));
		
		nubesLentas = new Timeline(new KeyFrame(Duration.millis(1000 / FPS_60), new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
	            for (int i = 0; i < listaNubesLentas.size(); i++) {
	            	
	                if (listaNubesLentas.get(i).getX() < - listaNubesLentas.get(i).getImage().getWidth() * listaNubesLentas.get(i).getScaleX()) {
	                	listaNubesLentas.get(i).setX(listaNubesLentas.get(i).getX() + 1000 + listaNubesLentas.get(i).getImage().getWidth() * listaNubesLentas.get(i).getScaleX());
	                }
	                
	                listaNubesLentas.get(i).setX(listaNubesLentas.get(i).getX() - 1);
	   
	            }
	            
			}
			
		}));
	    
		nubesRapidas.setCycleCount(-1);
		nubesRapidas.play();
		nubesLentas.setCycleCount(-1);
		nubesLentas.play();
		
	}

	public ArrayList<Nube> getListaNubesRapidas() {
		return listaNubesRapidas;
	}

	public ArrayList<Nube> getListaNubesLentas() {
		return listaNubesLentas;
	}

}