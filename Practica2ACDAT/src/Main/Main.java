
package Main;

import Ejercicios.CrearEsquema;
import Ejercicios.InsertarDatos.InsertarDatosAlumnos;
import Ejercicios.Procedimientos.ProcedimientoAltaAlumnos;
import Ejercicios.Procedimientos.ProcedimientoMatricularAlumnos;
import Logica.Auxiliar.Contar;
import Logica.Auxiliar.Nombres;

/**
 *
 * @author loboz
 */
public class Main {
    public static void main(String[] args){
        String url="jdbc:mysql://localhost/instituto";
        String user="root";
        String pass="root";
        
        Contar contar = new Contar(url,user,pass);
        Nombres nombres = new Nombres(url,user,pass);
        ProcedimientoAltaAlumnos altaAlumnos = new ProcedimientoAltaAlumnos();
        altaAlumnos.Ejercicio2_ejecutaraltaAlumnos(url, user, pass);
    }
}
