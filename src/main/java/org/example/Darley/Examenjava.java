package org.example.Darley;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Examenjava {
    /*📘 Examen de Programación Básica en Java
🎯 Objetivo
Construir un algoritmo en Java (sin POO) que gestione empleados usando ArrayList, HashMap y estructuras de control, interactuando a través de la consola con un menú de opciones.

👉 Se evaluará la organización del código, el uso correcto de estructuras de datos, la creatividad en la consola (colores y emojis) y la calidad del backlog en forma de Historias de Usuario (HU).

📋 Requisitos del Programa
El sistema debe ofrecer el siguiente menú (en consola):

1️⃣ Registrar empleado

Datos: id, nombre, cargo, valor base de hora, número horas trabajadas al mes.
2️⃣ Calcular salario mensual de un empleado

Fórmula: salario = valorBaseHora * numeroHorasMes.
3️⃣ Ver lista de todos los empleados

Mostrar en tabla o formato amigable en consola.
4️⃣ Consultar el empleado con mayor salario

Indicar nombre, cargo y salario total.
0️⃣ Salir del programa

⚡ Se espera un uso excelente de la consola, aplicando:

Colores ANSI (\u001B[32m para verde, \u001B[31m para rojo, etc.).
Emojis que hagan más clara la interacción con el usuario.*/
    static HashMap<String, Double> empleados = new HashMap<>();
    static Scanner leerdata = new Scanner(System.in);
    static String rojo = "\u001B[31m";
    static String quitar = "\u001B[0m";
    static String blue = "\u001B[34m";
    static String amarillo= "\u001B[33m";
    static String cyan="\u001B[46m";

    public static void main(String[] args) {
        int opcion = 0;
        while (opcion != 5) {
            try {
                System.out.println(amarillo + "***********************************************");
                System.out.println(amarillo+"*** Sistema de Registro de Empleados ***🧑‍💼"+quitar);
                System.out.println(rojo+"""
                         \t 1) Registrar empleado
                         \t 2) Calcular salario mensual de un empleado
                         \t 3) Ver lista de todos los empleados
                         \t 4) Consultar el empleado con mayor salario
                         \t 5) Salir del programa
                        """);
                System.out.print(quitar+"Ingrese la opción: "+blue);
                opcion = leerdata.nextInt();
                leerdata.nextLine();

                switch (opcion) {
                    case 1 -> registrarEmpleado();
                    case 2 -> calcularSalarioMensual();
                    case 3 -> verListaEmpleados();
                    case 4 -> empleadoMayorSalario();
                    case 5 -> salirPrograma();
                    default -> System.out.println("Opción inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                leerdata.nextLine();
            }
        }
    }

    static void registrarEmpleado() {
        System.out.println("Registro empleado");
        System.out.print("Digite nombre del empleado: ");
        String nombre = leerdata.nextLine();
        System.out.print("Digite cargo del empleado: ");
        String cargo = leerdata.nextLine();
        System.out.print("Digite valor base de la hora: ");
        int valorBaseHora = leerdata.nextInt();
        System.out.print("Digite número de horas trabajadas en el mes: ");
        int horasTrabajadasMes = leerdata.nextInt();
        leerdata.nextLine();

        double salario = valorBaseHora * horasTrabajadasMes;
        empleados.put(nombre + " (" + cargo + ")", salario);

        System.out.println(cyan+"✅ Empleado registrado exitosamente."+quitar);
    }

    static void calcularSalarioMensual() {
        System.out.println("Calcular Salario Mensual");
    }

    static void verListaEmpleados() {
        System.out.println("📋 Lista de empleados:");
        for (var entry : empleados.entrySet()) {
            System.out.println(entry.getKey() + " - Salario: " + entry.getValue());
        }
    }

    static void empleadoMayorSalario() {
        System.out.println("🔎 Empleado con mayor salario:");
        String mejorEmpleado = null;
        double mayorSalario = 0;
        for (var entry : empleados.entrySet()) {
            if (entry.getValue() > mayorSalario) {
                mejorEmpleado = entry.getKey();
                mayorSalario = entry.getValue();
            }
        }
        if (mejorEmpleado != null) {
            System.out.println(mejorEmpleado + " con salario: " + mayorSalario);
        } else {
            System.out.println("No hay empleados registrados.");
        }
    }

    static void salirPrograma() {
        System.out.println("✅ Su búsqueda ha finalizado");
    }
}
