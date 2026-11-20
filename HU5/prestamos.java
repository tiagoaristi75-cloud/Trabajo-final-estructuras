import java.util.Scanner;

///HU5: registrar, modificar y devolver prestamos. 
public class Prestamos {
    validaciones v = new validaciones();
    Busquedas busquedas = new Busquedas();

    // ===================== REGISTRAR (HU5) =====================

    public void registrar(int carrera, Inventario inv, Scanner sc) {
        System.out.println("\n>> Registrar prestamo - " + utilidades.nombreCarrera(carrera));
        String cedula = v.ValidaNumerico(sc, "Cedula: ", 5, 12);

        // Un estudiante no puede tener dos equipos ni estar registrado dos veces
        if (inv.cedulaExiste(cedula)) {
            System.out.println("ERROR: esa cedula ya tiene un equipo asignado. No se registro el prestamo.");
            return;
        }

        String nombre = v.ValidaTexto(sc, "Nombre: ");
        String apellido = v.ValidaTexto(sc, "Apellido: ");
        String telefono = v.ValidaNumerico(sc, "Telefono: ", 7, 10);

        if (carrera == 1) {
            int semestre = v.ValidaRango(sc, "Numero de semestre (1-12): ", 1, 12);
            float promedio = v.ValidaDecimal(sc, "Promedio acumulado (0.0 - 5.0): ", 0f, 5f);
            ComputadorPortatil p = pedirPortatil(inv, sc);
            inv.registrar(new EstudianteIngenieria(cedula, nombre, apellido, telefono, semestre, promedio,
                    p.getSerial()), p);
        } else {
            String modalidad = pedirModalidad(sc);
            int asignaturas = v.ValidaRango(sc, "Cantidad de asignaturas (1-10): ", 1, 10);
            TabletaGrafica t = pedirTableta(inv, sc);
            inv.registrar(new EstudianteDiseno(cedula, nombre, apellido, telefono, modalidad, asignaturas,
                    t.getSerial()), t);
        }
        System.out.println("OK: prestamo registrado correctamente.");
    }

    public ComputadorPortatil pedirPortatil(Inventario inv, Scanner sc) {
        System.out.println("-- Datos del computador portatil --");
        String serial = pedirSerial(inv, sc);
        String marca = v.ValidaTexto(sc, "Marca: ");
        float tamano = v.ValidaDecimal(sc, "Tamano en pulgadas (10-20): ", 10f, 20f);
        float precio = v.ValidaDecimal(sc, "Precio: ", 1f, 100000000f);
        ComputadorPortatil p = new ComputadorPortatil(serial, marca, tamano, precio);
        p.elegirSistemaOperativo(sc, v);
        p.elegirProcesador(sc, v);
        return p;
    }

    public TabletaGrafica pedirTableta(Inventario inv, Scanner sc) {
        System.out.println("-- Datos de la tableta grafica --");
        String serial = pedirSerial(inv, sc);
        String marca = v.ValidaTexto(sc, "Marca: ");
        float tamano = v.ValidaDecimal(sc, "Tamano en pulgadas (5-32): ", 5f, 32f);
        float precio = v.ValidaDecimal(sc, "Precio: ", 1f, 100000000f);
        float peso = v.ValidaDecimal(sc, "Peso en kg (0.1-5): ", 0.1f, 5f);
        TabletaGrafica t = new TabletaGrafica(serial, marca, tamano, precio, peso);
        t.elegirAlmacenamiento(sc, v);
        return t;
    }

    /// Pide el serial y repite (recursivo) mientras ya exista en el inventario. 
    public String pedirSerial(Inventario inv, Scanner sc) {
        String serial = v.ValidaAlfanumerico(sc, "Serial del equipo: ");
        if (!inv.serialExiste(serial)) {
            return serial;
        }
        System.out.println("Ese serial ya esta registrado, ingrese otro.");
        return pedirSerial(inv, sc);
    }

    public String pedirModalidad(Scanner sc) {
        System.out.println("Modalidad de estudio:");
        System.out.println("1. Virtual");
        System.out.println("2. Presencial");
        return v.ValidaRango(sc, "Seleccione una opcion: ", 1, 2) == 1 ? "Virtual" : "Presencial";
    }

    // ===================== MODIFICAR (HU5) =====================

    public void modificar(int carrera, Inventario inv, Scanner sc) {
        System.out.println("\n>> Modificar prestamo - " + utilidades.nombreCarrera(carrera));
        Estudiante e = busquedas.localizar(carrera, inv, sc);
        if (e == null) {
            return;
        }
        boolean seguir = true;
        while (seguir) {
            System.out.println("\nRegistro actual:");
            e.imprimir();
            System.out.println("(La cedula y el serial no se pueden modificar)");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Telefono");
            System.out.println(carrera == 1 ? "4. Semestre" : "4. Modalidad de estudio");
            System.out.println(carrera == 1 ? "5. Promedio acumulado" : "5. Cantidad de asignaturas");
            System.out.println("6. Marca del equipo");
            System.out.println("7. Terminar modificacion");
            switch (v.ValidaRango(sc, "Seleccione el campo a modificar: ", 1, 7)) {
                case 1:
                    e.setNombre(v.ValidaTexto(sc, "Nuevo nombre: "));
                    break;
                case 2:
                    e.setApellido(v.ValidaTexto(sc, "Nuevo apellido: "));
                    break;
                case 3:
                    e.setTelefono(v.ValidaNumerico(sc, "Nuevo telefono: ", 7, 10));
                    break;
                case 4:
                    if (carrera == 1) {
                        ((EstudianteIngenieria) e).setSemestre(v.ValidaRango(sc, "Nuevo semestre (1-12): ", 1, 12));
                    } else {
                        ((EstudianteDiseno) e).setModalidad(pedirModalidad(sc));
                    }
                    break;
                case 5:
                    if (carrera == 1) {
                        ((EstudianteIngenieria) e)
                                .setPromedio(v.ValidaDecimal(sc, "Nuevo promedio (0.0 - 5.0): ", 0f, 5f));
                    } else {
                        ((EstudianteDiseno) e)
                                .setAsignaturas(v.ValidaRango(sc, "Nueva cantidad de asignaturas (1-10): ", 1, 10));
                    }
                    break;
                case 6:
                    inv.buscarEquipo(e).setMarca(v.ValidaTexto(sc, "Nueva marca: "));
                    break;
                default:
                    seguir = false;
                    break;
            }
            if (seguir) {
                System.out.println("OK: campo actualizado correctamente.");
            }
        }
        System.out.println("Modificacion finalizada.");
    }

    // ===================== DEVOLVER (HU5) =====================

    public void devolver(int carrera, Inventario inv, Scanner sc) {
        System.out.println("\n>> Devolucion de equipo - " + utilidades.nombreCarrera(carrera));
        Estudiante e = busquedas.localizar(carrera, inv, sc);
        if (e == null) {
            return;
        }
        e.imprimir();
        inv.buscarEquipo(e).imprimir();
        System.out.println("Confirmar devolucion? 1. Si  2. No");
        if (v.ValidaRango(sc, "Seleccione una opcion: ", 1, 2) == 2) {
            System.out.println("Devolucion cancelada, el registro se conserva.");
            return;
        }
        if (inv.devolver(e)) {
            System.out.println("OK: equipo devuelto y registro eliminado. Estudiantes en total: "
                    + inv.totalEstudiantes() + ", equipos en total: " + inv.totalEquipos());
        } else {
            System.out.println("ERROR: no se pudo eliminar el registro.");
        }
    }
}
