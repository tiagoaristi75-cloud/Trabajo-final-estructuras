import java.util.Scanner;


 ///Clase principal (HU5). Solo despliega el menu y llama a los metodos
 /// asociados. No tiene ningun otro metodo aparte del main.
 
public class menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        utilidades u = new utilidades();
        Renting renting = new Renting();
        boolean seguir = true;
        while (seguir) {
            switch (u.menu(sc)) {
                case 1:
                    u.gestionClientes(renting, sc);
                    break;
                case 2:
                    u.gestionVehiculos(renting, sc);
                    break;
                case 3:
                    u.gestionContratos(renting, sc);
                    break;
                case 4:
                    u.imprimirInforme(renting);
                    break;
                case 5:
                    System.out.println("Hasta luego, programa finalizado.");
                    seguir = false;
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }
}

