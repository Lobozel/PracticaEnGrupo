/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Ejercicios.Actualizaciones.EliminarAlumno;
import Ejercicios.Actualizaciones.EliminarModulo;
import Ejercicios.Actualizaciones.ModificacionDatosAlumno;
import Ejercicios.Actualizaciones.ModificacionNotaAlumno;
import Ejercicios.Consultas.ConsultaAlumnos;
import Ejercicios.Consultas.ConsultaNotas;
import Ejercicios.Consultas.ConsultaProfesores;
import Ejercicios.CrearEsquema;
import Ejercicios.InsertarDatos.InsertarDatosAlumnos;
import Ejercicios.InsertarDatos.InsertarDatosModulos;
import Ejercicios.InsertarDatos.InsertarDatosProfesores;
import Ejercicios.ModificarTablaModuloAlumno;
import Ejercicios.Procedimientos.InsertarProcedimientoAltaAlumnos;
import Ejercicios.Procedimientos.InsertarProcedimientoMatricularAlumnos;
import Intefaz.Ventana;
import Logica.Auxiliar.Contar;
import Logica.Auxiliar.Nombres;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author loboz
 */
public class ControlActionListener implements ActionListener {

    Ventana v;
    String url = "jdbc:mysql://localhost/instituto";
    String user = "root";
    String pass = "root";
    //Objetos sobre los cuales llamaré a los distintos métodos necesarios
    CrearEsquema esquema = new CrearEsquema();
    InsertarDatosAlumnos datosAlumnos = new InsertarDatosAlumnos();
    InsertarDatosModulos datosModulos = new InsertarDatosModulos();
    InsertarDatosProfesores datosProfesores = new InsertarDatosProfesores();
    InsertarProcedimientoAltaAlumnos altaAlumnos = new InsertarProcedimientoAltaAlumnos();
    InsertarProcedimientoMatricularAlumnos matricularAlumnos = new InsertarProcedimientoMatricularAlumnos();
    ConsultaAlumnos alumnos = new ConsultaAlumnos();
    ConsultaNotas notas = new ConsultaNotas();
    ConsultaProfesores profesores = new ConsultaProfesores();
    EliminarAlumno eliminarAlumno = new EliminarAlumno();
    EliminarModulo eliminarModulo = new EliminarModulo();
    ModificacionDatosAlumno modDatosAlumno = new ModificacionDatosAlumno();
    ModificacionNotaAlumno modNotaAlumno = new ModificacionNotaAlumno();
    ModificarTablaModuloAlumno modificarTabla = new ModificarTablaModuloAlumno();
    Contar contar = new Contar(url,user,pass);
    Nombres nombres = new Nombres(url,user,pass);

