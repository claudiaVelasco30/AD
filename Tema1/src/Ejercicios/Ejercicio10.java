package Ejercicios;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Ejercicio10 {
	
	private static final String DOCTRABAJO_IN = "Ficheros/Ejercicio10.xml";
	
	public static void main (String[] args) {
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(new File(DOCTRABAJO_IN));
			
			URI uri = new URI("https://acortar.link/dO4FNx");
			Document docWeb = db.parse(uri.toURL().openStream());
			
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.transform(new DOMSource(doc), new StreamResult(System.out));
			
			System.out.println("\n\nDocumento en línea");
			t.transform(new DOMSource(docWeb), new StreamResult(System.out));
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
