/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Estudiante;
import com.vkaiido.BD.ConexionAMYSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Vkaiido
 */
public class Estudiantes {

    ConexionAMYSQL con = new ConexionAMYSQL();
    Connection conexion = con.getConecction();

    public ArrayList<Estudiante> ListadoEstudiantes() {
        ArrayList<Estudiante> listado = null;

        try {
            listado = new ArrayList<Estudiante>();

            CallableStatement cb = conexion.prepareCall("{call SP_S_ESTUDIANTE()}");
            ResultSet resultado = cb.executeQuery();
            
            
            while (resultado.next()) {    
                //Llamar al objeto de entidades (tabla) este debe estar dentro del bucle
                //porque se va particionando
                Estudiante es = new Estudiante();
                es.setNombre(resultado.getString("Nombre"));
                es.setApellido(resultado.getString("Apellido"));
                
                listado.add(es);
            }
            
        } catch (Exception e) {
            System.out.println("Error man"+e);
        }

        return listado;

    }
    
    public void AddEstudiante(Estudiante es){
    
        try {
            CallableStatement cb = conexion.prepareCall("{call SP_I_ESTUDIANTE(?,?)}");
            cb.setString("PNombre", es.getNombre());
            cb.setString("PApellido", es.getApellido());
            cb.execute();
            
            JOptionPane.showMessageDialog(null, "Persona Agregada");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error"+ex);
        }
    
    }
}
