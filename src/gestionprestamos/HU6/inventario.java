import java.util.LinkedList;


 ///HU6 y HU7: guarda las cuatro listas dinamicas (ingenieros, disenadores,
 ///portatil, tableta) y se encarga de mantenerlas sincronizadas: cada
 ///estudiante entra y sale junto con su equipo.
 
public class Inventario {
    private LinkedList<EstudianteIngenieria> ingenieros = new LinkedList<>();
    private LinkedList<EstudianteDiseno> disenadores = new LinkedList<>();
    private LinkedList<ComputadorPortatil> portatil = new LinkedList<>();
    private LinkedList<TabletaGrafica> tableta = new LinkedList<>();

    // ---------- Registro (sobrecarga por tipo de estudiante) ----------

    public void registrar(EstudianteIngenieria e, ComputadorPortatil p) {
        ingenieros.add(e);
        portatil.add(p);
    }

    public void registrar(EstudianteDiseno d, TabletaGrafica t) {
        disenadores.add(d);
        tableta.add(t);
    }

    // ---------- Busqueda de estudiantes (sobrecarga) ----------

    /** Busca por cedula. */
    public Estudiante buscar(String cedula) {
        return buscar(cedula, 1);
    }

    /** Criterio 1 = cedula, criterio 2 = serial del equipo. */
    public Estudiante buscar(String dato, int criterio) {
        for (EstudianteIngenieria e : ingenieros) {
            if (coincide(e, dato, criterio)) {
                return e;
            }
        }
        for (EstudianteDiseno d : disenadores) {
            if (coincide(d, dato, criterio)) {
                return d;
            }
        }
        return null;
    }

    public boolean coincide(Estudiante e, String dato, int criterio) {
        if (criterio == 1) {
            return e.getCedula().equals(dato);
        }
        return e.getSerialEquipo().equalsIgnoreCase(dato);
    }

    // ---------- Busqueda de equipos (sobrecarga) ----------

    /** Busca un equipo por su serial en portatiles y tabletas. */
    public Equipo buscarEquipo(String serial) {
        for (ComputadorPortatil p : portatil) {
            if (p.getSerial().equalsIgnoreCase(serial)) {
                return p;
            }
        }
        for (TabletaGrafica t : tableta) {
            if (t.getSerial().equalsIgnoreCase(serial)) {
                return t;
            }
        }
        return null;
    }

    /** Busca el equipo que tiene prestado un estudiante. */
    public Equipo buscarEquipo(Estudiante e) {
        return buscarEquipo(e.getSerialEquipo());
    }

    // ---------- Duplicados ----------

    public boolean cedulaExiste(String cedula) {
        return buscar(cedula) != null;
    }

    public boolean serialExiste(String serial) {
        return buscarEquipo(serial) != null;
    }

    // ---------- Devolucion ----------

    ///Elimina al estudiante Y su equipo. Ambas listas se achican juntas. 
    public boolean devolver(Estudiante e) {
        Equipo eq = buscarEquipo(e);
        if (e instanceof EstudianteIngenieria) {
            return ingenieros.remove(e) & portatil.remove(eq);
        }
        return disenadores.remove(e) & tableta.remove(eq);
    }

    // ---------- Impresion ----------

    public void imprimirInventario() {
        System.out.println("\n================ INVENTARIO TOTAL ================");

        System.out.println("\n--- Estudiantes de Ingenieria (" + ingenieros.size() + ") ---");
        if (ingenieros.isEmpty()) {
            System.out.println("Sin registros.");
        }
        for (EstudianteIngenieria e : ingenieros) {
            e.imprimir();
        }

        System.out.println("\n--- Estudiantes de Diseno (" + disenadores.size() + ") ---");
        if (disenadores.isEmpty()) {
            System.out.println("Sin registros.");
        }
        for (EstudianteDiseno d : disenadores) {
            d.imprimir();
        }

        System.out.println("\n--- Computadores portatiles prestados (" + portatil.size() + ") ---");
        if (portatil.isEmpty()) {
            System.out.println("Sin registros.");
        }
        for (ComputadorPortatil p : portatil) {
            p.imprimir();
        }

        System.out.println("\n--- Tabletas graficas prestadas (" + tableta.size() + ") ---");
        if (tableta.isEmpty()) {
            System.out.println("Sin registros.");
        }
        for (TabletaGrafica t : tableta) {
            t.imprimir();
        }
        System.out.println("\n==================================================\n");
    }

    // ---------- Tamanos (para mensajes) ----------

    public int totalEstudiantes() {
        return ingenieros.size() + disenadores.size();
    }

    public int totalEquipos() {
        return portatil.size() + tableta.size();
    }
}
