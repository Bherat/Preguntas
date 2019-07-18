/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/**
 *
 * @author foo
 */
public class Temporalizador {
    private int tiempoSegundos;
    private int tiempoSegundosTemporal;

    public Temporalizador() {
    }

    public Temporalizador(int tiempoSegundos) {
        this.tiempoSegundos = tiempoSegundos;
        this.tiempoSegundosTemporal = tiempoSegundos;
    }
    
    public int getTiempoSegundos() {
        return this.tiempoSegundos;
    }

    public void setTiempoSegundos(int tiempoSegundos) {
        this.tiempoSegundos = tiempoSegundos;
    }
    
    public int getTiempoSegTempo() {
        return this.tiempoSegundosTemporal;
    }
    
}
