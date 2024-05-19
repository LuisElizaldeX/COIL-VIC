/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 17/05/2024
* Descripción: Clase encargada de modelar la clase CoordinadorCOIL
*/

package coilvic.modelo.pojo;

public class CoordinadorCOIL {
    private int idCoordinadorCOIL;
    private int idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public CoordinadorCOIL() {
    }

    public CoordinadorCOIL(int idCoordinadorCOIL, int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idCoordinadorCOIL = idCoordinadorCOIL;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getIdCoordinadorCOIL() {
        return idCoordinadorCOIL;
    }

    public void setIdCoordinadorCOIL(int idCoordinadorCOIL) {
        this.idCoordinadorCOIL = idCoordinadorCOIL;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
}
