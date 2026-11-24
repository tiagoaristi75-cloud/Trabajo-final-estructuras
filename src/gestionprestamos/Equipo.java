package gestionprestamos;

public abstract class Equipo {

    private final String serial;
    private String marca;
    private float tamano;
    private float precio;

    public Equipo(String serial, String marca, float tamano, float precio) {
        this.serial = serial;
        this.marca = marca;
        this.tamano = tamano;
        this.precio = precio;
    }

    public String getSerial() {
        return serial;
    }

    public String getMarca() {
        return marca;
    }

    public float getTamano() {
        return tamano;
    }

    public float getPrecio() {
        return precio;
    }

    // La marca solo admite letras y espacios.
    public boolean setMarca(String marca) {
        if (!Validaciones.esTextoValido(marca, 0)) {
            return false;
        }
        this.marca = marca.trim();
        return true;
    }

    public boolean setTamano(float tamano) {
        if (tamano <= 0) {
            return false;
        }
        this.tamano = tamano;
        return true;
    }

    public boolean setPrecio(float precio) {
        if (precio < 0) {
            return false;
        }
        this.precio = precio;
        return true;
    }

    public abstract void mostrarDatos();
}
