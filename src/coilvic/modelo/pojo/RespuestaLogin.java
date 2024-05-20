/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 17/05/2024
* Descripción: Clase encargada de modelar la respuesta login
*/

package coilvic.modelo.pojo;

public class RespuestaLogin {
    private boolean error;
    private String mensaje;
    private Usuario usuario;

    public RespuestaLogin() {
    }

    public RespuestaLogin(boolean error, String mensaje, Usuario usuario) {
        this.error = error;
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
