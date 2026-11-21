package gestionprestamos;

import java.util.Scanner;

public class TabletaGrafica extends Equipo {
    private String almacenamiento;
    private float peso;

    public TabletaGrafica(String serial, String marca, float tamano,
            float precio, String almacenamiento, float peso) {
        super(serial, marca, tamano, precio);
        if (!setAlmacenamiento(almacenamiento) || !setPeso(peso)) {
            throw new IllegalArgumentException("Almacenamiento o peso no valido.");
        }
    }

    public String getAlmacenamiento() { return almacenamiento; }
    public float getPeso() { return peso; }

    public boolean setAlmacenamiento(String almacenamiento) {
        if (!Validaciones.esAlfanumerico(almacenamiento, 0)) { return false; }
        this.almacenamiento = almacenamiento;
        return true;
    }

    public boolean setPeso(float peso) {
        if (!Float.isFinite(peso) || peso <= 0) { return false; }
        this.peso = peso;
        return true;
    }

    // No se fijan capacidades que no aparecen en el reparto disponible.
    public static String seleccionarAlmacenamiento(Scanner scanner) {
        return Validaciones.leerSerial(scanner,
                "Almacenamiento (sin espacios; ejemplo 256GB): ");
    }

    @Override
    public void mostrarDatos() {
        System.out.println("===== TABLETA GRAFICA =====");
        System.out.println("Serial: " + getSerial());
        System.out.println("Marca: " + getMarca());
        System.out.println("Tamano: " + getTamano());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Almacenamiento: " + almacenamiento);
        System.out.println("Peso: " + peso);
    }
}
