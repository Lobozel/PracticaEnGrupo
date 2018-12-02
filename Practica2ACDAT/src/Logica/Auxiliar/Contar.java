package Logica.Auxiliar;
/*
* Esta clase es sirve de forma auxiliar para la ventana
* Cada vez que hay que escoger entre un alumno o un modulo
* la ventana dispone de una lista desplegable con todos los
* alumnos o modulos, la cantidad de estos la saco de aquí.
* Esto tiene como objetivo ofrecer una interfaz más sencilla
* y cómoda para el usuario a la hora de gestionar la BD.
*/

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
public class Contar {
    //Sentencias SQL que usará esta clase: 
    String numAlumnos
            = "SELECT count(*) FROM alumno;";
    String numModulos
            = "SELECT count(*) FROM modulo;";

    //Objetos comunes
    Connection conexion = null;
    Statement sentencia = null;
    ResultSet result = null;
    
    //Me conecto desde el constructor
    public Contar(String url, String user, String pass){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(url, user, pass);
            sentencia = conexion.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Contar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Contar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Contar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Contar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Este método devuelve el número de alumnos.
    //Útil a la hora de crear el array de nombres y el menú desplegable
    public int contarAlumnos() {
        int cont = 0;
        
        try {
            result = sentencia.executeQuery(numAlumnos);
            while (result.next()) {
                cont = result.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Contar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cont;
    }

    //Este método devuelve el número de módulos.
    //Útil a la hora de crear el array de nombres y el menú desplegable
    public int contarModulos() {
        int cont = 0;
        
        try {
            result = sentencia.executeQuery(numModulos);
            while (result.next()) {
                cont = result.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Contar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cont;
    }
}
