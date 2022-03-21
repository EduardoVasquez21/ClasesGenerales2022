/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.user;
import com.vkaiido.BD.ConexionAMYSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Vkaiido
 */
public class Logeos {
     ConexionAMYSQL con = new ConexionAMYSQL();
     
     public Boolean Logu(user usu){
         
         Connection conexion = con.getConecction();
         
         Boolean valid = false;
         try{
         CallableStatement cb = conexion.prepareCall("{call SPS_LOG(?,?)}");
         cb.setString("PPUSUARIO", usu.getUser());
         cb.setString("PPPASS", usu.getPassword());
            ResultSet resultado = cb.executeQuery();

         int x = 0;
         while (resultado.next()){
         x+=1;
         }
         switch(x){
         
             case 1 : JOptionPane.showMessageDialog(null, "Bienvenido "+usu.getUser()+", con la contraseña xD: "+usu.getPassword());
             valid = true;
             break;
             
             case 0 : JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario");
             break;
         }
         
         }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, "error"+ex);
         
         
         
         }
         return valid;
     }


}
