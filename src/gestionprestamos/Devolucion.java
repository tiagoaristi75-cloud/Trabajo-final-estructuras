package gestionprestamos;

/**
 * Registro de un equipo devuelto junto con el estudiante que lo tenia.
 * Se guarda en la pila del historial de devoluciones.
 */
public class Devolucion {

    private final Object estudiante;
    private final Equipo equipo;

    public Devolucion(Object estudiante, Equipo equipo) {
        this.estudiante = estudiante;
        this.equipo = equipo;
    }

    public Object getEstudiante() {
        return estudiante;
    }

    public Equipo getEquipo() {
        return equipo;
    }
}
