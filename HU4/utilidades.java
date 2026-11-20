package HU4;
import java.util.Scanner;

public class utilidades {
    validaciones v = new validaciones();

    // ===================== MENUS (HU4) =====================

    public int menu(Scanner sc) {
        System.out.println("\n===== PRESTAMO DE EQUIPOS - SAN JUAN DE DIOS =====");
        System.out.println("1. Estudiantes de Ingenieria");
        System.out.println("2. Estudiantes de Diseno");
        System.out.println("3. Imprimir inventario total");
        System.out.println("4. Salir del programa");
        return v.ValidaRango(sc, "Seleccione una opcion: ", 1, 4);
    }

    public int subMenu(int carrera, Scanner sc) {
        System.out.println("\n--- " + nombreCarrera(carrera) + " ---");
        System.out.println("1. Registrar prestamo");
        System.out.println("2. Modificar prestamo (por serial o cedula)");
        System.out.println("3. Devolucion de equipo");
        System.out.println("4. Buscar equipo");
        System.out.println("5. Volver al menu principal");
        return v.ValidaRango(sc, "Seleccione una opcion: ", 1, 5);
    }

    /// Ciclo del submenu. Volver regresa al principal sin cerrar el programa.
    Prestamos prestamos = new Prestamos();
    Busquedas busquedas = new Busquedas();

    public void gestionar(int carrera, Inventario inv, Scanner sc) {
        boolean seguir = true;
        while (seguir) {
            switch (subMenu(carrera, sc)) {
                case 1:
                    prestamos.registrar(carrera, inv, sc);
                    break;
                case 2:
                    prestamos.modificar(carrera, inv, sc);
                    break;
                case 3:
                    prestamos.devolver(carrera, inv, sc);
                    break;
                case 4:
                    busquedas.buscarEquipo(carrera, inv, sc);
                    break;
                case 5:
                    seguir = false;
                    break;
                default:
                    System.out.println("Esta opcion no esta disponible, por favor valide.");
                    break;
            }
        }
    }

    public void imprimirInventario(Inventario inv) {
        inv.imprimirInventario();
    }

    // ===================== APOYO =====================

    public static String nombreCarrera(int carrera) {
        return carrera == 1 ? "ESTUDIANTES DE INGENIERIA" : "ESTUDIANTES DE DISENO";
    }
}
