
package Ejercicios.Actualizaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author MiguelAngel & Sergey
 */
public class Modificaciones {
    
    public void datosAlumno(String url, String user, String pass, String nombre, String delegado, int id){
        String updateAlumno="UPDATE alumno SET `Nombre` = '"+nombre+"', `Delegado` = "+delegado+" WHERE (`Expediente` = '"+id+"');";
        
        Connection conexion=null;
        Statement sentencia=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            sentencia=conexion.createStatement();
            sentencia.executeUpdate(updateAlumno);
            JOptionPane.showMessageDialog(null, "Se han modificado los datos con éxito");
            return;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
        JOptionPane.showMessageDialog(null, "Error inesperado","Error",JOptionPane.ERROR_MESSAGE);
    }
    
    public void notaAlumno(String url, String user, String pass, int idA, int idM, int nota){
        String updateAlumno="UPDATE modulo_alumno SET `notaFinal` = '"+nota+"' "
                + "WHERE (`Codigo_alumno` = '"+idA+"') && (`Codigo_modulo`='"+idM+"'); ";
        
        Connection conexion=null;
        Statement sentencia=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            sentencia=conexion.createStatement();
            sentencia.executeUpdate(updateAlumno);
            JOptionPane.showMessageDialog(null, "Se han modificado los datos con éxito");
            return;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
        JOptionPane.showMessageDialog(null, "Primero debes ejecutar la modificación de la tabla Modulo_Alumno","Error",JOptionPane.ERROR_MESSAGE);
    }
}
