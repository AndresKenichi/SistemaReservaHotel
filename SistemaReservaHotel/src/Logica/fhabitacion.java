/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vhabitacion;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ThinkPad
 */
//Crearemos una clase que tendra todas las funciones CRUD necesarias 
//para interactuar con nuestra tabla habitacion
public class fhabitacion {

    //Creamos una instancia a nuestra cadena de conexion
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();

    private String sSQL = "";
    private Integer totalregistros;

    //Creamos una funcion para mostrar los registros de la base de datos
    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;
        /* Declaramos 2 vectores. Uno para guardar los titulos 
           de la columna y otro para el contenido de la tabla(Sus registros). */

        String[] titulos = {"ID", "Número", "Piso", "Descripción", "Caracteristicas", "Precio dia", "Estado", "Tipo habitacion"};
        //el segundo vector lo vamos a inicializar en blanco con 8 indices
        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        //Colocamos la instruccion SQL
        sSQL = "select * from habitacion where piso like '%" + buscar + "%' order by idhabitacion";

        //Colocamos un try catch para controlar los posibles errores.
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            //usamos una estructura while para recorer cada uno de los registros
            while (rs.next()) {
                registro[0] = rs.getString("idhabitacion");
                registro[1] = rs.getString("numero");
                registro[2] = rs.getString("piso");
                registro[3] = rs.getString("descripcion");
                registro[4] = rs.getString("caracteristicas");
                registro[5] = rs.getString("precio_diario");
                registro[6] = rs.getString("estado");
                registro[7] = rs.getString("tipo_habitacion");

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
    public boolean insertar(vhabitacion dts) {

        sSQL = "insert into habitacion(numero,piso,descripcion,caracteristicas, precio_diario, estado, tipo_habitacion)"
                + "values(?,?,?,?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_habitacion());
            
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

    //Creamos una funcion para insertar datos en la bd
    public boolean editar(vhabitacion dts) {
        
        try {

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }

    //Creamos una funcion para insertar datos en la bd
    public boolean eliminar(vhabitacion dts) {
        try {

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }

}
