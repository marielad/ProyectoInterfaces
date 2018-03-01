package flappy.reports;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Partida {
	
	private StringProperty nombre;
	private IntegerProperty puntos;

	public Partida(String nombre, int puntos) {
		this.nombre = new SimpleStringProperty(this, "nombre", nombre);
		this.puntos = new SimpleIntegerProperty(this, "puntos", puntos);
	}
	
	public Partida() {
		this(null, 0);
	}

	public StringProperty nombreProperty() {
		return this.nombre;
	}
	

	public String getNombre() {
		return this.nombreProperty().get();
	}
	

	public void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	

	public IntegerProperty puntosProperty() {
		return this.puntos;
	}
	

	public int getPuntos() {
		return this.puntosProperty().get();
	}
	

	public void setPuntos(final int puntos) {
		this.puntosProperty().set(puntos);
	}
	
	
	
	
}
