/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Ejercicios.Actualizaciones.EliminarDatos;
import Ejercicios.Actualizaciones.Modificaciones;
import Ejercicios.Consultas.ConsultaAlumnos;
import Ejercicios.Consultas.ConsultaNotas;
import Ejercicios.Consultas.ConsultaProfesores;
import Ejercicios.CrearEsquema;
import Ejercicios.InsertarDatos.InsertarDatosAlumnos;
import Ejercicios.InsertarDatos.InsertarDatosModulos;
import Ejercicios.InsertarDatos.InsertarDatosProfesores;
import Ejercicios.ModificarTablaModuloAlumno;
import Ejercicios.Procedimientos.ProcedimientoAltaAlumnos;
import Ejercicios.Procedimientos.ProcedimientoMatricularAlumnos;
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
    ProcedimientoAltaAlumnos altaAlumnos = new ProcedimientoAltaAlumnos();
    ProcedimientoMatricularAlumnos matricularAlumnos = new ProcedimientoMatricularAlumnos();
    ConsultaAlumnos alumnos = new ConsultaAlumnos(url, user, pass);
    ConsultaNotas notas = new ConsultaNotas();
    ConsultaProfesores profesores = new ConsultaProfesores();
    EliminarDatos eliminar = new EliminarDatos();
    Modificaciones modificar = new Modificaciones();
    ModificarTablaModuloAlumno modificarTabla = new ModificarTablaModuloAlumno();
    Contar contar = new Contar(url, user, pass);
    Nombres nombres = new Nombres(url, user, pass);

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
            String[] alumnos = nombres.listaNombresAlumnos();
            String[] modulos = nombres.listaNombresModulos();
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
            String[] alumnos = nombres.listaNombresAlumnos();
            String[] modulos = nombres.listaNombresModulos();
            v.notasModulo.removeAllItems();
            for (String modulo : modulos) {
                v.notasModulo.addItem(modulo);
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
            String[] alumnos = nombres.listaNombresAlumnos();
            String[] modulos = nombres.listaNombresModulos();
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
        if (e.getSource() == v.crearEsquema) {
            esquema.Ejercicio1(url, user, pass);
        }
        //----------------------------------------------------------------------
        /*
         * Insertar Datos
         */
        //Introducir alumnos
        if (e.getSource() == v.introAlumnos) {
            //Compruebo si ya se ha introducido o no los alumnos
            int comprobar = contar.contarAlumnos();
            if (comprobar == 0) {
                datosAlumnos.Ejercicio2_Alumnos(url, user, pass);
            } else {
                JOptionPane.showMessageDialog(v, "Los datos ya éxisten", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Introducir modulos
        if (e.getSource() == v.introModulos) {
            //Compruebo si ya se ha introducido o no los modulos
            int comprobar = contar.contarModulos();
            if (comprobar == 0) {
                datosModulos.Ejercicio2_Modulos(url, user, pass);
            } else {
                JOptionPane.showMessageDialog(v, "Los datos ya éxisten", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Introducir profesores
        if (e.getSource() == v.introProfesores) {
            //Compruebo si ya se ha introducido o no los modulos y los profesores
            int comprobarM = contar.contarModulos();
            int comprobarP = contar.contarProfesores();
            if (comprobarM == 0) {
                JOptionPane.showMessageDialog(v, "Primero es necesario introducir los datos de los Modulos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (comprobarP == 0) {
                datosProfesores.Ejercicio2_Profesores(url, user, pass);
            } else {
                JOptionPane.showMessageDialog(v, "Los datos ya éxisten", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //----------------------------------------------------------------------
        /*
         * Insertar Procedimientos
         */
        if (e.getSource() == v.insertarAltaAlumnos) {
            //Compruebo si ya se ha introducido o no los alumnos
            int comprobar = contar.contarAlumnos();
            if (comprobar == 0) {
                JOptionPane.showMessageDialog(v, "Es necesario introducir primero los datos de los Alumnos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                altaAlumnos.Ejercicio2_insertarAltaAlumnos(url, user, pass);
            }
        }
        if (e.getSource() == v.insertarMatriculaAlumnos) {
            //Compruebo si ya se ha introducido o no los alumnos y de los modulos
            int comprobarA = contar.contarAlumnos();
            int comprobarM = contar.contarModulos();
            if (comprobarA == 0 || comprobarM == 0) {
                JOptionPane.showMessageDialog(v, "Es necesario introducir primero los datos de los Alumnos\n"
                        + "y los datos de los Modulos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                matricularAlumnos.Ejercicio2_insertarMatricularAlumnos(url, user, pass);
            }
        }
        //----------------------------------------------------------------------
        /*
         * Ejecutar Procedimientos
         */
        if (e.getSource() == v.altaAlumnos) {
            //Compruebo si ya se ha introducido o no los alumnos
            int comprobar = contar.contarAlumnos();
            if (comprobar == 0) {
                JOptionPane.showMessageDialog(v, "Es necesario introducir primero los datos de los Alumnos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int resultado = altaAlumnos.Ejercicio2_ejecutaraltaAlumnos(url, user, pass);
                if (resultado == -1) {
                    JOptionPane.showMessageDialog(v, "Es necesario insertar primero el Procedimiento.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    v.RespuestaAltaAlumnos.setText(resultado + " alumnos introducidos.");
                }
            }
        }
        if (e.getSource() == v.matricular) {
            //Compruebo si ya se ha introducido o no los alumnos y de los modulos
            int comprobarA = contar.contarAlumnos();
            int comprobarM = contar.contarModulos();
            if (comprobarA == 0 || comprobarM == 0) {
                JOptionPane.showMessageDialog(v, "Es necesario introducir primero los datos de los Alumnos\n"
                        + "y los datos de los Modulos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int modulo = v.listaModulos.getSelectedIndex() + 1;
                int alumno = v.listaAlumnos.getSelectedIndex() + 1;
                int resultado = matricularAlumnos.Ejercicio2_ejecutarMatricularAlumnos(url, user, pass, modulo, alumno);
                if (resultado == -1) {
                    JOptionPane.showMessageDialog(v, "Es necesario insertar primero el Procedimiento.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (resultado == 0) {
                    JOptionPane.showMessageDialog(v, "No se ha podido matricular, el módulo está lleno.", "Modulo lleno", JOptionPane.WARNING_MESSAGE);
                } else if (resultado == 1) {
                    JOptionPane.showMessageDialog(v, "El alumno se ha matriculado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        //----------------------------------------------------------------------
        /*
         * Modificar Tabla Modulo_Alumno
         */
        if (e.getSource() == v.modModulo_Alumno) {
            modificarTabla.Ejercicio3(url, user, pass);
        }
        //----------------------------------------------------------------------
        /*
         * Ejecutar Consultas
         */
        if (e.getSource() == v.listarNotas) {

        }
        if (e.getSource() == v.listarProfesores) {

        }
        if (e.getSource() == v.listarAlumnos) {
            //Compruebo si ya se ha introducido o no los alumnos y de los modulos
            int comprobarA = contar.contarAlumnos();
            int comprobarM = contar.contarModulos();
            if (comprobarA == 0 || comprobarM == 0) {
                JOptionPane.showMessageDialog(v, "Es necesario introducir primero los datos de los Alumnos\n"
                        + "y los datos de los Modulos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                String[] modulos = nombres.listaNombresModulos();
                int cantidad = contar.contarModulos();
                String[] resultado;
                resultado = alumnos.alumnosPorModulo(modulos, cantidad);
                v.RespuestaConsulta.setText(""); //Borro si hubiese algo
                //Pego la consulta en la caja de área de texto
                for (int i = 0; i < resultado.length; i++) {
                    v.RespuestaConsulta.append(resultado[i] + "\n");
                }
            }
        }
        //----------------------------------------------------------------------
        /*
         * Actualizar Datos
         */
        if (e.getSource() == v.modAlumno) {
            //Compruebo si ya se ha introducido o no los alumnos
            int comprobar = contar.contarAlumnos();
            if (comprobar == 0) {
                JOptionPane.showMessageDialog(v, "Es necesario introducir primero los datos de los Alumnos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (v.nombreAlumno.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(v, "Debes introducir un nombre nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String nombre = v.nombreAlumno.getText().trim();
                    String delegado = "";
                    int id = v.alumnoMB.getSelectedIndex() + 1;
                    if (v.condDelegado.getSelectedIndex() == 0) {
                        delegado = "_binary 'true\\0\\0\\0\\0\\0\\0'";
                    } else {
                        delegado = "_binary 'false\\0\\0\\0\\0\\0'";
                    }
                    modificar.datosAlumno(url, user, pass, nombre, delegado, id);
                    String[] alumnos = nombres.listaNombresAlumnos();
                    v.alumnoMB.removeAllItems();
                    for (String alumno : alumnos) {
                        v.alumnoMB.addItem(alumno);
                    }
                }
            }
        }
        if (e.getSource() == v.modModulo) {
            //Compruebo si ya se ha introducido o no los alumnos y de los modulos
            int comprobarA = contar.contarAlumnos();
            int comprobarM = contar.contarModulos();
            if (comprobarA == 0 || comprobarM == 0) {
                JOptionPane.showMessageDialog(v, "Es necesario introducir primero los datos de los Alumnos\n"
                        + "y los datos de los Modulos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (v.notaAlumno.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(v, "Debes introducir una nota nueva.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int idA = v.alumnoMB.getSelectedIndex() + 1;
                    int idM = v.moduloMB.getSelectedIndex() + 1;
                    /*Solo permito digitos en la caja de nota, por lo que puedo
                     convertirlo a int sin miedo*/
                    int nota = Integer.parseInt(v.notaAlumno.getText());
                    //valido que la nota sea válida
                    if (nota < 0 || nota > 10) {
                        JOptionPane.showMessageDialog(v, "Introduce una nota válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        modificar.notaAlumno(url, user, pass, idA, idM, nota);
                    }
                }
            }
        }
        //----------------------------------------------------------------------
        /*
         * Borrar Datos
         */
        if (e.getSource() == v.eliminarAlumno) {
            //Compruebo si ya se ha introducido o no los alumnos
            int comprobar = contar.contarAlumnos();
            if (comprobar == 0) {
                JOptionPane.showMessageDialog(v, "Es necesario introducir primero los datos de los Alumnos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int id = v.alumnoMB.getSelectedIndex() + 1;
                eliminar.eliminarAlumno(url, user, pass, id);
            }
        }
        if (e.getSource() == v.eliminarModulo) {
            //Compruebo si ya se ha introducido o no los modulos
            int comprobar = contar.contarModulos();
            if (comprobar == 0) {
                JOptionPane.showMessageDialog(v, "Es necesario introducir primero los datos de los Modulos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int id = v.moduloMB.getSelectedIndex() + 1;
                eliminar.eliminarModulo(url, user, pass, id);
            }
        }
        //----------------------------------------------------------------------
    }
}
