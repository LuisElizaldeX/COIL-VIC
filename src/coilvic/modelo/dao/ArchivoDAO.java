package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.Archivo;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ArchivoDAO {
        
    public static HashMap<String, Object> subirArchivo(Archivo archivo){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String consulta = "INSERT INTO archivo (nombre, archivoCol, idTipoArchivo) "
                        + "VALUES (?,?,?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta, 
                        Statement.RETURN_GENERATED_KEYS);
                prepararSentencia.setString(1, archivo.getNombre());
                prepararSentencia.setBytes(2, archivo.getArchivoCol());
                prepararSentencia.setInt(3, archivo.getIdTipoArchivo());
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas > 0){
                    ResultSet rs = prepararSentencia.getGeneratedKeys();
                    if (rs.next()){
                        int idArchivo = rs.getInt(1);
                        respuesta.put("idArchivo", idArchivo);
                    }
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, "Archivo guardado correctamente");
                } else {
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "Lo sentimos, hubo un error al subir el archivo, favor de intentarlo más tarde");
                }
                conexionBD.close();
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
   
    public static HashMap<String, Object> obtenerArchivo(int idArchivo){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String consulta = "SELECT nombre, archivoCol, idTipoArchivo FROM archivo "
                        + "WHERE idArchivo = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idArchivo);
                ResultSet resultado = prepararSentencia.executeQuery();
                Archivo archivo = new Archivo();
                if (resultado.next()) { 
                    archivo.setIdArchivo(idArchivo);
                    archivo.setNombre(resultado.getString("nombre"));
                    archivo.setArchivoCol(resultado.getBytes("archivoCol"));
                    archivo.setIdTipoArchivo(resultado.getInt("idTipoArchivo"));
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("archivo", archivo);
                conexionBD.close();
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
    public static HashMap<String, Object> registrarArchivoEnColaboracion(int idColaboracion, int idArchivo){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try {
                String consulta = "UPDATE colaboracion SET idArchivo = ? WHERE idColaboracion = ?;";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idArchivo);
                prepararSentencia.setInt(2, idColaboracion);
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas > 0){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "Archivo registrado correctamente en la colaboración");
                } else {
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "Lo sentimos, hubo un error al registrar el archivo en la "
                                    + "colaboración, favor de intentarlo más tarde");
                }
                conexionBD.close();
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
}
