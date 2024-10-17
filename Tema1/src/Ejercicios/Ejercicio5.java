package Ejercicios;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio5 {

	private static final int TAMANIO = 4;

	private static RandomAccessFile rfile;

	public static void main(String[] args) {
		try {
			rfile = new RandomAccessFile(new File("Ficheros\\fichero5.dat"), "rw");

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

	public static void escribir() {
		for (int i = 1; i < 101; i++) {
			try {
				rfile.writeInt(i);
			} catch (IOException e) {
				System.out.println("Error de escritura");
				e.printStackTrace();
			}
		}
	}

	public static void leer() {
		try {
			rfile.seek(0);
			while (rfile.getFilePointer() < rfile.length()) {
				System.out.println(rfile.readInt());
			}
		} catch (IOException e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
		}
	}

	public static void consultar(int lugar) {
		try {
			// Calculo la posición
			int posicion = (lugar - 1) * TAMANIO;

			// Compruebo que la posición sea correcta
			if (posicion >= rfile.length() || posicion < 0) {
				System.out.println("La posición indicada no es correcta");
			} else {
				rfile.seek(posicion);
				System.out.println("Valor de la posición " + lugar + ": " + rfile.readInt());
			}

		} catch (IOException e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
		}
	}

	public static void anadirFinal(int numero) {
		try {
			rfile.seek(rfile.length());
			rfile.writeInt(numero);

		} catch (IOException e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
		}
	}

	public static void modificarValor(int nuevoValor, int lugar) {
		try {
			int posicion = (lugar - 1) * TAMANIO;

			if (posicion >= rfile.length() || posicion < 0) {
				System.out.println("La posición indicada no es correcta");
			} else {
				rfile.seek(posicion);
				rfile.writeInt(nuevoValor);
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

		while (!salir) {
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
				consultar(consultar);
				break;
			case 3:
				System.out.println("Añadiendo elemento al final del fichero");
				System.out.print("Indique el valor a añadir: ");
				numFinal = in.nextInt();
				consultar(numFinal);
				break;
			case 4:
				System.out.println("Modificando el valor de la posición");
				System.out.print("Indique la posición a modificar: ");
				lugar = in.nextInt();
				System.out.print("indique el nuevo valor: ");
				modificarValor(in.nextInt(), lugar);

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
