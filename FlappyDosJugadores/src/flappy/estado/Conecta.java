package flappy.estado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conecta {

	public static void main(String[] args) throws Exception {
		hacerConexion();
		cerrarConexion(hacerConexion());
	}
	
	static public Connection hacerConexion() throws ClassNotFoundException, SQLException {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/Flappy","root","");
			System.out.println("Conexi�n correcta");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void cerrarConexion(Connection con) {
		//cerramos la conexi�n
		try {
			con.close();
			System.out.println("Conexi�n cerrada!");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
