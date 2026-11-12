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
        this.nombre = nombre;
        return true;
    }

    public boolean setApellido(String apellido) {
        this.apellido = apellido;
        return true;
    }

    public boolean setTelefono(String telefono) {
        this.telefono = telefono;
        return true;
    }

    public boolean setModalidad(String modalidad) {
        this.modalidad = modalidad;
        return true;
    }

    public boolean setCantidadAsignaturas(int cantidadAsignaturas) {
        this.cantidadAsignaturas = cantidadAsignaturas;
        return true;
    }
}
