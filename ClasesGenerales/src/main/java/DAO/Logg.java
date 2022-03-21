/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Usuario;
import ViewModel.LogginVM;
import com.vkaiido.BD.ConexionAMYSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Vkaiido
 */
public class Logg {

    ConexionAMYSQL con = new ConexionAMYSQL();

    public ArrayList<LogginVM> ListUser(String User, String Password) {
        ArrayList<LogginVM> listado = null;

        Connection conexion = con.getConecction();
        try {

            listado = new ArrayList<LogginVM>();

            CallableStatement cb = conexion.prepareCall("{call SP_I_USER(?,?)}");
            ResultSet resultado = cb.executeQuery();

            while (resultado.next()) {
                //Llamar a el objeto de Entidad
                LogginVM es = new LogginVM();
                es.getUser(resultado.getString("PrUser"));
                es.getPassword(resultado.getString("PPASSWORD"));
                listado.add(es);

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return listado;
    }

    public void AddUser(Usuario es) {
        try {
            Connection conexion = con.getConecction();
            

            CallableStatement cb = conexion.prepareCall("{call SP_I_USER(?,?)}");
            cb.setString("PrUser", es.getUser());
            cb.setString("PPASSWORD", es.getPassword());
            cb.execute();

            JOptionPane.showMessageDialog(null, "Usuario agregado correctamente", "Mensaje sistems", 1);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error, algo anda mal" + ex.toString(), "Mensaje sistems", 1);
        }

    }

    public boolean LogEstudiantes(String usuario, String password) {
        Connection conexion = con.getConecction();
        ArrayList<Usuario> ListaUserPass = new ArrayList<>();
        try {
            CallableStatement Statement = conexion.prepareCall("{call SP_S_LOGEO(?,?)}");
            Statement.setString("PPUSUARIO", usuario);
            Statement.setString("PPPASS", password);
            ResultSet consulta = Statement.executeQuery();

            while (consulta.next()) {
                Usuario es = new Usuario();
                es.setUser(consulta.getString("user"));
                es.setPassword(consulta.getString("password"));
                ListaUserPass.add(es);
            }
            String userBD = null;
            String passwordBD = null;
            for (var iterador : ListaUserPass) {
                userBD = iterador.getUser();
                passwordBD = iterador.getPassword();
            }

            if (userBD.equals(usuario) && passwordBD.equals(password)) {
                
               JOptionPane.showMessageDialog(null, "Hola, el metodo si se ejecuta");
                return true;

            }

            conexion.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

}
