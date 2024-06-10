/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 17/05/2024
* Descripción: Clase DAO para manejar el acceso, guardado, modificacion y 
  eliminacion de los datos de la clase CoordinadorCOIL
*/

package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.CoordinadorCOIL;
import coilvic.modelo.pojo.RespuestaCoordinadorCOIL;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoordinadorCOILDAO {
    
   public static RespuestaCoordinadorCOIL obtenerInformacionCoordinadorCOIL(int idUsuario){
        RespuestaCoordinadorCOIL respuesta = new RespuestaCoordinadorCOIL();
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idCoordinadorCOIL, nombre, apellidoPaterno, "
                        + "apellidoMaterno, idUsuario "
                        + "FROM coordinadorcoil "
                        + "WHERE idUsuario = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idUsuario);
                ResultSet resultado = prepararSentencia.executeQuery();
                if(resultado.next()){
                    CoordinadorCOIL coordinador = new CoordinadorCOIL();
                    coordinador.setIdCoordinadorCOIL
        (resultado.getInt("idCoordinadorCOIL"));
                    coordinador.setNombre(resultado.getString("nombre"));
                    coordinador.setApellidoPaterno
        (resultado.getString("apellidoPaterno"));
                    coordinador.setApellidoMaterno
        (resultado.getString("apellidoMaterno"));
                    coordinador.setIdUsuario(resultado.getInt("idUsuario"));
                    respuesta.setCoordinador(coordinador);
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Informacion incorrecta");
                }
                conexionBD.close();
            }catch(SQLException e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        }else {
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
   }
    
}
