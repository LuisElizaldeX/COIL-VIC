/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo experiencia educativa
 */

package coilvic.modelo.pojo;

public class ExperienciaEducativa {
    private int idExperienciaEducativa;
    private int idDependencia;
    private String nombre;
    private String descripcion;
    private int creditos;

    public ExperienciaEducativa() {
    }

    public ExperienciaEducativa(int idExperienciaEducativa, int idDependencia, int creditos, String nombre, String descripcion) {
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idDependencia = idDependencia;
        this.creditos = creditos;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }
    
    public int getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(int idDependencia) {
        this.idDependencia = idDependencia;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
