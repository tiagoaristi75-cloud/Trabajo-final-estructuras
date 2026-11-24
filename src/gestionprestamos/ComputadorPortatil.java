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

    // Solo se aceptan los sistemas del submenu.
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
        if ("AMD Ryzen".equals(procesador) || "Intel Core i5".equals(procesador)) {
            this.procesador = procesador;
            return true;
        }
        return false;
    }

    public static String seleccionarSistemaOperativo(Scanner scanner) {
        System.out.println("\n===== SISTEMA OPERATIVO =====");
        System.out.println("1. Windows 7");
        System.out.println("2. Windows 10");
        System.out.println("3. Windows 11");
        int opcion = Validaciones.leerOpcion(scanner, "Seleccione una opcion: ", 1, 3);

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
        int opcion = Validaciones.leerOpcion(scanner, "Seleccione una opcion: ", 1, 2);

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
