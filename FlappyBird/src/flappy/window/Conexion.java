package flappy.window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

import methods.CargarFicheros;

public class Conexion {
	
	/** 
	 * 
	 * @author Jorge Delgado, Ricardo Vargas 
	 *
	 */

	Connection conn = null;
	Statement stmt;

	public Conexion() {

		HsqlProperties hsqlProperties = new HsqlProperties();
		hsqlProperties.setProperty("server.database.0",
		"\\db\\dataBase");

		hsqlProperties.setProperty("server.dbname.0", "mdb");
		Server server = new Server();
		try {
			server.setProperties(hsqlProperties);

		} catch (IOException | AclFormatException e) {
			e.printStackTrace();
		}
		server.setTrace(true);
		server.start();
		
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mdb", "SA", "");

			stmt = conn.createStatement();
			stmt.executeQuery(CargarFicheros.fileToString("script/tablas.sql"));
			stmt.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public Connection getConexion() {

		return conn;

	}

	public void closeConexion() {

		try {

			stmt = conn.createStatement();
			stmt.executeQuery("SHUTDOWN COMPACT");
			stmt.close();

			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

}
