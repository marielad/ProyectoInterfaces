package flappy.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import flappy.database.ScoreDB;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Clase que crea el informe de Jasper
 * @author Jorge Delgado, Mariela Dorta, Daniel Paredes
 *
 */
public class InformeJasper {

	/**
	 * Constructor de Jasper 
	 */
	public InformeJasper() {
		try {
			List<Partida> datos = new ArrayList<>();
			datos = cargarDatos();
			
			InputStream is = InformeJasper.class.getResourceAsStream("InformePuntuaciones.jasper");

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("TITULO", "PUNTUACIONES");

			JasperPrint jp = JasperFillManager.fillReport(is, parametros, new JRBeanCollectionDataSource(datos));

			JasperExportManager.exportReportToPdfFile(jp, "report\\Informe.pdf");
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * cargarDatos() hace la consulta que guardaremos en el JasperReport
	 * @return los datos que utilizaremos para rellenar el JasperReports
	 * @throws Exception
	 */
	public static List<Partida> cargarDatos() throws Exception {
		List<Partida> datos = new ArrayList<>();

		ScoreDB conn = new ScoreDB();
		Statement stmt = conn.getConexion().createStatement();

		try {
			ResultSet rstSet = stmt.executeQuery("SELECT Nombre, Puntos FROM Puntuaciones ORDER BY Puntos DESC");

			while (rstSet.next()) {
				String nomb = rstSet.getString(1);
				int puntos = rstSet.getInt(2);

				datos.add(new Partida(nomb, puntos));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conn.closeConexion();
		return datos;
	}
	
	/**
	 * show() se utiliza para abrir un pdf del JasperReport en el escritorio
	 */
	public void show() {
		try {
			Desktop.getDesktop().open(new File("report\\Informe.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}