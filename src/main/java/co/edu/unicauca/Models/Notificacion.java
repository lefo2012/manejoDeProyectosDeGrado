
package co.edu.unicauca.Models;

public class Notificacion {
    private int idNotificacion;   
    private String mensaje;       
    private String fecha;         
    private int leido;        
    private int idPersona;        


    public Notificacion() {}


    public Notificacion(String mensaje, String fecha, int leido, int idPersona) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.leido = leido;
        this.idPersona = idPersona;
    }

    public Notificacion(int idNotificacion, String mensaje, String fecha, int leido, int idPersona) {
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.leido = leido;
        this.idPersona = idPersona;
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int isLeido() {
        return leido;
    }

    public void setLeido(int leido) {
        this.leido = leido;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
}
