package Ejercicios;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class Ejercicio12_ampliacion {

	public static void main(String[] args) {
		LeerXML();

	}

	public static void LeerXML() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Cargamos en memoria el doc XML
			Document doc = builder.parse(new File("Ficheros/Ejercicio12.xml"));

			LeeNodo(doc.getDocumentElement());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void LeeNodo(Node nodo) {

		if (nodo.getNodeType() == Node.ELEMENT_NODE) {
			System.out.print("<" + nodo.getNodeName() + ">");

			NodeList nodosHijos = nodo.getChildNodes();
			for (int i = 0; i < nodosHijos.getLength(); i++) {
				LeeNodo(nodosHijos.item(i));
			}

			System.out.print("</" + nodo.getNodeName() + ">");

		} else if (nodo.getNodeType() == Node.TEXT_NODE) {
			System.out.print(nodo.getNodeValue());

		}

	}

}
