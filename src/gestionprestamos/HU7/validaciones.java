import java.util.Scanner;

public class validaciones {

    ///Entero cualquiera. 
    public int ValidaEntero(Scanner sc, String msg) {
        System.out.print(msg);
        String linea = sc.nextLine().trim();
        if (linea.matches("-?\\d{1,9}")) {
            return Integer.parseInt(linea);
        }
        System.out.println("Entrada invalida: ingrese solo un numero entero.");
        return ValidaEntero(sc, msg);
    }

    ///Entero dentro de un rango (sirve para menus y submenus). 
    public int ValidaRango(Scanner sc, String msg, int min, int max) {
        int n = ValidaEntero(sc, msg);
        if (n >= min && n <= max) {
            return n;
        }
        System.out.println("Opcion fuera de rango, elija entre " + min + " y " + max + ".");
        return ValidaRango(sc, msg, min, max);
    }

    /// Decimal positivo dentro de un rango. Acepta punto o coma. //
    public float ValidaDecimal(Scanner sc, String msg, float min, float max) {
        System.out.print(msg);
        String linea = sc.nextLine().trim().replace(',', '.');
        if (linea.matches("\\d{1,9}(\\.\\d{1,2})?")) {
            float n = Float.parseFloat(linea);
            if (n >= min && n <= max) {
                return n;
            }
            System.out.println("El valor debe estar entre " + min + " y " + max + ".");
        } else {
            System.out.println("Entrada invalida: ingrese un numero (ejemplo 3.5).");
        }
        return ValidaDecimal(sc, msg, min, max);
    }

    ///Texto solo con letras y espacios (nombres, apellidos, marca). 
    public String ValidaTexto(Scanner sc, String msg) {
        System.out.print(msg);
        String linea = sc.nextLine().trim();
        if (linea.matches("\\p{L}+( \\p{L}+)*") && linea.length() <= 30) {
            return linea;
        }
        System.out.println("Entrada invalida: solo letras, sin numeros ni simbolos, maximo 30 caracteres.");
        return ValidaTexto(sc, msg);
    }

    /// Solo digitos, con largo minimo y maximo (cedula, telefono).
    public String ValidaNumerico(Scanner sc, String msg, int minLen, int maxLen) {
        System.out.print(msg);
        String linea = sc.nextLine().trim();
        if (linea.matches("\\d{" + minLen + "," + maxLen + "}")) {
            return linea;
        }
        System.out.println("Entrada invalida: solo digitos, entre " + minLen + " y " + maxLen + " caracteres.");
        return ValidaNumerico(sc, msg, minLen, maxLen);
    }

    ///Letras y numeros sin espacios ni simbolos (serial del equipo). 
    public String ValidaAlfanumerico(Scanner sc, String msg) {
        System.out.print(msg);
        String linea = sc.nextLine().trim().toUpperCase();
        if (linea.matches("[A-Z0-9]{3,15}")) {
            return linea;
        }
        System.out.println("Entrada invalida: solo letras y numeros, sin espacios, entre 3 y 15 caracteres.");
        return ValidaAlfanumerico(sc, msg);
    }
}