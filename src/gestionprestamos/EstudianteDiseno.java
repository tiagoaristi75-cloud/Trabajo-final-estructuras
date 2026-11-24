package gestionprestamos;

public class EstudianteDiseno {

    private final String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String modalidad;
    private int cantidadAsignaturas;
    private final int serialEquipo;

    public EstudianteDiseno(String cedula, String nombre, String apellido, String telefono,
                            String modalidad, int cantidadAsignaturas, int serialEquipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.modalidad = modalidad;
        this.cantidadAsignaturas = cantidadAsignaturas;
        this.serialEquipo = serialEquipo;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getModalidad() {
        return modalidad;
    }

    public int getCantidadAsignaturas() {
        return cantidadAsignaturas;
    }

    public int getSerialEquipo() {
        return serialEquipo;
    }

    public boolean setNombre(String nombre) {
        if (!Validaciones.esTextoValido(nombre, 0)) {
            return false;
        }
        this.nombre = nombre.trim();
        return true;
    }

    public boolean setApellido(String apellido) {
        if (!Validaciones.esTextoValido(apellido, 0)) {
            return false;
        }
        this.apellido = apellido.trim();
        return true;
    }

    public boolean setTelefono(String telefono) {
        if (!Validaciones.esNumerico(telefono, 0)) {
            return false;
        }
        this.telefono = telefono;
        return true;
    }

    // Solo se aceptan las modalidades virtual y presencial.
    public boolean setModalidad(String modalidad) {
        if (modalidad == null) {
            return false;
        }
        String valor = modalidad.trim().toLowerCase();
        if (!valor.equals("virtual") && !valor.equals("presencial")) {
            return false;
        }
        this.modalidad = valor;
        return true;
    }

    public boolean setCantidadAsignaturas(int cantidadAsignaturas) {
        if (cantidadAsignaturas < 1) {
            return false;
        }
        this.cantidadAsignaturas = cantidadAsignaturas;
        return true;
    }
}
