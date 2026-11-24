package gestionprestamos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class GestorPrestamos {

    private final Scanner scanner;

    // Las listas van sincronizadas por posicion: el estudiante de la
    // posicion i en ingenieros tiene el equipo de la posicion i en portatil.
    private final ArrayList<EstudianteIngenieria> ingenieros;
    private final ArrayList<EstudianteDiseno> disenadores;
    private final ArrayList<ComputadorPortatil> portatil;
    private final ArrayList<TabletaGrafica> tableta;

    // Equipos en el orden en que fueron entregados.
    private final Queue<Equipo> ordenEntrega;

    // La devolucion mas reciente queda en el tope.
    private final Stack<Devolucion> historialDevoluciones;

    public GestorPrestamos(Scanner scanner) {
        this.scanner = scanner;
        ingenieros = new ArrayList<>();
        disenadores = new ArrayList<>();
        portatil = new ArrayList<>();
        tableta = new ArrayList<>();
        ordenEntrega = new LinkedList<>();
        historialDevoluciones = new Stack<>();
    }

    public void registrarPrestamoIngenieria() {
    }

    public void registrarPrestamoDiseno() {
    }

    public void modificarPrestamoIngenieria() {
    }

    public void modificarPrestamoDiseno() {
    }

    public void devolverEquipoIngenieria() {
    }

    public void devolverEquipoDiseno() {
    }

    public void buscarEquipoIngenieria() {
    }

    public void buscarEquipoDiseno() {
    }

    public void imprimirInventarioTotal() {
    }

    // ================= MANEJO DE LAS ESTRUCTURAS =================

    public void agregarPrestamo(EstudianteIngenieria estudiante, ComputadorPortatil equipo) {
        ingenieros.add(estudiante);
        portatil.add(equipo);
        ordenEntrega.add(equipo);
    }

    public void agregarPrestamo(EstudianteDiseno estudiante, TabletaGrafica equipo) {
        disenadores.add(estudiante);
        tableta.add(equipo);
        ordenEntrega.add(equipo);
    }

    // Quita al estudiante y su equipo de la misma posicion para no romper la sincronia.
    public void eliminarPrestamoIngenieria(int posicion) {
        EstudianteIngenieria estudiante = ingenieros.remove(posicion);
        ComputadorPortatil equipo = portatil.remove(posicion);
        ordenEntrega.remove(equipo);
        historialDevoluciones.push(new Devolucion(estudiante, equipo));
    }

    public void eliminarPrestamoDiseno(int posicion) {
        EstudianteDiseno estudiante = disenadores.remove(posicion);
        TabletaGrafica equipo = tableta.remove(posicion);
        ordenEntrega.remove(equipo);
        historialDevoluciones.push(new Devolucion(estudiante, equipo));
    }

    // ================= IMPRESION =================

    public void imprimirPrestamosIngenieria() {
        if (ingenieros.isEmpty()) {
            System.out.println("No hay prestamos de Ingenieria.");
            return;
        }
        for (int i = 0; i < ingenieros.size(); i++) {
            mostrarEstudiante(ingenieros.get(i));
            portatil.get(i).mostrarDatos();
            System.out.println();
        }
    }

    public void imprimirPrestamosDiseno() {
        if (disenadores.isEmpty()) {
            System.out.println("No hay prestamos de Diseno.");
            return;
        }
        for (int i = 0; i < disenadores.size(); i++) {
            mostrarEstudiante(disenadores.get(i));
            tableta.get(i).mostrarDatos();
            System.out.println();
        }
    }

    public void imprimirOrdenEntrega() {
        if (ordenEntrega.isEmpty()) {
            System.out.println("No hay equipos entregados.");
            return;
        }
        int turno = 1;
        for (Equipo equipo : ordenEntrega) {
            System.out.println(turno + ". " + equipo.getSerial() + " - " + equipo.getMarca());
            turno++;
        }
    }

    // Recorre la pila desde la devolucion mas reciente sin vaciarla.
    public void imprimirHistorialDevoluciones() {
        if (historialDevoluciones.isEmpty()) {
            System.out.println("No hay devoluciones registradas.");
            return;
        }
        for (int i = historialDevoluciones.size() - 1; i >= 0; i--) {
            Devolucion devolucion = historialDevoluciones.get(i);
            mostrarEstudiante(devolucion.getEstudiante());
            devolucion.getEquipo().mostrarDatos();
            System.out.println();
        }
    }

    public void mostrarEstudiante(Object estudiante) {
        if (estudiante instanceof EstudianteIngenieria) {
            EstudianteIngenieria ingeniero = (EstudianteIngenieria) estudiante;
            System.out.println("===== ESTUDIANTE DE INGENIERIA =====");
            System.out.println("Cedula: " + ingeniero.getCedula());
            System.out.println("Nombre: " + ingeniero.getNombre());
            System.out.println("Apellido: " + ingeniero.getApellido());
            System.out.println("Telefono: " + ingeniero.getTelefono());
            System.out.println("Semestre: " + ingeniero.getSemestre());
            System.out.println("Promedio acumulado: " + ingeniero.getPromedioAcumulado());
            System.out.println("Serial del equipo: " + ingeniero.getSerialEquipo());
        } else if (estudiante instanceof EstudianteDiseno) {
            EstudianteDiseno disenador = (EstudianteDiseno) estudiante;
            System.out.println("===== ESTUDIANTE DE DISENO =====");
            System.out.println("Cedula: " + disenador.getCedula());
            System.out.println("Nombre: " + disenador.getNombre());
            System.out.println("Apellido: " + disenador.getApellido());
            System.out.println("Telefono: " + disenador.getTelefono());
            System.out.println("Modalidad: " + disenador.getModalidad());
            System.out.println("Cantidad de asignaturas: " + disenador.getCantidadAsignaturas());
            System.out.println("Serial del equipo: " + disenador.getSerialEquipo());
        }
    }
}
