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
public class Persona {

    //Atributos de la clase
    String nombre;
    String apellido;

    //Metodos es un fragmento de codigo que podemos reutilizar
    public void desplegarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
    }

}
