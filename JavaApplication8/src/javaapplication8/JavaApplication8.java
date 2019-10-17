/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *prueba
 * 
 * @author pablo
 */
public class JavaApplication8 {

    private static int opcion;
    private static ArrayList<Miembro> miembros = new ArrayList<Miembro>();
    private static ArrayList<Moto> motos = new ArrayList<Moto>();
    private static ArrayList<Cesion> cesiones = new ArrayList<Cesion>();

    public static void main(String[] args) {
        
        //Primero leemos el fichero donde guardamos la informacion
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        File archivo2 = null;
        FileWriter fw = null;
        
        

        ArrayList<Moto> auxArray = new ArrayList<>();
        Miembro miembro1 = new Miembro();
        Moto moto1 = new Moto();
        Cesion cesion1 = new Cesion();
        Scanner scanner;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(".\\src\\javaapplication8\\info.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            //Lectura del fichero
            String linea;

            while ((linea = br.readLine()) != null) {
                //primero comprobamos los miembros
                if (linea.contains("miembro:")) {
                    linea = br.readLine();
                    miembro1.setIdMiembro(Integer.parseInt(linea.substring(4)));
                    //System.out.println(" id " + linea.substring(4) + "\n");
                    linea = br.readLine();
                    miembro1.setNombre(linea.substring(8));
                    //System.out.println("nombre " + linea.substring(8) + "\n");
                    linea = br.readLine();
                    miembro1.setDineroAcum(Float.parseFloat(linea.substring(12)));
                    //System.out.println("precio " + linea.substring(12) + "\n");
                    miembros.add(miembro1);
                        //System.out.println(miembros.size());
                    //System.out.println(miembros.get(0).getIdMiembro());
                    //System.out.println(miembros.get(0).getNombre());
                    //System.out.println(miembros.get(0).getDineroAcum());
                    miembro1 = new Miembro();
                }
                //despues comprobamos las motos
                else if (linea.contains("moto:")) {
                    int aux = 6;
                    int cont = 0;
                    for (int i = 6; i < linea.length(); i++) {

                        if ((linea.charAt(i) == ',') && (cont == 0)) {
                            moto1.setIdMoto(Integer.parseInt(linea.substring(aux, i)));
                            //System.out.println("moto:" + linea.substring(aux, i) + "\n");
                            aux = i + 1;
                            cont++;

                        } else if (((linea.charAt(i) == ',') && (cont == 1))) {
                            moto1.setDescripcion(linea.substring(aux, i));
                            //System.out.println("moto:" + linea.substring(aux, i) + "\n");
                            aux = i + 1;
                            cont++;

                        } else if ((cont == 2)) {
                            cont = 0;
                            moto1.setDinero(Float.parseFloat(linea.substring(aux)));
                            //System.out.println("moto: " + linea.substring(aux) + "\n");
                        }
                    }
                    miembros.get(miembros.size() - 1).getMotos().add(moto1);
                    motos.add(moto1);
                    moto1 = new Moto();
                }
                //y por ultimo las cesiones
                else if (linea.contains("cesion:")){
                    linea = br.readLine();
                    cesion1.setIdCesion(Integer.parseInt(linea.substring(4)));
                    linea = br.readLine();
                    cesion1.setIdMiembro1(Integer.parseInt(linea.substring(12)));
                    linea = br.readLine();
                    cesion1.setIdMiembro2(Integer.parseInt(linea.substring(12)));
                    linea = br.readLine();
                    cesion1.setIdMoto(Integer.parseInt(linea.substring(8)));
                    
                    cesiones.add(cesion1);
                    
                    cesion1 = new Cesion();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        //ahora pasamos a ver la opcion que se ha elegido
        opcion = 1;
        while (opcion != 7) {

            //apartir de aqui
            opcion = Menu();
            switch (opcion) {
                //introducir un nuevo miembro
                case 1:
                    scanner = new Scanner(System.in);
                    String nombre;
                    float precioAcum = 0;
                    int opcionMto = 1;
                    ArrayList<Moto> aux = new ArrayList<>();
                    //System.out.println("Numero de miemrbos:" + " " + miembros.size());
                    //System.out.println("Numero de motos:" + " " + motos.size());
                    System.out.println("Introduce el nombre de la persona");
                    nombre = scanner.nextLine();

                    while (opcionMto != 0) {
                        System.out.println("Si quieres introducir una moto pulsa 1, si no pulsa 0");
                        opcionMto = scanner.nextInt();

                        if (opcionMto == 1) {
                            Scanner scanner1 = new Scanner(System.in);
                            int id;
                            String descripcion;
                            float precio;

                            //System.out.println("Numero de motos:" + " " + motos.size());
                            id = motos.size();
                            //System.out.println("id" + " " + id);

                            System.out.println("Escribe la descripcion de la motocicleta");
                            descripcion = scanner1.nextLine();

                            System.out.println("Escribe el precio de la moto");
                            precio = scanner.nextFloat();
                            precioAcum = precioAcum + precio;

                            if (precioAcum <= 60000) {
                                Moto moto = new Moto(id, descripcion, precio);
                                aux.add(moto);
                                motos.add(moto);
                            } else {
                                System.out.println("No se ha podido introducir la ultima moto.");
                                System.out.println("El precio pasa de los 60000. \n");
                            }

                        }
                    }

                    Miembro miembro = new Miembro(miembros.size(), nombre, precioAcum, aux);
                    miembros.add(miembro);
                    //System.out.println(miembros.get(miembros.size() -1).getNombre());
                    //System.out.println(miembros.get(miembros.size() - 1).getMotos().get(miembros.get(miembros.size() -1).getMotos().size() - 1).getDescripcion());
                    break;
                //introducir una nueva moto
                case 2:
                    Scanner scanner1 = new Scanner(System.in);
                    int id;
                    boolean encontrado = false;
                    float precio;
                    
                    for (int i = 0; i < miembros.size(); i++) {
                        System.out.println(miembros.get(i).getIdMiembro() + " " +
                                miembros.get(i).getNombre()+ "\n");
                    }
                    System.out.println("Introduce el id del propietario de la moto");
                    id = scanner1.nextInt();

                    System.out.println("Introduce el precio de la moto");
                    precio = scanner1.nextFloat();

                    for (int i = 0; i < miembros.size(); i++) {
                        if (miembros.get(i).getIdMiembro() == id && (miembros.get(i).getDineroAcum() + precio <= 60000)) {
                            encontrado = true;
                            int idMoto;
                            String descripcion;
                            Scanner scanner2 = new Scanner(System.in);

                            idMoto = motos.size();
                            //System.out.println("idMoto " + idMoto);

                            System.out.println("Introduce la descripcion de la moto");
                            descripcion = scanner2.nextLine();

                            Moto moto = new Moto(idMoto, descripcion, precio);
                            motos.add(moto);
                            miembros.get(i).setMotos(moto);
                        }
                    }

                    if (encontrado == false) {
                        System.out.println("No se ha registrado la moto. Motivos: \n");
                        System.out.println("1.- El id del miembro no es correcto. \n");
                        System.out.println("2.- El miembro no puede superar el importe de 60000e en motos.");
                    }

                    break;
                //crear una cesion
                case 3:
                    Scanner scanner3 = new Scanner(System.in);

                    int idM1;
                    int idM;
                    int idM2;
                    float precioCesion = 0;

                    for (int i = 0; i < miembros.size(); i++) {
                        System.out.println(miembros.get(i).getIdMiembro() + " " +
                                miembros.get(i).getNombre()+ "\n");
                    }
                    System.out.println("Introduce el id del miembro que cede la moto");
                    idM1 = scanner3.nextInt();

                    for (int i = 0; i < miembros.size(); i++) {
                        if(miembros.get(i).getIdMiembro() == idM1){
                            for (int j = 0; j < miembros.get(i).getMotos().size(); j++) {
                                System.out.println(miembros.get(i).getMotos().get(j).getIdMoto() + 
                                        " " + miembros.get(i).getMotos().get(j).getDescripcion() +
                                        "\n");
                            }
                        }
                    }
                    System.out.println("Introduce el id de la moto que se va a ceder");
                    idM = scanner3.nextInt();

                    for (int i = 0; i < miembros.size(); i++) {
                        System.out.println(miembros.get(i).getIdMiembro() + " " +
                                miembros.get(i).getNombre()+ "\n");
                    }
                    System.out.println("Introduce el id del miembro que recibe la moto");
                    idM2 = scanner3.nextInt();

                    if ((idM1 == idM2) || (idM1 > miembros.size()) || (idM2 > miembros.size()) || (idM > motos.size())) {
                        System.out.println(" Error:");
                        System.out.println(" Alguno de los datos introducidos no existe o es el mismo");
                        break;
                    }

                    for (int i = 0; i < motos.size(); i++) {
                        if (motos.get(i).getIdMoto() == idM) {
                            precioCesion = motos.get(i).getDinero();
                        }
                    }

                    if ((miembros.get(idM2 - 1).getDineroAcum() + precioCesion) > 60000) {
                        System.out.println("No se ha registrado la moto. Motivos: \n");
                        System.out.println("1.- El id del miembro no es correcto. \n");
                        System.out.println("2.- El miembro no puede superar el importe de 60000e en motos.");
                    } else {
                        int idCesion = cesiones.size();
                        Date fecha = new Date();
                        Cesion cesion = new Cesion(idCesion, idM, idM1, idM2, fecha);
                        cesiones.add(cesion);

                        for (int i = 0; i < miembros.size(); i++) {
                            for (int j = 0; j < miembros.get(i).getMotos().size(); j++) {
                                // falta no poder ceder la moto de uno a otro mismo.
                                if (miembros.get(i).getMotos().get(j).getIdMoto() == idM) {
                                    for (int k = 0; k < miembros.size(); k++) {
                                        if (miembros.get(k).getIdMiembro() == idM2) {
                                            miembros.get(k).getMotos().add(miembros.get(i).getMotos().get(j));
                                            miembros.get(i).getMotos().remove(miembros.get(i).getMotos().get(j));
                                            System.out.println("realizado");
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;
                //mostrar miembros
                case 4:
                    //System.out.println(miembros.size());
                    for (int i = 0; i < miembros.size(); i++) {
                        miembros.get(i).outMiembro();
                    }
                    break;
                //mostrar motos
                case 5:
                    for (int i = 0; i < motos.size(); i++) {
                        motos.get(i).outMoto();
                    }
                    break;
                //mostrar cesiones
                case 6:
                    for (int i = 0; i < cesiones.size(); i++) {
                        cesiones.get(i).outCesion();
                    }
                    break;
                //finalizar el programa y guardar el fichero
                case 7:
                    
                    try{
                        archivo = new File(".\\src\\javaapplication8\\info.txt");
                        fw = new FileWriter(archivo);
                        
                        for (int i = 0; i < miembros.size(); i++) {
                            fw.write(miembros.get(i).toString());
                        }
                        for (int i = 0; i < cesiones.size(); i++) {
                            fw.write(cesiones.get(i).toString());
                        }
                        
                        
                    } catch (Exception e3){
                        e3.printStackTrace();
                    }
                    finally{
                        try{
                            fw.close();
                        }
                        catch (Exception e4){ 
                            e4.printStackTrace();
                        }
                    }
                    
                    
                    
                    
                    
                    System.out.println("Gracias por utilizar el programa");
                    System.exit(0);
                    break;
                default:
                    opcion = Menu();
                    break;
            }
        }
    }

    //funcion que implementa el menu con sus distintas opciones
    public static int Menu() {
        int opcion = -1;

        Scanner scanner = new Scanner(System.in);

        while ((opcion >= 8) || (opcion <= 0)) {
            System.out.println("=====================================");
            System.out.println("                   MENU              ");
            System.out.println("=====================================");
            System.out.println(" 1.- Registrar un nuevo miembro.");
            System.out.println(" 2.- Registrar una nueva motocicleta.");
            System.out.println(" 3.- Registrar un cesion.");
            System.out.println(" 4.- Miembros.");
            System.out.println(" 5.- Lista de motos.");
            System.out.println(" 6.- Cesiones realizadas.");
            System.out.println(" 7.- Salir del programa");
            System.out.println("=====================================");

            opcion = scanner.nextInt();

        }

        return opcion;
    }

}
