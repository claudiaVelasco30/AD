package app;

import java.util.HashSet;

import javax.xml.xquery.XQConnection;

import modelo.ModeloXQJ;
import modelo.clasesXML.Modulo;

public class App {

	public static void main(String[] args) {
		ModeloXQJ modeloxqj = new ModeloXQJ("admin", "ADMIN");
		
		XQConnection con = modeloxqj.getCon();
		modeloxqj.metaInformacion(con);
		
		
		modeloxqj.muestraModulosCiclo(con, "DAM");
		
		Modulo mod = new Modulo("0003", "Acceso a datos", 180, 2, new HashSet<String>());
		modeloxqj.anadirModulo(con, mod);
		
	}

}
