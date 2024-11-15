package MVC_Ejercicio18.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField textFieldUsuario;
	public JTextField textFieldContrasenia;
	public JTextField textFieldNombre;
	public JTextField textFieldLocalidad;
	public JTable tableResultados;
	public DefaultTableModel modeloTb1 = new DefaultTableModel();
	public JButton btnIniciarSesion;
	public JButton btnNuevo;
	public JButton btnListar;
	public JButton btnModificar;
	public JButton btnBorrar;
	public JButton btnGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 469);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnConectar = new JMenu("Conectar");
		menuBar.add(mnConectar);
		
		JMenu mnConectarBD = new JMenu("Conectar ddbb");
		mnConectar.add(mnConectarBD);
		
		JMenuItem mntmOracle = new JMenuItem("Oracle");
		mnConectarBD.add(mntmOracle);
		
		JMenuItem mntmMySQL = new JMenuItem("MySQL");
		mnConectarBD.add(mntmMySQL);
		
		JMenuItem mntmSQLite = new JMenuItem("SQLite");
		mnConectarBD.add(mntmSQLite);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnConectar.add(mntmNewMenuItem);
		
		JMenu mnSalir = new JMenu("Salir");
		menuBar.add(mnSalir);
		
		JMenuItem mntmDesconectar = new JMenuItem("Desconectar");
		mnSalir.add(mntmDesconectar);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnSalir.add(mntmSalir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 26, 232, 144);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(10, 24, 46, 14);
		panel.add(lblNewLabel);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(83, 21, 119, 20);
		panel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setBounds(10, 63, 56, 14);
		panel.add(lblNewLabel_1);
		
		textFieldContrasenia = new JTextField();
		textFieldContrasenia.setBounds(83, 60, 119, 20);
		panel.add(textFieldContrasenia);
		textFieldContrasenia.setColumns(10);
		
		btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.setBounds(83, 103, 119, 23);
		panel.add(btnIniciarSesion);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(267, 26, 388, 370);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 20, 46, 14);
		panel_1.add(lblNombre);
		
		JLabel lblNewLabel_1_1 = new JLabel("Localidad");
		lblNewLabel_1_1.setBounds(22, 54, 56, 14);
		panel_1.add(lblNewLabel_1_1);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(88, 17, 119, 20);
		panel_1.add(textFieldNombre);
		
		textFieldLocalidad = new JTextField();
		textFieldLocalidad.setColumns(10);
		textFieldLocalidad.setBounds(88, 51, 119, 20);
		panel_1.add(textFieldLocalidad);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(246, 11, 109, 23);
		panel_1.add(btnNuevo);
		
		btnListar = new JButton("Listar");
		btnListar.setBounds(246, 45, 109, 23);
		panel_1.add(btnListar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(246, 79, 109, 23);
		panel_1.add(btnModificar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(246, 113, 109, 23);
		panel_1.add(btnBorrar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(246, 147, 109, 23);
		panel_1.add(btnGuardar);
		
		tableResultados = new JTable();
		tableResultados.setBounds(22, 181, 333, 171);
		panel_1.add(tableResultados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 181, 333, 171);
		panel_1.add(scrollPane);
		
		setVisible(true);
	}
}
