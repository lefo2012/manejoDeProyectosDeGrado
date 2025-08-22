package co.edu.unicauca.Test;
import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Models.Facultad.Facultad;
import co.edu.unicauca.Models.Facultad.Departamento;
import co.edu.unicauca.Repository.Implementation.ProfesorRepositorySQLite;
import java.util.LinkedList;

public class TestInsert {
    public static void main(String[] args) {
        ProfesorRepositorySQLite repo = new ProfesorRepositorySQLite();

        // Simulamos que el profesor pertenece a un departamento y facultades
        Departamento dep = new Departamento("Ingeniería de Sistemas");
        LinkedList<Facultad> facultades = new LinkedList<>();

        Profesor prof = new Profesor(
            dep,
            "Juan", 
            "Pérez", 
            "3001234567", 
            "juan.perez123@unicauca.edu.co", 
            "1234", 
            facultades
        );

        repo.registrar(prof);
        
        var encontrado = repo.buscarPorCorreo("juan.perez123@unicauca.edu.co");
        if (encontrado != null) {
            System.out.println("Profesor encontrado: " + encontrado.getNombre() + " " + encontrado.getApellido());
        } else {
            System.out.println("No se encontró el profesor en la BD");
        }
        
    }
}
