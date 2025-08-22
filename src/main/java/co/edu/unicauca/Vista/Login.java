package co.edu.unicauca.Vista;

import co.edu.unicauca.Models.Persona;
import co.edu.unicauca.Services.PersonaService;
import co.edu.unicauca.Services.PersonaServiceFactory;
import co.edu.unicauca.dto.PersonaDTO;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private JLabel lblLogo;
    private JLabel lblTitulo;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private JLabel lblOlvido;
    private JPanel panelPrincipal;

    public Login() {
        initComponents();
    }

    private void initComponents() {
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(0, 0, 102));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        lblLogo = new JLabel();
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon logoOriginal = new ImageIcon("src/resources/Logo.png");
        Image logoEscalado = logoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(logoEscalado));

        lblTitulo = new JLabel("INICIAR SESIÓN");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 30, 0));

        txtCorreo = new JTextField("Correo electronico");
        txtCorreo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtCorreo.setBackground(new Color(0, 0, 80));
        txtCorreo.setForeground(Color.WHITE);
        txtCorreo.setCaretColor(Color.WHITE);
        txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCorreo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 50, 150), 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        txtContrasena = new JPasswordField("Contraseña");
        txtContrasena.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtContrasena.setBackground(new Color(0, 0, 80));
        txtContrasena.setForeground(Color.WHITE);
        txtContrasena.setCaretColor(Color.WHITE);
        txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 50, 150), 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        lblOlvido = new JLabel("Olvidé mi contraseña");
        lblOlvido.setForeground(Color.RED);
        lblOlvido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblOlvido.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblOlvido.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JCheckBox chkProfesor = new JCheckBox("¿Es profesor?");
        chkProfesor.setBackground(new Color(0, 0, 153));
        chkProfesor.setForeground(Color.WHITE);
        chkProfesor.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnIngresar = new JButton("INGRESAR");
        btnIngresar.setBackground(new Color(70, 90, 200));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIngresar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnIngresar.setMaximumSize(new Dimension(200, 45));

        panelPrincipal.add(lblLogo);
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(txtCorreo);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(txtContrasena);
        panelPrincipal.add(lblOlvido);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(btnIngresar);
        
        add(panelPrincipal);
        setTitle("Inicio de Sesión");
        setSize(400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        btnIngresar.addActionListener(e -> {
        try {
            
            PersonaDTO persona = new PersonaDTO();
            persona.setCorreoElectronico(txtCorreo.getText());
            persona.setContrasenia(new String(txtContrasena.getPassword()));
            persona.setEsProfesor(chkProfesor.isSelected());
            System.out.println(persona.getCorreoElectronico());
            System.out.println(persona.getContrasenia());
            
            PersonaService service = PersonaServiceFactory.getService(persona);
            System.out.println(service.getClass());
            
            String resultado = service.iniciarSesion(persona.getCorreoElectronico(),persona.getContrasenia());

            JOptionPane.showMessageDialog(this,resultado );
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en el registro: " + ex.getMessage(), 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
