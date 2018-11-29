
package Main;

import Ejercicios.CreacionEsquema;
import Ejercicios.InsertarDatosAlumnos;

/**
 *
 * @author loboz
 */
public class Main {
    public static void main(String[] args){
        String url="jdbc:mysql://localhost/instituto";
        String user="root";
        String pass="root";
        
        InsertarDatosAlumnos polla1 = new InsertarDatosAlumnos();
        polla1.Ejercicio2_Alumnos(url, user, pass);
    }
}
