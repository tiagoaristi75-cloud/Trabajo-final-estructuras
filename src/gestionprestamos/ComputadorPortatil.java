package gestionprestamos;

import java.util.Scanner;

public class ComputadorPortatil extends Equipo {

    private String sistemaOperativo;
    private String procesador;

    public ComputadorPortatil(String serial, String marca,
                             float tamano, float precio,
                             String sistemaOperativo, String procesador) {

        super(serial, marca, tamano, precio);

        if (!setSistemaOperativo(sistemaOperativo)) {
            throw new IllegalArgumentException("Sistema operativo no valido.");
        }

        if (!setProcesador(procesador)) {
            throw new IllegalArgumentException("Procesador no valido.");
        }
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public String getProcesador() {
        return procesador;
    }

    public boolean setSistemaOperativo(String sistemaOperativo) {
        if ("Windows 7".equals(sistemaOperativo)
                || "Windows 10".equals(sistemaOperativo)
                || "Windows 11".equals(sistemaOperativo)) {

            this.sistemaOperativo = sistemaOperativo;
            return true;
        }

        return false;
    }

    public boolean setProcesador(String procesador) {
        if ("AMD Ryzen".equals(procesador)
                || "Intel Core i5".equals(procesador)) {

            this.procesador = procesador;
            return true;
        }

        return false;
    }

    public static String seleccionarSistemaOperativo(Scanner scanner) {
        while (true) {
            System.out.println("===== SISTEMA OPERATIVO =====");
            System.out.println("1. Windows 7");
            System.out.println("2. Windows 10");
            System.out.println("3. Windows 11");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    return "Windows 7";
                case "2":
                    return "Windows 10";
                case "3":
                    return "Windows 11";
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    public static String seleccionarProcesador(Scanner scanner) {
        while (true) {
            System.out.println("===== PROCESADOR =====");
            System.out.println("1. AMD Ryzen");
            System.out.println("2. Intel Core i5");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    return "AMD Ryzen";
                case "2":
                    return "Intel Core i5";
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    @Override
    public void mostrarDatos() {
        System.out.println("===== COMPUTADOR PORTATIL =====");
        System.out.println("Serial: " + getSerial());
        System.out.println("Marca: " + getMarca());
        System.out.println("Tamano: " + getTamano());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Sistema operativo: " + sistemaOperativo);
        System.out.println("Procesador: " + procesador);
    }
}