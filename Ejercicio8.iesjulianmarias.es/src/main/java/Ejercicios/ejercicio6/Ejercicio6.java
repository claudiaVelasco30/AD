package Ejercicios.ejercicio6;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import utilidades.Utilidades;

public class Ejercicio6 {
	
	private static Scanner in = new Scanner(System.in);
	private static ObjectOutputStream oos;
	private final static String DOCTRABAJO_IN = "Ejercicio6.txt";
	
	public static void main(String[] args) {
		inicializar();
		escribirObjeto(obtenerDatos());
		leerObjeto();
		try {
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void inicializar() {
		 try {
			 File file = new File(Utilidades.RUTA+DOCTRABAJO_IN);
			 if (file.exists() && file.length() > 0) {
				oos = new MyObjectOutputStream(new FileOutputStream(file,true));
			} else {
				oos = new ObjectOutputStream(new FileOutputStream(file,true));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Pide por teclado los datos de la persona
	 * @return un objeto de la clase Persona
	 */
	public static Persona obtenerDatos() {
		Persona persona = new Persona();
		
		System.out.println("DATOS DEL USUARIO\n\tNombre: ");
		persona.setNombre(new StringBuilder (in.nextLine()));
		System.out.println("\tPrimer apellido: ");
		persona.setApellido1(new StringBuilder (in.nextLine()));
		System.out.println("\tSegundo apellido: ");
		persona.setApellido2(new StringBuilder (in.nextLine()));
		System.out.println("\tFecha de nacimiento (dd-mm-yyyy): ");
		
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd-mm-yyyy");
			persona.setNacimiento(formato.parse(in.nextLine()));
		} catch (ParseException e) {
			System.out.println("Error en el formato de la fecha");
			e.printStackTrace();
		}
		
		return persona;
	}
	
	/**
	 * Escribe un objeto de la clase Persona en un stream de salida
	 * @param persona Objeto a escribir
	 */
	public static void escribirObjeto(Persona persona) {
		try {
			oos.writeObject(persona);
		} catch (IOException e) {
			System.out.println("Error al abrir el fichero para escritura");
			e.printStackTrace();
		}
	}
	
	public static void leerObjeto() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(Utilidades.RUTA+DOCTRABAJO_IN)));
			try {
				Persona persona = new Persona();
				while (true) {
					persona = (Persona) ois.readObject();
					System.out.println(persona);
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Error de lectura");
				e.printStackTrace();
			} catch (EOFException e) {
				
			} finally {
				ois.close();
			}
			
		} catch (IOException e) {
			System.out.println("Error al abrir el fichero para lectura");
			e.printStackTrace();
		}
		
	}
}
