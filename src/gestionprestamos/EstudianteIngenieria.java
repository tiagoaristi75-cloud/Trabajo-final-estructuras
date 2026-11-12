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

    public boolean setSemestre(int semestre) {
        this.semestre = semestre;
        return true;
    }

    public boolean setPromedioAcumulado(float promedioAcumulado) {
        this.promedioAcumulado = promedioAcumulado;
        return true;
    }
}
