
package co.edu.unicauca.Models;

import co.edu.unicauca.Util.Tipo;

/**
 *
 * @author LEFO
 */
public class Proyecto {
    private String idProyecto;
    private String titulo;
    private String objetivo;
    private String objetivoEspecifico;
    private String estado;
    private Tipo tipo;
    private String fechaDeSubida;    
    private String fechaRevision;    
    private String archivoAdjunto;  



    public Proyecto(String idProyecto, String titulo, String objetivo, String objetivoEspecifico,
                    String estado, Tipo tipo,
                    String fechaDeSubida, String fechaRevision, String archivoAdjunto) {
        this.idProyecto = idProyecto;
        this.titulo = titulo;
        this.objetivo = objetivo;
        this.objetivoEspecifico = objetivoEspecifico;
        this.estado = estado;
        this.tipo = tipo;
        this.fechaDeSubida = fechaDeSubida;
        this.fechaRevision = fechaRevision;
        this.archivoAdjunto = archivoAdjunto;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getObjetivoEspecifico() {
        return objetivoEspecifico;
    }

    public void setObjetivoEspecifico(String objetivoEspecifico) {
        this.objetivoEspecifico = objetivoEspecifico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getFechaDeSubida() {
        return fechaDeSubida;
    }

    public void setFechaDeSubida(String fechaDeSubida) {
        this.fechaDeSubida = fechaDeSubida;
    }

    public String getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(String fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(String archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }
    
}
