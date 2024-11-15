package MVC_DEMO.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textFieldResult;
	public JTextField textFieldOperador1;
	public JTextField textFieldOperador2;
	private JLabel lblResultado;
	public JButton btnSumar;
	public JButton btnRestar;


	/**
	 * Create the frame.
	 */
	public Vista() {
		setTitle("Calculadora simple");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 282, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 246, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textFieldOperador1 = new JTextField();
		textFieldOperador1.setBounds(124, 32, 86, 20);
		panel.add(textFieldOperador1);
		textFieldOperador1.setColumns(10);
		
		textFieldOperador2 = new JTextField();
		textFieldOperador2.setBounds(124, 68, 86, 20);
		panel.add(textFieldOperador2);
		textFieldOperador2.setColumns(10);
		
		textFieldResult = new JTextField();
		textFieldResult.setBounds(124, 122, 86, 20);
		panel.add(textFieldResult);
		textFieldResult.setColumns(10);
		
		JLabel lblOperador1 = new JLabel("Operador 1:");
		lblOperador1.setBounds(39, 35, 75, 14);
		panel.add(lblOperador1);
		
		JLabel lblNewLabel_1 = new JLabel("Operador 2:");
		lblNewLabel_1.setBounds(39, 71, 75, 14);
		panel.add(lblNewLabel_1);
		
		lblResultado = new JLabel("Resultado:");
		lblResultado.setBounds(39, 125, 75, 14);
		panel.add(lblResultado);
		
		btnSumar = new JButton("Sumar");
		btnSumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSumar.setBounds(25, 186, 89, 23);
		panel.add(btnSumar);
		
		btnRestar = new JButton("Restar");
		btnRestar.setBounds(121, 186, 89, 23);
		panel.add(btnRestar);
		
		setVisible(true);
	}
}
