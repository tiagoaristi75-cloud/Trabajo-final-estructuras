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

    // ================= REGISTRO =================

    public void registrarPrestamoIngenieria() {
        System.out.println("\n===== REGISTRAR PRESTAMO INGENIERIA =====");

        String cedula = leerCedula();
        if (existeCedula(cedula)) {
            System.out.println("Error: esta cedula ya tiene un equipo asignado.");
            return;
        }

        String serial = Validaciones.leerSerial(scanner, "Serial del portatil: ");
        if (existeSerial(serial)) {
            System.out.println("Error: ese serial ya esta registrado.");
            return;
        }

        String nombre = Validaciones.leerTexto(scanner, "Nombre: ");
        String apellido = Validaciones.leerTexto(scanner, "Apellido: ");
        String telefono = leerTelefono();
        int semestre = Validaciones.leerOpcion(scanner, "Semestre (1 a 10): ", 1, 10);
        float promedio = Validaciones.leerFloat(scanner, "Promedio acumulado (0 a 5): ", 0, 5);

        String marca = Validaciones.leerTexto(scanner, "Marca del portatil: ");
        float tamano = Validaciones.leerFloat(scanner, "Tamano en pulgadas: ", 1, 30);
        float precio = Validaciones.leerFloat(scanner, "Precio: ", 0, 50000000);
        String sistemaOperativo = ComputadorPortatil.seleccionarSistemaOperativo(scanner);
        String procesador = ComputadorPortatil.seleccionarProcesador(scanner);

        EstudianteIngenieria estudiante = new EstudianteIngenieria(
                cedula, nombre, apellido, telefono, semestre, promedio, serial);
        ComputadorPortatil equipo = new ComputadorPortatil(
                serial, marca, tamano, precio, sistemaOperativo, procesador);

        agregarPrestamo(estudiante, equipo);
        System.out.println("Prestamo registrado correctamente.");
    }

    public void registrarPrestamoDiseno() {
        System.out.println("\n===== REGISTRAR PRESTAMO DISENO =====");

        String cedula = leerCedula();
        if (existeCedula(cedula)) {
            System.out.println("Error: esta cedula ya tiene un equipo asignado.");
            return;
        }

        int serial = Validaciones.leerEntero(scanner, "Serial de la tableta (solo numeros): ");
        if (existeSerial(String.valueOf(serial))) {
            System.out.println("Error: ese serial ya esta registrado.");
            return;
        }

        String nombre = Validaciones.leerTexto(scanner, "Nombre: ");
        String apellido = Validaciones.leerTexto(scanner, "Apellido: ");
        String telefono = leerTelefono();
        String modalidad = Validaciones.leerModalidad(scanner);
        int asignaturas = Validaciones.leerOpcion(scanner, "Cantidad de asignaturas (1 a 10): ", 1, 10);

        String marca = Validaciones.leerTexto(scanner, "Marca de la tableta: ");
        float tamano = Validaciones.leerFloat(scanner, "Tamano en pulgadas: ", 1, 30);
        float precio = Validaciones.leerFloat(scanner, "Precio: ", 0, 50000000);
        String almacenamiento = TabletaGrafica.seleccionarAlmacenamiento(scanner);
        float peso = Validaciones.leerFloat(scanner, "Peso en kg: ", 0.1f, 10);

        EstudianteDiseno estudiante = new EstudianteDiseno(
                cedula, nombre, apellido, telefono, modalidad, asignaturas, serial);
        TabletaGrafica equipo = new TabletaGrafica(
                String.valueOf(serial), marca, tamano, precio, almacenamiento, peso);

        agregarPrestamo(estudiante, equipo);
        System.out.println("Prestamo registrado correctamente.");
    }

    // ================= MODIFICACION =================

    // La cedula y el serial se conservan; solo se cambian los demas datos.
    public void modificarPrestamoIngenieria() {
        System.out.println("\n===== MODIFICAR PRESTAMO INGENIERIA =====");

        int posicion = ubicarPrestamoIngenieria();
        if (posicion == -1) {
            System.out.println("Error: no existe un prestamo de Ingenieria con ese dato.");
            return;
        }

        EstudianteIngenieria estudiante = ingenieros.get(posicion);
        ComputadorPortatil equipo = portatil.get(posicion);
        System.out.println("Cedula " + estudiante.getCedula() + " y serial "
                + equipo.getSerial() + ": estos datos no se pueden modificar.");

        estudiante.setNombre(Validaciones.leerTexto(scanner, "Nombre: "));
        estudiante.setApellido(Validaciones.leerTexto(scanner, "Apellido: "));
        estudiante.setTelefono(leerTelefono());
        estudiante.setSemestre(Validaciones.leerOpcion(scanner, "Semestre (1 a 10): ", 1, 10));
        estudiante.setPromedioAcumulado(Validaciones.leerFloat(scanner, "Promedio acumulado (0 a 5): ", 0, 5));

        equipo.setMarca(Validaciones.leerTexto(scanner, "Marca del portatil: "));
        equipo.setTamano(Validaciones.leerFloat(scanner, "Tamano en pulgadas: ", 1, 30));
        equipo.setPrecio(Validaciones.leerFloat(scanner, "Precio: ", 0, 50000000));
        equipo.setSistemaOperativo(ComputadorPortatil.seleccionarSistemaOperativo(scanner));
        equipo.setProcesador(ComputadorPortatil.seleccionarProcesador(scanner));

        System.out.println("Prestamo modificado correctamente.");
    }

    public void modificarPrestamoDiseno() {
        System.out.println("\n===== MODIFICAR PRESTAMO DISENO =====");

        int posicion = ubicarPrestamoDiseno();
        if (posicion == -1) {
            System.out.println("Error: no existe un prestamo de Diseno con ese dato.");
            return;
        }

        EstudianteDiseno estudiante = disenadores.get(posicion);
        TabletaGrafica equipo = tableta.get(posicion);
        System.out.println("Cedula " + estudiante.getCedula() + " y serial "
                + equipo.getSerial() + ": estos datos no se pueden modificar.");

        estudiante.setNombre(Validaciones.leerTexto(scanner, "Nombre: "));
        estudiante.setApellido(Validaciones.leerTexto(scanner, "Apellido: "));
        estudiante.setTelefono(leerTelefono());
        estudiante.setModalidad(Validaciones.leerModalidad(scanner));
        estudiante.setCantidadAsignaturas(Validaciones.leerOpcion(scanner, "Cantidad de asignaturas (1 a 10): ", 1, 10));

        equipo.setMarca(Validaciones.leerTexto(scanner, "Marca de la tableta: "));
        equipo.setTamano(Validaciones.leerFloat(scanner, "Tamano en pulgadas: ", 1, 30));
        equipo.setPrecio(Validaciones.leerFloat(scanner, "Precio: ", 0, 50000000));
        equipo.setAlmacenamiento(TabletaGrafica.seleccionarAlmacenamiento(scanner));
        equipo.setPeso(Validaciones.leerFloat(scanner, "Peso en kg: ", 0.1f, 10));

        System.out.println("Prestamo modificado correctamente.");
    }

    // Pregunta si se busca por cedula o por serial y devuelve la posicion, o -1.
    public int ubicarPrestamoIngenieria() {
        System.out.println("Buscar el prestamo por:");
        System.out.println("1. Cedula");
        System.out.println("2. Serial");
        int opcion = Validaciones.leerOpcion(scanner, "Seleccione una opcion: ", 1, 2);

        if (opcion == 1) {
            return posicionIngeniero(leerCedula());
        }
        return posicionPortatil(Validaciones.leerSerial(scanner, "Serial del portatil: "));
    }

    public int ubicarPrestamoDiseno() {
        System.out.println("Buscar el prestamo por:");
        System.out.println("1. Cedula");
        System.out.println("2. Serial");
        int opcion = Validaciones.leerOpcion(scanner, "Seleccione una opcion: ", 1, 2);

        if (opcion == 1) {
            return posicionDisenador(leerCedula());
        }
        return posicionTableta(String.valueOf(Validaciones.leerEntero(scanner, "Serial de la tableta: ")));
    }

    // ================= DEVOLUCION =================

    public void devolverEquipoIngenieria() {
        System.out.println("\n===== DEVOLUCION DE PORTATIL =====");

        int posicion = posicionIngeniero(leerCedula());
        if (posicion == -1) {
            System.out.println("Error: no existe un prestamo de Ingenieria con esa cedula.");
            return;
        }

        eliminarPrestamoIngenieria(posicion);
        System.out.println("Devolucion registrada. El prestamo se elimino del registro.");
    }

    public void devolverEquipoDiseno() {
        System.out.println("\n===== DEVOLUCION DE TABLETA =====");

        int posicion = posicionDisenador(leerCedula());
        if (posicion == -1) {
            System.out.println("Error: no existe un prestamo de Diseno con esa cedula.");
            return;
        }

        eliminarPrestamoDiseno(posicion);
        System.out.println("Devolucion registrada. El prestamo se elimino del registro.");
    }

    // ================= POSICIONES =================

    public int posicionIngeniero(String cedula) {
        for (int i = 0; i < ingenieros.size(); i++) {
            if (ingenieros.get(i).getCedula().equals(cedula)) {
                return i;
            }
        }
        return -1;
    }

    public int posicionDisenador(String cedula) {
        for (int i = 0; i < disenadores.size(); i++) {
            if (disenadores.get(i).getCedula().equals(cedula)) {
                return i;
            }
        }
        return -1;
    }

    public int posicionPortatil(String serial) {
        for (int i = 0; i < portatil.size(); i++) {
            if (portatil.get(i).getSerial().equalsIgnoreCase(serial)) {
                return i;
            }
        }
        return -1;
    }

    public int posicionTableta(String serial) {
        for (int i = 0; i < tableta.size(); i++) {
            if (tableta.get(i).getSerial().equals(serial)) {
                return i;
            }
        }
        return -1;
    }

    // Un estudiante no puede tener mas de un equipo, sin importar la carrera.
    public boolean existeCedula(String cedula) {
        return buscar(cedula) != null;
    }

    // ================= LECTURA DE DATOS =================

    public String leerCedula() {
        return Validaciones.leerNumeroTexto(scanner, "Cedula: ", 6, 10);
    }

    public String leerTelefono() {
        return Validaciones.leerNumeroTexto(scanner, "Telefono: ", 7, 10);
    }

    // ================= BUSQUEDA E INVENTARIO =================

    public void buscarEquipoIngenieria() {
        String serial = Validaciones.leerSerial(scanner, "Serial del portatil: ");
        Equipo equipo = buscar(serial, 1);

        if (equipo == null) {
            System.out.println("No hay un portatil prestado con ese serial.");
            return;
        }

        equipo.mostrarDatos();
        System.out.println("\nPrestado a:");
        mostrarEstudiante(ingenieros.get(posicionPortatil(serial)));
    }

    public void buscarEquipoDiseno() {
        String serial = String.valueOf(Validaciones.leerEntero(scanner, "Serial de la tableta: "));
        Equipo equipo = buscar(serial, 2);

        if (equipo == null) {
            System.out.println("No hay una tableta prestada con ese serial.");
            return;
        }

        equipo.mostrarDatos();
        System.out.println("\nPrestada a:");
        mostrarEstudiante(disenadores.get(posicionTableta(serial)));
    }

    public void imprimirInventarioTotal() {
        System.out.println("\n===== INVENTARIO TOTAL =====");

        System.out.println("\n--- Prestamos de Ingenieria ---");
        imprimirPrestamosIngenieria();

        System.out.println("\n--- Prestamos de Diseno ---");
        imprimirPrestamosDiseno();

        System.out.println("\n--- Orden de entrega de equipos ---");
        imprimirOrdenEntrega();

        System.out.println("\n--- Historial de devoluciones ---");
        imprimirHistorialDevoluciones();

        System.out.println("\nPortatiles prestados: " + portatil.size());
        System.out.println("Tabletas prestadas: " + tableta.size());
        System.out.println("Devoluciones registradas: " + historialDevoluciones.size());
    }

    // Sobrecarga: con un solo dato busca un estudiante por cedula.
    public Object buscar(String cedula) {
        int posicion = posicionIngeniero(cedula);
        if (posicion != -1) {
            return ingenieros.get(posicion);
        }
        posicion = posicionDisenador(cedula);
        if (posicion != -1) {
            return disenadores.get(posicion);
        }
        return null;
    }

    // Sobrecarga: con serial y tipo busca un equipo (1 = portatil, 2 = tableta).
    public Equipo buscar(String serial, int tipoEquipo) {
        if (tipoEquipo == 1) {
            int posicion = posicionPortatil(serial);
            return posicion == -1 ? null : portatil.get(posicion);
        }
        int posicion = posicionTableta(serial);
        return posicion == -1 ? null : tableta.get(posicion);
    }

    public boolean existeSerial(String serial) {
        return buscar(serial, 1) != null || buscar(serial, 2) != null;
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
