/*
 * altaAlumnos(OUT nuevosAlumnos) -> Almacena los datos introducidos
 * por el usuario en la tabla alumno. Devuelve el número de alumnos
 * nuevos que se han introducido.
 */
package Ejercicios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MiguelAngel & Sergey
 */
public class InsertarProcedimientoAltaAlumnos {
    //Sentencias SQL que usará esta clase:    
    String procAltaAlumnos=
            "CREATE DEFINER=`root`@`localhost` PROCEDURE `altaAlumnos`(OUT nuevosAlumnos INT)" +
"BEGIN " +
" Select count(*) INTO nuevosAlumnos FROM alumno;" +
"END";
    
    /*
    * Este método inserta el procedimiento almacenado altaAlumnos
    * Se le pasa como parámetros los datos de MySQL necesarios para la conexión.
    */
    public void Ejercicio2_insertarAltaAlumnos(String url, String user, String pass){
        Connection conexion=null;
        Statement sentencia=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            sentencia=conexion.createStatement();
            sentencia.execute(procAltaAlumnos);
            System.out.println("Ha funcionao wey");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreacionEsquema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CreacionEsquema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CreacionEsquema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreacionEsquema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Ejercicio2_ejecutaraltaAlumnos(String url, String user, String pass){
        Connection conexion=null;
        Statement sentencia=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            String sql="{call altaAlumnos(?)}";
            
            CallableStatement procAlmacenado = conexion.prepareCall(sql);
            
            int nuevosAlumnos = 0;
            
            procAlmacenado.setInt(1, nuevosAlumnos);
            
            procAlmacenado.registerOutParameter(1, java.sql.Types.INTEGER);
            
            procAlmacenado.execute();
            
            nuevosAlumnos=procAlmacenado.getInt(1);
            
            System.out.println(nuevosAlumnos);
            
            System.out.println("Ha funcionao wey");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreacionEsquema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CreacionEsquema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CreacionEsquema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreacionEsquema.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
