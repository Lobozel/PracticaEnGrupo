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
    }
    
}
