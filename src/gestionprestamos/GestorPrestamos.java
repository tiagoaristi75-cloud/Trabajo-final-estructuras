package gestionprestamos;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class GestorPrestamos {

    private final Scanner scanner;

    private final ArrayList<EstudianteIngenieria> ingenieros;
    private final ArrayList<EstudianteDiseno> disenadores;
    private final ArrayList<ComputadorPortatil> portatiles;
    private final ArrayList<TabletaGrafica> tabletas;

    private final Stack<Devolucion> historialDevoluciones;

    public GestorPrestamos(Scanner scanner) {
        this.scanner = scanner;

        ingenieros = new ArrayList<>();
        disenadores = new ArrayList<>();
        portatiles = new ArrayList<>();
        tabletas = new ArrayList<>();

        historialDevoluciones = new Stack<>();
    }

    // ================= BUSQUEDAS =================

    public Estudiante buscar(String cedula) {
        for (EstudianteIngenieria estudiante : ingenieros) {
            if (estudiante.getCedula().equals(cedula)) {
                return estudiante;
            }
        }

        for (EstudianteDiseno estudiante : disenadores) {
            if (estudiante.getCedula().equals(cedula)) {
                return estudiante;
            }
        }

        return null;
    }

    public Equipo buscar(String serial, int tipoEquipo) {
        if (tipoEquipo == 1) {
            for (ComputadorPortatil portatil : portatiles) {
                if (portatil.getSerial().equalsIgnoreCase(serial)) {
                    return portatil;
                }
            }
        } else if (tipoEquipo == 2) {
            for (TabletaGrafica tableta : tabletas) {
                if (tableta.getSerial().equalsIgnoreCase(serial)) {
                    return tableta;
                }
            }
        }

        return null;
    }

    public boolean existeCedula(String cedula) {
        return buscar(cedula) != null;
    }

    public boolean existeSerial(String serial) {
        return buscar(serial, 1) != null
                || buscar(serial, 2) != null;
    }

    // ================= REGISTRO =================

    public void registrarPrestamoIngenieria() {
        System.out.println("\n===== REGISTRAR PRESTAMO INGENIERIA =====");

        String cedula = leerCedula();

        if (existeCedula(cedula)) {
            System.out.println(
                    "Esta cedula ya tiene un prestamo activo."
            );
            return;
        }

        String serial = Validaciones.leerSerial(
                scanner,
                "Serial del portatil: "
        );

        if (existeSerial(serial)) {
            System.out.println(
                    "Este serial ya tiene un prestamo activo."
            );
            return;
        }

        EstudianteIngenieria estudiante =
                leerIngeniero(cedula, serial);

        ComputadorPortatil portatil = leerPortatil(serial);

        // Solo se agregan cuando se han obtenido todos los datos.
        ingenieros.add(estudiante);
        portatiles.add(portatil);

        System.out.println("Prestamo registrado correctamente.");
    }

    public void registrarPrestamoDiseno() {
        System.out.println("\n===== REGISTRAR PRESTAMO DISENO =====");

        String cedula = leerCedula();

        if (existeCedula(cedula)) {
            System.out.println(
                    "Esta cedula ya tiene un prestamo activo."
            );
            return;
        }

        String serial = Validaciones.leerSerial(
                scanner,
                "Serial de la tableta: "
        );

        if (existeSerial(serial)) {
            System.out.println(
                    "Este serial ya tiene un prestamo activo."
            );
            return;
        }

        EstudianteDiseno estudiante =
                leerDisenador(cedula, serial);

        TabletaGrafica tableta = leerTableta(serial);

        disenadores.add(estudiante);
        tabletas.add(tableta);

        System.out.println("Prestamo registrado correctamente.");
    }

    // ================= MODIFICACION =================

    public void modificarPrestamoIngenieria() {
        System.out.println("\n===== MODIFICAR PRESTAMO INGENIERIA =====");

        Estudiante encontrado = buscar(leerCedula());

        if (!(encontrado instanceof EstudianteIngenieria)) {
            System.out.println(
                    "No existe un prestamo de Ingenieria con esa cedula."
            );
            return;
        }

        EstudianteIngenieria anterior =
                (EstudianteIngenieria) encontrado;

        ComputadorPortatil portatilAnterior =
                (ComputadorPortatil) buscar(
                        anterior.getSerialEquipo(),
                        1
                );

        if (portatilAnterior == null) {
            System.out.println(
                    "No se encontro el equipo asociado. Revise los datos."
            );
            return;
        }

        System.out.println(
                "Ingrese nuevamente los datos modificables."
        );
        System.out.println(
                "La cedula y el serial se conservaran."
        );

        EstudianteIngenieria actualizado = leerIngeniero(
                anterior.getCedula(),
                anterior.getSerialEquipo()
        );

        ComputadorPortatil portatilActualizado = leerPortatil(
                portatilAnterior.getSerial()
        );

        // Se reemplazan los registros conservando sus identificadores.
        ingenieros.set(
                ingenieros.indexOf(anterior),
                actualizado
        );

        portatiles.set(
                portatiles.indexOf(portatilAnterior),
                portatilActualizado
        );

        System.out.println("Prestamo modificado correctamente.");
    }

    public void modificarPrestamoDiseno() {
        System.out.println("\n===== MODIFICAR PRESTAMO DISENO =====");

        Estudiante encontrado = buscar(leerCedula());

        if (!(encontrado instanceof EstudianteDiseno)) {
            System.out.println(
                    "No existe un prestamo de Diseno con esa cedula."
            );
            return;
        }

        EstudianteDiseno anterior = (EstudianteDiseno) encontrado;

        TabletaGrafica tabletaAnterior =
                (TabletaGrafica) buscar(
                        anterior.getSerialEquipo(),
                        2
                );

        if (tabletaAnterior == null) {
            System.out.println(
                    "No se encontro el equipo asociado. Revise los datos."
            );
            return;
        }

        System.out.println(
                "Ingrese nuevamente los datos modificables."
        );
        System.out.println(
                "La cedula y el serial se conservaran."
        );

        EstudianteDiseno actualizado = leerDisenador(
                anterior.getCedula(),
                anterior.getSerialEquipo()
        );

        TabletaGrafica tabletaActualizada = leerTableta(
                tabletaAnterior.getSerial()
        );

        disenadores.set(
                disenadores.indexOf(anterior),
                actualizado
        );

        tabletas.set(
                tabletas.indexOf(tabletaAnterior),
                tabletaActualizada
        );

        System.out.println("Prestamo modificado correctamente.");
    }

    // ================= DEVOLUCION =================

    public void devolverEquipoIngenieria() {
        System.out.println("\n===== DEVOLVER PORTATIL =====");

        Estudiante encontrado = buscar(leerCedula());

        if (!(encontrado instanceof EstudianteIngenieria)) {
            System.out.println(
                    "No existe un prestamo de Ingenieria con esa cedula."
            );
            return;
        }

        EstudianteIngenieria estudiante =
                (EstudianteIngenieria) encontrado;

        Equipo equipo = buscar(estudiante.getSerialEquipo(), 1);

        if (equipo == null) {
            System.out.println(
                    "No se encontro el equipo asociado. Revise los datos."
            );
            return;
        }

        historialDevoluciones.push(
                new Devolucion(estudiante, equipo)
        );

        ingenieros.remove(estudiante);
        portatiles.remove(equipo);

        System.out.println("Devolucion registrada correctamente.");
    }

    public void devolverEquipoDiseno() {
        System.out.println("\n===== DEVOLVER TABLETA =====");

        Estudiante encontrado = buscar(leerCedula());

        if (!(encontrado instanceof EstudianteDiseno)) {
            System.out.println(
                    "No existe un prestamo de Diseno con esa cedula."
            );
            return;
        }

        EstudianteDiseno estudiante =
                (EstudianteDiseno) encontrado;

        Equipo equipo = buscar(estudiante.getSerialEquipo(), 2);

        if (equipo == null) {
            System.out.println(
                    "No se encontro el equipo asociado. Revise los datos."
            );
            return;
        }

        historialDevoluciones.push(
                new Devolucion(estudiante, equipo)
        );

        disenadores.remove(estudiante);
        tabletas.remove(equipo);

        System.out.println("Devolucion registrada correctamente.");
    }

    // ================= CONSULTAS DEL MENU =================

    public void buscarEquipoIngenieria() {
        String serial = Validaciones.leerSerial(
                scanner,
                "Ingrese el serial del portatil: "
        );

        mostrarResultadoBusqueda(buscar(serial, 1));
    }

    public void buscarEquipoDiseno() {
        String serial = Validaciones.leerSerial(
                scanner,
                "Ingrese el serial de la tableta: "
        );

        mostrarResultadoBusqueda(buscar(serial, 2));
    }

    private void mostrarResultadoBusqueda(Equipo equipo) {
        if (equipo == null) {
            System.out.println(
                    "No existe un prestamo activo con ese serial."
            );
            return;
        }

        equipo.mostrarDatos();

        Estudiante estudiante = buscarEstudiantePorSerial(
                equipo.getSerial()
        );

        if (estudiante != null) {
            System.out.println("\n===== ESTUDIANTE ASOCIADO =====");
            estudiante.mostrarDatos();
        }
    }

    private Estudiante buscarEstudiantePorSerial(String serial) {
        for (EstudianteIngenieria estudiante : ingenieros) {
            if (estudiante.getSerialEquipo().equalsIgnoreCase(serial)) {
                return estudiante;
            }
        }

        for (EstudianteDiseno estudiante : disenadores) {
            if (estudiante.getSerialEquipo().equalsIgnoreCase(serial)) {
                return estudiante;
            }
        }

        return null;
    }

    public void imprimirInventarioTotal() {
        System.out.println("\n===== INVENTARIO DE PRESTAMOS ACTIVOS =====");

        System.out.println("\n===== PORTATILES =====");

        if (portatiles.isEmpty()) {
            System.out.println("No hay portatiles en prestamo.");
        } else {
            for (ComputadorPortatil portatil : portatiles) {
                mostrarResultadoBusqueda(portatil);
                System.out.println();
            }
        }

        System.out.println("\n===== TABLETAS GRAFICAS =====");

        if (tabletas.isEmpty()) {
            System.out.println("No hay tabletas en prestamo.");
        } else {
            for (TabletaGrafica tableta : tabletas) {
                mostrarResultadoBusqueda(tableta);
                System.out.println();
            }
        }

        System.out.println(
                "Total de portatiles en prestamo: " + portatiles.size()
        );
        System.out.println(
                "Total de tabletas en prestamo: " + tabletas.size()
        );
        System.out.println(
                "Devoluciones registradas: " + historialDevoluciones.size()
        );
    }

    public void mostrarHistorialDevoluciones() {
        System.out.println("\n===== HISTORIAL DE DEVOLUCIONES =====");

        if (historialDevoluciones.isEmpty()) {
            System.out.println("No hay devoluciones registradas.");
            return;
        }

        // Recorre desde la ultima devolucion sin vaciar la pila.
        for (int i = historialDevoluciones.size() - 1; i >= 0; i--) {
            Devolucion devolucion = historialDevoluciones.get(i);

            devolucion.estudiante.mostrarDatos();
            devolucion.equipo.mostrarDatos();

            System.out.println();
        }
    }

    // ================= LECTURA DE DATOS =================

    private String leerCedula() {
        return Validaciones.leerNumeroTexto(
                scanner,
                "Cedula: ",
                1,
                Integer.MAX_VALUE
        );
    }

    private String leerTelefono() {
        return Validaciones.leerNumeroTexto(
                scanner,
                "Telefono: ",
                1,
                Integer.MAX_VALUE
        );
    }

    private EstudianteIngenieria leerIngeniero(
            String cedula, String serial) {

        String nombre = Validaciones.leerTexto(
                scanner, "Nombre: "
        );

        String apellido = Validaciones.leerTexto(
                scanner, "Apellido: "
        );

        String telefono = leerTelefono();

        int semestre = leerEnteroPositivo("Semestre: ");

        float promedio = Validaciones.leerFloat(
                scanner, "Promedio acumulado (0 a 5): ", 0.0f, 5.0f
        );

        return new EstudianteIngenieria(
                cedula,
                nombre,
                apellido,
                telefono,
                semestre,
                promedio,
                serial
        );
    }

    private EstudianteDiseno leerDisenador(
            String cedula, String serial) {

        String nombre = Validaciones.leerTexto(
                scanner, "Nombre: "
        );

        String apellido = Validaciones.leerTexto(
                scanner, "Apellido: "
        );

        String telefono = leerTelefono();

        String modalidad = Validaciones.leerModalidad(scanner);

        int cantidadAsignaturas = leerEnteroPositivo(
                "Cantidad de asignaturas: "
        );

        return new EstudianteDiseno(
                cedula,
                nombre,
                apellido,
                telefono,
                modalidad,
                cantidadAsignaturas,
                serial
        );
    }

    private ComputadorPortatil leerPortatil(String serial) {
        String marca = Validaciones.leerTexto(
                scanner, "Marca del portatil: "
        );

        float tamano = leerFloatPositivo("Tamano del portatil: ");
        float precio = leerFloatNoNegativo("Precio del portatil: ");

        String sistemaOperativo =
                ComputadorPortatil.seleccionarSistemaOperativo(scanner);

        String procesador =
                ComputadorPortatil.seleccionarProcesador(scanner);

        return new ComputadorPortatil(
                serial,
                marca,
                tamano,
                precio,
                sistemaOperativo,
                procesador
        );
    }

    private TabletaGrafica leerTableta(String serial) {
        String marca = Validaciones.leerTexto(
                scanner, "Marca de la tableta: "
        );

        float tamano = leerFloatPositivo("Tamano de la tableta: ");
        float precio = leerFloatNoNegativo("Precio de la tableta: ");

        String almacenamiento =
                TabletaGrafica.seleccionarAlmacenamiento(scanner);

        float peso = leerFloatPositivo("Peso de la tableta: ");

        return new TabletaGrafica(
                serial,
                marca,
                tamano,
                precio,
                almacenamiento,
                peso
        );
    }

    private int leerEnteroPositivo(String mensaje) {
        while (true) {
            int valor = Validaciones.leerEntero(scanner, mensaje);

            if (valor > 0) {
                return valor;
            }

            System.out.println("El valor debe ser mayor que cero.");
        }
    }

    private float leerFloatPositivo(String mensaje) {
        while (true) {
            float valor = Validaciones.leerFloat(
                    scanner,
                    mensaje,
                    0.0f,
                    Float.MAX_VALUE
            );

            if (Float.isFinite(valor) && valor > 0) {
                return valor;
            }

            System.out.println(
                    "Ingrese un valor finito mayor que cero."
            );
        }
    }

    private float leerFloatNoNegativo(String mensaje) {
        while (true) {
            float valor = Validaciones.leerFloat(
                    scanner,
                    mensaje,
                    0.0f,
                    Float.MAX_VALUE
            );

            if (Float.isFinite(valor) && valor >= 0) {
                return valor;
            }

            System.out.println(
                    "Ingrese un valor finito mayor o igual que cero."
            );
        }
    }

    // Cada elemento de la pila conserva estudiante y equipo devueltos.
    private static class Devolucion {

        private final Estudiante estudiante;
        private final Equipo equipo;

        private Devolucion(Estudiante estudiante, Equipo equipo) {
            this.estudiante = estudiante;
            this.equipo = equipo;
        }
    }
}
