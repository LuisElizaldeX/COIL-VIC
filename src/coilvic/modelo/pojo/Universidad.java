/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo universidad
 */

package coilvic.modelo.pojo;

public class Universidad {
    private int idUniversidad;
    private String nombre;

    public Universidad() {
    }

    public Universidad(int idUniversidad, String nombre) {
        this.idUniversidad = idUniversidad;
        this.nombre = nombre;
    }

    public int getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
