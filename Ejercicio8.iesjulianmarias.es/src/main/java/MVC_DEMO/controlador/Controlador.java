package MVC_DEMO.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MVC_DEMO.modelo.Modelo;
import MVC_DEMO.vista.Vista;

public class Controlador implements ActionListener {

	private Modelo modelo;
	private Vista vista;
	
	public Controlador() {
		
	}
	
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		
		vista.btnSumar.addActionListener(this);
		vista.btnRestar.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		modelo.setOperador1(Integer.parseInt(vista.textFieldOperador1.getText()));
		modelo.setOperador2(Integer.parseInt(vista.textFieldOperador2.getText()));
		
		if(e.getSource() == vista.btnSumar) {
			vista.textFieldResult.setText(String.valueOf(modelo.suma()));
		} else if(e.getSource() == vista.btnRestar) {
			vista.textFieldResult.setText(String.valueOf(modelo.resta()));
		}
		
	}
	
}
