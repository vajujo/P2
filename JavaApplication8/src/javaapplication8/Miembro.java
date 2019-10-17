/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class Miembro {
    private static int id = 0;
    private int idMiembro;
    private String nombre;
    private float dineroAcum;
    private ArrayList<Moto> motos = new ArrayList<>();
    
    //constructores
    public Miembro() {
        id++;
        idMiembro = id;
    }

    public Miembro(int id, String nombre, float dineroAcum, ArrayList<Moto> moto) {
        this.nombre = nombre;
        this.dineroAcum = dineroAcum;
        this.motos = moto;
        id++;
        idMiembro = id;
    }

    //seters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*public void setMotos(Moto moto) {
        this.motos.add(moto);
    }*/

    public void setDineroAcum(float dineroAcum) {
        this.dineroAcum = dineroAcum;
    }

    
    public static void setId(int id) {
        Miembro.id = id;
    }
    
    public void reset(){
        nombre = "";
        dineroAcum = 0;
        //motos.clear();
    }

    //geters
    public String getNombre() {
        return nombre;
    }
    
    
    public int getIdMiembro() {
        return idMiembro;
    }

    public void setMotos(Moto motos) {
        this.motos.add(motos);
    }

    public float getDineroAcum() {
        dineroAcum = 0;
        for (int i = 0; i < motos.size(); i++) {
            dineroAcum = dineroAcum + motos.get(i).getDinero();
        }
        
        return dineroAcum;
    }

    public ArrayList<Moto> getMotos() {
        return motos;
    }

    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }
    
    
    //funcion para imprimir
    public void outMiembro(){
        System.out.println("miembro:");
        System.out.println("id: "+ idMiembro);
        System.out.println("nombre: " + nombre);  
        System.out.println("precioAcum: " + getDineroAcum());
        for (int i = 0; i < motos.size(); i++) {
            System.out.println("moto: " + motos.get(i).getIdMoto() + "," + motos.get(i).getDescripcion() + "," + motos.get(i).getDinero());
        }
        System.out.println();
    }
    
    //funcion para convertirlo a String
    @Override
    public String toString(){
        String texto = "";
        
        texto += "miembro: \n";
        texto += "id: " + idMiembro + "\n";
        texto += "nombre: " + nombre + "\n";
        texto += "precioAcum: " + getDineroAcum() + "\n";
        
        for (int i = 0; i < motos.size(); i++) {
            texto += "moto: " + motos.get(i).getIdMoto() + "," + motos.get(i).getDescripcion() + "," + motos.get(i).getDinero() + "\n";
        }
        texto += "\n";
        return texto;
    }
    
}
