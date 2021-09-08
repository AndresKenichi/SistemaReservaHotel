/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author ThinkPad
 */
public class PruebaPersona {
    
    public static void main(String[] args){
        
        Persona persona1; //Creamos una variable de tipo persona.
       
        persona1=new Persona();  //Inicializamos el variable para crear el objeto. Usando el constructor.
        
        persona1.nombre="Juan";
        persona1.apellido="Perez";
        
        persona1.desplegarInformacion();
        
        
        
        
    }
    
    
}
