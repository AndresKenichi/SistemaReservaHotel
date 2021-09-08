/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vhabitacion;
import Datos.vproducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ThinkPad
 */
//Crearemos una clase que tendra todas las funciones CRUD necesarias 
//para interactuar con nuestra tabla producto
public class fproducto {
    
    //Creamos una instancia a nuestra cadena de conexion
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();

    private String sSQL = "";
    public Integer totalregistros;

    //Creamos una funcion para mostrar los registros de la base de datos
    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;
        /* Declaramos 2 vectores. Uno para guardar los titulos 
           de la columna y otro para el contenido de la tabla(Sus registros). */

        String[] titulos = {"ID", "Producto", "Descripción", "Unidad Medida", "Precio Venta"};
        //el segundo vector lo vamos a inicializar en blanco con 8 indices
        String[] registro = new String[5];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        //Colocamos la instruccion SQL
        sSQL = "select * from producto where nombre like '%" + buscar + "%' order by idproducto desc";

        //Colocamos un try catch para controlar los posibles errores.
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            //usamos una estructura while para recorer cada uno de los registros
            while (rs.next()) {
                registro[0] = rs.getString("idproducto");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("descripcion");
                registro[3] = rs.getString("unidad_medida");
                registro[4] = rs.getString("precio_venta");

                //incrementamos el total de registros en 1 para sabes su total
                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    //Creamos una funcion para insertar datos en la bd
    public boolean insertar(vproducto dts) {

        sSQL = "insert into producto(nombre,descripcion,unidad_medida, precio_venta)"
                + "values(?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidad_medida());
            pst.setDouble(4, dts.getPrecio_venta());
           
            
            //variable para almacenar el resultado de la ejecucion del statement
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }else{
                return false;
            }
            
            

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
                   return false;
        }

    }

    //Creamos una funcion para Editar los datos en la bd
    public boolean editar(vproducto dts) {
        sSQL="update producto set nombre=?, descripcion=?, unidad_medida=?, precio_venta=?"+
                " where idproducto=?";

            try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidad_medida());
            pst.setDouble(4, dts.getPrecio_venta());
            pst.setInt(5, dts.getIdproducto());
           
            
            //variable para almacenar el resultado de la ejecucion del statement
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }else{
                return false;
            }
            
            

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
                   return false;
        }

    }

    //Creamos una funcion para eliminar los datos en la bd
    public boolean eliminar(vproducto dts) {
        sSQL="delete from producto where idproducto=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdproducto());
            
            //variable para almacenar el resultado de la ejecucion del statement
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    } 
    
    
    
    
    
    
    
    
    
}
