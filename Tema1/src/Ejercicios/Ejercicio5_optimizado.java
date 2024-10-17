package Ejercicios;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio5_optimizado {

	private static final int TAMANIO = 4;

	private static RandomAccessFile rfile;

	public static void main(String[] args) {
		try {
			rfile = new RandomAccessFile(new File("Ficheros\\fichero5_optimizado.dat"), "rw");

			
			menu();

			rfile.close();

		} catch (FileNotFoundException e) {
			System.out.println("El fichero no existe");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de lectura/escritura");
			e.printStackTrace();
		}

	}

	/**
	 * Escribe números del 1 al 100 en un fcichero de acceso aleatorio o modifica el
	 * valor de una posición determinada (ambos valores, posición y nuevo valor se
	 * pasan como prámetros) o añade al final del fichero un valor que se pasa como
	 * parámetro.
	 * 
	 * @param valores Si vacío, inicializa el fichero con los valores del 1 al 100
	 *                Si tiene un único valor, se añade al final Si tiene dos
	 *                valores, el primero es tomado como la posición a sobreescribir
	 *                y el segundo como nuevo valor
	 */
	public static void escribir(int... valores) {
		try {
			if (valores.length == 0) {
				for (int i = 1; i < 101; i++) {
					rfile.writeInt(i);
				}
			} else if (valores.length == 1) {
				rfile.seek(rfile.length());
				rfile.writeInt(valores[0]);
			} else if (valores.length == 2) {
				// primer mparámetro lo tmo como lugar y el segundo como nuevo valor
				int posicion = (valores[0] - 1) * TAMANIO;
				// Compruebo que la posición sea correcta no superior al tamaño del fichero ni
				// inferior a 0
				if (posicion >= rfile.length() || posicion < 0) {
					System.out.println("La posición indicada no es correcta");
				} else {
					rfile.seek(posicion);
					rfile.writeInt(valores[1]);
				}
			}

		} catch (IOException e) {
			System.out.println("Error de escritura");
			e.printStackTrace();
		}
	}

	/**
	 * Lee el contenido del fichero o el valor de una posición que se pasa como
	 * prámetro
	 * 
	 * @param valores Si vacío, lee y muestra el contenido de todo el fichero Si
	 *                tiene un único valor, muestra el elemento contenido en ese
	 *                lugar
	 */

	public static void leer(int... valores) {
		try {
			if (valores.length == 0) {
				rfile.seek(0);
				while (rfile.getFilePointer() < rfile.length()) {
					System.out.println(rfile.readInt());
				}
			} else if (valores.length == 1) {
				// Calculo la posición
				int posicion = (valores[0] - 1) * TAMANIO;

				// Compruebo que la posición sea correcta
				if (posicion >= rfile.length() || posicion < 0) {
					System.out.println("La posición indicada no es correcta");
				} else {
					rfile.seek(posicion);
					System.out.println("Valor de la posición " + valores[0] + ": " + rfile.readInt());
				}
			}

		} catch (IOException e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
		}
	}

	public static void menu() {
		Scanner in = new Scanner(System.in);
		boolean salir = true;
		int opcion, lugar;
		int consultar;

		while (salir == true) {
			System.out.println("1. Leer fichero");
			System.out.println("\n2. Consultar");
			System.out.println("\n3. Añadir al final");
			System.out.println("\n4. Modificar valor");
			System.out.println("\n5. Salir");
			System.out.print("Escriba una de las opcines: ");
			opcion = in.nextInt();
			int numFinal;

			switch (opcion) {
			case 1:
				System.out.println("Leyendo el contenido del fichero:");
				leer();
				break;
			case 2:
				System.out.println("Consultando una posición del fichero");
				System.out.print("Indique posición a consultar: ");
				consultar = in.nextInt();
				leer(consultar);
				break;
			case 3:
				System.out.println("Añadiendo elemento al final del fichero");
				System.out.print("Indique el valor a añadir: ");
				numFinal = in.nextInt();
				escribir(numFinal);
				break;
			case 4:
				System.out.println("Modificando el valor de la posición");
				System.out.print("Indique la posición a modificar: ");
				lugar = in.nextInt();
				System.out.print("indique el nuevo valor: ");
				escribir(in.nextInt(), lugar);

				break;
			case 5:
				System.out.println("Saliendo del programa...");
				salir = false;
				break;

			default:
				System.out.println("Error");
				break;
			}
		}

	}
}
