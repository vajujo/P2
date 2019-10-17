/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

/**
 *
 * @author pablo
 */
public class Moto {
    private static int id;
    private int idMoto;
    private String descripcion;
    private float dinero;

    //constructores
    public Moto() {
        id++;
        idMoto = id;
    }

    public Moto(int id, String descripcion, float dinero) {
        this.descripcion = descripcion;
        this.dinero = dinero;
        this.id = id;
        id++;
        idMoto = id;
    }

    //seters
    public void setIdMoto(int idMoto) {
        this.idMoto = idMoto;
    }

    
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

    public static void setId(int id) {
        Moto.id = id;
    }

    //geters
    public int getIdMoto() {
        return idMoto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getDinero() {
        return dinero;
    }
    //imprimir
    public void outMoto(){
        System.out.println("-IdMoto: " + String.format("%03d", idMoto) + " -Descripcion: " + descripcion + " -Precio: " + dinero);
    }
    
    //convertir a String
    @Override
    public String toString (){
        String texto;
        
        texto = "IdMoto: " + String.format("%03d", idMoto) + "\n";
        texto = texto + "Descripcion: " + descripcion + "\n";
        texto = texto + "Precio: " + dinero + "\n\n";
        
        return texto;
    }
    
}
