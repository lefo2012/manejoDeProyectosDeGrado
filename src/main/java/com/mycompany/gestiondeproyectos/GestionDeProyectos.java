package com.mycompany.gestiondeproyectos;

import co.edu.unicauca.database.InitDB;
import co.edu.unicauca.Vista.Login;
import co.edu.unicauca.Vista.Register;


public class GestionDeProyectos {

    public static void main(String[] args) {
        System.out.println("Iniciando aplicación...");
        InitDB.crearTablas();

        // Mostrar interfaz gráfica de Login
        java.awt.EventQueue.invokeLater(() -> {
            //new Login().setVisible(true);
            new Login().setVisible(true);
            
    
        });
    }
}