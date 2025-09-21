package co.edu.unicauca.Builders;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Util.Tipo;

/**
 *
 * @author LEFO
 */
public class PracticaBuilder extends Builder{
    @Override
    public void setEstudiantes(Estudiante est1,Estudiante est2) throws Exception
    {
        formato.getEstudiantes().add( est1);
        if(est2!=null)
        {
        
            throw new Exception("Error el proyecto en la modalidad de practica profesional solo acepta un estudiante");
        
        }
        
    }
    @Override
    public void setModalidad(Tipo tipo) throws Exception
    {
        if(!tipo.equals(Tipo.PracticaProfesional))
        {
            throw new Exception("Error el builder solo acepta la modalidad de practica profesional");
                    
        }
        formato.setTipo(tipo);
    }
}
