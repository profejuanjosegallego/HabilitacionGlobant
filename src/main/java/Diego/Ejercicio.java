package Diego;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ejercicio {
    //Sintaxis para aplicar los colores

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";

    //Est es la estructura principal

    static  ArrayList<HashMap<String, Object>> empleados = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true){
        mostrarMenu();
        int opcion = leerEntero("Selecciona una opción:");
        switch (opcion){
        case 1 -> registrarEmpleado();
            case 2 -> calcularSalario();
                case 3 -> mostrarEmpleados();
                    case 4 -> empleadoMayorSalario();
                        case 0 -> {
                            System.out.println(ANSI_GREEN + "Adios 🫡" + ANSI_RESET);
                            return;
                        }
            default -> System.out.println(ANSI_RED + "Opcion invalida ✖️."+ ANSI_RED + "Intenta de nuevo 😉" + ANSI_RESET);

        }
        }
    }


    //La función de mostrar menu ps la cree con void ya que no vamos a hacer operaciones con ella, solo necesito que me la muestre, el menu ps:

    static void mostrarMenu() {
        System.out.println(ANSI_CYAN + "\n=== Menú Empleados ===" + ANSI_RESET);
        System.out.println("1️⃣  Registrar empleado");
        System.out.println("2️⃣  Calcular salario mensual");
        System.out.println("3️⃣  Ver todos los empleados");
        System.out.println("4️⃣  Consultar empleado con mayor salario");
        System.out.println("0️⃣  Salir");
    }

    static int leerEntero(String mensaje){
        System.out.println(ANSI_CYAN + mensaje + "" + ANSI_RESET);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println(ANSI_RED + "Entrada inválida, Ingresa solo un número: " + ANSI_RESET);
        }
        return scanner.nextInt();
    }

    static void registrarEmpleado(){
        System.out.println(ANSI_GREEN + "\nRegistra el empleado: " + ANSI_RESET );
        int id = leerEntero("ID:");
        scanner.nextLine();
        //Validación de que no esté repetido el ID
        for (HashMap<String, Object> e : empleados){
            if ((int) e.get("id") == id){
                System.out.println(ANSI_RED + "ID ya existe. Registro Cancelado" + ANSI_RESET);
                return;
            }
        }
        //validación de ID repetido...
        System.out.println(ANSI_CYAN + "nombre" + ANSI_RESET);
        String nombre = scanner.nextLine();

        System.out.println(ANSI_CYAN + "cargo" + ANSI_RESET);
        String cargo = scanner.nextLine();

        double valorHora = leerDouble("Valor de la hora:");
        scanner.nextLine(); // limpia nuevo salto si usas nextDouble

        int horasMes = leerEntero("Cantidad de horas trabajadas al mes:");
        scanner.nextLine(); // limpia salto de nextInt

        HashMap<String, Object> emp = new HashMap<>();
        emp.put("id", id);
        emp.put("nombre".toLowerCase(), nombre);
        emp.put("cargo", cargo);
        emp.put("valorHora", valorHora);
        emp.put("horasMes", horasMes);

        empleados.add(emp);
        System.out.println(ANSI_GREEN + "Empleado registrado con éxito. " + ANSI_RESET);
    }

    static double leerDouble (String mensaje){
        System.out.println(ANSI_CYAN + mensaje + "" + ANSI_RESET);
        while (!scanner.hasNextDouble()){
            scanner.next();
            System.out.println(ANSI_RED + "Entrada inválida, ingresa un numero (decimal es posible): " + ANSI_RESET);
        }
        return scanner.nextDouble();
    }

    static void calcularSalario () {
        System.out.println(ANSI_GREEN + "\n Calcular Salario Mensual" + ANSI_RESET);
        int id = leerEntero("ID Empleado: ");
        scanner.nextLine();
        for (HashMap<String, Object> emp : empleados){
            if ((int) emp.get("id") == id){
                double salario = (double) emp.get("valorHora") * (int) emp.get ("horasMes");
                System.out.printf(ANSI_GREEN + "Salario de %s: %.2f\n" + ANSI_RESET,
                        emp.get("nombre"), salario);
                return;
            }
        }
        System.out.println(ANSI_RED + "Empleado no encontrado." + ANSI_RESET);
    }

    static void mostrarEmpleados (){
        System.out.println(ANSI_GREEN + "\nLista de empleados:" + ANSI_RESET);
        System.out.printf("%-5s %-15s %-15s %-10s %-10s %-10s\n", "ID", "Nombre", "Cargo", "ValorHora", "Horas", "Salario");
        for (HashMap<String, Object> emp : empleados){
            double salario = (double) emp.get("valorHora") * (int) emp.get("horasMes");
            System.out.printf("%-5d %-15s %-15s %-10.2f %-10d %-10.2f\n",
                    emp.get("id"), emp.get("nombre"), emp.get("cargo"),
                    emp.get("valorHora"), emp.get("horasMes"), salario);
        }
    }

    static void empleadoMayorSalario (){
        System.out.println(ANSI_GREEN + "\nEmpleado con mayor salario:" + ANSI_RESET);
        if (empleados.isEmpty()){
            System.out.println(ANSI_RED + "No hay empleados registrados. " + ANSI_RESET);
            return;
        }
        HashMap<String, Object> mayor = empleados.get(0);
        double maxSal = (double) mayor.get("valorHora") * (int) mayor.get("horasMes");
        for (HashMap<String, Object> emp : empleados){
            double salario = (double) emp.get("valorHora") * (int) emp.get("horasMes");
            if (salario > maxSal){
                maxSal = salario;
                mayor = emp;
            }
        }
        System.out.printf(ANSI_GREEN +
                        "Nombre: %s, Cargo: %s, Salario: %.2f\n" +
                        ANSI_RESET,
                mayor.get("nombre"), mayor.get("cargo"), maxSal);    }



}
