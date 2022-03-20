/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Estudiante;
import Entidades.Nota;
import ViewModel.NotaVM;
import com.vkaiido.BD.ConexionAMYSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Vkaiido
 */
public class Notas {
    ConexionAMYSQL con = new ConexionAMYSQL();
    Connection conexion = con.getConecction();

    /*public ArrayList<Nota> ListadoNota() {
        ArrayList<Nota> listado = null;

        try {
            listado = new ArrayList<Nota>();

            CallableStatement cb = conexion.prepareCall("{call SP_S_MOSTRARNOTAS()}");
            ResultSet resultado = cb.executeQuery();
            
            
            while (resultado.next()) {    
                //Llamar al objeto de entidades (tabla) este debe estar dentro del bucle
                //porque se va particionando
                Nota es = new Nota();
                es.setNota(resultado.getString("nota"));
                es.setNombreMateria(resultado.getString("NombreMateria"));
                es.setNombre(resultado.getString("Nombre"));
                
                listado.add(es);
            }
            
        } catch (Exception e) {
            System.out.println("Error man"+e);
        }

        return listado;

    }*/
    //trabajando con ViewModels
        public ArrayList<NotaVM> ListadoEstudianteConNota() {
        ArrayList<NotaVM> listado = null;

        try {
            listado = new ArrayList<NotaVM>();

            CallableStatement cb = conexion.prepareCall("{call SP_S_MOSTRARNOTAS()}");
            ResultSet resultado = cb.executeQuery();
            
            
            while (resultado.next()) {    
                //Llamar al objeto de entidades (tabla) este debe estar dentro del bucle
                //porque se va particionando
                NotaVM es = new NotaVM();
                es.setNota(resultado.getString("nota"));
                es.setNombreMateria(resultado.getString("NombreMateria"));
                es.setNombre(resultado.getString("Nombre"));
                
                listado.add(es);
            }
            
        } catch (Exception e) {
            System.out.println("Error man"+e);
        }

        return listado;

    }
       
        
}
