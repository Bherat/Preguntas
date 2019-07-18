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
public class Pregunta {
    private int id;
    private String pregunta;
    private String respuestaV;
    private String[] respuestaF;
    private String respuestaExplayada;

    public Pregunta() {
    }

    public Pregunta(int id, String pregunta, String respuestaV, String[] respuestaF, String respuestaExplayada) {
        this.id = id;
        this.pregunta = pregunta;
        this.respuestaV = respuestaV;
        this.respuestaF = respuestaF;
        this.respuestaExplayada = respuestaExplayada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestaV() {
        return respuestaV;
    }

    public void setRespuestaV(String respuestaV) {
        this.respuestaV = respuestaV;
    }

    public String[] getRespuestaF() {
        return respuestaF;
    }

    public void setRespuestaF(String[] respuestaF) {
        this.respuestaF = respuestaF;
    }

    public String getRespuestaExplayada() {
        return respuestaExplayada;
    }

    public void setRespuestaExplayada(String respuestaExplayada) {
        this.respuestaExplayada = respuestaExplayada;
    }

    @Override
    public String toString() {
        return "Pregunta{" + "id=" + id + ", pregunta=" + pregunta + ", respuestaV=" + respuestaV + ", respuestaF1=" + respuestaF[0] + ", respuestaF2=" + respuestaF[1] 
                 + ", respuestaF3=" + respuestaF[2] + ", respuestaExplayada=" + respuestaExplayada + '}';
    }
    
    
    
    
}
