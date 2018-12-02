package Ejercicios.Consultas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MiguelAngel & Sergey
 */
public class ConsultaNotas {

    Connection conexion = null;
    Statement sentencia = null;

    public ConsultaNotas(String url, String user, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(url, user, pass);
            sentencia = conexion.createStatement();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public String[] notasAlumno(int id) {
        String numAlumnos = "SELECT count(Codigo_alumno) FROM modulo_alumno WHERE Codigo_modulo='" + id + "';";
        String alumnosEnModulo = "SELECT Codigo_alumno FROM modulo_alumno WHERE Codigo_modulo='" + id + "';";
        int[] idsAlumnos, notasAlumnos;
        String ConsultNombresAlumnos = "";
        String ConsultNotasAlumnos = "";
        String[] nombresAlumnos;
        String[] resultado = null;
        int tamanio = 0, cont = 0;
        ResultSet result = null;

        try {
            result = sentencia.executeQuery(numAlumnos);
            while (result.next()) {
                tamanio = result.getInt(1);
            }

            idsAlumnos = new int[tamanio];
            notasAlumnos = new int[tamanio];
            resultado = new String[tamanio];
            nombresAlumnos = new String[tamanio];

            result = sentencia.executeQuery(alumnosEnModulo);
            while (result.next()) {
                idsAlumnos[cont] = result.getInt(1);
                cont++;
            }
            
            for(int i=0;i<idsAlumnos.length;i++){
                ConsultNombresAlumnos = "SELECT nombre FROM alumno WHERE Expediente='"+(i+1)+"';";
                result = sentencia.executeQuery(ConsultNombresAlumnos);
                while (result.next()) {
                    nombresAlumnos[i] = result.getString(1);
                }
            }
            
            for(int i=0;i<notasAlumnos.length;i++){
                ConsultNotasAlumnos = "SELECT notaFinal FROM modulo_alumno WHERE Codigo_alumno='"+(i+1)+"' && Codigo_modulo='"+id+"';";
                result = sentencia.executeQuery(ConsultNotasAlumnos);
                while (result.next()) {
                    notasAlumnos[i] = result.getInt(1);
                }
            }
            
            for(int i=0;i<resultado.length;i++){
                resultado[i]=nombresAlumnos[i]+": "+notasAlumnos[i];
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }

        //devuelvo el array de cadenas con
        return resultado;
    }
}
