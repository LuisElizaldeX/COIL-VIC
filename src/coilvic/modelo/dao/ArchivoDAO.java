package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.Archivo;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, archivo.getNombre());
                prepararSentencia.setBytes(2, archivo.getArchivoCol());
                prepararSentencia.setInt(3, archivo.getIdTipoArchivo());
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas > 0){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, "Archivo guardado correctamente");
                } else {
                    respuesta.put(Constantes.KEY_MENSAJE, "Lo sentimos, hubo un error al subir el archivo, favor de intentarlo más tarde");
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