    public ControlActionListener(Ventana v) {
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
         * Moverse entre las ventanas
         */
        if (e.getSource() == v.insertarDatos || e.getSource() == v.Alumnos
                || e.getSource() == v.Modulos || e.getSource() == v.Profesores
                || e.getSource() == v.Procedimientos || e.getSource() == v.verConsultas
                || e.getSource() == v.ActualizacionesBorrado) {
            //Ocultolas ventanas
            v.IntroducirDatos.setVisible(false);
            v.IntroducirAlumnos.setVisible(false);
            v.IntroducirModulos.setVisible(false);
            v.IntroducirProfesores.setVisible(false);
            v.ProcedimientosAlmacenados.setVisible(false);
            v.consultas.setVisible(false);
            v.ActualizarBorrar.setVisible(false);
        }

        //Ir a la ventana de Introducir Datos
        if (e.getSource() == v.insertarDatos) {
            v.IntroducirDatos.setVisible(true);
        }

        //Ir a la ventana de Introducir Alumnos
        if (e.getSource() == v.Alumnos) {
            v.IntroducirAlumnos.setVisible(true);
        }

        //Ir a la ventana de Introducir Modulos
        if (e.getSource() == v.Modulos) {
            v.IntroducirModulos.setVisible(true);
        }

        //Ir a la ventana de Introducir Profesores
        if (e.getSource() == v.Profesores) {
            v.IntroducirProfesores.setVisible(true);
        }

        //Ir a la ventana de Procedimientos Almacenados
        if (e.getSource() == v.Procedimientos) {
            v.ProcedimientosAlmacenados.setVisible(true);
            //Genero las listas desplegables
            String[] alumnos=nombres.listaNombresAlumnos();
            String[] modulos=nombres.listaNombresModulos();
            v.listaAlumnos.removeAllItems();
            for (String alumno : alumnos) {
                v.listaAlumnos.addItem(alumno);
            }
            v.listaModulos.removeAllItems();
            for (String modulo : modulos) {
                v.listaModulos.addItem(modulo);
            }
        }

        //Ir a la ventana de Consultas
        if (e.getSource() == v.verConsultas) {
            v.consultas.setVisible(true);
            //Genero las listas desplegables
            String[] alumnos=nombres.listaNombresAlumnos();
            String[] modulos=nombres.listaNombresModulos();
            v.notasModulo.removeAllItems();
            for (String modulo : modulos) {
                v.notasModulo.addItem(modulo);
            }
            v.alumnoModulo.removeAllItems();
            for (String modulo : modulos) {
                v.alumnoModulo.addItem(modulo);
            }
            v.profesorAlumno.removeAllItems();
            for (String alumno : alumnos) {
                v.profesorAlumno.addItem(alumno);
            }
        }

        //Ir a la ventana de Actualizaciones o Borrado
        if (e.getSource() == v.ActualizacionesBorrado) {
            v.ActualizarBorrar.setVisible(true);
            //Genero las listas desplegables
            String[] alumnos=nombres.listaNombresAlumnos();
            String[] modulos=nombres.listaNombresModulos();
            v.alumnoMB.removeAllItems();
            for (String alumno : alumnos) {
                v.alumnoMB.addItem(alumno);
            }
            v.moduloMB.removeAllItems();
            for (String modulo : modulos) {
                v.moduloMB.addItem(modulo);
            }
        }
        //----------------------------------------------------------------------
        /*
         * Crear Esquema
         */
        if(e.getSource()==v.crearEsquema){
            esquema.Ejercicio1(url, user, pass);
        }
        //----------------------------------------------------------------------
        /*
         * Insertar Datos
         */
        //Introducir alumnos
        if(e.getSource()==v.introAlumnos){
            //Compruebo si ya se ha introducido o no los alumnos
            int comprobar=contar.contarAlumnos();
            if(comprobar==0){                
                datosAlumnos.Ejercicio2_Alumnos(url, user, pass);
            }else{
                JOptionPane.showMessageDialog(v, "Los datos ya éxisten", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Introducir modulos
        if(e.getSource()==v.introModulos){
            //Compruebo si ya se ha introducido o no los modulos
            int comprobar=contar.contarModulos();
            if(comprobar==0){                
                datosModulos.Ejercicio2_Modulos(url, user, pass);
            }else{
                JOptionPane.showMessageDialog(v, "Los datos ya éxisten", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Introducir profesores
        if(e.getSource()==v.introProfesores){
            //Compruebo si ya se ha introducido o no los modulos y los profesores
            int comprobarM=contar.contarModulos();
            int comprobarP=contar.contarProfesores();
            if(comprobarM==0){                
                JOptionPane.showMessageDialog(v, "Primero es necesario introducir los datos de los Modulos.", "Error", JOptionPane.ERROR_MESSAGE);
            }else if (comprobarP==0){
                datosProfesores.Ejercicio2_Profesores(url, user, pass);
            }else{
                JOptionPane.showMessageDialog(v, "Los datos ya éxisten", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //----------------------------------------------------------------------
        /*
         * Insertar Procedimientos
         */
        if(e.getSource()==v.insertarAltaAlumnos){
            //Compruebo si ya se ha introducido o no los alumnos
            int comprobar=contar.contarAlumnos();
            if(comprobar==0){        
                JOptionPane.showMessageDialog(v, "Es necesario introducir primero los datos de los Alumnos", "Error", JOptionPane.ERROR_MESSAGE);                        
            }else{
                altaAlumnos.Ejercicio2_insertarAltaAlumnos(url, user, pass);
            }
        }
        if(e.getSource()==v.insertarMatriculaAlumnos){
            //Compruebo si ya se ha introducido o no los alumnos y de los modulos
            int comprobarA=contar.contarAlumnos();
            int comprobarM=contar.contarModulos();
            if(comprobarA==0 || comprobarM==0){        
                JOptionPane.showMessageDialog(v, "Es necesario introducir primero los datos de los Alumnos\n"
                        + "y los datos de los Modulos", "Error", JOptionPane.ERROR_MESSAGE);                        
            }else{
                matricularAlumnos.Ejercicio2_insertarMatricularAlumnos(url, user, pass);
            }
        }
        //----------------------------------------------------------------------
        /*
         * Ejecutar Procedimientos
         */
        if(e.getSource()==v.altaAlumnos){
            
        }
        if(e.getSource()==v.matricular){
            
        }
        //----------------------------------------------------------------------
        /*
         * Modificar Tabla Modulo_Alumno
         */
        if(e.getSource()==v.modModulo_Alumno){
            
        }
        //----------------------------------------------------------------------
        /*
         * Ejecutar Consultas
         */
        if(e.getSource()==v.listarNotas){
            
        }
        if(e.getSource()==v.listarProfesores){
            
        }
        if(e.getSource()==v.listarAlumnos){
            
        }
        //----------------------------------------------------------------------
        /*
         * Actualizar Datos
         */
        if(e.getSource()==v.modAlumno){
            
        }
        if(e.getSource()==v.modModulo){
            
        }
        //----------------------------------------------------------------------
        /*
         * Borrar Datos
         */
        if(e.getSource()==v.eliminarAlumno){
            
        }
        if(e.getSource()==v.eliminarModulo){
            
        }
        //----------------------------------------------------------------------
    }
}

