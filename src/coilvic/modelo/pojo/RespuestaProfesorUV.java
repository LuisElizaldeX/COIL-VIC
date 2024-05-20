/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Clase para modelar el objeto RespuestaProfesorUV
*/

package coilvic.modelo.pojo;

public class RespuestaProfesorUV {
    private boolean error;
    private String mensaje;
    private ProfesorUV profesoruv;

    public RespuestaProfesorUV() {
    }

    public RespuestaProfesorUV(boolean error, String mensaje, ProfesorUV profesoruv) {
        this.error = error;
        this.mensaje = mensaje;
        this.profesoruv = profesoruv;
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

    public ProfesorUV getProfesoruv() {
        return profesoruv;
    }

    public void setProfesoruv(ProfesorUV profesoruv) {
        this.profesoruv = profesoruv;
    }
    
}
