/*
 * Autor: Luis Angel Elizalde Arroyo
 * Fecha de creación: 17/05/2024
 * Descripción: Clase del modelo ProfesorUV
 */

package coilvic.modelo.pojo;

public class ProfesorUV {
    private int idProfesorUV;
    private int idUsuario;
    private int numeroPersonal;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;

    public ProfesorUV() {
    }

    public ProfesorUV(int idProfesorUV, int idUsuario, String telefono, int numeroPersonal, 
            String nombre, String apellidos, String correo) {
        this.idProfesorUV = idProfesorUV;
        this.idUsuario = idUsuario;
        this.telefono = telefono;
        this.numeroPersonal = numeroPersonal;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    public int getIdProfesorUV() {
        return idProfesorUV;
    }

    public void setIdProfesorUV(int idProfesorUV) {
        this.idProfesorUV = idProfesorUV;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNumeroPersonal() {
        return numeroPersonal;
    }

    public void setNumeroPersonal(int numeroPersonal) {
        this.numeroPersonal = numeroPersonal;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return  nombre + " " + apellidos;
    }
    
}
