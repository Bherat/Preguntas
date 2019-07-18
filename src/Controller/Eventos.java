/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Componentes;
import Model.DAO.PreguntaDAO;
import Model.Pregunta;
import Model.Temporalizador;
import View.ExplayarRespuesta;
import View.Inicio;
import View.Jugar;
import View.NuevaPregunta;
import View.VentanaVF;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JDesktopPane;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author foo
 */
public class Eventos implements ActionListener {

    private final Inicio inicio;
    private final Jugar jugar;
    private final VentanaVF venVF;
    private final NuevaPregunta nuePreg;
    private final ExplayarRespuesta expResp;

    private Temporalizador temporalizador;

    private List<Pregunta> pregunta;
    private int cantPreguntas;
    private Timer timer;

    public Eventos(Inicio ini, NuevaPregunta nPreg, ExplayarRespuesta expResp, Jugar jug, VentanaVF VF) {
        this.inicio = ini;
        this.nuePreg = nPreg;
        this.expResp = expResp;
        this.jugar = jug;
        this.venVF = VF;
        
        this.pregunta = PreguntaDAO.getListPreguntas();

        actionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDesktopPane desktop = this.inicio.getjDesktop();

        if (e.getSource() == inicio.getBtnJugar() || e.getSource() == this.venVF.getBtnContinuar()) {
            if(this.pregunta.size() > 0) {
                Random random = new Random(System.currentTimeMillis());
                temporalizador = new Temporalizador(30);
                TimerTask task;

                List<Component> comp = Componentes.getAllComponents(this.jugar);

                comp.forEach(com -> {
                    if (!com.isEnabled()) {
                        com.setEnabled(true);
                    }
                });

                desktop.add(this.jugar, 0);

                this.timer = new Timer();

                task = new TimerTask() {
                    @Override
                    public void run() {
                        if (temporalizador.getTiempoSegundos() > 0) {
                            int colorTempo = temporalizador.getTiempoSegTempo();

                            int i = temporalizador.getTiempoSegundos();
                            i--;
                            String tempo = String.valueOf(i);
                            jugar.getLbTemporalizador().setText(tempo);
                            temporalizador.setTiempoSegundos(i);

                            if (colorTempo / 2 < i) {
                                jugar.getLbTemporalizador().setForeground(Color.black);
                            }
                            if ((colorTempo / 2 + 1) > i) {
                                jugar.getLbTemporalizador().setForeground(new Color(255, 215, 10));
                            }
                            if ((colorTempo / 3) > i) {
                                jugar.getLbTemporalizador().setForeground(Color.red);
                            }
                            if (i == 0) {
                                jugar.getLbTemporalizador().setForeground(Color.gray);
                            }

                        } else {
                            if (temporalizador.getTiempoSegundos() < 1) {
                        venVF("¡Tiempo agotado!");
                            }
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 50, 1000);

                cantPreguntas = random.nextInt(this.pregunta.size());
                
                String contenPregunta = this.pregunta.get(cantPreguntas).getPregunta();
                
                if(!contenPregunta.startsWith("¿")) {
                    contenPregunta = "¿" + contenPregunta;
                    
                    if(!contenPregunta.endsWith("?")) {
                        contenPregunta = contenPregunta + "?";
                    }
                }
                
                this.jugar.getLbPregunta().setText("<html>"+contenPregunta+"</html>");

                int numAlea = random.nextInt(6);

                switch (numAlea) {
                    case 0:
                        this.jugar.getBtnRespuesta1().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaV()+"</html>");
                        this.jugar.getBtnRespuesta2().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[0]+"</html>");
                        this.jugar.getBtnRespuesta3().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[1]+"</html>");
                        this.jugar.getBtnRespuesta4().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[2]+"</html>");
                        break;

                    case 1:
                        this.jugar.getBtnRespuesta2().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaV()+"</html>");
                        this.jugar.getBtnRespuesta4().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[0]+"</html>");
                        this.jugar.getBtnRespuesta1().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[1]+"</html>");
                        this.jugar.getBtnRespuesta3().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[2]+"</html>");
                        break;

                    case 2:
                        this.jugar.getBtnRespuesta4().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaV()+"</html>");
                        this.jugar.getBtnRespuesta3().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[0]+"</html>");
                        this.jugar.getBtnRespuesta2().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[1]+"</html>");
                        this.jugar.getBtnRespuesta1().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[2]+"</html>");
                        break;

                    case 3:
                        this.jugar.getBtnRespuesta3().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaV()+"</html>");
                        this.jugar.getBtnRespuesta1().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[0]+"</html>");
                        this.jugar.getBtnRespuesta4().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[1]+"</html>");
                        this.jugar.getBtnRespuesta2().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[2]+"</html>");
                        break;

                    case 4:
                        this.jugar.getBtnRespuesta2().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaV()+"</html>");
                        this.jugar.getBtnRespuesta3().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[0]+"</html>");
                        this.jugar.getBtnRespuesta1().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[1]+"</html>");
                        this.jugar.getBtnRespuesta4().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[2]+"</html>");
                        break;

                    case 5:
                        this.jugar.getBtnRespuesta4().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaV()+"</html>");
                        this.jugar.getBtnRespuesta2().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[0]+"</html>");
                        this.jugar.getBtnRespuesta1().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[1]+"</html>");
                        this.jugar.getBtnRespuesta3().setText("<html>"+this.pregunta.get(cantPreguntas).getRespuestaF()[2]+"</html>");
                        break;
                }

                mouseEvents();

                if (this.venVF.isVisible()) {
                    this.venVF.dispose();
                }

                this.inicio.getJInternalInicio().dispose();
                this.jugar.toFront();
                this.jugar.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(null, "No hay preguntas en la base de datos, agregue algunas para poder jugar.");
            }
        } 

        if (e.getSource() == inicio.getBtnNPregunta()) {
            this.inicio.getJInternalInicio().dispose();

            desktop.add(this.nuePreg, 0);

            this.nuePreg.setVisible(true);
            this.nuePreg.toFront();

        }

        if (e.getSource() == this.nuePreg.getBtnAtras()) {
            this.nuePreg.dispose();
            desktop.add(this.inicio.getJInternalInicio(), 0);

            this.inicio.getJInternalInicio().setVisible(true);
            this.inicio.getJInternalInicio().toFront();
        }

        if (e.getSource() == this.nuePreg.getBtnContinuar()) {
            String txtPregunta = String.valueOf(this.nuePreg.getTxtPregunta().getText());
            String respV = String.valueOf(this.nuePreg.getTxtRespuestaV().getText());
            String respF1 = String.valueOf(this.nuePreg.getTxtRespuestaF1().getText());
            String respF2 = String.valueOf(this.nuePreg.getTxtRespuestaF2().getText());
            String respF3 = String.valueOf(this.nuePreg.getTxtRespuestaF3().getText());

            if (!(txtPregunta.isEmpty() || txtPregunta.equals("null")) && !(respV.isEmpty() || respV.equals("null"))
                    && !(respF1.isEmpty() || respF1.equals("null")) && !(respF2.isEmpty() || respF2.equals("null"))
                    && !(respF3.isEmpty() || respF3.equals("null"))) {
                desktop.add(this.expResp, 0);
                desktop.setLayout(new BorderLayout());

                List<Component> comp = Componentes.getAllComponents(this.nuePreg);

                comp.forEach(com -> {
                    com.setEnabled(false);
                });

                expResp.setLocation((desktop.getWidth() - expResp.getWidth()) / 2,
                        (desktop.getHeight() - expResp.getHeight()) / 2);

                expResp.toFront();
                expResp.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this.nuePreg, "Rellene todos los campos para continuar.");
            }
        }

        if (e.getSource() == this.expResp.getBtnGuardar()) {
            String[] respFalsas = {this.nuePreg.getTxtRespuestaF1().getText(),
                this.nuePreg.getTxtRespuestaF2().getText(),
                this.nuePreg.getTxtRespuestaF3().getText()};

            int retorno = PreguntaDAO.addPregunta(this.nuePreg.getTxtPregunta().getText(), this.nuePreg.getTxtRespuestaV().getText(), respFalsas,
                    this.expResp.getTxtExplayarRespuesta().getText());

            if (retorno > 0) {
                this.expResp.dispose();
                this.nuePreg.limpiarFields();
                this.expResp.getTxtExplayarRespuesta().setText("");
                this.pregunta = PreguntaDAO.getListPreguntas();
                
                List<Component> comp = Componentes.getAllComponents(this.nuePreg);

                comp.forEach(com -> {
                    if (!com.isEnabled()) {
                        com.setEnabled(true);
                    }
                });

                desktop.add(this.nuePreg, 0);
                this.nuePreg.toFront();
                this.nuePreg.setVisible(true);
                JOptionPane.showMessageDialog(this.expResp, "Registro Exitoso.");
            }
            if (retorno < 1) {
                JOptionPane.showMessageDialog(this.expResp, "Hubo problemas con la Base de datos.");
            }
        }

        if (e.getSource() == this.venVF.getBtnPantallaPrincipal()) {
            desktop.removeAll();

            desktop.add(this.inicio.getJInternalInicio(), 0);
            this.inicio.getJInternalInicio().toFront();
            this.inicio.getJInternalInicio().setVisible(true);
        }

    }

    public void mouseEvents() {
        JDesktopPane desktop = this.inicio.getjDesktop();
        
        this.jugar.getBtnRespuesta1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jugar.getBtnRespuesta1().isEnabled()) {
        String pregV = "<html>"+pregunta.get(cantPreguntas).getRespuestaV()+"</html>";
                    if (pregV.equals(jugar.getBtnRespuesta1().getText())) {
                        venVF("¡Correcto!");
                    } else {
                        venVF("¡Incorrecto!");
                    }
                }
                
            }
        });
        this.jugar.getBtnRespuesta2().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jugar.getBtnRespuesta2().isEnabled()) {
        String pregV = "<html>"+pregunta.get(cantPreguntas).getRespuestaV()+"</html>";
                    if (pregV.equals(jugar.getBtnRespuesta2().getText())) {
                        venVF("¡Correcto!");
                    } else {
                        venVF("¡Incorrecto!");
                    }
                }


            }
        });
        this.jugar.getBtnRespuesta3().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jugar.getBtnRespuesta3().isEnabled()) {
        String pregV = "<html>"+pregunta.get(cantPreguntas).getRespuestaV()+"</html>";
                    if (pregV.equals(jugar.getBtnRespuesta3().getText())) {
                        venVF("¡Correcto!");
                    } else {
                        venVF("¡Incorrecto!");
                    }
                }
            }
        });
        
        this.jugar.getBtnRespuesta4().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jugar.getBtnRespuesta4().isEnabled()) {
        String pregV = "<html>"+pregunta.get(cantPreguntas).getRespuestaV()+"</html>";
                    if (pregV.equals(jugar.getBtnRespuesta4().getText())) {
                        venVF("¡Correcto!");
                    } else {
                        venVF("¡Incorrecto!");
                    }
                }
            }
        });

    }

    private void venVF(String vf) {
        this.timer.cancel();

        JDesktopPane desktop = this.inicio.getjDesktop();
        desktop.add(this.venVF, 0);
        desktop.setLayout(new BorderLayout());

        this.venVF.setLocation((desktop.getWidth() - this.venVF.getWidth()) / 2, (desktop.getHeight() - this.venVF.getHeight()) / 2);

        List<Component> comp = Componentes.getAllComponents(this.jugar);

        comp.forEach(com -> {
            com.setEnabled(false);
        });

        this.venVF.getLbVerdaderoFalso().setText(vf);
        this.venVF.getLbFallo().setText(this.pregunta.get(cantPreguntas).getRespuestaExplayada());

        this.venVF.toFront();
        this.venVF.setVisible(true);
    }



    public void venVFPosicion() {
        JDesktopPane desktop = this.inicio.getjDesktop();
        this.venVF.setLocation((desktop.getWidth() - this.venVF.getWidth()) / 2,
                (desktop.getHeight() - this.venVF.getHeight()) / 2);
    }

    private void actionListeners() {
        this.inicio.getBtnJugar().addActionListener(this);
        this.inicio.getBtnNPregunta().addActionListener(this);
        this.nuePreg.getBtnAtras().addActionListener(this);
        this.nuePreg.getBtnContinuar().addActionListener(this);
        this.expResp.getBtnGuardar().addActionListener(this);
        this.venVF.getBtnPantallaPrincipal().addActionListener(this);
        this.venVF.getBtnContinuar().addActionListener(this);
    }

}
