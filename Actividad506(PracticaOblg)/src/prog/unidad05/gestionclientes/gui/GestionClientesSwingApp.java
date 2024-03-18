package prog.unidad05.gestionclientes.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import prog.unidad05.gestionclientes.core.Cliente;
import prog.unidad05.gestionclientes.core.Clientes;
import prog.unidad05.gestionclientes.core.ClientesException;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientesException;
import prog.unidad05.gestionclientes.impl.ProveedorAlmacenamientoClientesFichero;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class GestionClientesSwingApp {

	private JFrame frame;
	// campo de texto del nif
	private JTextField txtNIF;
	// campo de texto del nombre
	private JTextField txtNombre;
	// campo de texto del empleados
	private JTextField txtEmpleados;
	// campo de texto del apellidos
	private JTextField txtApellidos;
	// campo de texto del facturacion
	private JTextField txtFacturacion;

	// Ruta donde se va a crear el fichero
	private String rutaFichero = "clientes.dat";
	// btn para crear cliente
	private JButton btnNuevo;
	// btn para eliminar cliente
	private JButton btnEliminar;
	// btn para confirmar un proceso
	private JButton btnAceptar;
	// btn para cancelar un proceso
	private JButton btnCancelar;
	// btn para actualizar un cliente
	private JButton btnActualizar;
	// btn para salir del programa
	private JButton btnSalir;

	// Modelo para la lista
	private DefaultListModel<String> listModel;
	// Lista para mostrar cliente
	private JList<String> lstClientes;
	// Array de clientes
	private Cliente[] arrayClientes;
	// Almacen secundario de clientes
	private Clientes clientes;
	// Proveedor del almancen
	private ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero(rutaFichero);
	// Checkbox
	private JCheckBox checkBoxUE;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionClientesSwingApp window = new GestionClientesSwingApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GestionClientesSwingApp() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(600, 250, 580, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Todos los campos de la interfaz, campos de datos, labels y botones
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 40, 550, 200);

		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setBounds(10, 11, 70, 25);
		lblClientes.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		JLabel lblNif = new JLabel("NIF:");
		lblNif.setBounds(10, 250, 34, 25);
		lblNif.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		txtNIF = new JTextField();
		txtNIF.setBounds(33, 250, 86, 25);
		txtNIF.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(130, 252, 70, 25);
		lblNombre.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		txtNombre = new JTextField();
		txtNombre.setBounds(180, 252, 147, 25);
		txtNombre.setColumns(10);

		JLabel lblEmpleados = new JLabel("Empleados");
		lblEmpleados.setBounds(347, 250, 70, 25);
		lblEmpleados.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		txtEmpleados = new JTextField();
		txtEmpleados.setBounds(420, 250, 135, 25);
		txtEmpleados.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 300, 70, 25);
		lblApellidos.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		txtApellidos = new JTextField();
		txtApellidos.setBounds(75, 300, 252, 25);
		txtApellidos.setColumns(10);

		JLabel lblFacturacion = new JLabel("Facturación");
		lblFacturacion.setBounds(340, 300, 80, 25);
		lblFacturacion.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		txtFacturacion = new JTextField();
		txtFacturacion.setBounds(420, 300, 135, 25);
		txtFacturacion.setColumns(10);

		checkBoxUE = new JCheckBox("¿Es nacional de la UE?");
		checkBoxUE.setBounds(10, 340, 200, 25);
		checkBoxUE.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		/**
		 * Boton Añadir Cliente
		 */
		// Botón para añadir un nuevo cliente
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(10, 380, 125, 40);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anyadirCliente();
			}
		});
		btnNuevo.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		/**
		 * Boton Eliminar cliente
		 */
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(220, 380, 125, 40);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Eliminamos cliente
				eliminarCliente();
				// Y vaciamos los campos
				txtNIF.setText("");
				txtNombre.setText("");
				txtApellidos.setText("");
				txtFacturacion.setText("");
				txtEmpleados.setText("");
				checkBoxUE.setSelected(false);
			}
		});
		btnEliminar.setFont(new Font("Bebas Neue", Font.PLAIN, 20));

		/**
		 * Boton Actualizar cliente
		 */
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(430, 380, 125, 40);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualizamos el cliente
				actualizarCliente();
			}
		});
		btnActualizar.setFont(new Font("Bebas Neue", Font.PLAIN, 20));

		/**
		 * Boton aceptar
		 */
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10, 440, 125, 40);
		btnAceptar.setFont(new Font("Bebas Neue", Font.PLAIN, 20));

		/**
		 * Boton cancelar
		 */
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(220, 440, 125, 40);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Restauramos el estado original de la interfaz si se le da a cancelar
				estadoOriginal();
			}
		});
		btnCancelar.setFont(new Font("Bebas Neue", Font.PLAIN, 20));

		/**
		 * Boton para salir del programa
		 */
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(430, 440, 125, 40);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Sacamos dialogo para preguntar si se esta seguro
				int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres salir?", "Confirmar salida",
						JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					// Si el usuario confirma, cerramos la aplicación
					System.exit(0);
				}
			}
		});
		btnSalir.setFont(new Font("Bebas Neue", Font.PLAIN, 20));

		/**
		 * Lista de clientes
		 */
		lstClientes = new JList();
		lstClientes.setFont(new Font("Bebas Neue", Font.PLAIN, 20));
		try {
			// Obtenemos la lista de clientes
			arrayClientes = proveedor.getAll();
		} catch (ProveedorAlmacenamientoClientesException e1) {
		}
		// Creamos el almacen de clientes con su proveedor
		Clientes clientes = new Clientes(proveedor);
		lstClientes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// Obtenemos el nif del cliente que se seleccione en la lista
				String clienteSeleccionado = (String) lstClientes.getSelectedValue();
				String nifClienteSeleccionado = null;
				if (clienteSeleccionado != null) {
					nifClienteSeleccionado = clienteSeleccionado.substring(5, 14);
				}
				try {
					// Creamos un cliente que hemos encontrado en la lista
					Cliente clienteAMostrar = clientes.getByNif(nifClienteSeleccionado);
					// Rellenamos los campos con los datos del cliente
					txtNIF.setText(nifClienteSeleccionado);
					txtNombre.setText(clienteAMostrar.getNombre());
					txtApellidos.setText(clienteAMostrar.getApellidos());
					btnCancelar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// Actualizamos la lista de clientes al cancelar
							updateListaClientes();
							btnCancelar.setEnabled(false);
						}
					});
					// Pasamos facturacion a String
					txtFacturacion.setText(String.valueOf(clienteAMostrar.getFacturacion()));
					txtEmpleados.setText(String.valueOf(clienteAMostrar.getEmpleados()));
					boolean esNacionalUe = clienteAMostrar.isNacionalUe();
					checkBoxUE.setSelected(true);

				} catch (NullPointerException e1) {
				}

			}
		});
		scrollPane.setViewportView(lstClientes);
		listModel = new DefaultListModel<>();

		try {
			// Obtenemos los clientes del array
			arrayClientes = proveedor.getAll();
			// Para cada cliente
			for (Cliente clienteLista : arrayClientes) {
				// Añadimos a la lista el cliente en forma de string
				listModel.addElement(
						"Nif: " + clienteLista.getNif() + ", " + clienteLista.getApellidos() + ", " + clienteLista.getNombre());
			}
		} catch (ProveedorAlmacenamientoClientesException e) {
		}
		lstClientes.setModel(listModel);

		// Al iniciar la aplicacion deshabilitamos los botones y los campos de texto
		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
		btnAceptar.setEnabled(false);
		btnCancelar.setEnabled(false);
		txtNIF.setEditable(false);
		txtNombre.setEditable(false);
		txtApellidos.setEditable(false);
		txtFacturacion.setEditable(false);
		txtEmpleados.setEditable(false);
		checkBoxUE.setEnabled(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblClientes);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(txtNIF);
		frame.getContentPane().add(lblNif);
		frame.getContentPane().add(txtNombre);
		frame.getContentPane().add(lblNombre);
		frame.getContentPane().add(lblEmpleados);
		frame.getContentPane().add(txtEmpleados);
		frame.getContentPane().add(txtApellidos);
		frame.getContentPane().add(lblApellidos);
		frame.getContentPane().add(lblFacturacion);
		frame.getContentPane().add(txtFacturacion);
		frame.getContentPane().add(checkBoxUE);
		frame.getContentPane().add(btnNuevo);
		frame.getContentPane().add(btnEliminar);
		frame.getContentPane().add(btnActualizar);
		frame.getContentPane().add(btnAceptar);
		frame.getContentPane().add(btnCancelar);
		frame.getContentPane().add(btnSalir);

		lstClientes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// Verifica si hay un elemento seleccionado en la lista
				// Si no hay nada seleccionado
				if (!lstClientes.isSelectionEmpty()) {
					btnEliminar.setEnabled(true);
					btnActualizar.setEnabled(true);
					btnNuevo.setEnabled(false);
					btnCancelar.setEnabled(true);
				} else {
					// Si se selecciona algun cliente
					// Habilitamos los botones de actualizar y de eliminar
					// Deshabilitamos el boton de añadir cliente
					btnEliminar.setEnabled(false);
					btnActualizar.setEnabled(false);
					btnNuevo.setEnabled(true);
				}
			}
		});
	}

