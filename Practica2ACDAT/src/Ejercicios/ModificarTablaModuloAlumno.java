/*
 * Modificar la tabla Modulo_alumno para que incluya un nuevo campo,
 * notaFinal, y así poder almacenar la nota que ha obtenido en cada
 * módulo.
 */
package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author MiguelAngel & Sergey
 */
public class ModificarTablaModuloAlumno {
    //Sentencias SQL que usará esta clase:    
    String modificarModuloAlumno=
            "ALTER TABLE `instituto`.`modulo_alumno`" +
            "ADD COLUMN `notaFinal` INT NULL AFTER `Codigo_modulo`;";
    /*
    * Este método añade un nuevo campo a la tabla Modulo_Alumnos
    * Se le pasa como parámetros los datos de MySQL necesarios para la conexión.
    */
    public void Ejercicio3(String url, String user, String pass){
        Connection conexion=null;
        Statement sentencia=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            sentencia=conexion.createStatement();
            sentencia.execute(modificarModuloAlumno);
            JOptionPane.showMessageDialog(null, "La Tabla se ha Modificado con éxito.");
            return;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
        JOptionPane.showMessageDialog(null, "La Tabla ya está modificada.", "Error", JOptionPane.ERROR_MESSAGE);
    } 
}
