package Ejercicios.Consultas;

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
public class ConsultaProfesores {

    Connection conexion = null;
    Statement sentencia = null;

    public ConsultaProfesores(String url, String user, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(url, user, pass);
            sentencia = conexion.createStatement();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public String[] profesoresDeAlumno(int id) {
        int tamanio = 0, cont = 0;
        int[] idsModulo;
        String[] nombresProfesores = null;
        String nombreProfesor;
        String modulosProfesor = "SELECT Codigo_modulo FROM modulo_alumno WHERE Codigo_alumno='" + id + "';";
        String contarModulos = "SELECT count(Codigo_modulo) FROM modulo_alumno WHERE Codigo_alumno='" + id + "';";
        String contarProfesores;
        ResultSet result = null;

        try {
            //tamaño del array de modulos que tiene el Alumno
            result = sentencia.executeQuery(contarModulos);
            while (result.next()) {
                tamanio = result.getInt(1);
            }

            idsModulo = new int[tamanio];

            //Recogida en array de ids de modulos que tiene el alumno
            result = sentencia.executeQuery(modulosProfesor);
            while (result.next()) {
                idsModulo[cont] = result.getInt(1);
                cont++;
            }

            //tamaño del array de nombres de profesores que imparten dichos modulos
            tamanio = 0;
            for (int i = 0; i < idsModulo.length; i++) {
                contarProfesores = "SELECT count(*) FROM profesor WHERE Codigo_modulo='" + (i + 1) + "';";
                result = sentencia.executeQuery(contarProfesores);
                while (result.next()) {
                    tamanio += result.getInt(1);
                }
            }

            nombresProfesores = new String[tamanio];
            cont=0;
            //Recogida en array de nombres de profesores que imparten dichos modulos.
            for (int i = 0; i < idsModulo.length; i++) {
                nombreProfesor = "SELECT nombre FROM profesor WHERE Codigo_modulo='" + (i + 1) + "';";
                result = sentencia.executeQuery(nombreProfesor);                
                while (result.next()) {
                    nombresProfesores[cont] = result.getString(1);
                    cont++;
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }

        //devuelvo el array de cadenas con el nombre del modulo y los alumnos que tiene
        return nombresProfesores;
    }
}
