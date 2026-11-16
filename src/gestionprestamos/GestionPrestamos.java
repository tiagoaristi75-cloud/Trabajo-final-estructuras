package gestionprestamos;

import java.util.Scanner;

public class GestionPrestamos {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String sistemaOperativo =
                ComputadorPortatil.seleccionarSistemaOperativo(scanner);

        String procesador =
                ComputadorPortatil.seleccionarProcesador(scanner);

        ComputadorPortatil portatil = new ComputadorPortatil(
                "PORT001",
                "Lenovo",
                15.6f,
                2500000f,
                sistemaOperativo,
                procesador
        );

        portatil.mostrarDatos();

        scanner.close();
    }
}