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

    public boolean setMarca(String marca) {
        this.marca = marca;
        return true;
    }

    public boolean setTamano(float tamano) {
        this.tamano = tamano;
        return true;
    }

    public boolean setPrecio(float precio) {
        this.precio = precio;
        return true;
    }

    public abstract void mostrarDatos();
}
