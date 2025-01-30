package modelo;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xquery.*;

import modelo.clasesXML.Modulo;
import net.xqj.exist.ExistXQDataSource;

public class ModeloXQJ {
	
	private static String user;
	private static String passswd;
	
	public ModeloXQJ(String user, String passwd) {
		this.user = user;
		this.passswd = passwd;
	}

	public static XQConnection getCon() {
		XQConnection conn = null;
		try {
			XQDataSource xqs = new ExistXQDataSource();
			xqs.setProperty("serverName", "localhost");
			xqs.setProperty("port", "8080");
			xqs.setProperty("user", user);
			xqs.setProperty("password", passswd);
			conn = xqs.getConnection();
		} catch (XQException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}

	public void metaInformacion(XQConnection cnn) {
		try {
			XQMetaData xqmd = cnn.getMetaData();
			System.out.println("Nombre de usuario:" + xqmd.getUserName());
			System.out.println("Soporte de transacciones:" + xqmd.isTransactionSupported());
			System.out.println("Soporte de XQuery:" + xqmd.isXQueryXSupported());
			System.out.println("Solo lectura:" + xqmd.isReadOnly());
		} catch (XQException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Mostramos el nombre de los módulos de un ciclo cuyas siglas se pasan como prámetro
	 * @param siglas
	 */
	public void muestraModulosCiclo(XQConnection cnn, String siglas) {
		try {
			String consultaNombreCiclo = "doc('/db/ejercicio_clase/ciclos.xml')/fp/ciclos/ciclo[@siglas='" + siglas + "']/nombre";
			String consultaModulosCiclo = "for $mod in doc('/db/ejercicio_clase/ciclos.xml')/fp/modulos/modulo\r\n"
					+ "    where $mod/ciclos/ciclo/@siglas='" + siglas + "'\r\n"
					+ "    return $mod/nombre";
			
			XQExpression xqe = cnn.createExpression();
			XQResultSequence xqrs = xqe.executeQuery(consultaModulosCiclo);
			
			while (xqrs.next()) {
				XMLStreamReader xsr = xqrs.getItemAsStream();
				while (xsr.hasNext()) {
					if (xsr.getEventType() == XMLStreamConstants.CHARACTERS) {
						System.out.println(xsr.getText());
					}
					
					xsr.next();
				}
			}
			
		} catch (XQException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public void anadirModulo(XQConnection cnn, Modulo modulo) {
		try {
			XQExpression xqe = cnn.createExpression();
			
			String elemento = String.format(
					"update insert<modulo codigo=%s>"
							+ "<nombre>%s</nombre>"
							+ "<duracion unidad='horas'>%d</duracion>\r\n"
							+ "<curso>%d</curso>",
					modulo.getCodigo(), modulo.getNombre(), modulo.getHoras(), modulo.getCurso());
			
			xqe.executeCommand(elemento);
			
		} catch (XQException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
