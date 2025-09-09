package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    // Colores ANSI
    static final String GREEN = "\u001B[32m";
    static final String RED = "\u001B[31m";
    static final String BLUE = "\u001B[34m";
    static final String YELLOW = "\u001B[33m";
    static final String CYAN = "\u001B[36m";
    static final String RESET = "\u001B[0m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<HashMap<String, Object>> empleados = new ArrayList<>();

        int opcion;

        do {
            System.out.println(BLUE + "\n📋 MENÚ DE GESTIÓN DE EMPLEADOS" + RESET);
            System.out.println(GREEN + "1️⃣ Registrar empleado");
            System.out.println("2️⃣ Calcular salario mensual de un empleado");
            System.out.println("3️⃣ Ver lista de todos los empleados");
            System.out.println("4️⃣ Consultar el empleado con mayor salario");
            System.out.println("5️⃣ Salir" + RESET);
            System.out.print(YELLOW + "\n👉 Selecciona una opción: " + RESET);

            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1: // Registrar empleado
                    HashMap<String, Object> empleado = new HashMap<>();

                    System.out.print("🆔 Ingrese el ID del empleado: ");
                    empleado.put("id", scanner.nextInt());
                    scanner.nextLine(); // limpiar buffer

                    System.out.print("👤 Ingrese nombre: ");
                    empleado.put("nombre", scanner.nextLine());

                    System.out.print("💼 Ingrese cargo: ");
                    empleado.put("cargo", scanner.nextLine());

                    System.out.print("💲 Ingrese valor base por hora: ");
                    empleado.put("valorHora", scanner.nextFloat());

                    System.out.print("⏱️ Ingrese número de horas trabajadas por mes: ");
                    empleado.put("horas", scanner.nextInt());

                    empleados.add(empleado);
                    System.out.println(GREEN + "✅ Empleado registrado con éxito." + RESET);
                    break;

                case 2: // Calcular salario
                    if (empleados.isEmpty()) {
                        System.out.println(RED + "📭 No hay empleados registrados." + RESET);
                        break;
                    }

                    System.out.print("🔍 Ingrese el ID del empleado para calcular salario: ");
                    int idBuscar = scanner.nextInt();
                    boolean encontrado = false;

                    for (HashMap<String, Object> emp : empleados) {
                        if ((int) emp.get("id") == idBuscar) {
                            float salario = (float) emp.get("valorHora") * (int) emp.get("horas");
                            System.out.println(CYAN + "💸 Salario mensual de " + emp.get("nombre")
                                    + " (" + emp.get("cargo") + ") es: $" + salario + RESET);
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println(RED + "❌ Empleado no encontrado." + RESET);
                    }
                    break;

                case 3: // Ver lista de empleados
                    if (empleados.isEmpty()) {
                        System.out.println(RED + "📭 No hay empleados registrados." + RESET);
                    } else {
                        System.out.println(GREEN + "\n📃 Lista de empleados:\n" + RESET);
                        System.out.printf("%-5s %-15s %-15s %-10s %-5s\n", "ID", "Nombre", "Cargo", "ValorHora", "Horas");
                        System.out.println("---------------------------------------------------------");

                        for (HashMap<String, Object> emp : empleados) {
                            System.out.printf("%-5d %-15s %-15s %-10.2f %-5d\n",
                                    emp.get("id"),
                                    emp.get("nombre"),
                                    emp.get("cargo"),
                                    emp.get("valorHora"),
                                    emp.get("horas")
                            );
                        }
                    }
                    break;

                case 4: // Empleado con mayor salario
                    if (empleados.isEmpty()) {
                        System.out.println(RED + "📭 No hay empleados registrados." + RESET);
                    } else {
                        HashMap<String, Object> mayor = null;
                        float maxSalario = 0;

                        for (HashMap<String, Object> emp : empleados) {
                            float salario = (float) emp.get("valorHora") * (int) emp.get("horas");
                            if (mayor == null || salario > maxSalario) {
                                maxSalario = salario;
                                mayor = emp;
                            }
                        }

                        System.out.println(YELLOW + "\n🏆 El empleado con mayor salario es:" + RESET);
                        System.out.println("👤 Nombre: " + mayor.get("nombre"));
                        System.out.println("💼 Cargo: " + mayor.get("cargo"));
                        System.out.println("💰 Salario: $" + maxSalario);
                    }
                    break;

                case 5: // Salir
                    System.out.println(RED + "👋 Saliendo del sistema... ¡Hasta pronto!" + RESET);
                    break;

                default:
                    System.out.println(RED + "❌ Opción inválida. Intente de nuevo." + RESET);
            }

        } while (opcion != 5);

        scanner.close();
    }
}
