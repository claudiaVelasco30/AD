package xstream;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Ejercicios.ejercicio6.Ejercicio6;
import Ejercicios.ejercicio6.Persona;
import utilidades.Utilidades;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio15 {

	private final static String DOCUMENTO_IN = "Ejercicio6.txt";
	private final static String DOCUMENTO_OUT = "Ejercicio15.xml";
	private final static String DOCUMENTO_IN2 = "ejercicio15_in.xml";
	private static ObjectInputStream ois = null;
	private static XStream xs = new XStream(new DomDriver("UTF-8"));

	public static void main(String[] args) {

		//deserializa_a_xml();
		serializa_desde_xml();
		Ejercicio6.leerObjeto();

	}

	private static void deserializa_a_xml() {
		ListaPersonas lp = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(new File(Utilidades.RUTA + DOCUMENTO_IN)));
			lp = new ListaPersonas();
			while (true) {
				Persona persona = (Persona) ois.readObject();
				lp.añadir(persona);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				defineEstructura();
				xs.toXML(lp, new FileOutputStream(Utilidades.RUTA + DOCUMENTO_OUT));
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void defineEstructura() {
		xs.alias("familia", ListaPersonas.class);
		xs.alias("persona", Persona.class);
		xs.addImplicitCollection(ListaPersonas.class, "lista");
		xs.aliasField("primerApellido", Persona.class, "apellido1");
		xs.aliasField("segundoApellido", Persona.class, "apellido2");
		xs.aliasField("name", Persona.class, "nombre");
		xs.useAttributeFor(Persona.class, "nombre");
	}

	private static void serializa_desde_xml() {
		try {
			ListaPersonas lp = new ListaPersonas();
			xs.addPermission(AnyTypePermission.ANY);
			defineEstructura();
			xs.aliasField("nombre", Persona.class, "nombre");
			lp = (ListaPersonas) xs.fromXML(new FileInputStream(Utilidades.RUTA + DOCUMENTO_IN2));

			Iterator<Persona> it = lp.getLista().iterator();
			Ejercicio6.inicializar();

			Persona p = new Persona();
			while (it.hasNext()) {
				p = it.next();
				Ejercicio6.escribirObjeto(p);
			}

			try {
				Ejercicio6.getoos().close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
