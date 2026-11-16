package gestionprestamos;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final GestorPrestamos gestor;

    public Menu() {
        scanner = new Scanner(System.in);
        gestor = new GestorPrestamos(scanner);
    }

    public void iniciar() {
        int opcion;

        do {
            System.out.println("\n===== GESTION DE PRESTAMOS =====");
            System.out.println("1. Ingenieria");
            System.out.println("2. Diseno");
            System.out.println("3. Mostrar inventario total");
            System.out.println("4. Salir");

            opcion = leerOpcion(1, 4);

            switch (opcion) {
                case 1:
                    submenu(true);
                    break;
                case 2:
                    submenu(false);
                    break;
                case 3:
                    gestor.imprimirInventarioTotal();
                    break;
                case 4:
                    System.out.println("Programa finalizado.");
                    break;
            }
        } while (opcion != 4);

        scanner.close();
    }

    public void submenu(boolean esIngenieria) {
        int opcion;

        do {
            String carrera = esIngenieria ? "INGENIERIA" : "DISENO";

            System.out.println("\n===== " + carrera + " =====");
            System.out.println("1. Registrar prestamo");
            System.out.println("2. Modificar prestamo");
            System.out.println("3. Devolver equipo");
            System.out.println("4. Buscar equipo");
            System.out.println("5. Volver al menu principal");

            opcion = leerOpcion(1, 5);

            switch (opcion) {
                case 1:
                    if (esIngenieria) {
                        gestor.registrarPrestamoIngenieria();
                    } else {
                        gestor.registrarPrestamoDiseno();
                    }
                    break;
                case 2:
                    if (esIngenieria) {
                        gestor.modificarPrestamoIngenieria();
                    } else {
                        gestor.modificarPrestamoDiseno();
                    }
                    break;
                case 3:
                    if (esIngenieria) {
                        gestor.devolverEquipoIngenieria();
                    } else {
                        gestor.devolverEquipoDiseno();
                    }
                    break;
                case 4:
                    if (esIngenieria) {
                        gestor.buscarEquipoIngenieria();
                    } else {
                        gestor.buscarEquipoDiseno();
                    }
                    break;
                case 5:
                    System.out.println("Volviendo al menu principal...");
                    break;
            }
        } while (opcion != 5);
    }

    private int leerOpcion(int min, int max) {
        while (true) {
            System.out.print("Seleccione una opcion: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());

                if (opcion >= min && opcion <= max) {
                    return opcion;
                }
            } catch (NumberFormatException e) {
                // La entrada no representa un entero.
            }

            System.out.println(
                    "Ingrese un numero entre " + min + " y " + max + "."
            );
        }
    }
}