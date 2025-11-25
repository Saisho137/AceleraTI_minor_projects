import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su nombre:");
        String name = scanner.nextLine();
        System.out.println("Hola, " + name + "! Bienvenido a la aplicación.");

        System.out.println("Ingrese el primer número: ");
        double numero1 = scanner.nextDouble();

        System.out.println("Ingrese el primer número: ");
        double numero2 = scanner.nextDouble();

        double resultado = numero1 + numero2;
        System.out.println("La suma de " + numero1 + " y " + numero2 + " es: " + resultado);

        scanner.close();
    }
}

/*
Compilar: javac Main.java
Ejecutar: java Main
Verificar: java -version
*/