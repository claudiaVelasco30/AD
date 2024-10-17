package Ejercicios;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Ejercicio3 {
	public static void main(String[] args) {
		try {
			File fichero3 = new File("Ficheros\\3_Ejercicio.csv");
			BufferedReader br = new BufferedReader(new FileReader(fichero3));
			String linea;
			String elementos[] = new String[4];
			while ((linea = br.readLine()) != null) {
				elementos = linea.split(";");
				
				for (int i = 0; i < elementos.length; i++) {
					System.out.print(elementos[i] + " ");
				}
				System.out.println();
			}
		
			br.close();
			
			PrintWriter pw = new PrintWriter(new FileWriter(fichero3.getAbsolutePath(),true));
			pw.println("Claudia;Velasco;DAM;2");
			
			pw.close();
		} catch (

		FileNotFoundException nfe) {
			System.out.println("Error en la lectura del fichero: " + nfe);
		} catch (IOException e) {
			System.out.println("Error de lectura: " + e);
		}

	}
}
