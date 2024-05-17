/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo experiencia educativa
 */

package coilvic.modelo.pojo;

public class ExperienciaEducatica {
    private int idExperienciaEducativa;
    private int idProgramaEducativo;
    private int creditos;
    private String nombre;
    private String descripcion;

    public ExperienciaEducatica() {
    }

    public ExperienciaEducatica(int idExperienciaEducativa, int idProgramaEducativo ,int creditos, String nombre, String descripcion) {
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idProgramaEducativo = idProgramaEducativo;
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

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
    }

}
