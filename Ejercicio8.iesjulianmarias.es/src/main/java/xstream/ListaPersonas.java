package xstream;

import java.util.ArrayList;
import Ejercicios.ejercicio6.Persona;

public class ListaPersonas {
	private ArrayList<Persona> lista = new ArrayList<Persona>();

	public ListaPersonas() {
		super();
	}

	public ListaPersonas(ArrayList<Persona> lista) {
		super();
		this.lista = lista;
	}

	public ArrayList<Persona> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Persona> lista) {
		this.lista = lista;
	}
	
	public void añadir(Persona persona) {
		lista.add(persona);
	}
}
