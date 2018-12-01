/*
 * MatricularAlumnos (IN modulo INT, IN alumno INT, OUT matriculado)
 * -> almacena el módulo en el que se matricula un alumno. Devuelve
 * si el alumno se ha podido matricular(1) o no (0). Se considera
 * que alumno no puede matricularse si el módulo está lleno, es decir,
 * tiene todas las plazas ocupadas (30).
 */
package Ejercicios.Procedimientos;

import Ejercicios.Consultas.CreacionEsquema;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MiguelAngel & Sergey
 */
public class InsertarProcedimientoMatricularAlumnos {
    //Sentencias SQL que usará esta clase:    
    String procMatricularAlumnos=
            "CREATE DEFINER=`root`@`localhost` PROCEDURE `MatricularAlumnos`(IN modulo INT, IN alumno INT, OUT matricula INT)" +
            "BEGIN" +
            "	DECLARE modAlum_count INTEGER;" +
            "	if (SELECT count(*) FROM modulo_alumno WHERE 'codigo_modulo'=modulo)<30 then" +
            "		SET matricula=1;" +
            "       SELECT count(*)+1 INTO modAlum_count FROM modulo_alumno;" +
            "        INSERT INTO modulo_alumno VALUES(modAlum_count,alumno,modulo);" +
            "	else \n" +
            "		SET matricula=0;\n" +
            "	end if;" +
            "END";
    /*
    * Este método inserta el procedimiento almacenado MatricularAlumnos
    * Se le pasa como parámetros los datos de MySQL necesarios para la conexión.
    */
    public void Ejercicio2_insertarMatricularAlumnos(String url, String user, String pass){
        Connection conexion=null;
        Statement sentencia=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            sentencia=conexion.createStatement();
            sentencia.execute(procMatricularAlumnos);
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
    
    public void Ejercicio2_ejecutarMatricularAlumnos(String url, String user, String pass){
        Connection conexion=null;
        Statement sentencia=null;
        int modulo = 2, alumno = 4, matricula = 0;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            String sql="{call MatricularAlumnos(?,?,?)}";
            
            CallableStatement procAlmacenado = conexion.prepareCall(sql);
            
            procAlmacenado.setInt(1, modulo);
            procAlmacenado.setInt(2, alumno);
            procAlmacenado.setInt(3, matricula);
            
            procAlmacenado.registerOutParameter(3, java.sql.Types.INTEGER);
            
            procAlmacenado.execute();
            
            matricula=procAlmacenado.getInt(3);
            
            if(matricula==1){
                System.out.println("Se ha matriculado wey");
            }else{
                System.out.println("No se ha matriculado wey");
            }
            
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
