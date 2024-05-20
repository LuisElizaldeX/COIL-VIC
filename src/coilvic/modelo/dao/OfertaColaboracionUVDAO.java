/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Clase DAO para manejar el acceso, guardado, modificacion y 
  eliminacion de los datos de la clase OfertaColaboracionUV
*/

package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.OfertaColaboracionUV;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class OfertaColaboracionUVDAO {
    
    public static HashMap<String, Object> obtenerOfertasColaboracionUV() {
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT ocuv.idOfertaColaboracionUV, ocuv.descripcion, ocuv.nombre AS ofertaNombre, " 
                + "ee.nombre AS experienciaEducativaNombre, "
                + "CONCAT(puv.nombre, ' ', puv.apellidoPaterno, ' ', puv.apellidoMaterno) "
                + "AS profesorNombreCompleto, eocuv.estado AS estadoOferta, ocuv.periodo " 
                + "FROM ofertaColaboracionUV ocuv "
                + "JOIN experienciaEducativa ee ON ocuv.idExperienciaEducativa = ee.idExperienciaEducativa " 
                + "JOIN profesoruv puv ON ocuv.profesoruv_idProfesoruv = puv.idProfesoruv "
                + "JOIN estadoOfertaColaboracionUV eocuv "
                + "ON ocuv.idEstadoOfertaColaboracionUV = eocuv.idEstadoOfertaColaboracionUV "
                + "WHERE ocuv.idEstadoOfertaColaboracionUV = 1";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<OfertaColaboracionUV> ofertaColaboracionUV = new ArrayList();
                while (resultado.next()) {
                    OfertaColaboracionUV oferta = new OfertaColaboracionUV();
                    oferta.setIdOfertaColaboracionUV(resultado.getInt("idOfertaColaboracionUV"));
                    oferta.setNombre(resultado.getString("ofertaNombre"));
                    oferta.setDescripcion(resultado.getString("descripcion"));
                    oferta.setExperienciaEducativa(resultado.getString("experienciaEducativaNombre"));
                    oferta.setPeriodo(resultado.getString("periodo"));
                    oferta.setEstado(resultado.getString("estadoOferta"));
                    //oferta.setIdEstadoOfertaColaboracionUV(resultado.getString("ofertaNombre"));
                    oferta.setProfesorUV(resultado.getString("profesorNombreCompleto"));
                    ofertaColaboracionUV.add(oferta);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("OfertasColaboracionUV", ofertaColaboracionUV);
                conexionBD.close();
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
    public static HashMap<String, Object> aprobarColaboracion(Integer idOfertaColaboracionUV){
         HashMap<String, Object> respuesta = new LinkedHashMap<>();
         respuesta.put(Constantes.KEY_ERROR, true);
         Connection conexionBD = ConexionBD.obtenerConexion();
         if (conexionBD != null){
             try{
                String sentencia = "UPDATE ofertacolaboracionuv SET "
                        + "idEstadoOfertaColaboracionUV = 3 "
                        + "WHERE idOfertaColaboracionUV = ?"; 
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idOfertaColaboracionUV);
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas > 0){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, 
                          "Oferta colaboracion UV aprobada correctamente");
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, 
                          "Lo sentimos, hubo un error al aprobar la oferta de "
                                  + "colaboracion UV. Intentelo más tarde");
                }
                conexionBD.close();
             }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
             }
         }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
    public static HashMap<String, Object> rechazarColaboracion(Integer idOfertaColaboracionUV){
         HashMap<String, Object> respuesta = new LinkedHashMap<>();
         respuesta.put(Constantes.KEY_ERROR, true);
         Connection conexionBD = ConexionBD.obtenerConexion();
         if (conexionBD != null){
             try{
                String sentencia = "UPDATE ofertacolaboracionuv SET "
                        + "idEstadoOfertaColaboracionUV = 2 "
                        + "WHERE idOfertaColaboracionUV = ?"; 
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idOfertaColaboracionUV);
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas > 0){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, 
                          "Oferta colaboracion UV rechazada correctamente");
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, 
                          "Lo sentimos, hubo un error al rechazar la oferta de "
                                  + "colaboracion UV. Intentelo más tarde");
                }
                conexionBD.close();
             }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
             }
         }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
}
