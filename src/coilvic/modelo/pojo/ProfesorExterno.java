/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo Profesor externo
 */

package coilvic.modelo.pojo;

public class ProfesorExterno {
    private int idProfesorExterno;
    private int idUniversidad;
    private String nombre;
    private String apellidos;
    private String pais;
    private String carrera;
    private String correo;
    private String materia;
    private String telefono;

    public ProfesorExterno() {
    }

    public ProfesorExterno(int idProfesorExterno, int idUniversidad, String nombre, String apellidos, String pais, 
            String carrera, String correo, String materia, String telefono) {
        this.idProfesorExterno = idProfesorExterno;
        this.idUniversidad = idUniversidad;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pais = pais;
        this.carrera = carrera;
        this.correo = correo;
        this.materia = materia;
        this.telefono = telefono;
    }

    public int getIdProfesorExterno() {
        return idProfesorExterno;
    }

    public void setIdProfesorExterno(int idProfesorExterno) {
        this.idProfesorExterno = idProfesorExterno;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
