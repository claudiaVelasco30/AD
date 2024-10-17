package Ejercicios;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio5_optimizado_temperaturas2 {

	private static final int TAMANIO = 13;

	private static RandomAccessFile rfile;

	public static void main(String[] args) {
		try {
			rfile = new RandomAccessFile(new File("Ficheros\\fichero5_optimizado_temperaturas2.dat"), "rw");

			escribir();
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
	 * Escribe regsitros de meses y temperaturas en un fichero de acceso aleatorio o
	 * modifica el valor de una posición determinada (ambos valores, posición y
	 * nuevo valor se pasan como prámetros) o añade al final del fichero un registro
	 * de temperatuas que se pasa como parámetro.
	 * 
	 * @param valores Si vacío, copia registros de temperaturas en el fichero
	 */
	              
	public static void escribir(int... valores) {
		try {
			String[] meses = { "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic" };
			int[] tempMax = { 12, 13, 16, 19, 24, 29, 34, 34, 28, 21, 15, 11 };
			int[] tempMin = { 3, 4, 6, 7, 12, 18, 22, 21, 18, 13, 7, 4 };
			if (valores.length == 0) {

				for (int i = 0; i < meses.length; i++) {
					rfile.writeUTF(meses[i]);
					rfile.writeInt(tempMax[i]);
					rfile.writeInt(tempMin[i]);
				}

			} else if (valores.length == 3) {
				rfile.seek(rfile.length());
				rfile.writeUTF(meses[valores[0] - 1]);
				rfile.writeInt(valores[1]);
				rfile.writeInt(valores[2]);
				System.out.println();

			} else if (valores.length == 4) {
				// primer mparámetro lo tmo como lugar y el segundo como nuevo valor
				int posicion = (valores[0] - 1) * TAMANIO;
				// Compruebo que la posición sea correcta no superior al tamaño del fichero ni
				// inferior a 0
				if (posicion >= rfile.length() || posicion < 0) {
					System.out.println("La posición indicada no es correcta");
				} else {
					rfile.seek(posicion);
					rfile.writeUTF(meses[valores[1] - 1]);
					rfile.writeInt(valores[2]);
					rfile.writeInt(valores[3]);
					System.out.println();
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
	 * @param valores Si vacío, lee y muestra el contenido de todo el fichero.
	 *                lugar
	 */

	public static void leer(int... valores) {
		try {
			if (valores.length == 0) {
				rfile.seek(0);
				while (rfile.getFilePointer() < rfile.length()) {
					System.out.println("-" + rfile.readUTF() + ", máxima: " + rfile.readInt() + " ºC, mínima: "
							+ rfile.readInt() + " ºC");
				}
				System.out.println();
			} else if (valores.length == 1) {
				// Calculo la posición
				int posicion = (valores[0] - 1) * TAMANIO;

				// Compruebo que la posición sea correcta
				if (posicion >= rfile.length() || posicion < 0) {
					System.out.println("La posición indicada no es correcta");
				} else {
					rfile.seek(posicion);
					System.out.println("Valor del mes " + valores[0] + ": " + rfile.readUTF() + ", máxima: "
							+ rfile.readInt() + " ºC, mínima: " + rfile.readInt() + " ºC");
				}
				System.out.println();
			}

		} catch (IOException e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
		}
	}

	public static void menu() {
		Scanner in = new Scanner(System.in);
		boolean salir = true;
		int opcion;
		int consultar, nuevoMes;
		int tempMax, tempMin;

		while (salir == true) {
			System.out.println("1. Leer fichero");
			System.out.println("2. Consultar");
			System.out.println("3. Añadir al final");
			System.out.println("4. Modificar valor");
			System.out.println("5. Salir");
			System.out.print("Escriba una de las opcines: ");
			opcion = in.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("\nLeyendo el contenido del fichero:");
				leer();
				break;
			case 2:
				System.out.println("\nConsultando una posición del fichero:");
				System.out.print("Indique el número del mes a consultar (1-12): ");
				consultar = in.nextInt();
				leer(consultar);
				break;
			case 3:
				System.out.println("Añadiendo elemento al final del fichero:");
				System.out.print("Indique el número del nuevo mes (1-12): ");
				nuevoMes = in.nextInt();
				System.out.print("Indique la temperatura máxima: ");
				tempMax = in.nextInt();
				System.out.print("Indique la temperatura mínima: ");
				tempMin = in.nextInt();
				escribir(nuevoMes, tempMax, tempMin);
				break;
			case 4:
				System.out.println("Modificando el valor de la posición:");
				System.out.print("Indique el número del mes a modificar (1-12): ");
				consultar = in.nextInt();
				System.out.print("Indique el número del nuevo mes (1-12): ");
				nuevoMes = in.nextInt();
				System.out.print("Indique la nueva temperatura máxima: ");
				tempMax = in.nextInt();
				System.out.print("Indique la nueva temperatura mínima: ");
				tempMin = in.nextInt();
				escribir(consultar, nuevoMes, tempMax, tempMin);
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
