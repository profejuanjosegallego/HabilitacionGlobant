package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    //Funciones

    public static Float calcular(float nHorasMes, float valorBaseHora) {
        return nHorasMes * valorBaseHora;
    }

    public static void lista(HashMap<Integer, String> empleados) {
        for (Integer id : empleados.keySet()) {
            System.out.println(id + " " + empleados.get(id));
        }
        System.out.println("\u001B[32m " + empleados);
    }

    public static void registrar(HashMap<Integer, String> empleados, Scanner sc) {
        System.out.print("Ingrese el ID del nuevo empleado: ");
        int idEmpleado = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el nombre del empleado nuevo: ");
        String nombreEmpleado = sc.nextLine();
        empleados.put(idEmpleado, nombreEmpleado);
        System.out.println("Empleado registrado ✅");
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        byte opcionMenu;

        System.out.println("\u001B[31mBienvenido al gestor de empleados 📊");
        System.out.println("\u001B[31m========================================");

        // Mapa y array
        HashMap<Integer, String> empleados = new HashMap<>();
        empleados.put(1, "Javier Suarez");
        empleados.put(2, "Pablito Osorio");
        empleados.put(3, "Daniel Flores");
        empleados.put(4, "Roberto Bolaños");


        do {
            System.out.println("\u001B[32m Menú 🔎" +
                    "\n1. Lista de empleados 📋" +
                    "\n2. Calcular salario mensual 💵🫰" +
                    "\n3. Registrar nuevo empleado 📑" +
                    "\n4. Salir 👋");
            System.out.print("Seleccione una opción: ");
            opcionMenu = sc.nextByte();
            sc.nextLine();

            switch (opcionMenu) {
                case 1:
                    System.out.println("\n📋 Lista de empleados:");
                    lista(empleados);
                    break;

                case 2:
                    System.out.print("Ingrese el número de horas laboradas mensuales: ");
                    float nHorasMes = sc.nextFloat();
                    System.out.print("Ingrese el valor por hora: ");
                    float valorHora = sc.nextFloat();
                    float salarioMensual = calcular(nHorasMes, valorHora);
                    System.out.println("Salario mensual: " + salarioMensual);
                    break;

                case 3:
                    registrar(empleados, sc);
                    break;

                case 4:
                    System.out.println("Bye 😪");
                    break;
            }

            } while (opcionMenu != 4);

        sc.close();


        }
    }

