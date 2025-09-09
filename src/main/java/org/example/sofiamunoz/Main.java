package org.example.sofiamunoz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static final String ROJO = "\u001B[31m";
    static final String Azul = "\u001B[34m";

    static ArrayList<HashMap<String, Object>> empleados = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarEmpleado();
                    break;
                case 2:
                        calcularSalario();
                    break;
                case 3:
                    listarEmpleados();
                    break;
                case 4:
                    empleadoMayorSalario();
                    break;
                case 0:
                    System.out.println(Azul + "👍 Saliendo del sistema...Por espere un momento.");
                    salir = true;
                    break;
                default:
                    System.out.println(ROJO + "❌ Opción no válida.");
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println(Azul + " 📋 MENÚ DE GESTIÓN DE EMPLEADOS ");
        System.out.println("1️⃣ Registrar empleado");
        System.out.println("2️⃣ Calcular salario mensual de un empleado");
        System.out.println("3️⃣ Ver lista de todos los empleados");
        System.out.println("4️⃣ Consultar el empleado con mayor salario");
        System.out.println("0️⃣ Salir del programa");
        System.out.print(Azul + "👉 Elige una opción: ");
    }

    public static void registrarEmpleado() {
        HashMap<String, Object> empleado = new HashMap<>();

        System.out.print("🆔 Ingrese ID: ");
        String id = scanner.nextLine();
        for (HashMap<String, Object> emp : empleados) {
            if (emp.get("id").equals(id)) {
                System.out.println(ROJO + "❌ Ya existe un empleado con ese ID.");
                return;
            }
        }
        empleado.put("id", id);
        System.out.print("👤 Nombre: ");
        empleado.put("nombre", scanner.nextLine());
        System.out.print("💼 Cargo: ");
        empleado.put("cargo", scanner.nextLine());
        System.out.print("💲 Valor base por hora: ");
        empleado.put("valorHora", scanner.nextDouble());
        System.out.print("⏱️ Horas trabajadas al mes: ");
        empleado.put("horas", scanner.nextInt());
        scanner.nextLine();

        empleados.add(empleado);
        System.out.println(Azul + "✅ Empleado registrado con éxito.");
    }
    public static void calcularSalario() {
        System.out.print("🔍 Ingrese el ID del empleado: ");
        String buscarId = scanner.nextLine();

        for (HashMap<String, Object> emp : empleados) {
            if (emp.get("id").equals(buscarId)) {
                double salario = (double) emp.get("valorHora") * (int) emp.get("horas");
                System.out.println("✅ Salario mensual de " + emp.get("nombre") +
                        " (" + emp.get("cargo") + "): " + salario);
                return;
            }
        }
        System.out.println(ROJO + "❌ Empleado no encontrado." );
    }


    public static void listarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println(ROJO + "❌ No hay empleados registrados.");
            return;
        }

        System.out.println(Azul + "🔰 LISTA DE EMPLEADOS:");
        System.out.printf("%-10s %-15s %-15s %-10s\n", "🆔 ID", "👤 Nombre", "💼 Cargo", "💵 Salario");

        for (HashMap<String, Object> emp : empleados) {
            double salario = (double) emp.get("valorHora") * (int) emp.get("horas");
            System.out.printf("%-10s %-15s %-15s %-10.2f\n",
                    emp.get("id"), emp.get("nombre"), emp.get("cargo"), salario);
        }
    }
    public static void empleadoMayorSalario() {
        if (empleados.isEmpty()) {
            System.out.println(ROJO + "❌ No hay empleados registrados." );
            return;
        }

        HashMap<String, Object> mayor = empleados.get(0);
        double maxSalario = (double) mayor.get("valorHora") * (int) mayor.get("horas");

        for (HashMap<String, Object> emp : empleados) {
            double salario = (double) emp.get("valorHora") * (int) emp.get("horas");
            if (salario > maxSalario) {
                mayor = emp;
                maxSalario = salario;
            }
        }

        System.out.println( "🏆 Empleado con mayor salario:" );
        System.out.println("👤 Nombre: " + mayor.get("nombre"));
        System.out.println("💼 Cargo: " + mayor.get("cargo"));
        System.out.println("💵 Salario: " + maxSalario);
    }
}













