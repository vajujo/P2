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
    private float otros_gastos;

    //constructores
    public Moto() {
        id++;
        idMoto = id;
    }

    public Moto(int id, String descripcion, float dinero, float otros_gastos) {
        this.descripcion = descripcion;
        this.dinero = dinero;
        this.otros_gastos = otros_gastos;
        Moto.id = id;
        id++;
        idMoto = id;
    }

    //seters
    public void setIdMoto(int idMoto) {
        this.idMoto = idMoto;
    }

    public float getOtros_gastos() {
        return otros_gastos;
    }

    public void setOtros_gastos(float masgastos) {
        this.otros_gastos += masgastos;
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
        System.out.println("-IdMoto: " + idMoto + " -Descripcion: " +
                descripcion + " -Precio: " + dinero + " -Gastos: " + otros_gastos);
    }
    
    //convertir a String
    @Override
    public String toString (){
        String texto;
        
        texto = "IdMoto: " + idMoto + "\n";
        texto = texto + "Descripcion: " + descripcion + "\n";
        texto = texto + "Precio: " + dinero + "\n";
        texto = texto + "Gastos: " + otros_gastos + "\n\n";
        
        return texto;
    }
    
}
