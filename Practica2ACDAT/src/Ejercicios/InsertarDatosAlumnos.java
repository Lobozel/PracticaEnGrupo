/*
 * Insertar un mínimo de 4 alumnos y 2 alumnos en cada módulo.
 */
package Ejercicios;

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
public class InsertarDatosAlumnos {
    //Sentencias SQL que usará esta clase:    
    String insertarAlumnos=
            "INSERT INTO alumno  VALUES ('1', 'Miguel Ángel', 'López', 'Sánchez', '1996-09-06',_binary 'false\\0\\0\\0\\0\\0'),"
            + "('2', 'Sergey', 'Shevchenko', '', '1996-02-15',_binary 'false\\0\\0\\0\\0\\0'),"
            + "('3', 'Julian', 'Rodrigo', 'Jiménez', '1992-01-22',_binary 'false\\0\\0\\0\\0\\0'),"
            + "('4', 'Juan', 'González', 'Martín', '1998-05-05',_binary 'true\\0\\0\\0\\0\\0\\0');";
    /*
    * Este método inserta datos en la tabla Alumnos
    * Se le pasa como parámetros los datos de MySQL necesarios para la conexión.
    */
    public void Ejercicio2_Alumnos(String url, String user, String pass){
        Connection conexion=null;
        Statement sentencia=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            sentencia=conexion.createStatement();
            sentencia.execute(insertarAlumnos);
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
