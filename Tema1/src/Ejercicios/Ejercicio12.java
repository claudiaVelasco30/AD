package Ejercicios;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Ejercicios.ejercicio6.Persona;

public class Ejercicio12 {
	
	private static final String DOCTRABAJO = "Ficheros/Ejercicio12.xml";

	public static void main(String[] args) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Ficheros\\Ejercicio6")));
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			try {
				Persona persona = new Persona();
				
				Element raiz = doc.createElement("personas");
				doc.appendChild(raiz);
				
				while (true) {
					persona = (Persona) ois.readObject();
					
					Element hijo = doc.createElement("persona");
					raiz.appendChild(hijo);
					
					crearElemento("nombre", persona.getNombre().toString(), hijo, doc);
					crearElemento("apellido1", persona.getApellido1().toString(), hijo, doc);
					crearElemento("apellido2", persona.getApellido2().toString(), hijo, doc);
					crearElemento("nacimiento", persona.getNacimiento().toString(), hijo, doc);
					
					/*Element nombre = doc.createElement("nombre");
					hijo.appendChild(nombre);
					nombre.appendChild(doc.createTextNode((persona.getNombre()).toString()));
					
					Element apellido1 = doc.createElement("apellido1");
					hijo.appendChild(apellido1);
					apellido1.appendChild(doc.createTextNode((persona.getApellido1()).toString()));
					
					Element apellido2 = doc.createElement("apellido2");
					hijo.appendChild(apellido2);
					apellido2.appendChild(doc.createTextNode((persona.getApellido2()).toString()));
					
					Element nacimiento = doc.createElement("fecha_nacimiento");
					hijo.appendChild(nacimiento);
					nacimiento.appendChild(doc.createTextNode((persona.getNacimiento()).toString()));*/
					
					
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Error de lectura");
				e.printStackTrace();
			} catch (EOFException e) {
				
			} finally {
				ois.close();
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(doc), new StreamResult(System.out));
			t.transform(new DOMSource(doc), new StreamResult(new File(DOCTRABAJO)));
			
		} catch (IOException e) {
			System.out.println("Error al abrir el fichero para lectura");
			e.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	private static void crearElemento(String etiqueta, String valor, Element padre, Document doc) {
		Element elemento = doc.createElement(etiqueta);
		padre.appendChild(elemento);
		elemento.appendChild(doc.createTextNode(valor));
		
	}
	
	

}
