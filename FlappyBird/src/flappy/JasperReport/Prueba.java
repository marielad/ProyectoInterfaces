package flappy.JasperReport;

import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import flappy.window.Conexion;
import methods.CargarFicheros;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Prueba {

	
	public static void main(String[] args) throws Exception {
		
		
		List<Partida> datos = new ArrayList<>();
		datos = datosMySql();
		
		InputStream is = Prueba.class.getResourceAsStream("InformePuntuaciones.jasper");
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("TITULO", "PUNTUACIONES");
		
		JasperPrint jp = JasperFillManager.fillReport(is, parametros, 
				new JRBeanCollectionDataSource(datos));
		
		JasperExportManager.exportReportToPdfFile(jp, "informe.pdf");
		
		Desktop.getDesktop().open(new File("informe.pdf"));

	}
	
public static List<Partida> datosMySql() throws Exception {
		
		List<Partida> datos = new ArrayList<>();
		
		int tamanioLista = 0;
		int tamanio = 0;

		Conexion conn = new Conexion();
		Statement stmt = conn.getConexion().createStatement();
		stmt.executeQuery(CargarFicheros.fileToString("script/registros.sql"));

		try {

			ResultSet rstSet = stmt.executeQuery("SELECT * FROM puntuaciones ORDER BY puntos DESC");

			while (rstSet.next() && tamanioLista != 10) {

				int id = rstSet.getInt(1);
				String nomb = rstSet.getString(2);
				int puntos = rstSet.getInt(3);

				datos.add(new Partida(nomb, puntos));
				tamanioLista++;

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		conn.closeConexion();
		return datos;
	}

}
