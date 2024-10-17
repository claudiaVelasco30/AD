package Ejercicios;
import java.io.File;

public class Ejercicio1 {
	public static void main (String[] args) {
		File dirActual = new File(System.getProperty("user.dir"));
		System.out.println(dirActual.getName());
		File[] listaFicheros;
		
		listaFicheros = dirActual.listFiles();
		
		System.out.println("Nombre del directorio de trabajo actual: " + dirActual.getName() + ", tiene " + listaFicheros.length + " ficheros y directorios contenidos.");
		System.out.println("Contenido:");
		
		
		for (int i = 0; i < listaFicheros.length; i++) {
			System.out.print(listaFicheros[i].getName());
			
			if (listaFicheros[i].isFile()) {
				System.out.println(", es un fichero");
			} else {
				System.out.println(", es un directorio");
			}
			
		}
	}
}
