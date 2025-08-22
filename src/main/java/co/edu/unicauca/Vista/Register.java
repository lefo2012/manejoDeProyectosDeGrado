package co.edu.unicauca.Vista;

import co.edu.unicauca.Services.PersonaService;
import co.edu.unicauca.Services.PersonaServiceFactory;
import co.edu.unicauca.dto.PersonaDTO;
import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {

    private JLabel lblLogo;
    private JLabel lblTitulo;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JButton btnRegistrar;
    private JCheckBox chkProfesor;

    public Register() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0, 0, 153));
        setSize(400, 500);
        setLocationRelativeTo(null);

        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setBackground(new Color(0, 0, 153));
        panelMain.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        lblLogo = new JLabel();
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/logo.png"));
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(img));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblTitulo = new JLabel("REGISTRAR USUARIO");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Serif", Font.PLAIN, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelMain.add(lblLogo);
        panelMain.add(Box.createVerticalStrut(10));
        panelMain.add(lblTitulo);
        panelMain.add(Box.createVerticalStrut(20));

        txtCorreo = crearCampo("CORREO ELECTRONICO");
        txtContrasena = new JPasswordField();
        estilizarCampo(txtContrasena, "CONTRASEÑA");
        txtNombre = crearCampo("NOMBRE");
        txtApellido = crearCampo("APELLIDO");
        txtTelefono = crearCampo("TELEFONO");

        panelMain.add(txtCorreo);
        panelMain.add(Box.createVerticalStrut(15));
        panelMain.add(txtContrasena);
        panelMain.add(Box.createVerticalStrut(15));
        panelMain.add(txtNombre);
        panelMain.add(Box.createVerticalStrut(15));
        panelMain.add(txtApellido);
        panelMain.add(Box.createVerticalStrut(15));
        panelMain.add(txtTelefono);
        panelMain.add(Box.createVerticalStrut(25));
        
        chkProfesor = new JCheckBox("¿Es profesor?");
        chkProfesor.setBackground(new Color(0, 0, 153));
        chkProfesor.setForeground(Color.WHITE);
        chkProfesor.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelMain.add(Box.createVerticalStrut(15));
        panelMain.add(chkProfesor);

        btnRegistrar = new JButton("REGISTRARSE");
        btnRegistrar.setBackground(new Color(0, 51, 204));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelMain.add(btnRegistrar);

        add(panelMain, BorderLayout.CENTER);
        btnRegistrar.addActionListener(e -> {
        try {
            // Construir el DTO con los datos del formulario
            PersonaDTO persona = new PersonaDTO();
            persona.setNombre(txtNombre.getText());
            persona.setApellido(txtApellido.getText());
            persona.setCorreoElectronico(txtCorreo.getText());
            persona.setContrasenia(new String(txtContrasena.getPassword()));
            persona.setTelefono(txtTelefono.getText());
            persona.setEsProfesor(chkProfesor.isSelected());
            System.out.println(persona.getCorreoElectronico());
            System.out.println(persona.getContrasenia());
            // Obtener el servicio correcto según si es profesor o estudiante
            
            PersonaService service = PersonaServiceFactory.getService(persona);
            System.out.println(service.getClass());

            // Llamar al servicio
            String resultado = service.registrar(persona);

            // Mostrar mensaje al usuario
            JOptionPane.showMessageDialog(this, resultado);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en el registro: " + ex.getMessage(), 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
        });
    }

    private JTextField crearCampo(String placeholder) {
        JTextField campo = new JTextField(15);
        estilizarCampo(campo, placeholder);
        return campo;
    }

    private void estilizarCampo(JTextField campo, String placeholder) {
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        campo.setBackground(new Color(0, 0, 153));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setHorizontalAlignment(JTextField.CENTER);
        campo.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 204), 2, true));
        campo.setText(placeholder);

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (campo.getText().isEmpty()) {
                    campo.setText(placeholder);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Register().setVisible(true));
    }
}
