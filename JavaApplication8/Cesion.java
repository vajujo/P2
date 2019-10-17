/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;



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
    private int dia;
    private int mes;
    private int anyo;

    //constructores
    public Cesion() {
        id++;
        idCesion = id;
    }

    public Cesion(int id, int idMoto, int idMiembro1, int idMiembro2, int dia, int mes, int anyo) {
        this.idMoto = idMoto;
        this.idMiembro1 = idMiembro1;
        this.idMiembro2 = idMiembro2;
        this.dia = dia;
        this.mes = mes;
        this.anyo = anyo;
        Cesion.id = id;
        id++;
        idCesion = id;
    }

    //geters
    public static int getId() {
        return id;
    }

    
    public int getIdMiembro1() {
        return idMiembro1;
    }

    public int getAnyo() {
        return anyo;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
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

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
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

    public void setIdCesion(int idCesion) {
        this.idCesion = idCesion;
    }
    
    //imprimir
    public void outCesion(){
        System.out.println("idCesion " +  String.format("%03d", idCesion) + " miembro1 " + String.format("%03d", idMiembro1) + " miembro2 "
        +  String.format("%03d", idMiembro2) + " moto " +  String.format("%03d", idMoto) + " fecha " +  String.format("%02d", dia) + "/" +  String.format("%02d", mes) + "/" +  String.format("%04d", anyo));
    }
    
    //convertir a String
    @Override
    public String toString(){
        String texto = "";
        
        texto += "cesion:\n";
        texto += "id: " +  String.format("%03d", idCesion) + "\n";
        texto += "idMiembro1: " + String.format("%03d", idMiembro1) + "\n";
        texto += "idMiembro2: " +  String.format("%03d", idMiembro2) + "\n";
        texto += "idMoto: " +  String.format("%03d", idMoto) + "\n";
        texto = texto + "fecha: " +  String.format("%02d", dia) + "/" +  String.format("%02d", mes) + "/" +  String.format("%04d", anyo);
        
        return texto;
    }
}
