/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.ExplayarRespuesta;
import View.Inicio;
import View.Jugar;
import View.NuevaPregunta;
import View.VentanaVF;


/**
 *
 * @author foo
 */
public class Main {
        
    public static void main(String[] args) {
        
        Inicio inicio = new Inicio();
        
        Proceso pro = new Proceso("thread");
        
        
        pro.setInicio(inicio);
        pro.start();
        
        inicio.setVisible(true);
        
    }
    
}
