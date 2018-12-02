
package Main;

import Ejercicios.Consultas.CreacionEsquema;
import Ejercicios.InsertarDatos.InsertarDatosAlumnos;
import Ejercicios.Procedimientos.InsertarProcedimientoAltaAlumnos;
import Ejercicios.Procedimientos.InsertarProcedimientoMatricularAlumnos;
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
        
        String[] prueba = new String[contar.contarAlumnos()];;
        
        prueba=nombres.listaNombresAlumnos();
        
        for(int i=0;i<prueba.length;i++){
            System.out.println(prueba[i]);
        }
        
        //Contar polla1 = new Contar();
        //polla1.contarAlumnos(url, user, pass);
    }
}
