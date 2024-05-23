/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo estado de oferta de colaboracion UV
 */

package coilvic.modelo.pojo;

public class EstadoOfertaColaboracionUV {
    private int estadoOfertaColaboracionUV;
    private String estado;

    public EstadoOfertaColaboracionUV() {
    }

    public EstadoOfertaColaboracionUV(int estadoOfertaColaboracionUV, String nombre) {
        this.estadoOfertaColaboracionUV = estadoOfertaColaboracionUV;
        this.estado = nombre;
    }

    public int getEstadoOfertaColaboracionUV() {
        return estadoOfertaColaboracionUV;
    }

    public void setEstadoOfertaColaboracionUV(int estadoOfertaColaboracionUV) {
        this.estadoOfertaColaboracionUV = estadoOfertaColaboracionUV;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
