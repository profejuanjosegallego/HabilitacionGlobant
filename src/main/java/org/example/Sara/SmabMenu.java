package org.example.Sara;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class SmabMenu {

        // Colores ANSI para hacer la consola más visual:
        public static final String RESET = "\u001B[0m";
        public static final String NEGRITA = "\u001B[1m";
        public static final String ROJO = "\u001B[31m";
        public static final String AZUL = "\u001B[34m";

        // Colores degradado naranja → amarillo:
        public static final String NARANJA = "\u001B[38;5;202m";
        public static final String NARANJA_CLARO = "\u001B[38;5;208m";
        public static final String DORADO = "\u001B[38;5;214m";
        public static final String AMARILLO_OSCURO = "\u001B[38;5;220m";
        public static final String AMARILLO = "\u001B[38;5;226m";

        // Aquí guardo todos los empleados. El ID es la clave.
        static HashMap<Integer, String[]> empleados = new HashMap<>();

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int opcion = -1;

            // Bucle principal del menú.
            do {
                try {
                    mostrarMenu();
                    System.out.print("\nElige una opción: ");
                    opcion = sc.nextInt();
                    sc.nextLine(); // Evito errores.

                    // Posibles opciones:
                    switch (opcion) {
                        case 1 -> registrarEmpleado(sc);
                        case 2 -> calcularSalario(sc);
                        case 3 -> listarEmpleados();
                        case 4 -> empleadoMayorSalario();
                        case 0 -> System.out.println(ROJO + "Saliendo del programa..." + RESET);
                        default -> System.out.println(ROJO + "Opción inválida, intenta de nuevo." + RESET);
                    }
                } catch (InputMismatchException e) {
                    // Si el usuario mete letras en lugar de números.
                    System.out.println(ROJO + "Error: debes ingresar un número." + RESET);
                    sc.nextLine(); // Si hago esto se evitan errores.
                }
            } while (opcion != 0);

            sc.close();
        }

        // Muestra el menú en consola con colores:
        public static void mostrarMenu() {
            System.out.println(AZUL + NEGRITA + "\n==== MENÚ PRINCIPAL ====" + RESET);
            System.out.println(NARANJA + "1. Registrar empleado" + RESET);
            System.out.println(NARANJA_CLARO + "2. Calcular salario mensual de un empleado" + RESET);
            System.out.println(DORADO + "3. Ver lista de todos los empleados" + RESET);
            System.out.println(AMARILLO_OSCURO + "4. Consultar el empleado con mayor salario" + RESET);
            System.out.println(ROJO + "0. Salir del programa" + RESET);
        }

        // Registro de un nuevo empleado:
        public static void registrarEmpleado(Scanner sc) {
            try {
                System.out.println(AZUL + "\n=== Registro de Empleado ===" + RESET);

                System.out.print("ID (solo números, ej: 123): ");
                int id = sc.nextInt();
                sc.nextLine();

                // Verifico si ya existe ese ID.
                if (empleados.containsKey(id)) {
                    System.out.println(ROJO + "\nError: ya existe un empleado con ese ID." + RESET);
                    return;
                }

                // Pido los demás datos.
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Cargo: ");
                String cargo = sc.nextLine();

                System.out.print("Valor base de hora (ej: 10,50): ");
                double valorHora = sc.nextDouble();

                System.out.print("Número de horas trabajadas al mes: ");
                int horasMes = sc.nextInt();

                // Guardo todo en un arreglo de String. (No sé porque sale verde :c)
                String[] datos = {nombre, cargo, String.valueOf(valorHora), String.valueOf(horasMes)};
                empleados.put(id, datos);

                System.out.println(NARANJA_CLARO + "\n¡Empleado registrado con éxito!" + RESET);

            } catch (InputMismatchException e) {
                // Si el usuario mete mal un número.
                System.out.println(ROJO + "Error: tipo de dato inválido." + RESET);
                sc.nextLine();
            }
        }

        // Calculo el salario de un empleado por ID:
        public static void calcularSalario(Scanner sc) {
            if (empleados.isEmpty()) {
                System.out.println(ROJO + "No hay empleados registrados." + RESET);
                return;
            }

            try {
                System.out.print("Ingresa el ID del empleado: ");
                int id = sc.nextInt();

                if (empleados.containsKey(id)) {
                    String[] datos = empleados.get(id);
                    double valorHora = Double.parseDouble(datos[2]);
                    int horasMes = Integer.parseInt(datos[3]);
                    double salario = valorHora * horasMes;

                    System.out.println(AMARILLO + "Salario mensual de " + datos[0] + " (" + datos[1] + "): " + salario + RESET);
                } else {
                    // Si no encuentra el empleado.
                    System.out.println(ROJO + "Empleado no encontrado." + RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ROJO + "Error: debes ingresar un número válido." + RESET);
                sc.nextLine();
            }
        }

        // Listar todos los empleados en formato tabla.
        public static void listarEmpleados() {
            if (empleados.isEmpty()) {
                System.out.println(ROJO + "No hay empleados registrados." + RESET);
                return;
            }

            System.out.println(DORADO + NEGRITA + "\n=== Lista de Empleados ===" + RESET);
            System.out.printf("%-5s %-15s %-15s %-12s %-10s\n", "ID", "Nombre", "Cargo", "ValorHora", "HorasMes");
            System.out.println("-------------------------------------------------------");

            for (Map.Entry<Integer, String[]> entry : empleados.entrySet()) {
                int id = entry.getKey();
                String[] datos = entry.getValue();
                System.out.printf("%-5d %-15s %-15s %-12s %-10s\n", id, datos[0], datos[1], datos[2], datos[3]);
            }
        }

        // Busca al empleado con mayor salario.
        public static void empleadoMayorSalario() {
            if (empleados.isEmpty()) {
                System.out.println(ROJO + "No hay empleados registrados." + RESET);
                return;
            }

            int idMax = -1;
            double maxSalario = 0;

            // Recorro todos los empleados.
            for (Map.Entry<Integer, String[]> entry : empleados.entrySet()) {
                String[] datos = entry.getValue();
                double valorHora = Double.parseDouble(datos[2]);
                int horasMes = Integer.parseInt(datos[3]);
                double salario = valorHora * horasMes;

                if (salario > maxSalario) {
                    maxSalario = salario;
                    idMax = entry.getKey();
                }
            }

            // Muestro el que tiene mayor salario.
            String[] datos = empleados.get(idMax);
            System.out.println(AMARILLO_OSCURO + "\nEmpleado con mayor salario:" + RESET);
            System.out.println("Nombre: " + datos[0]);
            System.out.println("Cargo: " + datos[1]);
            System.out.println("Salario: " + maxSalario);
        }
    }
