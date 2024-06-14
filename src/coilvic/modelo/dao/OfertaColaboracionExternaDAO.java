/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 02/06/2024
* Descripción: Clase OfertaColaboracionExternaDAO para modificar, 
* crear y eliminar su informacion
*/

package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.EstadoOfertaColaboracionExterna;
import coilvic.modelo.pojo.OfertaColaboracionExterna;
import coilvic.modelo.pojo.ProfesorExterno;
import coilvic.modelo.pojo.Universidad;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


public class OfertaColaboracionExternaDAO {
    
    public static HashMap<String, Object> obtenerOfertaColaboracionExterna(){
         HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        
        if(conexionBD != null){
            try{
                String consulta = "SELECT o.idOfertaColaboracionExterna, "
                        + "o.nombre AS nombreOferta, o.periodo, o.descripcion, " +
                        "e.estado, p.nombre AS nombreProfesorExterno, p.idProfesorexterno," +
                        "p.apellidos, p.correo, p.pais, p.telefono, p.materia, p.carrera, "
                        + "u.nombre AS nombreUniversidad " +
                        "FROM ofertacolaboracionexterna o " +
                        "JOIN estadoofertacolaboracionexterna e "
                        + "ON o.idEstadoOferta = e.idEstadoOferta " +
                        "JOIN profesorexterno p ON o.idProfesorExterno = p.idProfesorExterno " +
                        "JOIN universidad u ON p.idUniversidad = u.idUniversidad "
                        + "WHERE o.idEstadoOferta = 1";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<OfertaColaboracionExterna> ofertasExternas = new ArrayList();
                
                while(resultado.next()){
                    OfertaColaboracionExterna ofertaExterna = new OfertaColaboracionExterna();
                            
                    ofertaExterna.setIdOfertaColaboracionExterna
        (resultado.getInt("idOfertaColaboracionExterna"));
                    ofertaExterna.setIdProfesorExterno
        (resultado.getInt("idProfesorexterno"));
                    ofertaExterna.setNombre(resultado.getString("nombreOferta"));
                    ofertaExterna.setPeriodo(resultado.getString("periodo"));
                    ofertaExterna.setDescripcion
        (resultado.getString("descripcion"));
                    ofertaExterna.setEstado(resultado.getString("estado"));
                    ofertaExterna.setNombreProfesorExterno
        (resultado.getString("nombreProfesorExterno"));
                    ofertaExterna.setApellidos(resultado.getString("apellidos"));
                    ofertaExterna.setCorreo(resultado.getString("correo"));
                    ofertaExterna.setPais(resultado.getString("pais"));
                    ofertaExterna.setTelefono(resultado.getString("telefono"));
                    ofertaExterna.setMateria(resultado.getString("materia"));
                    ofertaExterna.setCarrera(resultado.getString("carrera"));
                    ofertaExterna.setNombreUniversidad
        (resultado.getString("nombreUniversidad"));
                
                    ofertasExternas.add(ofertaExterna);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("ofertasColaboracionExternas", ofertasExternas);
                conexionBD.close();

                
            }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
                
            }    
        }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);                
                    
        }
        return respuesta;
    }
    
    
    public static HashMap<String, Object> 
        actualizarOfertaColaboracionExterna(OfertaColaboracionExterna ofertaExterna){
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        
        if(conexionBD != null){
            try{
                String consulta = "update ofertacolaboracionexterna " +
                                  "set nombre=?, descripcion=?, periodo=? " +
                                  "where idOfertaColaboracionExterna=?";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);

                prepararSentencia.setString(1, ofertaExterna.getNombre());
                prepararSentencia.setString(2, ofertaExterna.getDescripcion());
                prepararSentencia.setString(3, ofertaExterna.getPeriodo());
                prepararSentencia.setInt
        (4, ofertaExterna.getIdOfertaColaboracionExterna());
                
                int filasAfectadas = prepararSentencia.executeUpdate();
                        
                if(filasAfectadas == 1){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "Informacion de la oferta externa actualizada correctamente");
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "Lo sentimos, hubo un error en actualizar "
                                    + "la informacion de la oferta externa");
                }
                conexionBD.close();
                
            }catch(SQLException ex){
                respuesta.put(Constantes.KEY_MENSAJE, ex.getMessage());
            }  
            
        }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);  
        }
        return respuesta;
    }
    
    
    public static HashMap<String, Object> 
        guardarOfertaExterna(OfertaColaboracionExterna ofertaExterna) {
        Connection conexionBD = ConexionBD.obtenerConexion();
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
       
        if (conexionBD != null) {
            try {
                String consulta = "insert into ofertacolaboracionexterna "
                        + "(nombre, descripcion, periodo, idProfesorExterno, idEstadoOferta)" +
                    "VALUES (?, ?, ?, (SELECT idProfesorExterno FROM profesorexterno ORDER BY "
                        + "idProfesorExterno DESC LIMIT 1), 1)";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, ofertaExterna.getNombre());
                prepararSentencia.setString(2, ofertaExterna.getDescripcion());
                prepararSentencia.setString(3, ofertaExterna.getPeriodo());

                
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas == 1){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "Oferta de colaboración COIL registrada con éxito");
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, "Lo sentimos, hubo un error en "
                            + "guardar la informacion de la oferta de colaboración COIl");
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
    
    
    public static HashMap<String, Object> guardarProfesorExterno(ProfesorExterno profesorExterno){
        Connection conexionBD = ConexionBD.obtenerConexion();
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
       
        if (conexionBD != null) {
            try {
                String consulta = "insert into profesorexterno (nombre, apellidos, correo, pais, "
                        + "telefono, materia, carrera, idUniversidad)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, (SELECT idUniversidad "
                        + "FROM Universidad ORDER BY idUniversidad DESC LIMIT 1))";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, profesorExterno.getNombre());
                prepararSentencia.setString(2, profesorExterno.getApellidos());
                prepararSentencia.setString(3, profesorExterno.getCorreo());
                prepararSentencia.setString(4, profesorExterno.getPais());
                prepararSentencia.setString(5, profesorExterno.getTelefono());
                prepararSentencia.setString(6, profesorExterno.getMateria());
                prepararSentencia.setString(7, profesorExterno.getCarrera());
     
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas == 1){
                    respuesta.put(Constantes.KEY_ERROR, false);
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, "Lo sentimos, "
                            + "hubo un error en guardar la informacion del profesor externo");
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
    
    
    public static HashMap<String, Object> guardarUniversidad(Universidad universidad){
        Connection conexionBD = ConexionBD.obtenerConexion();
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
       
        if (conexionBD != null) {
            try {
                String consulta = "insert into universidad (nombre) values (?)";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, universidad.getNombre());


                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas == 1){
                    respuesta.put(Constantes.KEY_ERROR, false);
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, "Lo sentimos, "
                            + "hubo un error en guardar la informacion de la universidad");
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
    
    
    public static HashMap<String, Object> 
        actualizarEstadoOferta(int idOfertaColaboracionExterna) {
        Connection conexionBD = ConexionBD.obtenerConexion();
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
       
        if (conexionBD != null) {
            try {
                String consulta = "UPDATE ofertacolaboracionexterna SET idEstadoOferta = 2 "
                        + "WHERE idOfertaColaboracionExterna = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idOfertaColaboracionExterna);
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas == 1){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "Oferta de colaboración COIL actualizada con éxito");
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, "Lo sentimos, hubo un error en "
                            + "guardar la informacion de la oferta de colaboración COIl");
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
