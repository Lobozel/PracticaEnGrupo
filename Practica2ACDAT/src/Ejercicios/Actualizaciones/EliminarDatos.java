
package Ejercicios.Actualizaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
        String contarAlumnosEnModulo="SELECT count(Codigo_alumno) FROM modulo_alumno WHERE Codigo_modulo='"+id+"';";
        String eliminarModulo="DELETE FROM modulo WHERE (`Codigo` = '"+id+"');";
        int cont=0;
        
        Connection conexion=null;
        Statement sentencia=null;
        ResultSet result=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            sentencia=conexion.createStatement();
            
            /*Cuento los alumnos que hay en el módulo seleccionado para saber
            si el módulo tiene alumnos o no*/
            result = sentencia.executeQuery(contarAlumnosEnModulo);
            while (result.next()) {
                cont = result.getInt(1);
            }
            if(cont>0){
                JOptionPane.showMessageDialog(null, "No se puede borrar un Módulo con Alumnos matriculados.","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                sentencia.executeUpdate(eliminarModulo);
                JOptionPane.showMessageDialog(null, "Se ha eliminado el Módulo con éxito");               
            }
            return;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
        JOptionPane.showMessageDialog(null, "Error inesperado","Error",JOptionPane.ERROR_MESSAGE);
    }
}
