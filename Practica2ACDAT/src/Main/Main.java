
package Main;

import Ejercicios.Consultas.CreacionEsquema;
import Ejercicios.InsertarDatos.InsertarDatosAlumnos;
import Ejercicios.Procedimientos.InsertarProcedimientoAltaAlumnos;
import Ejercicios.Procedimientos.InsertarProcedimientoMatricularAlumnos;

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
