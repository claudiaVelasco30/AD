package Ejercicios;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Ejercicio14 {

	private final static String DOCTRABAJO_XSL = "Ficheros/Ejercicio14.xsl";
	private final static String DOCTRABAJO_XML = "Ficheros/Ejercicio14.xml";
	private final static String DOCTRABAJO_HTML = "Ficheros/Ejercicio14.html";
	
	public static void main(String[] args) {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer(new StreamSource(DOCTRABAJO_XSL));
			t.transform(new StreamSource(DOCTRABAJO_XML), new StreamResult(DOCTRABAJO_HTML));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

}
