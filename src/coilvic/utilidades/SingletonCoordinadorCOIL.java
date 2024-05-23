/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Clase para modelar el objeto SingletonCoordinadorCOIL,una clase estatica de la instancia
*/

package coilvic.utilidades;

import coilvic.modelo.pojo.CoordinadorCOIL;

public class SingletonCoordinadorCOIL {
    private static SingletonCoordinadorCOIL instancia;
    private CoordinadorCOIL coordinador;
    
    private SingletonCoordinadorCOIL() {
        
    }
    
    public static SingletonCoordinadorCOIL getInstancia() {
        if (instancia == null) {
            instancia = new SingletonCoordinadorCOIL();
        }
        return instancia;
    }
    
    public CoordinadorCOIL getCoordinadorCOIL() {
        return coordinador;
    }
    
    public void setCoordinadorCOIL(CoordinadorCOIL coordinador) {
        this.coordinador = coordinador;
    }
    
}
