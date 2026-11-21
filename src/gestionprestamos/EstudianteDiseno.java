package gestionprestamos;

public class EstudianteDiseno extends Estudiante {
    private String modalidad;
    private int cantidadAsignaturas;

    public EstudianteDiseno(String cedula, String nombre, String apellido,
            String telefono, String modalidad, int cantidadAsignaturas,
            String serialEquipo) {
        super(cedula, nombre, apellido, telefono, serialEquipo);
        if (!setModalidad(modalidad) || !setCantidadAsignaturas(cantidadAsignaturas)) {
            throw new IllegalArgumentException("Modalidad o asignaturas no validas.");
        }
    }

    public String getModalidad() { return modalidad; }
    public int getCantidadAsignaturas() { return cantidadAsignaturas; }

    public boolean setModalidad(String modalidad) {
        if (modalidad == null) { return false; }
        String valor = modalidad.trim().toLowerCase(java.util.Locale.ROOT);
        if (!valor.equals("virtual") && !valor.equals("presencial")) { return false; }
        this.modalidad = valor;
        return true;
    }

    public boolean setCantidadAsignaturas(int cantidadAsignaturas) {
        if (cantidadAsignaturas < 1) { return false; }
        this.cantidadAsignaturas = cantidadAsignaturas;
        return true;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("===== ESTUDIANTE DE DISENO =====");
        mostrarDatosComunes();
        System.out.println("Modalidad: " + modalidad);
        System.out.println("Cantidad de asignaturas: " + cantidadAsignaturas);
    }
}