//Método privado para añadir un nuevo cliente
	private void anyadirCliente() {
		// Desactivamos y activamos los botones correspondientes
		btnAceptar.setEnabled(true);
		btnCancelar.setEnabled(true);
		btnNuevo.setEnabled(false);
		txtNIF.setEditable(true);
		txtNombre.setEditable(true);
		txtApellidos.setEditable(true);
		txtFacturacion.setEditable(true);
		txtEmpleados.setEditable(true);
		checkBoxUE.setEnabled(true);
		lstClientes.setEnabled(false);

		// ActionListener del botón "Aceptar"
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Se recogen los datos de los txt
					String nif = txtNIF.getText();
					String nombre = txtNombre.getText();
					String apellidos = txtApellidos.getText();
					int empleados = Integer.parseInt(txtEmpleados.getText());
					double facturacion = Double.parseDouble(txtFacturacion.getText());
					boolean esNacional = checkBoxUE.isSelected();
					// Creamos el cliente
					Cliente clienteAAnyadir = new Cliente(nif, apellidos, nombre, empleados, facturacion, esNacional);

					// Rellenamos el almacen de clientes
					clientes = new Clientes(proveedor);
					// Añadimos el cliente
					clientes.addCliente(clienteAAnyadir);
					// Sobreescribimos todo
					Cliente[] arrayAnyadirClientes = new Cliente[arrayClientes.length + 1];
					for (int i = 0; i < arrayClientes.length; i++) {
						arrayAnyadirClientes[i] = arrayClientes[i];
					}
					arrayAnyadirClientes[arrayClientes.length] = clienteAAnyadir;
					// Guardamos la lista con el cliente nuevo
					proveedor.saveAll(arrayAnyadirClientes);
					// Actualizamos la lista en la interfaz
					updateListaClientes();
					// Restauramos el estado de los botones y campos de texto
					estadoOriginal();
				} catch (NumberFormatException ex) {
					// Manejo de la excepción si hay errores en el formato de los campos numéricos
					JOptionPane.showMessageDialog(frame, "Error en el formato de los campos numéricos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (IllegalArgumentException ex) {
					// Manejo de la excepción si hay errores en el formato de los campos numéricos
					JOptionPane.showMessageDialog(frame, "Error en el formato de los campos de texto.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException | ClientesException | ProveedorAlmacenamientoClientesException ex) {
					// Manejamos cualquier otra excepción ocurrida durante la creación del cliente
					JOptionPane.showMessageDialog(frame, "Error al crear el cliente. Por favor, revise los campos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

//Metodo para actualizar la lista cada vez que se añada, actualice o se elimine algún cliente
	private void updateListaClientes() {
		try {
			// HAbilitamos la lista
			lstClientes.setEnabled(true);
			// Obtenemos los clientes del fichero
			arrayClientes = proveedor.getAll();
			DefaultListModel<String> listModel = new DefaultListModel<>();
			// Para cada cliente de la lista
			for (Cliente clienteLista : arrayClientes) {
				// Escribimos los clientes en forma de string
				listModel.addElement(
						"Nif: " + clienteLista.getNif() + ", " + clienteLista.getApellidos() + ", " + clienteLista.getNombre());
			}
			// Habilitamos y reiniciamos los botones y campos correspondientes
			lstClientes.setModel(listModel);
			lstClientes.clearSelection();
			txtNIF.setText("");
			txtNombre.setText("");
			txtApellidos.setText("");
			txtFacturacion.setText("");
			txtEmpleados.setText("");
			checkBoxUE.setSelected(false);
		} catch (ProveedorAlmacenamientoClientesException e) {
		}
	}

	// Método para eliminar un cliente
	private void eliminarCliente() {
		// Obtenemos el nif del cliente que se quiere eliminar
		String nifClienteAEliminar = txtNIF.getText();
		// Dialogo para confirmar que se quiere eliminar
		int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar este cliente?",
				"Confirmar eliminación", JOptionPane.YES_NO_OPTION);
		// "Si"
		if (opcion == JOptionPane.YES_OPTION) {
			try {
				// Eliminamos el cliente
				clientes = new Clientes(proveedor);
				clientes.removeCliente(nifClienteAEliminar);
				updateListaClientes();

			} catch (NullPointerException | ClientesException e1) {
			}
		}
	}

	private void actualizarCliente() {
		btnActualizar.setEnabled(false);
		// Obtenemos el NIF del cliente
		String nif = txtNIF.getText();

		try {
			// Habilitamos y deshabilitamos los botones correspondientes
			btnAceptar.setEnabled(true);
			btnCancelar.setEnabled(true);
			btnNuevo.setEnabled(false);
			// El texto del NIF el único que no se puede editar, ya que queremos actualizar
			// el cliente
			txtNIF.setEditable(false);
			txtNombre.setEditable(true);
			txtApellidos.setEditable(true);
			txtFacturacion.setEditable(true);
			txtEmpleados.setEditable(true);
			checkBoxUE.setEnabled(true);
			lstClientes.setEnabled(false);
			btnEliminar.setEnabled(false);

			// Si se acepta
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Preguntamos si se desea continuar
					int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres actualizar este cliente?",
							"Confirmar actualización", JOptionPane.YES_NO_OPTION);
					if (opcion == JOptionPane.YES_OPTION) {
						try {
							// Si se confirma completamente
							// Creamos un nuevo cliente actualizado
							// Obtenemos todos los datos de los campos de texto
							String nombre = txtNombre.getText();
							String apellidos = txtApellidos.getText();
							int empleados = Integer.parseInt(txtEmpleados.getText());
							double facturacion = Double.parseDouble(txtFacturacion.getText());
							boolean esNacional = checkBoxUE.isSelected();
							Cliente clienteActualizado = new Cliente(nif, apellidos, nombre, empleados, facturacion, esNacional);
							clientes = new Clientes(proveedor);
							clientes.updateCliente(clienteActualizado);
							updateListaClientes();
						} catch (IllegalArgumentException | NullPointerException | ClientesException
								| ProveedorAlmacenamientoClientesException ex) {
							// Manejamos cualquier excepción ocurrida durante la actualización del cliente
							JOptionPane.showMessageDialog(frame, "Error al actualizar el cliente. Por favor, revise los campos.",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		} catch (NumberFormatException | NullPointerException e) {
			// Manejamos cualquier excepción ocurrida durante la actualización del cliente
			JOptionPane.showMessageDialog(frame, "Error al actualizar el cliente. Por favor, revise los campos.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Metodo para devolver el estado en el que se encuentra la interfaz al iniciar
	// la aplicacion
	private void estadoOriginal() {
		// Limpia los campos de texto
		txtNIF.setText("");
		txtNombre.setText("");
		txtApellidos.setText("");
		txtFacturacion.setText("");
		txtEmpleados.setText("");
		// Habilita los campos de texto
		txtNIF.setEditable(false);
		txtNombre.setEditable(false);
		txtApellidos.setEditable(false);
		txtFacturacion.setEditable(false);
		txtEmpleados.setEditable(false);
		// Desmarca el checkbox
		checkBoxUE.setSelected(false);

		// Restaura el estado de los botones
		btnAceptar.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnNuevo.setEnabled(true);
		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
		lstClientes.clearSelection();
		lstClientes.setEnabled(true);
	}
}