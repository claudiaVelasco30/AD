package MVC_Ejercicio18.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import MVC_Ejercicio18.modelo.Departamento;
import MVC_Ejercicio18.modelo.Modelo;
import MVC_Ejercicio18.vista.Vista;

public class Controlador {
	private Modelo modelo;
	private Vista vista;

	
	public Controlador() {

	}

	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		
		vista.btnNuevo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarControles();
				
			}
		});
		
		vista.btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modelo.anadirDpto(vista.textFieldNombre.getText(), vista.textFieldLocalidad.getText());
				limpiarControles();
			}
		});
		
		vista.btnListar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Departamento> dptoListado = modelo.listarDptos();
				for(Departamento dpto:dptoListado) {
					
				}
			}
		});
	}
	
	protected void limpiarControles() {
		vista.textFieldNombre.setText(null);
		vista.textFieldLocalidad.setText(null);
	}
}
