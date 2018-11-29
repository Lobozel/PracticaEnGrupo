/*
 * Modificar la tabla Modulo_alumno para que incluya un nuevo campo,
 * notaFinal, y así poder almacenar la nota que ha obtenido en cada
 * módulo.
 */
package Ejercicios;

/**
 *
 * @author MiguelAngel & Sergey
 */
public class ModificarTablaModuloAlumno {
    //Sentencias SQL que usará esta clase:    
    String modificarModuloAlumno=
            "ALTER TABLE `instituto`.`modulo_alumno`" +
"ADD COLUMN `notaFinal` INT NULL AFTER `Codigo_modulo`;";
    /*
    * Este método añade un nuevo campo a la tabla Modulo_Alumnos
    * Se le pasa como parámetros los datos de MySQL necesarios para la conexión.
    */
    public void Ejercicio3(String url, String user, String pass){
        
    } 
}
