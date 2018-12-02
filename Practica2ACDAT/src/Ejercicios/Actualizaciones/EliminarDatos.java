
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
public class EliminarDatos {    
    
    public void eliminarAlumno(String url, String user, String pass, int id){
        String eliminarAlumno="DELETE FROM alumno WHERE (`Expediente` = '"+id+"');";
        
        Connection conexion=null;
        Statement sentencia=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            sentencia=conexion.createStatement();
            sentencia.executeUpdate(eliminarAlumno);
            JOptionPane.showMessageDialog(null, "Se ha eliminado el alumno con éxito");
            return;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
        JOptionPane.showMessageDialog(null, "Error inesperado","Error",JOptionPane.ERROR_MESSAGE);
    }
    
    public void eliminarModulo(String url, String user, String pass, int id){
        String eliminarModulo="DELETE FROM modulo WHERE (`Codigo` = '"+id+"');";
        
        Connection conexion=null;
        Statement sentencia=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            sentencia=conexion.createStatement();
            sentencia.executeUpdate(eliminarModulo);
            JOptionPane.showMessageDialog(null, "Se ha eliminado el modulo con éxito");
            return;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
        JOptionPane.showMessageDialog(null, "Error inesperado","Error",JOptionPane.ERROR_MESSAGE);
    }
}
