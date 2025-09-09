import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random numeroId = new Random();
        Set<Integer> idAsignados = new HashSet<>();

        ArrayList<ArrayList<String>> empleados = new ArrayList<>();
        HashMap<Integer, Integer> empleadosIndex = new HashMap<>(); // ID -> índice en ArrayList

        int idMinimo = 1, idMaximo = 999;
        boolean menu = true;

        while (menu) {
            System.out.println("\n \u001B[38;5;196m>─────────⇌•• MENÚ PRINCIPAL DE EMPLEADOS ••⇋────────────<\u001B[38;5;196m");
            System.out.println("\u001B[38;5;202m1. Registrar empleado 🧑‍💼\u001B[38;5;202m");
            System.out.println("\u001B[38;5;220m2. Calculadora de salario mensual 💰\u001B[38;5;220m");
            System.out.println("\u001B[38;5;46m3. Lista de todos los empleados 📑\u001B[38;5;46m");
            System.out.println("\u001B[38;5;51m4. Empleado con mayor salario 👑\u001B[38;5;51m");
            System.out.println("\u001B[38;5;27m0. Salir 🚪\u001B[38;5;27m");
            System.out.print("\n \u001B[38;5;201mSelecciona una opción 👉 \u001B[38;5;201m");

            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese su nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Qué cargo tiene: ");
                    String cargo = sc.nextLine();

                    System.out.print("Valor de la hora: ");
                    String valorHoras = sc.nextLine();

                    System.out.print("Cuántas horas ha trabajado: ");
                    String horasTrabajadas = sc.nextLine();

                    //Generador de ID unico
                    int id;
                    do {
                        id = idMinimo + numeroId.nextInt(idMaximo - idMinimo + 1);
                    } while (idAsignados.contains(id));
                    idAsignados.add(id);

                    // Informacion de los empleados guadada
                    ArrayList<String> empleado = new ArrayList<>();
                    empleado.add(String.valueOf(id));
                    empleado.add(nombre);
                    empleado.add(cargo);
                    empleado.add(valorHoras);
                    empleado.add(horasTrabajadas);

                    empleados.add(empleado);
                    empleadosIndex.put(id, empleados.size() - 1);

                    System.out.println("Empleado registrado con ID: " + id);
                    System.out.println("\u001B[38;5;220m━━─┉┈┈◈❖◈┈┈┉─━━\u001B[38;5;220m");
                }

                case 2 -> {
                    System.out.print("Ingrese su ID: ");
                    int idBuscado = sc.nextInt();
                    sc.nextLine();

                    if (empleadosIndex.containsKey(idBuscado)) {
                        int index = empleadosIndex.get(idBuscado);
                        ArrayList<String> emp = empleados.get(index);
                        String nombre = emp.get(1);
                        String cargo = emp.get(2);
                        int valorHora = Integer.parseInt(emp.get(3));
                        int horas = Integer.parseInt(emp.get(4));
                        int salario = valorHora * horas;

                        System.out.println("Empleado: " + nombre);
                        System.out.println("Cargo: " + cargo);
                        System.out.println("Horas trabajadas: " + horas);
                        System.out.println("Valor por hora: " + valorHora);
                        System.out.println("Salario mensual: " + salario);
                    } else {
                        System.out.println("No se encontró ningún empleado con ese ID.");
                    }
                }

                case 3 -> {
                    System.out.println("Lista de todos los empleados:");
                    for (ArrayList<String> emp : empleados) {
                        System.out.println("ID: " + emp.get(0) + ", Nombre: " + emp.get(1) + ", Cargo: " + emp.get(2));
                    }
                }

                case 4 -> {
                    if (empleados.isEmpty()) {
                        System.out.println("No hay empleados registrados.");
                        break;
                    }

                    ArrayList<String> mayor = empleados.get(0);
                    int salarioMayor = Integer.parseInt(mayor.get(3)) * Integer.parseInt(mayor.get(4));

                    for (ArrayList<String> emp : empleados) {
                        int salario = Integer.parseInt(emp.get(3)) * Integer.parseInt(emp.get(4));
                        if (salario > salarioMayor) {
                            mayor = emp;
                            salarioMayor = salario;
                        }
                    }

                    System.out.println("Empleado con mayor salario:");
                    System.out.println("ID: " + mayor.get(0) + ", Nombre: " + mayor.get(1) + ", Cargo: " + mayor.get(2));
                    System.out.println("Salario mensual: " + salarioMayor);
                }

                case 0 -> {
                    System.out.println("Saliendo...");
                    menu = false;
                }

                default -> System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }

        sc.close();
    }
}
