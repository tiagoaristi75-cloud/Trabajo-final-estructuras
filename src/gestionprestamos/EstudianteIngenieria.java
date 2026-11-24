package gestionprestamos;

public class EstudianteIngenieria {

    private final String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private int semestre;
    private float promedioAcumulado;
    private final String serialEquipo;

    public EstudianteIngenieria(String cedula, String nombre, String apellido, String telefono,
                                int semestre, float promedioAcumulado, String serialEquipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.semestre = semestre;
        this.promedioAcumulado = promedioAcumulado;
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

    public int getSemestre() {
        return semestre;
    }

    public float getPromedioAcumulado() {
        return promedioAcumulado;
    }

    public String getSerialEquipo() {
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

    public boolean setSemestre(int semestre) {
        if (semestre < 1) {
            return false;
        }
        this.semestre = semestre;
        return true;
    }

    // El promedio se maneja en escala de 0 a 5.
    public boolean setPromedioAcumulado(float promedioAcumulado) {
        if (promedioAcumulado < 0 || promedioAcumulado > 5) {
            return false;
        }
        this.promedioAcumulado = promedioAcumulado;
        return true;
    }
}
