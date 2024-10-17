package Ejercicios;
import java.io.File;
import java.util.Scanner;

public class Ejercicio2 {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.print("Escribre el nombre del fichero o directorio: ");
		String nombreFichero = in.nextLine();

		File dirActual = new File(nombreFichero);

		if (dirActual.exists()) {
			if (dirActual.isFile()) {
				System.out.println("El fichero tiene el siguiente tamaño: " + dirActual.length() + " bytes");
				if (dirActual.canRead()) {
					System.out.println("El fichero puede leerse");
				} else {
					System.out.println("El fichero no puede leerse");
				}
				
				if (dirActual.canWrite()) {
					System.out.println("El fichero puede modificarse");
				} else {
					System.out.println("El fichero no puede modificarse");
				}
				
				if (dirActual.canExecute()) {
					System.out.println("El fichero puede ejecutarse");
				} else {
					System.out.println("El fichero no puede ejecutarse");
				}
				
			} else {
				File[] listaFicheros;
				listaFicheros = dirActual.listFiles();

				System.out.println("Elementos que contiene el directorio:");
				for (int i = 0; i < listaFicheros.length; i++) {
					System.out.println(listaFicheros[i].getName());
				}
			}

		} else {
			System.out.println("El fichero o directorio no existe");
		}

	}
}
