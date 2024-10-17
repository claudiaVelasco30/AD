package Ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Ejercicio8 {
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("Ficheros\\ejercicio08.csv")));
			String linea;
			
			while ((linea=br.readLine()) != null) {
				String [] valores = linea.split(",");
				 //mostrar(valores);
				 valores = eliminarComillas(valores);
				 mostrar(valores);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String[] eliminarComillas(String[] valores) {
		for (int i = 0; i < valores.length; i++) {
			valores[i] = valores[i].trim().replaceAll("^\"", "").replaceAll("\"$", "").replaceAll("\"\" + \"\"", "\"\"");
		}
		return valores;
	}

	private static void mostrar(String [] valores) {
		for (int i = 0; i < valores.length; i++) {
			System.out.print(valores[i] + " ");
		}
		
		System.out.println();
	}
}
