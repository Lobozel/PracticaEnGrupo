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
public class ConsultaAlumnos {

    Connection conexion = null;
    Statement sentencia = null;

    public ConsultaAlumnos(String url, String user, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(url, user, pass);
            sentencia = conexion.createStatement();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public String[] alumnosPorModulo(String[] nombres, int c) {
        int cantidad = c;
        String[] modulos = nombres;
        int[] alumnosEnModulo = new int[cantidad];
        ResultSet result = null;

        for (int i = 0; i < alumnosEnModulo.length; i++) {
            String alumnosPorModulo = "SELECT count(*) FROM modulo_alumno WHERE Codigo_modulo='" + (i + 1) + "';";
            try {
                result = sentencia.executeQuery(alumnosPorModulo);
                while (result.next()) {
                    alumnosEnModulo[i] = result.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getCause());
            }
        }
        
        //ordeno los arrays
        BurbujaMejorado(alumnosEnModulo, modulos);
        
        //Junto los arrays
        for(int i=0;i<modulos.length;i++){
            modulos[i]=modulos[i]+" tiene "+alumnosEnModulo[i]+" alumnos.";
        }

        //devuelvo el array de cadenas con el nombre del modulo y los alumnos que tiene
        return modulos;
    }

    public static void BurbujaMejorado(int[] a, String[] s) {
        int aux;
        String auxS = "";
        boolean fin = true;
        for (int i = a.length; i > 0 && fin; i--) {
            fin = false;
            for (int j = 0; j < i - 1; j++) {
                if (a[j + 1] < a[j]) {
                    aux = a[j + 1];
                    auxS = s[j + 1];
                    a[j + 1] = a[j];
                    s[j + 1] = s[j];
                    a[j] = aux;
                    s[j] = auxS;
                    fin = true;
                }
            }
        }
    }
}
