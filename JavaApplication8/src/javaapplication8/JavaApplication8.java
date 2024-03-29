/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * prueba
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

        float precioMax;

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
                } //despues comprobamos las motos
                else if (linea.contains("moto:")) {
                    int aux = 6;
                    int cont = 0;
                    for (int i = 6; i < linea.length(); i++) {
                        if ((linea.charAt(i) == ',') && (cont == 0)) {
                            moto1.setIdMoto(Integer.parseInt(linea.substring(aux, i)));
                            aux = i + 1;
                            cont++;

                        } else if ((linea.charAt(i) == ',') && (cont == 1)) {
                            moto1.setDescripcion(linea.substring(aux, i));
                            aux = i + 1;
                            cont++;
                        } else if ((linea.charAt(i) == ',') && (cont == 2)) {
                            moto1.setDinero(Float.parseFloat(linea.substring(aux, i)));
                            aux = i + 1;
                            cont++;
                        } else if (cont == 3) {
                            moto1.setOtros_gastos(Float.parseFloat(linea.substring(aux)));
                        }

                    }
                    miembros.get(miembros.size() - 1).getMotos().add(moto1);
                    motos.add(moto1);
                    moto1 = new Moto();
                } //y por ultimo las cesiones
                else if (linea.contains("cesion:")) {
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
        Scanner scannerPrecio = new Scanner(System.in);
        System.out.println("Introduce el precio maximo acumulador por miembro");
        precioMax = scannerPrecio.nextFloat();
        while (opcion != 10) {

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

                            if (precioAcum <= precioMax) {
                                Moto moto = new Moto(id, descripcion, precio, 0);
                                aux.add(moto);
                                motos.add(moto);
                            } else {
                                System.out.println("No se ha podido introducir la ultima moto.");
                                System.out.println("El precio pasa de los " + precioMax + ".\n");
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
                    float otros_gastos;

                    for (int i = 0; i < miembros.size(); i++) {
                        System.out.println(miembros.get(i).getIdMiembro() + " "
                                + miembros.get(i).getNombre() + "\n");
                    }
                    System.out.println("Introduce el id del propietario de la moto");
                    id = scanner1.nextInt();

                    System.out.println("Introduce el precio de la moto");
                    precio = scanner1.nextFloat();

                    for (int i = 0; i < miembros.size(); i++) {
                        if (miembros.get(i).getIdMiembro() == id && (miembros.get(i).getDineroAcum() + precio <= precioMax)) {
                            encontrado = true;
                            int idMoto;
                            String descripcion;
                            Scanner scanner2 = new Scanner(System.in);

                            idMoto = motos.size();
                            //System.out.println("idMoto " + idMoto);

                            System.out.println("Introduce la descripcion de la moto");
                            descripcion = scanner2.nextLine();

                            System.out.println("Introduce los gastos de la moto");
                            otros_gastos = scanner1.nextFloat();

                            Moto moto = new Moto(idMoto, descripcion, precio, otros_gastos);
                            motos.add(moto);
                            miembros.get(i).setMotos(moto);
                        }
                    }

                    if (encontrado == false) {
                        System.out.println("No se ha registrado la moto. Motivos: \n");
                        System.out.println("1.- El id del miembro no es correcto. \n");
                        System.out.println("2.- El miembro no puede superar el importe de " + precioMax + "e en motos.");
                    }

                    break;
                case 3:
                    scanner1 = new Scanner(System.in);
                    for (int i = 0; i < motos.size(); i++) {
                        motos.get(i).outMoto();
                    }
                    System.out.println("Introduce la moto: \n");
                    id = scanner1.nextInt();
                    System.out.println("Introduce los gastos: \n");
                    otros_gastos = scanner1.nextFloat();

                    motos.get(id - 1).setOtros_gastos(otros_gastos);
                    break;

                //crear una cesion
                case 4:
                    Scanner scanner2 = new Scanner(System.in);
                    int idMiembro1;
                    int idMiembro2;
                    int idMoto;

                    for (int i = 0; i < miembros.size(); i++) {
                        System.out.println(miembros.get(i).getIdMiembro() + " " + miembros.get(i).getNombre());
                    }
                    System.out.println("Introduce el id del miembro que cede la moto");
                    idMiembro1 = Integer.parseInt(scanner2.nextLine());
                    System.out.println("");
                    for (int i = 0; i < miembros.size(); i++) {
                        if (miembros.get(i).getIdMiembro() == idMiembro1){
                            for (int j = 0; j < miembros.get(i).getMotos().size(); j++) {
                                System.out.println(miembros.get(i).getMotos().get(j).getIdMoto() + " " + miembros.get(i).getMotos().get(j).getDescripcion());
                            }
                        }
                    }
                    System.out.println("Intoduce el id de la moto que quieres ceder");
                    idMoto = Integer.parseInt(scanner2.nextLine());
                    System.out.println("");
                    
                    for (int i = 0; i < miembros.size(); i++) {
                        if (miembros.get(i).getIdMiembro() != idMiembro1){
                            System.out.println(miembros.get(i).getIdMiembro() + " " + miembros.get(i).getNombre());
                        }
                    }
                    System.out.println("Introduce el id del miembro al que se le cede la moto");
                    idMiembro2 = Integer.parseInt(scanner2.nextLine());
                    System.out.println("");
                    
                    if(!realizarCesion(idMiembro1, idMiembro2, idMoto, precioMax)){
                        System.out.println("Error al realizar la sesion");
                    }
                    break;
                //mostrar miembros
                case 5:
                    //System.out.println(miembros.size());
                    for (int i = 0; i < miembros.size(); i++) {
                        miembros.get(i).outMiembro();
                    }
                    break;
                //mostrar motos
                case 6:
                    for (int i = 0; i < motos.size(); i++) {
                        motos.get(i).outMoto();
                    }
                    break;
                //mostrar cesiones
                case 7:
                    if (cesiones.isEmpty()) {
                        System.out.println("No se ha realizado ninguna cesion");
                    } else {
                        for (int i = 0; i < cesiones.size(); i++) {
                            cesiones.get(i).outCesion();
                        }
                    }

                    break;
                //Eliminar Miembro
                case 8:
                    scanner2 = new Scanner(System.in);
                    int idMiembroEliminar;
                    int idMotoCeder;
                    int idMiembroCeder;
                    for (int i = 0; i < miembros.size(); i++) {
                        System.out.println(miembros.get(i).getIdMiembro() + " " + miembros.get(i).getNombre());
                    }
                    System.out.println("Introduce el id del miembro que quieres eliminar");
                    idMiembroEliminar = Integer.parseInt(scanner2.nextLine());
                    System.out.println("");
                    
                    for (int i = 0; i < miembros.size(); i++) {
                        if (miembros.get(i).getIdMiembro() == idMiembroEliminar){
                            if(miembros.get(i).getMotos().isEmpty()){
                                miembros.remove(i);
                                for (int j = i; j < miembros.size(); j++) {
                                    miembros.get(j).setIdMiembro(miembros.get(j).getIdMiembro() - 1);
                                }
                            } else {
                                System.out.println(" El miembro no puede tener ninguna moto en su posesion ");
                                
                                while(!miembros.get(i).getMotos().isEmpty()){
                                    System.out.println("Debes ceder " + miembros.get(i).getMotos().size() + " motos");
                                    
                                    for (int j = 0; j < miembros.get(i).getMotos().size(); j++) {
                                        System.out.println(miembros.get(i).getMotos().get(j).getIdMoto() + " " + miembros.get(i).getMotos().get(j).getDescripcion());
                                    }
                                    System.out.println("Introduce el id de la moto que quieres ceder");
                                    idMotoCeder = Integer.parseInt(scanner2.nextLine());
                                    System.out.println("");
                                    for (int j = 0; j < miembros.size(); j++) {
                                        if (miembros.get(j).getIdMiembro() != idMiembroEliminar){
                                            System.out.println(miembros.get(j).getIdMiembro() + " " + miembros.get(j).getNombre());
                                        }
                                    }
                                    System.out.println("Introduce el id del miembro al que quieres ceder la moto");
                                    idMiembroCeder = Integer.parseInt(scanner2.nextLine());
                                    System.out.println("");
                                    
                                    realizarCesion(idMiembroEliminar, idMiembroCeder, idMotoCeder, precioMax);      
                                }
                                miembros.remove(i);
                            }
                        }
                    }
                    break;
                case 9:
                    int posicion = 0;
                    int posicion1 = 0;
                    int max = -1;
                    int miembroMasCesiones;
                    ArrayList<Integer> contadorCesiones = new ArrayList<>();
                    for (int i = 0; i < miembros.size(); i++) {
                        contadorCesiones.add(0);
                    }
                    
                    for (int i = 0; i < cesiones.size(); i++) {
                        miembroMasCesiones = cesiones.get(i).getIdMiembro2();
                        contadorCesiones.set(miembroMasCesiones - 1, (contadorCesiones.get(miembroMasCesiones - 1) + 1));
                    }
                    
                    for (int i = 0; i < contadorCesiones.size(); i++) {
                        if(max < contadorCesiones.get(i)){
                            max = contadorCesiones.get(i);
                            posicion = i;
                        }
                    }
                    
                    for (int i = 0; i < contadorCesiones.size(); i++) {
                        System.out.println(contadorCesiones.get(i));
                    }
                    
                    contadorCesiones.remove(posicion);
                    for (int i = 0; i < contadorCesiones.size(); i++) {
                        if(max < contadorCesiones.get(i)){
                            max = contadorCesiones.get(i);
                            posicion1 = i + 1;
                        }
                    }
                    System.out.println("El miembro con mas cesiones es el miembro, id: " + miembros.get(posicion).getIdMiembro() + " " + miembros.get(posicion).getNombre());
                    System.out.println("El segundo miembro con mas cesiones es el miembro, id: " + miembros.get(posicion1).getIdMiembro() + " " + miembros.get(posicion1).getNombre());
                    break;
                //finalizar el programa y guardar el fichero
                case 10:
                    try {
                        archivo = new File(".\\src\\javaapplication8\\info.txt");
                        fw = new FileWriter(archivo);

                        for (int i = 0; i < miembros.size(); i++) {
                            fw.write(miembros.get(i).toString());
                        }
                        for (int i = 0; i < cesiones.size(); i++) {
                            fw.write(cesiones.get(i).toString());
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    } finally {
                        try {
                            fw.close();
                        } catch (Exception e4) {
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

        while ((opcion >= 11) || (opcion <= 0)) {
            System.out.println("=====================================");
            System.out.println("                   MENU              ");
            System.out.println("=====================================");
            System.out.println(" 1.- Registrar un nuevo miembro.");
            System.out.println(" 2.- Registrar una nueva motocicleta.");
            System.out.println(" 3.- Añadir gastos a una moto.");
            System.out.println(" 4.- Registrar un cesion.");
            System.out.println(" 5.- Miembros.");
            System.out.println(" 6.- Lista de motos.");
            System.out.println(" 7.- Cesiones realizadas.");
            System.out.println(" 8.- Eliminar Miembro.");
            System.out.println(" 9.- Miembros con mas cesiones");
            System.out.println(" 10.- Salir del programa");
            System.out.println("=====================================");

            opcion = scanner.nextInt();

        }

        return opcion;
    }

    private static boolean realizarCesion(int idMiembro1, int idMiembro2, int idMoto, float precioMax) {
        boolean posible = false;
        boolean hecho = false;
        float precioMoto = 0;
        float precioMiembro2 = 0;
        float precioTotal;
        
        if ((idMiembro1 > miembros.size()) || (idMiembro2 > miembros.size()) || (idMoto > motos.size())){
        return false;
        }
        for (int i = 0; i < motos.size(); i++) {
            if (motos.get(i).getIdMoto() == idMoto){
                precioMoto = motos.get(i).getDinero();
            }
        }
        
        for (int i = 0; i < miembros.size(); i++) {
            if(miembros.get(i).getIdMiembro() == idMiembro2){
                precioMiembro2 = miembros.get(i).getDineroAcum();
            }
        }
        
        precioTotal = precioMoto + precioMiembro2;
        
        if(precioTotal <= precioMax){
            posible = true;
        } else {
            if (idMiembro1 != idMiembro2){
                posible = true;
            } else {
                for (int i = 0; i < miembros.size(); i++) {
                    if (miembros.get(i).getIdMiembro() == idMiembro1){
                        for (int j = 0; j < miembros.get(j).getMotos().size(); j++) {
                                if(miembros.get(i).getMotos().get(j).getIdMoto() == idMoto){
                                    posible = true;
                                }
                        }
                    }
                }
            }
        }
        
        if (posible){
            for (int i = 0; i < miembros.size(); i++) {
                if(miembros.get(i).getIdMiembro() == idMiembro1){
                    for (int j = 0; j < miembros.get(i).getMotos().size(); j++) {
                        if (miembros.get(i).getMotos().get(j).getIdMoto() == idMoto){
                            for (int k = 0; k < miembros.size(); k++) {
                                if(miembros.get(k).getIdMiembro() == idMiembro2){
                                    miembros.get(k).getMotos().add(miembros.get(i).getMotos().get(j));
                                    miembros.get(i).getMotos().remove(miembros.get(i).getMotos().get(j));
                                    Cesion cesion = new Cesion(cesiones.size(), idMoto, idMiembro1, idMiembro2, null);
                                    cesiones.add(cesion);
                                    hecho = true;
                                    System.out.println("Hecho");
                                }
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Ha habido algun error en la introduccion de los datos");
        }
        return hecho;
    }
}
