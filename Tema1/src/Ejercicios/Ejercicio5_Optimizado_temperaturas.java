package Ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio5_Optimizado_temperaturas {

	private static RandomAccessFile rfile;

	public static void main(String[] args) {
		try {
			rfile = new RandomAccessFile(new File("Ficheros\\fichero5_temperaturas.dat"), "rw");

			if (rfile.length() == 0) {
				escribir();
			}

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
		String[] meses = { "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic" };
		int[] tempMax = { 12, 13, 16, 19, 24, 29, 34, 34, 28, 21, 15, 11 };
		int[] tempMin = { 3, 4, 6, 8, 12, 18, 22, 21, 18, 13, 7, 4 };

		try {
			for (int i = 0; i < meses.length; i++) {
				String registro = meses[i] + ";" + tempMax[i] + ";" + tempMin[i] + "\n";
				rfile.writeChars(registro);
			}
		} catch (IOException e) {
			System.out.println("Error de escritura");
			e.printStackTrace();
		}
	}

	public static void leer() {
		try {
			rfile.seek(0);
			String linea;
			while (rfile.getFilePointer() != rfile.length()) {
				String[] datos = rfile.readLine().split(";");
				System.out.println("Mes: " + datos[0] + ", Máxima: " + datos[1] + "ºC, Mínima: " + datos[2] + "ºC");
			}
		} catch (IOException e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
		}
	}

	public static void consultar(int lugar) {
		try {
			rfile.seek(0);
			String linea;
			int contador = 1;
			while ((linea = rfile.readLine()) != null) {
				if (contador == lugar) {
					String[] datos = linea.split(";");
					String mes = datos[0];
					int tempMax = Integer.parseInt(datos[1]);
					int tempMin = Integer.parseInt(datos[2]);
					System.out.println("Mes: " + mes + " - Máxima: " + tempMax + "ºC, Mínima: " + tempMin + "ºC");
					return;
				}
				contador++;
			}
			System.out.println("No se encontr� el mes en la posición indicada.");

		} catch (IOException e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
		}
	}

	public static void anadirFinal(String mes, int tempMax, int tempMin) {
		try {
			rfile.seek(rfile.length());
			String registro = mes + ";" + tempMax + ";" + tempMin + "\n";
			rfile.writeBytes(registro);
			System.out.println("Registro añadido correctamente.");

		} catch (IOException e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
		}
	}

	public static void modificarValor(String mes, int tempMax, int tempMin, int lugar) {
		try {
			rfile.seek(0);
			StringBuilder nuevoContenido = new StringBuilder();
			String linea;
			int contador = 1;
			while ((linea = rfile.readLine()) != null) {
				if (contador == lugar) {
					linea = mes + ";" + tempMax + ";" + tempMin;
				}
				nuevoContenido.append(linea).append("\n");
				contador++;
			}
			rfile.setLength(0);
			rfile.writeBytes(nuevoContenido.toString());
			System.out.println("Registro modificado correctamente.");

		} catch (IOException e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
		}
	}

	public static void menu() {
		Scanner in = new Scanner(System.in);
		boolean salir = true;
		int opcion;

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
				System.out.println("Leyendo el contenido del fichero:");
				leer();
				break;
			case 2:
				System.out.print("Indique el número del mes a consultar (1-12): ");
				int mesConsulta = in.nextInt();
				consultar(mesConsulta);
				break;
			case 3:
				System.out.print("Indique el nombre del mes: ");
				String nuevoMes = in.next();
				System.out.print("Indique la temperatura máxima: ");
				int tempMax = in.nextInt();
				System.out.print("Indique la temperatura mínima: ");
				int tempMin = in.nextInt();
				anadirFinal(nuevoMes, tempMax, tempMin);
				break;
			case 4:
				System.out.print("Indique el n�mero del mes a modificar (1-12): ");
				int mesMod = in.nextInt();
				System.out.print("Indique el nuevo nombre del mes: ");
				String mesNuevo = in.next();
				System.out.print("Indique la nueva temperatura máxima: ");
				int nuevaTempMax = in.nextInt();
				System.out.print("Indique la nueva temperatura mínima: ");
				int nuevaTempMin = in.nextInt();
				modificarValor(mesNuevo, nuevaTempMax, nuevaTempMin, mesMod);

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
