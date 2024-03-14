package prog.unidad05.gestionclientes.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class GestionClientesSwingApp {

  private JFrame frame;
  private JTextField txtNIF;
  private JTextField txtNombre;
  private JTextField txtEmpleados;
  private JTextField txtApellidos;
  private JTextField txtFacturacion;

  /**
   * Launch the application.
   */
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

  /**
   * Create the application.
   */
  public GestionClientesSwingApp() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 580, 550);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JScrollPane scrollPane = new JScrollPane();
    
    JLabel lblClientes = new JLabel("Clientes");
    lblClientes.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    
    JLabel lblNif = new JLabel("NIF:");
    lblNif.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    
    txtNIF = new JTextField();
    txtNIF.setColumns(10);
    
    JLabel lblNombre = new JLabel("Nombre");
    lblNombre.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    
    txtNombre = new JTextField();
    txtNombre.setColumns(10);
    
   
    JLabel lblEmpleados = new JLabel("Empleados");
    lblEmpleados.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    
    txtEmpleados = new JTextField();
    txtEmpleados.setColumns(10);
        
    
    JLabel lblApellidos = new JLabel("Apellidos");
    lblApellidos.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    
    txtApellidos = new JTextField();
    txtApellidos.setColumns(10);
    
    JLabel lblFacturacion = new JLabel("Facturación");
    lblFacturacion.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    
    txtFacturacion = new JTextField();
    txtFacturacion.setColumns(10);
    
    JCheckBox checkBoxUE= new JCheckBox("¿Es nacional de la UE?");
    checkBoxUE.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    
    /**
     * Botones
     */    
    JButton btnNuevo = new JButton("Nuevo");
    btnNuevo.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
    
    JButton btnEliminar = new JButton("Eliminar");
    btnEliminar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
    
    JButton btnActualizar = new JButton("Actualizar");
    btnActualizar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
    
    JButton btnAceptar = new JButton("Aceptar");
    btnAceptar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
    
    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
    
    
    JButton btnSalir = new JButton("Salir");
    btnSalir.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
    GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(10)
          .addComponent(lblClientes, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(8)
          .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE))
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(10)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(lblNif, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
            .addGroup(groupLayout.createSequentialGroup()
              .addGap(30)
              .addComponent(txtNIF, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
          .addGap(10)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
              .addGap(60)
              .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
            .addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
          .addGap(10)
          .addComponent(lblEmpleados, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
          .addGap(20)
          .addComponent(txtEmpleados, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(10)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
              .addGap(65)
              .addComponent(txtApellidos, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
            .addComponent(lblApellidos, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
          .addGap(10)
          .addComponent(lblFacturacion, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
          .addGap(10)
          .addComponent(txtFacturacion, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(10)
          .addComponent(checkBoxUE, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(10)
          .addComponent(btnNuevo, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
          .addGap(85)
          .addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
          .addGap(85)
          .addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(10)
          .addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
          .addGap(85)
          .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
          .addGap(85)
          .addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(11)
          .addComponent(lblClientes, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
          .addGap(4)
          .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
          .addGap(10)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(lblNif, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            .addComponent(txtNIF, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            .addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            .addComponent(lblEmpleados, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            .addComponent(txtEmpleados, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
          .addGap(25)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(txtApellidos, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            .addComponent(lblApellidos, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            .addComponent(lblFacturacion, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            .addComponent(txtFacturacion, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
          .addGap(15)
          .addComponent(checkBoxUE, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
          .addGap(15)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(btnNuevo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
            .addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
            .addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
          .addGap(20)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
            .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
    );
    /**
     * Lista
     */
    JList lstClientes = new JList();
    scrollPane.setViewportView(lstClientes);
    frame.getContentPane().setLayout(groupLayout);
  }
}
