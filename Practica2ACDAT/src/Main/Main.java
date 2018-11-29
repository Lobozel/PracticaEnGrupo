
package Main;

import Ejercicios.CreacionEsquema;
import Ejercicios.InsertarDatosAlumnos;
import Ejercicios.InsertarProcedimientoAltaAlumnos;
import Ejercicios.InsertarProcedimientoMatricularAlumnos;

/**
 *
 * @author loboz
 */
public class Main {
    public static void main(String[] args){
        String url="jdbc:mysql://localhost/instituto";
        String user="root";
        String pass="root";
        
        InsertarProcedimientoMatricularAlumnos polla1 = new InsertarProcedimientoMatricularAlumnos();
        polla1.Ejercicio2_ejecutarMatricularAlumnos(url, user, pass);
    }
}
