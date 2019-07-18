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
public class Proceso extends Thread {
    
    Inicio ini;
    
    public Proceso(String nombre) {
        super(nombre);
    }
    
    @Override
    public void run() {
        
        NuevaPregunta nuePreg = new NuevaPregunta();
        ExplayarRespuesta expResp = new ExplayarRespuesta();
        Jugar jugar = new Jugar();
        VentanaVF vf = new VentanaVF();
        
        Eventos events = new Eventos(ini, nuePreg, expResp, jugar, vf);
    }
    
    
    public void setInicio(Inicio ini) {
        this.ini = ini;
    }
    
    
}
