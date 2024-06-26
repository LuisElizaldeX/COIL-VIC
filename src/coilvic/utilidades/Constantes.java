/*
* Autor: Luis Angel Elizalde Arroyo
* Fecha de creación: 17/05/2024
* Descripción: Clase encargada de guardar las variables estaticas
*/

package coilvic.utilidades;

public class Constantes {
     public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String NOMBRE_BD = "coilvic";
    public static final String HOSTNAME = "localhost";
    public static final String PUERTO = "3306";
    
    public static final String USUARIO = "UsuarioCOIL";
    public static final String PASSWORD = "COIL-VIC17@";
    
    
    public static final String MSJ_ERROR_CONEXION = "Por el momento no hay conexión "
            + "con la base de datos.";
    
    public static final String KEY_ERROR = "error";
    public static final String KEY_MENSAJE = "mensaje";
    public static final long PESO_MAXIMO = 1024 * 1024 * 20;
    public static final int ID_ARCHIVO_EVIDENCIA = 2;
}
