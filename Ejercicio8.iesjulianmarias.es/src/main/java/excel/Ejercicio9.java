package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilidades.Utilidades;

/**
 * Modificar el ejercicio creando al menos dos métodos: buscarCiudad le
 * pasaremos el nombre de una ciudad y nos mostrará las ubicaciones de los
 * puntos de recarga.
 * buscarMarca le pasaremos la marca y nos mostrará las ubicaciones de
 * los puntos de recarga.
 * MODIFICACIÓN: crear una hoja en el documento excel con el
 * nombre que se le pase como parámetro y almacenar en ella la información
 * obtenida.
 */

public class Ejercicio9 {

	private final static String DOCTRABAJO_IN = "vehiculosElectricos.xlsx";
	private static Workbook wb;

	public static void main(String[] args) {

		try {
			// Abro un documento excel con extensión xslx
			wb = new XSSFWorkbook(new FileInputStream(new File(Utilidades.RUTA + DOCTRABAJO_IN)));
			// tomo la primera página
			buscarMarca("IBERDROLA");
			buscarCiudad("SALAMANCA");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void buscarMarca(String marca) {
		Sheet hoja = wb.getSheetAt(0);
		int numFila = 1;
		Row fila = hoja.getRow(numFila);

		// Muestro las localizaciones de los puntos de carga de la marca IBERDROLA
		System.out.println("PUNTOS DE RECARGA DE " + marca + " EN CASTILLA Y LEÓN");
		while (fila != null) {
			Cell celdaBusqueda = fila.getCell(2);
			if (celdaBusqueda != null) {
				if (celdaBusqueda.getStringCellValue().contains(marca)) {
					System.out.println("----> " + fila.getCell(0).getStringCellValue() + "\t - "
							+ fila.getCell(1).getStringCellValue());
				}
			}
			fila = hoja.getRow(numFila++);
		}
	}

	private static void buscarCiudad(String ciudad) {
		Sheet hoja = wb.getSheetAt(0);
		int numFila = 1;
		Row fila = hoja.getRow(numFila);

		// Muestro las localizaciones de los puntos de carga de la marca IBERDROLA
		System.out.println("\n\n\nPUNTOS DE RECARGA EN " + ciudad);
		while (fila != null) {
			Cell celdaBusqueda = fila.getCell(1);
			if (celdaBusqueda != null) {
				if (celdaBusqueda.getStringCellValue().contains(ciudad)) {
					System.out.println("----> " + fila.getCell(0).getStringCellValue() + "\t - "
							+ fila.getCell(1).getStringCellValue());
				}
			}
			numFila++;
			fila = hoja.getRow(numFila);
		}
	}
	
	/*private static void crearHoja(String nombre) {
	    try {
	    	Sheet hoja = wb.getSheetAt(0);
	    	Sheet newHoja = wb.createSheet(nombre);

		    

		    
		    
	        FileOutputStream fileOut = new FileOutputStream(Utilidades.RUTA + DOCTRABAJO_IN);
	        wb.write(fileOut);
	        fileOut.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}*/
}
