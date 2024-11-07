package jaxb;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import org.glassfish.jaxb.runtime.v2.runtime.property.UnmarshallerChain;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jaxb.clasesEjercicio16.Autor;
import jaxb.clasesEjercicio16.Examen;
import utilidades.Utilidades;

public class Ejercicio16 {

	private static JAXBContext jc;
	private static Examen examen;
	private static ArrayList<Autor> autores;
	private static final String DOCUMENTO = "ejercicio16.xml";

	public static void main(String[] args) {
		unmarshaling();
		menu();
		marshaling();
	}

	private static void menu() {
		Scanner in = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		boolean salir = false;
		while (!salir) {
			System.out.println("1. Leer autores \n2. Añadir autor \n3. Modificar autor \n4. Eliminar autor \n5. Salir");
			try {
				System.out.print("Seleccione una opción: ");
				switch (in.nextInt()) {
				case 1:
					System.out.println("Leyendo autores");
					leerAutores();
					break;
				case 2:
					System.out.println("Añadiendo autor");
					System.out.println(anadirAutor(pedirDatosAutor())?"Autor añadido correctamente":"El autor no se pudo añadir");
					break;
				case 3:
					System.out.println("Modificando autor");
					System.out.print("Id del autor a modificar: ");
					String idAutor = in2.nextLine();
					System.out.print("Nueva entidad de trabajo: ");
					String newET = in2.nextLine();
					System.out.print("Nuevo puesto: ");
					String newP = in2.nextLine();
					modificarAutor(idAutor, newET, newP);
					break;
				case 4:
					System.out.println("Eliminando autor");
					System.out.print("Id del autor a eliminar: ");
					idAutor = in2.nextLine();
					System.out.println((eliminarAutor(idAutor))?"Autor añadido correctamente":"El autor no se pudo añadir");
					break;
				case 5:
					System.out.println("Saliendo del programa");
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 5");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes escribir un número");
				in.next();
			}
		}
	}

	private static void leerAutores() {
		Iterator<Autor> it = autores.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());

		}
	}

	private static boolean anadirAutor(Autor autor) {
		return autores.add(autor);
	}

	private static Autor pedirDatosAutor() {
		Scanner in = new Scanner(System.in);
		Autor autor = new Autor();
		System.out.print("Identificador: ");
		autor.setId(in.nextLine());
		System.out.print("Nombre: ");
		autor.setNombre(in.nextLine());
		System.out.print("Primer Apellido: ");
		autor.setApellido1(in.nextLine());
		System.out.print("Segundo apellido: ");
		autor.setApellido2(in.nextLine());
		System.out.print("Entidad de trabajo: ");
		autor.setEntidadTrabajo(in.nextLine());
		System.out.print("Puesto: ");
		autor.setPuesto(in.nextLine());
		return autor;
	}

	private static void modificarAutor(String idAutor, String newET, String newP) {
		Autor autor = localizarAutor(idAutor);
		autor.setEntidadTrabajo(newET);
		autor.setPuesto(newP);
	}

	private static Autor localizarAutor(String idAutor) {
		Iterator<Autor> it = autores.iterator();
		Autor autorEnCurso;
		while(it.hasNext()) {
			autorEnCurso = it.next();
			if(autorEnCurso.getId().equals(idAutor)){
				return autorEnCurso;
			}
		}
		return null;
	}
	
	private static boolean eliminarAutor(String idAutor) {
		return autores.remove(localizarAutor(idAutor));
	}
	

	/**
	 * Carga en el javabean (clases creadas y etiquetadas) en xml
	 */
	public static void unmarshaling() {
		try {
			jc = JAXBContext.newInstance(Examen.class);
			Unmarshaller um = jc.createUnmarshaller();
			examen = (Examen) um.unmarshal(new File(Utilidades.RUTA + DOCUMENTO));
			autores = examen.getListaAutor();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public static void marshaling() {
		try {
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(examen, new File(Utilidades.RUTA + DOCUMENTO));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
