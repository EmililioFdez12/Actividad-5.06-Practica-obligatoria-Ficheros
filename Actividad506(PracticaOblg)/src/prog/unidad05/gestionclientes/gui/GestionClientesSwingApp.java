package prog.unidad05.gestionclientes.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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

		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		JLabel lblNif = new JLabel("NIF:");
		lblNif.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		txtNIF = new JTextField();
		txtNIF.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		JLabel lblEmpleados = new JLabel("Empleados");
		lblEmpleados.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		txtEmpleados = new JTextField();
		txtEmpleados.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);

		JLabel lblFacturacion = new JLabel("Facturación");
		lblFacturacion.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		txtFacturacion = new JTextField();
		txtFacturacion.setColumns(10);

		checkBoxUE = new JCheckBox("¿Es nacional de la UE?");
		checkBoxUE.setFont(new Font("Bebas Neue", Font.PLAIN, 18));

		/**
		 * Boton Añadir Cliente
		 */
		// Botón para añadir un nuevo cliente
		btnNuevo = new JButton("Nuevo");
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
		btnAceptar.setFont(new Font("Bebas Neue", Font.PLAIN, 20));

		/**
		 * Boton cancelar
		 */
		btnCancelar = new JButton("Cancelar");
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

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(lblClientes,
										GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(8).addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(10)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGap(24).addComponent(txtNIF,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addComponent(lblNif, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
										.addGap(4)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGap(56).addComponent(txtNombre,
														GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
												.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
										.addGap(20).addComponent(lblEmpleados, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
										.addGap(16).addComponent(txtEmpleados, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(10)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGap(65).addComponent(txtApellidos,
														GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
												.addComponent(lblApellidos, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
										.addGap(18).addComponent(lblFacturacion, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(txtFacturacion, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(checkBoxUE,
										GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(10)
										.addComponent(btnNuevo, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(85)
										.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(85)
										.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(10)
										.addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(85)
										.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(85)
										.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(11)
										.addComponent(lblClientes, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE).addGap(4)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE).addGap(10)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtNIF, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNif, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(txtNombre,
														GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
												.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblEmpleados, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtEmpleados, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
										.addGap(23)
										.addGroup(
												groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup().addGap(1)
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																		.addComponent(txtApellidos, GroupLayout.PREFERRED_SIZE, 25,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(lblApellidos, GroupLayout.PREFERRED_SIZE, 25,
																				GroupLayout.PREFERRED_SIZE)))
														.addComponent(lblFacturacion, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
														.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(txtFacturacion,
																GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
										.addGap(15).addComponent(checkBoxUE, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addGap(15)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnNuevo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
										.addGap(20)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))));
		frame.getContentPane().setLayout(groupLayout);

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