package Ejercicios;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio4 {
	public static void main(String[] args) {
		File  imagen = new File("Ficheros\\imagen.jpg");
		File copiaImagen = new File("Ficheros\\imagen_copia.jpg");
		
		try {
			FileInputStream fis = new FileInputStream(imagen);
			FileOutputStream fos = new FileOutputStream(copiaImagen);
			
			/**
			int leido;
			while ((leido=fis.read())!=1) {
				fos.write(leido);	
			}
			**/
			
			fos.write(fis.readAllBytes());
			
			fis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no existe");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println();
		}
		
		
		
		
	}
}
