/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Deivid Facio
 */
public class AcessoMySQL {
    
    Connection con;
    
    public Connection conectar(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sisinfo?user=root&password=java");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ETEC","root","Etec@etec");
            //JOptionPane.showMessageDialog(null, "CONECTADO ao Banco!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi Possível CONECTAR ao Banco!");
        }
        return con;

    }
    
    
    public void desconectar(){
        try{
            con.close();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Não Foi Possível DESCONECTAR ao Banco!");
        }
    }
    
}
