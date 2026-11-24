package gestionprestamos;

import java.util.Scanner;

public class TabletaGrafica extends Equipo {

    private String almacenamiento;
    private float peso;

    public TabletaGrafica(String serial, String marca, float tamano, float precio,
                          String almacenamiento, float peso) {
        super(serial, marca, tamano, precio);
        this.almacenamiento = almacenamiento;
        this.peso = peso;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public float getPeso() {
        return peso;
    }

    public boolean setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
        return true;
    }

    public boolean setPeso(float peso) {
        this.peso = peso;
        return true;
    }

    public static String seleccionarAlmacenamiento(Scanner scanner) {
        System.out.println("\n===== ALMACENAMIENTO =====");
        System.out.println("1. 256 GB");
        System.out.println("2. 512 GB");
        System.out.println("3. 1 TB");
        System.out.print("Seleccione una opcion: ");
        int opcion = Integer.parseInt(scanner.nextLine().trim());

        switch (opcion) {
            case 1:
                return "256 GB";
            case 2:
                return "512 GB";
            default:
                return "1 TB";
        }
    }

    @Override
    public void mostrarDatos() {
        System.out.println("===== TABLETA GRAFICA =====");
        System.out.println("Serial: " + getSerial());
        System.out.println("Marca: " + getMarca());
        System.out.println("Tamano: " + getTamano() + " pulgadas");
        System.out.println("Precio: " + getPrecio());
        System.out.println("Almacenamiento: " + almacenamiento);
        System.out.println("Peso: " + peso + " kg");
    }
}
