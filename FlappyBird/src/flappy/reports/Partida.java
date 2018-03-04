package flappy.reports;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelo de los datos con los que vamos a rellenar la base de datos
 * @author Jorge Delgado, Mariela Dorta, Daniel Paredes
 *
 */
public class Partida {
	
	private StringProperty nombre;
	private IntegerProperty puntos;
	private ObjectProperty<LocalDate> fecha;

	public Partida(String nombre, int puntos, LocalDate fecha) {
		this.nombre = new SimpleStringProperty(this, "nombre", nombre);
		this.puntos = new SimpleIntegerProperty(this, "puntos", puntos);
		this.fecha = new SimpleObjectProperty<>(this, "fecha", fecha);
	}
	
	public Partida() {
		this(null, 0, null);
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

	public ObjectProperty<LocalDate> fechaProperty() {
		return this.fecha;
	}
	

	public LocalDate getFecha() {
		return this.fechaProperty().get();
	}
	

	public void setFecha(final LocalDate fecha) {
		this.fechaProperty().set(fecha);
	}
	
	
}
