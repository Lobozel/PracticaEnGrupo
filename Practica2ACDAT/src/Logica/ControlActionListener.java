/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Ejercicios.ModificarTablaModuloAlumno;
import Intefaz.Ventana;
import Logica.Auxiliar.Contar;
import Logica.Auxiliar.Nombres;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            
        }
        //----------------------------------------------------------------------
        /*
         * Insertar Datos
         */
        //Introducir alumnos
        if(e.getSource()==v.introAlumnos){
            
        }
        //Introducir modulos
        if(e.getSource()==v.introModulos){
            
        }
        //Introducir profesores
        if(e.getSource()==v.introProfesores){
            
        }
        //----------------------------------------------------------------------
        /*
         * Insertar Procedimientos
         */
        if(e.getSource()==v.insertarAltaAlumnos){
            
        }
        if(e.getSource()==v.insertarMatriculaAlumnos){
            
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
