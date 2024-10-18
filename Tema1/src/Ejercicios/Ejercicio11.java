package Ejercicios;

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
import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Ejercicio11 {
	
	private static final String DOCTRABAJO = "Ficheros/Ejercicio11.xml";

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			//Creamos elementos del documento DOM
			
			Element raiz = doc.createElement("elementoRaiz");
			Element hijo1 = doc.createElement("elementoHijo");
			Element hijo2 = doc.createElement("elementoHijo");
			
			Text texto1 = doc.createTextNode("contenido hijo 1");
			Text texto2 = doc.createTextNode("contenido hijo 2");
			
			//Creamos estructura del DOM
			doc.appendChild(raiz);
			raiz.appendChild(hijo1);
			raiz.appendChild(hijo2);
			
			hijo1.appendChild(texto1);
			hijo1.setAttribute("nombre", "hijo1");
			hijo2.appendChild(texto2);
			hijo2.setAttribute("nombre", "hijo2");
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(doc), new StreamResult(System.out));
			t.transform(new DOMSource(doc), new StreamResult(new File(DOCTRABAJO)));
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
