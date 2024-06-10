/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 18/05/2024
* Descripción: Clase DAO para manejar el acceso, guardado, modificacion y 
  eliminacion de los datos de la clase OfertaColaboracionUV
*/

package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.AreaAcademica_Campus;
import coilvic.modelo.pojo.Dependencia;
import coilvic.modelo.pojo.ExperienciaEducativa;
import coilvic.modelo.pojo.OfertaColaboracionUV;
import coilvic.modelo.pojo.Periodo;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                String consulta = "SELECT ocuv.idOfertaColaboracionUV, ocuv.descripcion, "
                + "ocuv.nombre AS ofertaNombre, ee.nombre AS experienciaEducativaNombre, "
                + "CONCAT(puv.nombre, ' ', puv.apellidos) AS profesorNombreCompleto, "
                + "eocuv.estado AS estadoOferta, p.fechaInicio, p.fechaFin, aa.nombre "
                + "AS areaAcademicaNombre, c.nombre AS campusNombre "
                + "FROM ofertaColaboracionUV ocuv "
                + "JOIN experienciaEducativa ee "
                + "ON ocuv.idExperienciaEducativa = ee.idExperienciaEducativa "
                + "JOIN profesoruv puv ON ocuv.idProfesoruv = puv.idProfesoruv "
                + "JOIN periodo p ON ee.idPeriodo = p.idPeriodo "
                + "JOIN estadoOfertaColaboracionUV eocuv "
                + "ON ocuv.idEstadoOfertaColaboracionUV = eocuv.idEstadoOfertaColaboracionUV "
                + "JOIN dependencia dep ON ee.idDependencia = dep.idDependencia "
                + "JOIN programaeducativo pe ON dep.idProgramaEducativo = pe.idProgramaEducativo "
                + "JOIN areaacademica aa ON pe.idAreaAcademica = aa.idAreaAcademica "
                + "JOIN campus c ON dep.idCampus = c.idCampus "
                + "WHERE ocuv.idEstadoOfertaColaboracionUV = 1";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<OfertaColaboracionUV> ofertaColaboracionUV = new ArrayList();
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter outputFormatter = 
                        DateTimeFormatter.ofPattern("dd-MM-yyyy");
                while (resultado.next()) {
                    OfertaColaboracionUV oferta = new OfertaColaboracionUV();
                    oferta.setIdOfertaColaboracionUV
                        (resultado.getInt("idOfertaColaboracionUV"));
                    oferta.setNombre(resultado.getString("ofertaNombre"));
                    oferta.setDescripcion(resultado.getString("descripcion"));
                    oferta.setExperienciaEducativa
                    (resultado.getString("experienciaEducativaNombre"));
                    String fechaInicioBD = resultado.getString("fechaInicio");
                    LocalDate fechaInicio = 
                            LocalDate.parse(fechaInicioBD, inputFormatter);
                    oferta.setFechaInicio(fechaInicio.format(outputFormatter));
                    String fechaFinBD = resultado.getString("fechaFin");
                    LocalDate fechaFin = 
                            LocalDate.parse(fechaFinBD, inputFormatter);
                    oferta.setFechaFin(fechaFin.format(outputFormatter));
                    oferta.setEstado(resultado.getString("estadoOferta"));
                    oferta.setNombreAreaAcademica
                        (resultado.getString("areaAcademicaNombre"));
                    oferta.setCampus(resultado.getString("campusNombre"));
                    oferta.setProfesorUV
                        (resultado.getString("profesorNombreCompleto"));
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
    
    
    public static HashMap<String, Object> obtenerTodaOfertaColaboracionUV(){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT " +
                                "    oc.idOfertaColaboracionUV, " +
                                "    oc.descripcion AS ofertaDescripcion, " +
                                "    oc.nombre AS ofertaNombre, " +
                                "    ee.nombre AS experienciaNombre, " +
                                "    ee.descripcion AS experienciaDescripcion, " +
                                "    ee.creditos, " +
                                "    d.nombre AS dependenciaNombre, " +
                                "    d.municipio, " +
                                "    c.idCampus, " +
                                "    c.nombre AS campusNombre, " +
                                "    pe.idProgramaEducativo, " +
                                "    pe.nombre AS programaNombre, " +
                                "    aa.idAreaAcademica, " +
                                "    aa.nombre AS areaNombre, " +
                                "    p.nombre AS profesorUvNombre, " +
                                "    p.apellidos, " +
                                "    p.correo, " +
                                "    p.numeroPersonal, " +
                                "    p.telefono, " +
                                "    e.estado, " +
                                "    per.fechaInicio, " +
                                "    per.fechaFin " +
                                     
                                "FROM " +
                                "    ofertacolaboracionuv oc " +
                                "    JOIN experienciaeducativa ee ON oc.idExperienciaEducativa = "
                        + "ee.idExperienciaEducativa " +
                                "    JOIN dependencia d ON ee.idDependencia = d.idDependencia " +
                                "    JOIN campus c ON d.idCampus = c.idCampus " +
                                "    JOIN programaeducativo pe ON d.idProgramaEducativo = "
                        + "pe.idProgramaEducativo " +
                                "    JOIN areaacademica aa ON pe.idAreaAcademica = "
                        + "aa.idAreaAcademica " +
                                "    JOIN estadoofertacolaboracionuv e "
                        + "ON oc.idEstadoOfertaColaboracionUV = e.idEstadoOfertaColaboracionUV" +
                                "    JOIN profesoruv p ON oc.idProfesoruv = p.idProfesoruv " +
                                "    JOIN periodo per ON ee.idPeriodo = per.idPeriodo" +
                                "    WHERE oc.idEstadoOfertaColaboracionUV = 3";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                List<OfertaColaboracionUV> ofertaColaboracionUV = new ArrayList();
                while (resultado.next()) {
                    OfertaColaboracionUV oferta = new OfertaColaboracionUV();
                    oferta.setIdOfertaColaboracionUV
        (resultado.getInt("idOfertaColaboracionUV"));
                    oferta.setNombre(resultado.getString("ofertaNombre"));
                    oferta.setMunicipio(resultado.getString("municipio"));
                    oferta.setCampus(resultado.getString("campusNombre"));
                    oferta.setNombreDependencia
        (resultado.getString("dependenciaNombre"));
                    oferta.setNombreAreaAcademica
        (resultado.getString("areaNombre"));
                    oferta.setNombreProgramaEducativo
        (resultado.getString("programaNombre"));
                    oferta.setDescripcion
        (resultado.getString("ofertaDescripcion"));
                    oferta.setExperienciaEducativa
        (resultado.getString("experienciaNombre"));
                    oferta.setCreditos(resultado.getString("creditos"));
                    oferta.setDescripcionEe
        (resultado.getString("experienciaDescripcion"));
                    oferta.setNombreProfesorUv
        (resultado.getString("profesorUvNombre"));
                    oferta.setApellidos(resultado.getString("apellidos"));
                    oferta.setNumeroPersonal(resultado.getInt("numeroPersonal"));
                    oferta.setCorreo(resultado.getString("correo"));
                    oferta.setTelefono(resultado.getInt("telefono"));
                    oferta.setEstado(resultado.getString("estado"));
                    oferta.setFechaInicio(resultado.getString("fechaInicio"));
                    oferta.setFechaFin(resultado.getString("fechaFin"));
                    oferta.setIdCampus(resultado.getInt("idCampus"));
                    oferta.setIdAreaAcademica(resultado.getInt("idAreaAcademica"));
                    oferta.setIdProgramaEducativo
        (resultado.getInt("idProgramaEducativo"));
                    
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
    
    
    public static HashMap<String, Object> 
        actualizarOfertaColaboracionUV(OfertaColaboracionUV ofertaUv){
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        
        if(conexionBD != null){
            try{
                String consulta = "UPDATE ofertacolaboracionuv oc " +
                                    "JOIN experienciaeducativa ee "
                        + "ON oc.idExperienciaEducativa = ee.idExperienciaEducativa " +
                                    "JOIN dependencia d ON ee.idDependencia = d.idDependencia " +
                                    "JOIN campus c ON d.idCampus = c.idCampus " +
                                    "JOIN programaeducativo pe "
                        + "ON d.idProgramaEducativo = pe.idProgramaEducativo " +
                                    "JOIN areaacademica aa ON pe.idAreaAcademica = "
                        + "aa.idAreaAcademica " +
                                    "JOIN estadoofertacolaboracionuv e "
                        + "ON oc.idEstadoOfertaColaboracionUV = e.idEstadoOfertaColaboracionUV " +
                                    "JOIN periodo per ON ee.idPeriodo = per.idPeriodo " +
                                    "SET " +
                                    "oc.descripcion =?, " +
                                    "oc.nombre =?, " +
                                    "ee.nombre =?, " +
                                    "ee.descripcion =?, " +
                                    "ee.creditos =?, " +
                                    "d.nombre =?, " +
                                    "d.municipio =?, " +
                                    "d.idCampus =?, " +
                                    "d.idProgramaEducativo =?, " +
                                    "per.fechaInicio =?, " +
                                    "per.fechaFin =? " +
                                    "WHERE oc.idOfertaColaboracionUV =?;";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);

                prepararSentencia.setString(1, ofertaUv.getDescripcion());
                prepararSentencia.setString(2, ofertaUv.getNombre());
                prepararSentencia.setString(3, ofertaUv.getExperienciaEducativa());
                prepararSentencia.setString(4, ofertaUv.getDescripcionEe());
                prepararSentencia.setString(5, ofertaUv.getCreditos());
                prepararSentencia.setString(6, ofertaUv.getNombreDependencia());
                prepararSentencia.setString(7, ofertaUv.getMunicipio());
                prepararSentencia.setInt(8, ofertaUv.getIdCampus());
                prepararSentencia.setInt(9, ofertaUv.getIdProgramaEducativo());
                prepararSentencia.setString(10, ofertaUv.getFechaInicio());
                prepararSentencia.setString(11, ofertaUv.getFechaFin());
                prepararSentencia.setInt(12, ofertaUv.getIdOfertaColaboracionUV());

                prepararSentencia.executeUpdate();
                
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put(Constantes.KEY_MENSAJE, "Informacion de la oferta "
                        + "de colaboracion UV ha sido actualizada correctamente");
                
                conexionBD.close();
                
            }catch(SQLException ex){
                respuesta.put(Constantes.KEY_MENSAJE, ex.getMessage());
            }  
            
        }else{
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
    
    
    public static HashMap<String, Object> guardarDependencia(Dependencia dependencia){
        Connection conexionBD = ConexionBD.obtenerConexion();
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
       
        if (conexionBD != null) {
            try {
                String consulta = "insert into dependencia "
                        + "(nombre, municipio, idCampus, idProgramaEducativo) values (?, ?, ?, ?)";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, dependencia.getNombre());
                prepararSentencia.setString(2, dependencia.getMunicipio());
                prepararSentencia.setInt(3, dependencia.getIdCampus());
                prepararSentencia.setInt(4, dependencia.getIdProgramaEducativo());
                
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas == 1){
                    respuesta.put(Constantes.KEY_ERROR, false);
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, "Lo sentimos, "
                            + "hubo un error en guardar la informacion de la dependencia");
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
    
    
    public static HashMap<String, Object> guardarPeriodo(Periodo periodo){
        Connection conexionBD = ConexionBD.obtenerConexion();
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
       
        if (conexionBD != null) {
            try {
                String consulta = "insert into periodo (fechaInicio, fechaFin) values (?, ?);";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, periodo.getFechaInicio());
                prepararSentencia.setString(2, periodo.getFechaFin());
                
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas == 1){
                    respuesta.put(Constantes.KEY_ERROR, false);
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, "Lo sentimos, "
                            + "hubo un error en guardar la informacion del periodo");
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
        guardarExperienciaEducativa(ExperienciaEducativa experienciaEducativa){
        Connection conexionBD = ConexionBD.obtenerConexion();
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
       
        if (conexionBD != null) {
            try {
                String consulta = "insert into experienciaeducativa "
                        + "(nombre, descripcion, creditos, idDependencia, idPeriodo) "
                        + "values (?, ?, ?, (SELECT idDependencia "
                        + "FROM dependencia ORDER BY idDependencia DESC LIMIT 1), "
                        + "(SELECT idPeriodo FROM periodo ORDER BY idPeriodo DESC LIMIT 1))";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, experienciaEducativa.getNombre());
                prepararSentencia.setString(2, experienciaEducativa.getDescripcion());
                prepararSentencia.setInt(3, experienciaEducativa.getCreditos());
                
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas == 1){
                    respuesta.put(Constantes.KEY_ERROR, false);
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, "Lo sentimos, "
                           + "hubo un error en guardar la informacion de la experiencia educativa");
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
        guardarOfertaColaboracionUv(OfertaColaboracionUV ofertaUv){
        Connection conexionBD = ConexionBD.obtenerConexion();
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
       
        if (conexionBD != null) {
            try {
                String consulta = "insert into ofertacolaboracionuv "
                        + "(nombre, descripcion, idProfesoruv, "
                        + "idExperienciaEducativa, idEstadoOfertaColaboracionUV) " 
                        + "values (?, ?, ?, (SELECT idExperienciaEducativa "
                        + "FROM experienciaeducativa "
                        + "ORDER BY idExperienciaEducativa DESC LIMIT 1), 1)";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, ofertaUv.getNombre());
                prepararSentencia.setString(2, ofertaUv.getDescripcion());
                prepararSentencia.setInt(3, ofertaUv.getIdProfesorUV());

                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas == 1){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, 
                            "Oferta de colaboración COIL registrada con éxito");
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, "Lo sentimos, hubo un error en "
                            + "guardar la informacion de la oferta de colaboracion UV");
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
