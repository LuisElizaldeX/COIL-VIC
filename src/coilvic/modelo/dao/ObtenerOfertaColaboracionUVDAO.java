package coilvic.modelo.dao;

import coilvic.modelo.ConexionBD;
import coilvic.modelo.pojo.AreaAcademica;
import coilvic.modelo.pojo.Campus;
import coilvic.modelo.pojo.ProgramaEducativo;
import coilvic.utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


public class ObtenerOfertaColaboracionUVDAO {
    
    public static HashMap<String, Object> obtenerCampus() {
        Connection conexionBD = ConexionBD.obtenerConexion();
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
       
        if (conexionBD != null) {
            try {
                String consulta = "select* from campus";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                
                List<Campus> campus = new ArrayList();
                
                while (resultado.next()) {
                    Campus campuses = new Campus();
                    campuses.setIdCampus(resultado.getInt("idCampus"));
                    campuses.setNombre(resultado.getString("nombre"));
                    
                    campus.add(campuses);
                    
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("campus", campus);
                conexionBD.close();
                
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
    
    public static HashMap<String, Object> obtenerAreasAcademicas(int idCampus) {
        Connection conexionBD = ConexionBD.obtenerConexion();
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
       
        if (conexionBD != null) {
            try {
                String consulta = "SELECT a.idAreaAcademica, a.nombre "
                        + "FROM areaacademica a "
                        + "JOIN areaacademica_campus ac ON a.idAreaAcademica = ac.idAreaAcademica WHERE ac.idCampus = ?";
                
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idCampus);
                ResultSet resultado = prepararSentencia.executeQuery();

                
                List<AreaAcademica> areasAcademicas = new ArrayList();

                while (resultado.next()) {
                    AreaAcademica areaAcademica = new AreaAcademica();
                    areaAcademica.setIdAreaAcademica(resultado.getInt("idAreaAcademica"));
                    areaAcademica.setNombre(resultado.getString("nombre"));

                    areasAcademicas.add(areaAcademica);
                    
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("areasAcademicas", areasAcademicas);
                conexionBD.close();
                
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
    
    public static HashMap<String, Object> obtenerProgramasEducativos(int idAreaAcademica, int idCampus) {
        Connection conexionBD = ConexionBD.obtenerConexion();
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
       
        if (conexionBD != null) {
            try {
                String consulta = "SELECT pe.idProgramaEducativo, pe.nombre "
                        + "FROM programaeducativo pe "
                        + "JOIN programaeducativo_campus pc ON pe.idProgramaEducativo = pc.idProgramaEducativo "
                        + "JOIN campus c ON pc.idCampus = c.idCampus "
                        + "JOIN areaacademica aa ON pe.idAreaAcademica = aa.idAreaAcademica "
                        + "WHERE aa.idAreaAcademica = ? "
                        + "AND c.idCampus = ?";
                
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idAreaAcademica);
                prepararSentencia.setInt(2, idCampus);
                
                ResultSet resultado = prepararSentencia.executeQuery();

                
                List<ProgramaEducativo> programasEducativos = new ArrayList();

                while (resultado.next()) {
                    ProgramaEducativo programaEducativo = new ProgramaEducativo();
                    programaEducativo.setIdProgramaEducativo(resultado.getInt("idProgramaEducativo"));
                    programaEducativo.setNombre(resultado.getString("nombre"));

                    programasEducativos.add(programaEducativo);
                    
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("programasEducativos", programasEducativos);
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
