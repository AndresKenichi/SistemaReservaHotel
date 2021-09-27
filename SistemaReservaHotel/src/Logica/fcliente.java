/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vcliente;
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
//para interactuar con nuestra tabla habitacion
public class fcliente {

    //Creamos una instancia a nuestra cadena de conexion
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();

    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    //Creamos una funcion para mostrar los registros de la base de datos
    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;
        /* Declaramos 2 vectores. Uno para guardar los titulos 
           de la columna y otro para el contenido de la tabla(Sus registros). */

        String[] titulos = {"ID", "Nombre", "Apaterno", "Amaterno", "Doc", "Numero documento", "Direccion", "Telefono", "Email", "Codigo"};
        //el segundo vector lo vamos a inicializar en blanco con 8 indices
        String[] registro = new String[10];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        //Colocamos la instruccion SQL
        sSQL = "select p.idpersona, p.nombre, p.apaterno, p.amaterno,p.tipo_documento,p.num_documento,"
                + " p.direccion, p.telefono, p.email, c.codigo_cliente from persona p inner join cliente c "
                + "on p.idpersona=c.idpersona where num_documento like '%"
                + buscar + "%' order by idpersona desc";

        //Colocamos un try catch para controlar los posibles errores.
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            //usamos una estructura while para recorer cada uno de los registros
            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apaterno");
                registro[3] = rs.getString("amaterno");
                registro[4] = rs.getString("tipo_documento");
                registro[5] = rs.getString("num_documento");
                registro[6] = rs.getString("direccion");
                registro[7] = rs.getString("telefono");
                registro[8] = rs.getString("email");
                registro[9] = rs.getString("codigo_cliente");

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
    public boolean insertar(vcliente dts) {

        sSQL = "insert into persona(nombre,apaterno,amaterno, tipo_documento, num_documento,direccion, telefono,email)"
                + "values(?,?,?,?,?,?,?,?)";

        sSQL2 = "insert into cliente(idpersona,codigo_cliente)"
                + "values((select idpersona from persona order by idpersona desc limit 1),?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getAmaterno());
            pst.setString(3, dts.getApaterno());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());

            pst2.setString(1, dts.getCodigo_cliente());

            //variable para almacenar el resultado de la ejecucion del statement
            int n = pst.executeUpdate();

            if (n != 0) {

                int n2 = pst2.executeUpdate();
                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

    //Creamos una funcion para Editar los datos en la bd
    public boolean editar(vcliente dts) {
        sSQL = "update persona set nombre=?, apaterno=?, amaterno=?, tipo_documento=?, num_documento=?,"
                + " direccion=?, telefono=?, email=? where idpersona=?";
        
         sSQL2 = "update cliente set codigo_cliente=?"
                + " where idpersona=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getAmaterno());
            pst.setString(3, dts.getApaterno());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());
            pst.setInt(9, dts.getIdpersona());

            pst2.setString(1, dts.getCodigo_cliente());
            pst2.setInt(2, dts.getIdpersona());

            //variable para almacenar el resultado de la ejecucion del statement
            int n = pst.executeUpdate();

            if (n != 0) {

                int n2 = pst2.executeUpdate();
                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

    //Creamos una funcion para eliminar los datos en la bd
    public boolean eliminar(vcliente dts) {
        //En este caso primero debemos eliminar nuestro registro en la tabla cliente para poder eliminar el que tenemos en la tabla persona
        sSQL = "delete from cliente where idpersona=?";
        sSQL2 = "delete from persona where idpersona=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdpersona());
            
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst2.setInt(1, dts.getIdpersona());

            //variable para almacenar el resultado de la ejecucion del statement
            int n = pst.executeUpdate();

          if (n != 0) {

                int n2 = pst2.executeUpdate();
                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }

            }
            else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

}
