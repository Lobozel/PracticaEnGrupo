package Logica.Auxiliar;
/*
* Esta clase es sirve de forma auxiliar para la ventana
* Cada vez que hay que escoger entre un alumno o un modulo
* la ventana dispone de una lista desplegable con todos los
* alumnos o modulos, los cuales saco desde aquí.
* Esto tiene como objetivo ofrecer una interfaz más sencilla
* y cómoda para el usuario a la hora de gestionar la BD.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MiguelAngel & Sergey
 */
public class Nombres {
    //Sentencias SQL que usará esta clase:   
    String nombresAlumnos
            = "SELECT nombre FROM alumno;";
    String nombresModulos
            = "SELECT nombre FROM modulo;";

    //Objetos comunes
    Connection conexion = null;
    Statement sentencia = null;
    ResultSet result = null;
    Contar contar;

    //Me conecto desde el constructor
    public Nombres(String url, String user, String pass) {
        contar = new Contar(url, user, pass);
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(url, user, pass);
            sentencia = conexion.createStatement();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    //Este método devuelve un array con los nombres de los alumnos
    public String[] listaNombresAlumnos() {
        String[] nombresAlumnos = new String[contar.contarAlumnos()];
        int cont = 0;

        try {
            result = sentencia.executeQuery(this.nombresAlumnos);
            while (result.next()) {
                nombresAlumnos[cont] = result.getString(1);
                cont++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }

        return nombresAlumnos;
    }

    //Este método devuelve un array con los nombres de los modulos
    public String[] listaNombresModulos() {
        String[] nombresModulos = new String[contar.contarModulos()];
        int cont = 0;

        try {
            result = sentencia.executeQuery(this.nombresModulos);
            while (result.next()) {
                nombresModulos[cont] = result.getString(1);
                cont++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }

        return nombresModulos;
    }
}
