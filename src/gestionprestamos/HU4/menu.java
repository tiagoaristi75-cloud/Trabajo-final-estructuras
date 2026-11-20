import java.util.Scanner;


 //Clase principal. Solo crea el menu y llama a los metodos que hacen el trabajo
 //(HU4). No tiene ningun otro metodo aparte del main.
 
public class menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        utilidades u = new utilidades();
        Inventario inv = new Inventario();
        boolean seguir = true;
        while (seguir) {
            switch (u.menu(sc)) {
                case 1:
                    u.gestionar(1, inv, sc);
                    break;

                case 2:
                    u.gestionar(2, inv, sc);
                    break;

                case 3:
                    u.imprimirInventario(inv);
                    break;

                case 4:
                    System.out.println("Hasta luego, programa finalizado.");
                    seguir = false;
                    break;

                default:
                    System.out.println("Esta opcion no esta disponible, por favor valide.");
                    break;
            }
        }
        sc.close();
    }
}

