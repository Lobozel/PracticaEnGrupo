/*
 * Insertar al menos 2 módulos: ACDAT y PSP
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
public class InsertarDatosModulos {
    //Sentencias SQL que usará esta clase:    
    String insertarModulos=
            "INSERT INTO modulo VALUES"
            + "('1', 'ACDAT'),"
            +  "('2', 'PSP');";
    /*
    * Este método inserta datos en la tabla Modulos
    * Se le pasa como parámetros los datos de MySQL necesarios para la conexión.
    */
    public void Ejercicio2_Modulos(String url, String user, String pass){
        Connection conexion=null;
        Statement sentencia=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            sentencia=conexion.createStatement();
            sentencia.execute(insertarModulos);
            JOptionPane.showMessageDialog(null, "Se han insertado los datos con éxito");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
    } 
}
