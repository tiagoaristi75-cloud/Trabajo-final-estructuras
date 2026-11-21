package gestionprestamos;

public abstract class Estudiante {
    private final String cedula;
    private final String serialEquipo;
    private String nombre;
    private String apellido;
    private String telefono;

    public Estudiante(String cedula, String nombre, String apellido,
                      String telefono, String serialEquipo) {
        if (!Validaciones.esNumerico(cedula, 0)
                || !Validaciones.esAlfanumerico(serialEquipo, 0)) {
            throw new IllegalArgumentException("Cedula o serial no valido.");
        }
        this.cedula = cedula;
        this.serialEquipo = serialEquipo;
        if (!setNombre(nombre) || !setApellido(apellido)
                || !setTelefono(telefono)) {
            throw new IllegalArgumentException("Datos del estudiante no validos.");
        }
    }

    public String getCedula() { return cedula; }
    public String getSerialEquipo() { return serialEquipo; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }

    public boolean setNombre(String nombre) {
        if (!Validaciones.esTextoValido(nombre, 0)) { return false; }
        this.nombre = nombre.trim();
        return true;
    }

    public boolean setApellido(String apellido) {
        if (!Validaciones.esTextoValido(apellido, 0)) { return false; }
        this.apellido = apellido.trim();
        return true;
    }

    public boolean setTelefono(String telefono) {
        if (!Validaciones.esNumerico(telefono, 0)) { return false; }
        this.telefono = telefono;
        return true;
    }

    protected void mostrarDatosComunes() {
        System.out.println("Cedula: " + cedula);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Telefono: " + telefono);
        System.out.println("Serial del equipo: " + serialEquipo);
    }

    public abstract void mostrarDatos();
}
