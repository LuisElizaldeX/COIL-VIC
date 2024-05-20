/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 18/05/2024
 * Descripción: Clase del modelo de area estado constancia
 */

package coilvic.modelo.pojo;

public class EstadoConstancia {
    private int idEstadoConstancia;
    private String estado;

    public EstadoConstancia() {
    }

    public EstadoConstancia(int idEstadoConstancia, String estado) {
        this.idEstadoConstancia = idEstadoConstancia;
        this.estado = estado;
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
    
}
