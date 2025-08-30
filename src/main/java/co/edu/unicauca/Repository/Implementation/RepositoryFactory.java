/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.Repository.Implementation;


import co.edu.unicauca.Repository.PersonaRepository;

/**
 *
 * @author LEFO
 */
public class RepositoryFactory {
    
    public static PersonaRepository getInstance(String baseDatos)
    {
    
        if(baseDatos.equals("SQLite"))
        {
            return new PersonaRepositorySQLite();
        }
        return null;
    }
}
