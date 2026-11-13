package gestionprestamos;

public class ComputadorPortatil extends Equipo {

    private String sistemaOperativo;
    private String procesador;

    public ComputadorPortatil(String serial, String marca,
                              float tamano, float precio,
                              String sistemaOperativo, String procesador) {

        super(serial, marca, tamano, precio);

        this.sistemaOperativo = sistemaOperativo;
        this.procesador = procesador;
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