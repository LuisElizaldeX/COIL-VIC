/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Clase para modelar el objeto RespuestaCoordinadorCOIL
*/

package coilvic.modelo.pojo;

public class RespuestaCoordinadorCOIL {
    private boolean error;
    private String mensaje;
    private CoordinadorCOIL coordinador;

    public RespuestaCoordinadorCOIL() {
    }

    public RespuestaCoordinadorCOIL(boolean error, String mensaje, CoordinadorCOIL coordinador) {
        this.error = error;
        this.mensaje = mensaje;
        this.coordinador = coordinador;
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

    public CoordinadorCOIL getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(CoordinadorCOIL coordinador) {
        this.coordinador = coordinador;
    }
    
}
