/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/**
 *
 * @author ThinkPad
 */
public class conexion {
    
    public String db="basereserva";
    public String url="jdbc:mysql://localhost:3307/"+db;
    public String user="root";
    public String pass="";
    
    public conexion(){
        
    }
    
    //Creamos una funcion para conectarnos a la base de datos.
    public Connection conectar(){
        
        //Creamos una instancia para la conexion de la bd
        Connection link=null;
        
        try {
            
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.pass);
            
        } catch (ClassNotFoundException | SQLException e) {
            
            JOptionPane.showConfirmDialog(null, e);
            
        }
        
        return link;
        
        
        
    }
    
    
    
    
    
    
}
