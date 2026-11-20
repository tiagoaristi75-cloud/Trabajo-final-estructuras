import java.util.Scanner;

/// HU7: busqueda de equipos por cedula o serial (sobrecarga en Inventario). 
public class Busquedas {
    validaciones v = new validaciones();

    // ===================== BUSCAR EQUIPO (HU7) =====================

    public void buscarEquipo(int carrera, Inventario inv, Scanner sc) {
        System.out.println("\n>> Buscar equipo - " + utilidades.nombreCarrera(carrera));
        Estudiante e = localizar(carrera, inv, sc);
        if (e == null) {
            return;
        }
        System.out.println("Equipo encontrado:");
        inv.buscarEquipo(e).imprimir();
        System.out.println("Prestado a:");
        e.imprimir();
    }

    // ===================== APOYO =====================

    
      //Pide si se busca por cedula o por serial y devuelve el estudiante
      //(o null con mensaje si no existe o es de la otra carrera).
     
    public Estudiante localizar(int carrera, Inventario inv, Scanner sc) {
        System.out.println("Buscar por:");
        System.out.println("1. Cedula");
        System.out.println("2. Serial del equipo");
        int criterio = v.ValidaRango(sc, "Seleccione una opcion: ", 1, 2);
        String dato = (criterio == 1) ? v.ValidaNumerico(sc, "Cedula: ", 5, 12)
                : v.ValidaAlfanumerico(sc, "Serial: ");
        Estudiante e = inv.buscar(dato, criterio);
        if (e == null) {
            System.out.println("No se encontro ningun registro con ese dato.");
            return null;
        }
        boolean esIng = e instanceof EstudianteIngenieria;
        if ((carrera == 1) != esIng) {
            System.out.println("Ese registro existe, pero pertenece a otra carrera.");
            return null;
        }
        return e;
    }
}
