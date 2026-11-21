package gestionprestamos;

import java.util.Scanner;

/**
 * Valida y lee los datos que ingresa el usuario.
 * Cada método revisa el dato de forma recursiva y, si no es válido,
 * muestra el error y lo vuelve a pedir.
 */
public class Validaciones {

    // Solo letras (con tildes y ñ) y espacios. Se usa para nombres, apellidos y marcas.
    public static boolean esTextoValido(String texto, int i) {
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        if (i == texto.length()) {
            return true;
        }
        char c = texto.charAt(i);
        if (!Character.isLetter(c) && c != ' ') {
            return false;
        }
        return esTextoValido(texto, i + 1);
    }

    // Solo letras y números, sin espacios. Se usa para seriales.
    public static boolean esAlfanumerico(String texto, int i) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        if (i == texto.length()) {
            return true;
        }
        char c = texto.charAt(i);
        boolean esLetra = (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
        boolean esDigito = c >= '0' && c <= '9';
        if (!esLetra && !esDigito) {
            return false;
        }
        return esAlfanumerico(texto, i + 1);
    }

    // Solo dígitos. Se usa para cédulas, teléfonos y números enteros.
    public static boolean esNumerico(String texto, int i) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        if (i == texto.length()) {
            return true;
        }
        char c = texto.charAt(i);
        if (c < '0' || c > '9') {
            return false;
        }
        return esNumerico(texto, i + 1);
    }

    public static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        String dato = sc.nextLine().trim();
        if (esTextoValido(dato, 0)) {
            return dato;
        }
        System.out.println("Error: el campo no puede estar vacío y solo admite letras.");
        return leerTexto(sc, mensaje);
    }

    public static String leerSerial(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        String dato = sc.nextLine().trim();
        if (esAlfanumerico(dato, 0)) {
            return dato;
        }
        System.out.println("Error: el serial solo admite letras y números, sin espacios ni caracteres especiales.");
        return leerSerial(sc, mensaje);
    }

    // Lee un número que se guarda como texto, como la cédula o el teléfono.
    public static String leerNumeroTexto(Scanner sc, String mensaje, int min, int max) {
        System.out.print(mensaje);
        String dato = sc.nextLine().trim();
        if (!esNumerico(dato, 0)) {
            System.out.println("Error: el campo no puede estar vacío y solo admite números.");
            return leerNumeroTexto(sc, mensaje, min, max);
        }
        if (dato.length() < min || dato.length() > max) {
            System.out.println("Error: debe tener entre " + min + " y " + max + " dígitos.");
            return leerNumeroTexto(sc, mensaje, min, max);
        }
        return dato;
    }

    // Se limita a 9 dígitos para que el número siempre quepa en un int.
    public static int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        String dato = sc.nextLine().trim();
        if (esNumerico(dato, 0) && dato.length() <= 9) {
            return Integer.parseInt(dato);
        }
        System.out.println("Error: ingrese un número entero válido.");
        return leerEntero(sc, mensaje);
    }

    public static int leerOpcion(Scanner sc, String mensaje, int min, int max) {
        int opcion = leerEntero(sc, mensaje);
        if (opcion >= min && opcion <= max) {
            return opcion;
        }
        System.out.println("Opción inválida: elija un número entre " + min + " y " + max + ".");
        return leerOpcion(sc, mensaje, min, max);
    }

    // Acepta punto o coma como separador decimal.
    public static float leerFloat(Scanner sc, String mensaje, float min, float max) {
        System.out.print(mensaje);
        String dato = sc.nextLine().trim().replace(',', '.');
        String[] partes = dato.split("\\.", -1);
        boolean formatoValido = (partes.length == 1 && esNumerico(partes[0], 0))
                || (partes.length == 2 && esNumerico(partes[0], 0) && esNumerico(partes[1], 0));
        if (!formatoValido) {
            System.out.println("Error: ingrese un número válido, por ejemplo 4.5");
            return leerFloat(sc, mensaje, min, max);
        }
        float valor = Float.parseFloat(dato);
        if (valor < min || valor > max) {
            System.out.println("Error: el valor debe estar entre " + min + " y " + max + ".");
            return leerFloat(sc, mensaje, min, max);
        }
        return valor;
    }

    public static String leerModalidad(Scanner sc) {
        System.out.println("Modalidad de estudio:");
        System.out.println("1. Virtual");
        System.out.println("2. Presencial");
        int opcion = leerOpcion(sc, "Seleccione una opción: ", 1, 2);
        if (opcion == 1) {
            return "virtual";
        }
        return "presencial";
    }
}
