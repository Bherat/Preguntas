/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Pregunta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author foo
 */
public class PreguntaDAO {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/preguntasyrespuestas?zeroDateTimeBehavior=convertToNull";
    
    public PreguntaDAO() {
    }
    
    public static int addPregunta(String pregunta, String respuestaV, String[] respuestaF, String respExplayada) {
        int respDB = -1;
        
        try(Connection accessDB = DriverManager.getConnection(DB_URL, "preguntas", "preg");
                PreparedStatement ps = accessDB.prepareStatement("INSERT INTO "
                        + "Preguntas(ID, Pregunta, RespuestaV, RespuestaF1, RespuestaF2, RespuestaF3, RespuestaExplayada) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            
            ps.setString(1, null);
            ps.setString(2, pregunta);
            ps.setString(3, respuestaV);
            ps.setString(4, respuestaF[0]);
            ps.setString(5, respuestaF[1]);
            ps.setString(6, respuestaF[2]);
            ps.setString(7, respExplayada);
            
            respDB = ps.executeUpdate();
            
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return respDB;
    }
    
    public static List<Pregunta> getListPreguntas() {
        List<Pregunta> pregs = new ArrayList<>(); 
        
        try(Connection accessDB = DriverManager.getConnection(DB_URL, "preguntas", "preg");
            PreparedStatement ps = accessDB.prepareStatement("SELECT * FROM preguntas");
            ResultSet rs = ps.executeQuery()) 
        {
            while(rs.next())
            {
                String[] preg = {
                        rs.getString("RespuestaF1"),
                        rs.getString("RespuestaF2"),
                        rs.getString("RespuestaF3")};
                pregs.add(new Pregunta(
                        rs.getInt("ID"),
                        rs.getString("Pregunta"),
                        rs.getString("RespuestaV"),
                        preg,
                        rs.getString("RespuestaExplayada")));
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return pregs;
    }
    
}
