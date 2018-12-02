/*
 * Creación del esquema de la base de datos. Crea las tablas con
 * sus claves primarias, ajenas, y tipo de datos indicado en la
 * imagen. Las opciones de Borrado son restrictivas en todos los
 * casos, excepto cuando se vaya a eliminar un alumno, que se
 * eliminará toda la información asociada. Las modificaciones se
 * realizarán en todos los casos en cascada.
 */
package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author MiguelAngel & Sergey
 */
public class CrearEsquema {
    //Sentencias SQL que usará esta clase:    
    String tablaModulo=
            "CREATE TABLE `instituto`.`modulo` (" +
            "  `Codigo` INT NOT NULL," +
            "  `Nombre` VARCHAR(45) NULL," +
            "  PRIMARY KEY (`Codigo`));";
    String tablaProfesor=
            "CREATE TABLE `instituto`.`profesor` (" +
            "  `RFC` CHAR(15) NOT NULL," +
            "  `Nombre` VARCHAR(25) NULL," +
            "  `ApellidoP` VARCHAR(25) NULL," +
            "  `ApellidoM` VARCHAR(25) NULL," +
            "  `Direccion` VARCHAR(25) NULL," +
            "  `Telefono` CHAR(10) NULL," +
            "  `Codigo_modulo` INT NULL," +
            "  PRIMARY KEY (`RFC`)," +
            "  UNIQUE INDEX `RFC_UNIQUE` (`RFC` ASC) VISIBLE," +
            "  INDEX `imparte_idx` (`Codigo_modulo` ASC) VISIBLE," +
            "  CONSTRAINT `imparte`" +
            "    FOREIGN KEY (`Codigo_modulo`)" +
            "    REFERENCES `instituto`.`modulo` (`Codigo`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE RESTRICT);";
    String tablaAlumno=
            "CREATE TABLE `instituto`.`alumno` (" +
            "  `Expediente` INT NOT NULL," +
            "  `Nombre` VARCHAR(25) NULL," +
            "  `ApellidoP` VARCHAR(25) NULL," +
            "  `ApellidoM` VARCHAR(25) NULL," +
            "  `FechaNac` DATE NULL," +
            "  `Delegado` BINARY(10) NULL," +
            "  PRIMARY KEY (`Expediente`)," +
            "  UNIQUE INDEX `Expediente_UNIQUE` (`Expediente` ASC) VISIBLE);";
    String tablaModulo_Alumno=
            "CREATE TABLE `instituto`.`modulo_alumno` (" +
            "  `idModulo_Alumno` INT NOT NULL," +
            "  `Codigo_alumno` INT NULL," +
            "  `Codigo_modulo` INT NULL," +
            "  PRIMARY KEY (`idModulo_Alumno`)," +
            "  INDEX `matriculado_m_idx` (`Codigo_alumno` ASC) VISIBLE," +
            "  INDEX `matriculado_a_idx` (`Codigo_modulo` ASC) VISIBLE," +
            "  CONSTRAINT `matriculado_m`" +
            "    FOREIGN KEY (`Codigo_alumno`)" +
            "    REFERENCES `instituto`.`alumno` (`Expediente`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE CASCADE," +
            "  CONSTRAINT `matriculado_a`" +
            "    FOREIGN KEY (`Codigo_modulo`)" +
            "    REFERENCES `instituto`.`modulo` (`Codigo`)" +
            "    ON DELETE RESTRICT" +
            "    ON UPDATE CASCADE);";
    
    /*
    * Este método realiza la creación del esquema de la base de datos.
    * Se le pasa como parámetros los datos de MySQL necesarios para la conexión.
    */
    public void Ejercicio1(String url, String user, String pass){
        Connection conexion=null;
        Statement sentencia=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection(url,user,pass);
            sentencia=conexion.createStatement();
            sentencia.execute(tablaModulo);
            sentencia.execute(tablaProfesor);
            sentencia.execute(tablaAlumno);
            sentencia.execute(tablaModulo_Alumno);
            JOptionPane.showMessageDialog(null, "Creación del Esquema de la BD realizado con éxito");
            return;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getCause());
        }
        JOptionPane.showMessageDialog(null, "El esquema ya está creado.","Error",JOptionPane.WARNING_MESSAGE);
    }    
}
