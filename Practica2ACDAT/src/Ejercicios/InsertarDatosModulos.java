/*
 * Insertar al menos 2 módulos: ACDAT y PSP
 */
package Ejercicios;

/**
 *
 * @author MiguelAngel & Sergey
 */
public class InsertarDatosModulos {
    //Sentencias SQL que usará esta clase:    
    String insertarModulos=
            "INSERT INTO `instituto`.`modulo` (`Codigo`, `Nombre`) VALUES ('1', 'ACDAT');" +
"INSERT INTO `instituto`.`modulo` (`Codigo`, `Nombre`) VALUES ('2', 'PSP');";
    /*
    * Este método inserta datos en la tabla Modulos
    * Se le pasa como parámetros los datos de MySQL necesarios para la conexión.
    */
    public void Ejercicio2_Modulos(String url, String user, String pass){
        
    } 
}
