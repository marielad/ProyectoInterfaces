package flappy.JasperReport;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personaje {
	private StringProperty nombre;
	private StringProperty apellidos;

	public Personaje(String nombre, String apellidos) {
		this.nombre = new SimpleStringProperty(this, "nombre", nombre);
		this.apellidos = new SimpleStringProperty(this, "apellidos", apellidos);
	}
	
	public Personaje() {
		this(null, null);
	}

	public final StringProperty nombreProperty() {
		return this.nombre;
	}

	public final String getNombre() {
		return this.nombreProperty().get();
	}

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public final StringProperty apellidosProperty() {
		return this.apellidos;
	}

	public final String getApellidos() {
		return this.apellidosProperty().get();
	}

	public final void setApellidos(final String apellidos) {
		this.apellidosProperty().set(apellidos);
	}

}
