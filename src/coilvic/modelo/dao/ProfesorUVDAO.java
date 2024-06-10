/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Clase DAO para manejar el acceso, guardado, modificacion y 
  eliminacion de los datos de la clase ProfesorUV
*/

package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.ProfesorUV;
import coilvic.modelo.pojo.RespuestaProfesorUV;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorUVDAO {
    
    public static RespuestaProfesorUV obtenerInformacionProfesorUV(int idUsuario){
        RespuestaProfesorUV respuesta = new RespuestaProfesorUV();
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idProfesoruv, nombre, correo, numeroPersonal, telefono, "
                        + "apellidos, idUsuario "
                        + "FROM profesoruv "
                        + "WHERE idUsuario = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idUsuario);
                ResultSet resultado = prepararSentencia.executeQuery();
                if(resultado.next()){
                    ProfesorUV profesor = new ProfesorUV();
                    profesor.setIdProfesorUV(resultado.getInt("idProfesoruv"));
                    profesor.setNombre(resultado.getString("nombre"));
                    profesor.setCorreo(resultado.getString("correo"));
                    profesor.setNumeroPersonal
        (resultado.getInt("numeroPersonal"));
                    profesor.setTelefono(resultado.getString("telefono"));
                    profesor.setApellidos(resultado.getString("apellidos"));
                    profesor.setIdUsuario(resultado.getInt("idUsuario"));
                    respuesta.setProfesoruv(profesor);
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
