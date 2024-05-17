/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo estado de oferta
 */

package coilvic.modelo.pojo;

public class EstadoOferta {
    private int estadoOferta;
    private String nombre;

    public EstadoOferta() {
    }

    public EstadoOferta(int estadoOferta, String nombre) {
        this.estadoOferta = estadoOferta;
        this.nombre = nombre;
    }

    public int getEstadoOferta() {
        return estadoOferta;
    }

    public void setEstadoOferta(int estadoOferta) {
        this.estadoOferta = estadoOferta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
