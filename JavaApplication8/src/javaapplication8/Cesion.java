/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

import java.util.Date;

/**
 *
 * @author juanj
 */
public class Cesion {
    private static int id;
    private int idCesion;
    private int idMoto;
    private int idMiembro1;
    private int idMiembro2;
    private Date fecha;

    //constructores
    public Cesion() {
        id++;
        idCesion = id;
    }

    public Cesion(int id, int idMoto, int idMiembro1, int idMiembro2, Date fecha) {
        this.idMoto = idMoto;
        this.idMiembro1 = idMiembro1;
        this.idMiembro2 = idMiembro2;
        Cesion.id = id;
        id++;
        idCesion = id;
    }

    //geters
    public static int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public int getIdMiembro1() {
        return idMiembro1;
    }

    public int getIdMiembro2() {
        return idMiembro2;
    }

    public int getIdMoto() {
        return idMoto;
    }

    public int getIdCesion() {
        return idCesion;
    }

    //seters
    public static void setId(int id) {
        Cesion.id = id;
    }

    public void setIdMiembro1(int idMiembro1) {
        this.idMiembro1 = idMiembro1;
    }

    public void setIdMiembro2(int idMiembro2) {
        this.idMiembro2 = idMiembro2;
    }

    public void setIdMoto(int idMoto) {
        this.idMoto = idMoto;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setIdCesion(int idCesion) {
        this.idCesion = idCesion;
    }
    
    //imprimir
    public void outCesion(){
        System.out.println("idCesion " + idCesion + " miembro1 " + idMiembro1 + " miembro2 "
        + idMiembro2 + " moto " + idMoto + " fecha " + fecha.toString());
    }
    
    //convertir a String
    @Override
    public String toString(){
        String texto = "";
        
        texto += "cesion:\n";
        texto += "id: " + idCesion + "\n";
        texto += "idMiembro1: " + idMiembro1 + "\n";
        texto += "idMiembro2: " + idMiembro2 + "\n";
        texto += "idMoto: " + idMoto + "\n";
        texto += "fecha: " + fecha.toString() + "\n";
        
        return texto;
    }
}
