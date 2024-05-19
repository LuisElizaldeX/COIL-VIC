/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 17/05/2024
* Descripción: Clase encargada de modelar un usuario global del sistema
*/

package coilvic.modelo.pojo;

public class Usuario {
    private int idUsuario;
    private int idTipoUsuario;
    private String nombre;
    private String contrasena;

    public Usuario() {
        
    }

    public Usuario(int idUsuario, int idTipoUsuario, String nombre, String contrasena) {
        this.idUsuario = idUsuario;
        this.idTipoUsuario = idTipoUsuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
}
