package gestionprestamos;

import java.util.Scanner;

public class ComputadorPortatil extends Equipo {

    private String sistemaOperativo;
    private String procesador;

    public ComputadorPortatil(String serial, String marca, float tamano, float precio,
                              String sistemaOperativo, String procesador) {
        super(serial, marca, tamano, precio);
        this.sistemaOperativo = sistemaOperativo;
        this.procesador = procesador;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public String getProcesador() {
        return procesador;
    }

    public boolean setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
        return true;
    }

    public boolean setProcesador(String procesador) {
        this.procesador = procesador;
        return true;
    }

    public static String seleccionarSistemaOperativo(Scanner scanner) {
        System.out.println("\n===== SISTEMA OPERATIVO =====");
        System.out.println("1. Windows 7");
        System.out.println("2. Windows 10");
        System.out.println("3. Windows 11");
        System.out.print("Seleccione una opcion: ");
        int opcion = Integer.parseInt(scanner.nextLine().trim());

        switch (opcion) {
            case 1:
                return "Windows 7";
            case 2:
                return "Windows 10";
            default:
                return "Windows 11";
        }
    }

    public static String seleccionarProcesador(Scanner scanner) {
        System.out.println("\n===== PROCESADOR =====");
        System.out.println("1. AMD Ryzen");
        System.out.println("2. Intel Core i5");
        System.out.print("Seleccione una opcion: ");
        int opcion = Integer.parseInt(scanner.nextLine().trim());

        if (opcion == 1) {
            return "AMD Ryzen";
        }
        return "Intel Core i5";
    }

    @Override
    public void mostrarDatos() {
        System.out.println("===== COMPUTADOR PORTATIL =====");
        System.out.println("Serial: " + getSerial());
        System.out.println("Marca: " + getMarca());
        System.out.println("Tamano: " + getTamano() + " pulgadas");
        System.out.println("Precio: " + getPrecio());
        System.out.println("Sistema operativo: " + sistemaOperativo);
        System.out.println("Procesador: " + procesador);
    }
}
