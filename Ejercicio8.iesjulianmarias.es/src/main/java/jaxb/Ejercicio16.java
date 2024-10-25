package jaxb;

import org.glassfish.jaxb.runtime.v2.runtime.property.UnmarshallerChain;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jaxb.clasesEjercicio16.Examen;

public class Ejercicio16 {
	
	private static JAXBContext jc;

	public static void main(String[] args) {
		
	}
	
	/**
	 * Carga en el javabean (clases creadas y etiquetadas) en xml
	 */
	public static void unmarshaling() {
		try {
			jc = JAXBContext.newInstance(Examen.class);
			Unmarshaller um  = jc.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}
