/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo de solicitud de constancia
 */

package coilvic.modelo.pojo;

public class SolicitudConstancia {
    private int idSolicitudConstancia;
    private int idColaboracion;
    private int idEstadoConstancia;
    private String estado;
    private String fechaSolcitud;
    private String colaboracion;

    public SolicitudConstancia() {
    }

    public SolicitudConstancia(int idSolicitudConstancia, int idColaboracion, 
            int idEstadoConstancia, String estado, String fechaSolcitud, String colaboracion) {
        this.idSolicitudConstancia = idSolicitudConstancia;
        this.idColaboracion = idColaboracion;
        this.idEstadoConstancia = idEstadoConstancia;
        this.estado = estado;
        this.fechaSolcitud = fechaSolcitud;
        this.colaboracion = colaboracion;
    }

    public int getIdSolicitudConstancia() {
        return idSolicitudConstancia;
    }

    public void setIdSolicitudConstancia(int idSolicitudConstancia) {
        this.idSolicitudConstancia = idSolicitudConstancia;
    }

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setIdColaboracion(int idColaboracion) {
        this.idColaboracion = idColaboracion;
    }

    public int getIdEstadoConstancia() {
        return idEstadoConstancia;
    }

    public void setIdEstadoConstancia(int idEstadoConstancia) {
        this.idEstadoConstancia = idEstadoConstancia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaSolcitud() {
        return fechaSolcitud;
    }

    public void setFechaSolcitud(String fechaSolcitud) {
        this.fechaSolcitud = fechaSolcitud;
    }

    public String getColaboracion() {
        return colaboracion;
    }

    public void setColaboracion(String colaboracion) {
        this.colaboracion = colaboracion;
    }
    
}