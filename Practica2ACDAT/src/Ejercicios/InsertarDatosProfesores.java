/*
 * Insertar un mínimo de 3 profesores, donde dos de ellos compartirán módulo.
 */
package Ejercicios;

/**
 *
 * @author MiguelAngel & Sergey
 */
public class InsertarDatosProfesores {
    //Sentencias SQL que usará esta clase:    
    String insertarProfesores=
            "INSERT INTO `instituto`.`profesor` (`RFC`, `Nombre`, `ApellidoP`, `ApellidoM`, `Direccion`, `Telefono`, `Codigo_modulo`) VALUES ('1', 'Francisco Javier', 'Fernández-Arévalo', 'Collado', 'Almería', '654983275', '2');" +
"INSERT INTO `instituto`.`profesor` (`RFC`, `Nombre`, `ApellidoP`, `ApellidoM`, `Direccion`, `Telefono`, `Codigo_modulo`) VALUES ('2', 'Juan Miguel', 'Jiménez', 'Lechuga', 'Almería', '695843527', '2');" +
"INSERT INTO `instituto`.`profesor` (`RFC`, `Nombre`, `ApellidoP`, `ApellidoM`, `Direccion`, `Telefono`, `Codigo_modulo`) VALUES ('3', 'Francisco', 'Fernández de Piñar', 'López', 'Almería', '688425965', '1');";
    /*
    * Este método inserta datos en la tabla Profesores
    * Se le pasa como parámetros los datos de MySQL necesarios para la conexión.
    */
    public void Ejercicio2_Profesores(String url, String user, String pass){
        
    } 
}
