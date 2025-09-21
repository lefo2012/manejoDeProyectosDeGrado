package co.edu.unicauca.Builders;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Util.Tipo;

/**
 *
 * @author LEFO
 */
public class InvestigacionBuilder extends Builder{
    
    @Override
    public void setEstudiantes(Estudiante est1,Estudiante est2)
    {
        formato.getEstudiantes().add( est1);
        formato.getEstudiantes().add( est2);
    }
    @Override
    public void setModalidad(Tipo tipo) throws Exception
    {
        if(!tipo.equals(Tipo.Investigacion))
        {
            throw new Exception("Error el builder solo acepta la modalidad de investigacion");
                    
        }
        formato.setTipo(tipo);
    }
    
}
