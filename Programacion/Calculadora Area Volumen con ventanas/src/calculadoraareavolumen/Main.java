package calculadoraareavolumen;

import java.util.Scanner;
import ventanas.*;

public class Main {

    public static void main(String[] args) {
        
        Menu ventanaMenu = new Menu();
        
        ventanaMenu.setVisible(true);
      
      
//        boolean fin = false;
//        String opcion;
//        Scanner lectura = new Scanner(System.in);
//
//        System.out.println("Bienvenido a la calculadora de areas y volumenes");
//
//        while (fin == false) {
//           System.out.println("-----------------------------------------------");
//            System.out.println("Escoje que quieres hacer: |cilindro||cubo||esfera||Q(salir)| ");
//            opcion = lectura.nextLine().toLowerCase();
//
//            String opcion2;
//            double radio;
//            double altura;
//            double ancho;
//            double profundidad;
//
//            switch (opcion) {
//
//                case "cilindro" -> {
//                    System.out.print("Radio: ");
//                    radio = Double.parseDouble(lectura.nextLine());
//                    System.out.print("Altura: ");
//                    altura = Double.parseDouble(lectura.nextLine());
//                    cilindro cilindro1 = new cilindro(radio, altura);
//                    System.out.println("-----------------------------------------------");
//                    System.out.println("|area||altura|");
//
//                    opcion2 = lectura.nextLine().toLowerCase();
//                    System.out.println("-----------------------------------------------");
//                    if (opcion2.equals("area")) {
//                        cilindro1.area();
//                    } else {
//                        cilindro1.volumen();
//                    }
//                }
//                case "esfera" -> {
//                    System.out.print("Radio: ");
//                    radio = Double.parseDouble(lectura.nextLine());
//                    esfera esfera1 = new esfera(radio);
//                    System.out.println("-----------------------------------------------");
//                    System.out.println("|area||altura| ");
//
//                    opcion2 = lectura.nextLine().toLowerCase();
//                    System.out.println("-----------------------------------------------");
//
//                    if (opcion2.equals("area")) {
//                        esfera1.area();
//                    } else {
//                        esfera1.volumen();
//                    }
//                }
//                case "cubo" -> {
//                    System.out.print("Altura: ");
//                    altura = Double.parseDouble(lectura.nextLine());
//                    System.out.print("Ancho: ");
//                    ancho = Double.parseDouble(lectura.nextLine());
//                    System.out.print("Profundidad: ");
//                    profundidad = Double.parseDouble(lectura.nextLine());
//
//                    cubo cubo1 = new cubo(altura, ancho, profundidad);
//                    System.out.println("-----------------------------------------------");
//                    System.out.println("|area||altura| ");
//
//                    opcion2 = lectura.nextLine().toLowerCase();
//                    System.out.println("-----------------------------------------------");
//
//                    if (opcion2.equals("area")) {
//                        cubo1.area();
//                    } else {
//                        cubo1.volumen();
//                    }
//
//                }
//                case "q" -> {
//                    System.out.println("-----------------------------------------------");
//                    System.out.println("Adios");
//                    fin = true;
//                }
//
//                default ->
//                    System.out.println("Error");
//                    
//            }
//
//        }

    }

}
