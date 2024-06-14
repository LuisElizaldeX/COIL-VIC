/*
* Autor: Josué Melgarejo García
* Fecha de creación: 01/06/2024
* Descripción: Clase del modelo de Estudiante
*/
package coilvic.modelo.pojo;

public class Estudiante {
    private Integer idEstudiante;
    private String nombre;
    private String matricula;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreCompleto;

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiante, String nombre, String matricula, String apellidoPaterno, String apellidoMaterno, String nombreCompleto) {
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.matricula = matricula;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    
}
