/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 17/05/2024
* Descripción: Clase para obtener la conexión con la base de datos COILVIC
*/

package coilvic.modelo;

import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    public static final String URI_CONEXION = "jdbc:mysql://"
            +Constantes.HOSTNAME+":"
            +Constantes.PUERTO+"/"
            +Constantes.NOMBRE_BD+
            "?allowPublicKeyRetrieval=true&useSSL=false";
    
   public static Connection obtenerConexion(){
        Connection conexion = null;
          try{
              Class.forName(Constantes.DRIVER);
              conexion =(Connection) DriverManager.getConnection(URI_CONEXION, 
                      Constantes.USUARIO, 
                      Constantes.PASSWORD);
          }catch(ClassNotFoundException | SQLException ex){
              System.err.println("Error de conexion con BD: "+ex.getMessage());
              ex.printStackTrace();
          }
        return conexion;
    }
}
