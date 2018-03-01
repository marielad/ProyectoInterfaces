package methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CargarFicheros {
	
	/** 
	 * 
	 * @author Jorge Delegado
	 *
	 */

	public static String fileToString(String path) {

		String stringComplete = "";
		
		try {
			
			BufferedReader bfpath = new BufferedReader(new FileReader(path));
			stringComplete = "";
			String stringCurrent = "";

			while ((stringCurrent = bfpath.readLine()) != null) {
				
				stringComplete = stringComplete + stringCurrent;
				
			}
			
			bfpath.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}

		return stringComplete;
	}
}
