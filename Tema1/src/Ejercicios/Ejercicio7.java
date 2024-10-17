package Ejercicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

public class Ejercicio7 {

	private static Properties conf = new Properties();

	public static void main(String[] args) {
		crearConfiguracion();
		System.out.println("Configuración inicial");
		leerConfiguracion();
		modificarConfiguracion();
		System.out.println("\nConfiguración modificada");
		leerConfiguracion();
	}

	private static void crearConfiguracion() {
		conf.setProperty("user", "usuario");
		conf.setProperty("password", "micontrasena");
		conf.setProperty("server", "localhost");
		conf.setProperty("port", "3306");
		conf.setProperty("date", "2022-11-12");
		conf.setProperty("power", "true");

		try {
			conf.store(new FileOutputStream(new File("Ficheros\\configuracion.props")), "Fichero de configuracion");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void leerConfiguracion() {
		try {
			conf.load(new FileInputStream("Ficheros\\configuracion.props"));
			conf.list(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void modificarConfiguracion() {
		//Capturar excepciones
		Scanner in = new Scanner(System.in);
		System.out.println("Modificando configuración");
		try {
			conf.load(new FileInputStream("Ficheros\\configuracion.props"));

			System.out.print("user: ");
			conf.setProperty("user", in.nextLine());

			System.out.print("password: ");
			conf.setProperty("password", in.nextLine());

			System.out.print("server: ");
			conf.setProperty("server", in.nextLine());

			System.out.print("valor a sumar al puerto: ");
			int port = Integer.valueOf(in.nextLine());
			port = Integer.valueOf(conf.getProperty("port")) + port;
			conf.setProperty("port", String.valueOf(port));

			System.out.print("nuevo mes para la fecha: ");
			int date = Integer.valueOf(in.nextLine());
			conf.setProperty("date", String.valueOf(LocalDate.parse(conf.getProperty("date")).withMonth(date)));

			conf.setProperty("power", String.valueOf(!(Boolean.valueOf(conf.getProperty("power")))));
			
			conf.store(new FileOutputStream("Ficheros\\configuracion.props"), "Fichero modificado");
			in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
