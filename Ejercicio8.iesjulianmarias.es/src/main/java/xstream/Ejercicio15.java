package xstream;

import java.io.ObjectInputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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
	private static ObjectInputStream ois = null;

	public static void main(String[] args) {
		XStream xs = null;
		ListaPersonas lp = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(new File(Utilidades.RUTA + DOCUMENTO_IN)));
			xs = new XStream(new DomDriver("UTF-8"));
			lp = new ListaPersonas();
			while (true) {
				Persona persona = (Persona) ois.readObject();
				lp.añadir(persona);
			}

		}catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (EOFException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				xs.toXML(lp, new FileOutputStream(Utilidades.RUTA + DOCUMENTO_OUT));
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
