/*
 * Insertar un mínimo de 3 profesores, donde dos de ellos compartirán módulo.
 */
package Ejercicios.InsertarDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author MiguelAngel & Sergey
 */
public class InsertarDatosProfesores {
    //Sentencias SQL que usará esta clase:    
    String insertarProfesores=
            "INSERT INTO profesor VALUES"
            + "('1', 'Francisco Javier', 'Fernández-Arévalo', 'Collado', 'Almería', '654895578', '2'),"
            + "('2', 'Juan Miguel', 'Jiménez', 'Lechuga', 'Almería', '65488497', '2'),"
            + "('3', 'Francisco', 'Fernández de Piñar', 'López', 'Almería', '654782545', '1');";
    /*
    * Este método inserta datos en la tabla Profesores
    * Se le pasa como parámetros los datos de MySQL necesarios para la conexión.
    */
    public void Ejercicio2_Profesores(String url, String user, String pass){
       Connection conexion=null;
        Statement sentencia=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            sentencia=conexion.createStatement();
            sentencia.execute(insertarProfesores);
            JOptionPane.showMessageDialog(null, "Se han insertado los datos con éxito");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
    } 
}
