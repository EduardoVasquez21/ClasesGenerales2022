/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vkaiido.BD;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Vkaiido
 */
public class ConexionAMYSQL {
    private static Connection ConnectionBD = null;
    public Connection getConecction (){
        try{
            String url= "jdbc:mysql://localhost:3306/clase";
            String user="Vkaiido";
            String password="root";
            
            ConnectionBD = DriverManager.getConnection(url,user,password);
            System.out.println("Conexion establecida");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error"+e.toString());
        }
        
    return ConnectionBD;
    }
    
}
