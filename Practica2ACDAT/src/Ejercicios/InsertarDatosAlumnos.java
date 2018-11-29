/*
 * Insertar un mínimo de 4 alumnos y 2 alumnos en cada módulo.
 */
package Ejercicios;

/**
 *
 * @author MiguelAngel & Sergey
 */
public class InsertarDatosAlumnos {
    //Sentencias SQL que usará esta clase:    
    String insertarAlumnos=
            "INSERT INTO `instituto`.`alumno` (`Expediente`, `Nombre`, `ApellidoP`, `ApellidoM`, `FechaNac`,`Delegado`) VALUES ('1', 'Miguel Ángel', 'López', 'Sánchez', '1996-09-06',_binary 'false\\0\\0\\0\\0\\0');" +
"INSERT INTO `instituto`.`alumno` (`Expediente`, `Nombre`, `ApellidoP`, `FechaNac`,`Delegado`) VALUES ('2', 'Sergey', 'Shevchenko', '1996-02-15',_binary 'false\\0\\0\\0\\0\\0');" +
"INSERT INTO `instituto`.`alumno` (`Expediente`, `Nombre`, `ApellidoP`, `ApellidoM`, `FechaNac`,`Delegado`) VALUES ('3', 'Julian', 'Rodrigo', 'Jiménez', '1992-01-22',_binary 'false\\0\\0\\0\\0\\0');" +
"INSERT INTO `instituto`.`alumno` (`Expediente`, `Nombre`, `ApellidoP`, `ApellidoM`, `FechaNac`,`Delegado`) VALUES ('4', 'Juan', 'González', 'Martín', '1998-05-05',_binary 'false\\0\\0\\0\\0\\0');;";
    /*
    * Este método inserta datos en la tabla Alumnos
    * Se le pasa como parámetros los datos de MySQL necesarios para la conexión.
    */
    public void Ejercicio2_Alumnos(String url, String user, String pass){
        
    } 
}
