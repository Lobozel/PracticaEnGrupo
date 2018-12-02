/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Intefaz.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author loboz
 */
public class ControlActionListener implements ActionListener{

    Ventana v;
    
    public ControlActionListener(Ventana v){
        this.v=v;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        * Moverse entre las ventanas
        */
        if(e.getSource()==v.insertarDatos || e.getSource()==v.Alumnos ||
                 e.getSource()==v.Modulos || e.getSource()==v.Profesores ||
                e.getSource()==v.Procedimientos || e.getSource()==v.verConsultas ||
                e.getSource()==v.ActualizacionesBorrado){
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
        if(e.getSource()==v.insertarDatos){
            //Muestro la ventana que me interesa
            v.IntroducirDatos.setVisible(true);
        }
        
        //Ir a la ventana de Introducir Alumnos
        if(e.getSource()==v.Alumnos){
            //Muestro la ventana que me interesa
            v.IntroducirAlumnos.setVisible(true);
        }
        
        //Ir a la ventana de Introducir Modulos
        if(e.getSource()==v.Modulos){
            //Muestro la ventana que me interesa
            v.IntroducirModulos.setVisible(true);
        }
        
        //Ir a la ventana de Introducir Profesores
        if(e.getSource()==v.Profesores){
            //Muestro la ventana que me interesa
            v.IntroducirProfesores.setVisible(true);
        }
        
        //Ir a la ventana de Procedimientos Almacenados
        if(e.getSource()==v.Procedimientos){
            //Muestro la ventana que me interesa
            v.ProcedimientosAlmacenados.setVisible(true);
        }
        
        //Ir a la ventana de Consultas
        if(e.getSource()==v.verConsultas){
            //Muestro la ventana que me interesa
            v.consultas.setVisible(true);
        }
        
        //Ir a la ventana de Actualizaciones o Borrado
        if(e.getSource()==v.ActualizacionesBorrado){
            //Muestro la ventana que me interesa
            v.ActualizarBorrar.setVisible(true);
        }
        
        //----------------------------------------------------------------------
        
    }
    
}
