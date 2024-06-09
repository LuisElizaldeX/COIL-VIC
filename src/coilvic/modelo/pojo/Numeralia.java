/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 07/06/2024
 * Descripción: Clase del modelo de Numeralia
 */

package coilvic.modelo.pojo;

public class Numeralia {
    private String region;
    private String areaAcademica;
    private int alumnos;
    private int profesores;

    public Numeralia() {
    }

    public Numeralia(String region, String areaAcademica, int alumnos, int profesores) {
        this.region = region;
        this.areaAcademica = areaAcademica;
        this.alumnos = alumnos;
        this.profesores = profesores;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAreaAcademica() {
        return areaAcademica;
    }

    public void setAreaAcademica(String areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public int getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(int alumnos) {
        this.alumnos = alumnos;
    }

    public int getProfesores() {
        return profesores;
    }

    public void setProfesores(int profesores) {
        this.profesores = profesores;
    }
    
}
