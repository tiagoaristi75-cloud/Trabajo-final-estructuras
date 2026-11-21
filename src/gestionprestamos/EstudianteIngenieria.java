package gestionprestamos;

public class EstudianteIngenieria extends Estudiante {
    private int semestre;
    private float promedioAcumulado;

    public EstudianteIngenieria(String cedula, String nombre, String apellido,
            String telefono, int semestre, float promedioAcumulado,
            String serialEquipo) {
        super(cedula, nombre, apellido, telefono, serialEquipo);
        if (!setSemestre(semestre) || !setPromedioAcumulado(promedioAcumulado)) {
            throw new IllegalArgumentException("Semestre o promedio no valido.");
        }
    }

    public int getSemestre() { return semestre; }
    public float getPromedioAcumulado() { return promedioAcumulado; }

    public boolean setSemestre(int semestre) {
        if (semestre < 1) { return false; }
        this.semestre = semestre;
        return true;
    }

    public boolean setPromedioAcumulado(float promedioAcumulado) {
        if (!Float.isFinite(promedioAcumulado)
                || promedioAcumulado < 0 || promedioAcumulado > 5) {
            return false;
        }
        this.promedioAcumulado = promedioAcumulado;
        return true;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("===== ESTUDIANTE DE INGENIERIA =====");
        mostrarDatosComunes();
        System.out.println("Semestre: " + semestre);
        System.out.println("Promedio acumulado: " + promedioAcumulado);
    }
}
