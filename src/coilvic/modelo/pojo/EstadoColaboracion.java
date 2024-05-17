/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo estado de colaboracion
 */

package coilvic.modelo.pojo;

public class EstadoColaboracion {
    private int idEstadoColaboracion;
    private String estado;

    public EstadoColaboracion() {
    }

    public EstadoColaboracion(int idEstadoColaboracion, String estado) {
        this.idEstadoColaboracion = idEstadoColaboracion;
        this.estado = estado;
    }

    public int getIdEstadoColaboracion() {
        return idEstadoColaboracion;
    }

    public void setIdEstadoColaboracion(int idEstadoColaboracion) {
        this.idEstadoColaboracion = idEstadoColaboracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
