/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 18/05/2024
 * Descripción: Clase del modelo estado de oferta colaboracion externa
 */

package coilvic.modelo.pojo;

public class EstadoOfertaColaboracionExterna {
    private int idEstadoOfertaColaboracionExterna;
    private String estado;

    public EstadoOfertaColaboracionExterna() {
    }

    public EstadoOfertaColaboracionExterna(int idEstadoOfertaColaboracionExterna, String estado) {
        this.idEstadoOfertaColaboracionExterna = idEstadoOfertaColaboracionExterna;
        this.estado = estado;
    }

    public int getIdEstadoOfertaColaboracionExterna() {
        return idEstadoOfertaColaboracionExterna;
    }

    public void setIdEstadoOfertaColaboracionExterna(int idEstadoOfertaColaboracionExterna) {
        this.idEstadoOfertaColaboracionExterna = idEstadoOfertaColaboracionExterna;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
