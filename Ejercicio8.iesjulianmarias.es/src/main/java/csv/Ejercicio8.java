package csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import utilidades.Utilidades;

public class Ejercicio8 {
	
	private final static String DOCTRABAJO_IN = "Ejercicio08.csv";
	private final static char SEPARADOR = ',';
	private final static char COMILLAS = '"';
	
	public static void main(String[] args) {
		
		CSVReader reader = null;
		
		try {
			reader = new CSVReader(new FileReader(Utilidades.RUTA+DOCTRABAJO_IN), SEPARADOR, COMILLAS);
			String[] valoresLinea;
			while ((valoresLinea=reader.readNext()) != null) {
				mostrar(valoresLinea);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void mostrar(String [] valores) {
		for (int i = 0; i < valores.length; i++) {
			System.out.print(valores[i] + "\t");
		}
		
		System.out.println();
	}
}
