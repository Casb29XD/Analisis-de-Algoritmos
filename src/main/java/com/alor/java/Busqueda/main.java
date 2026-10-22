package com.alor.java.Busqueda;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("--- PRUEBAS DE BÚSQUEDA (JAVA) ---");
        System.out.print("Ingrese el tamaño del arreglo ordenado (ej. 10000): ");
        int n = scanner.nextInt();

        int[] arrOriginal = new int[n];
        for (int i = 0; i < n; i++) {
            arrOriginal[i] = 10000000 + rand.nextInt(90000000);
        }
        Arrays.sort(arrOriginal); // Los algoritmos de búsqueda requieren el array ordenado

        System.out.print("Ingrese el número a buscar (o -1 para elegir uno aleatorio que sí exista): ");
        int x = scanner.nextInt();
        if (x == -1) {
            x = arrOriginal[rand.nextInt(n)]; // Seleccionar uno que exista
            System.out.println("Buscando el número: " + x);
        }

        while (true) {
            System.out.println("\nSeleccione un algoritmo:");
            System.out.println("1. Búsqueda Lineal");
            System.out.println("2. Búsqueda Binaria");
            System.out.println("3. Búsqueda Ternaria");
            System.out.println("4. Jump Search");
            System.out.println("5. Interpolation Search");
            System.out.println("6. Exponential Search");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 0) break;

            long startTime = System.nanoTime();
            int resultado = -1;

            switch (opcion) {
                case 1: resultado = Busqueda.linearSearch(arrOriginal, x); break;
                case 2: resultado = Busqueda.binarySearch(arrOriginal, x); break;
                case 3: resultado = Busqueda.ternarySearch(arrOriginal, x); break;
                case 4: resultado = Busqueda.jumpSearch(arrOriginal, x); break;
                case 5: resultado = Busqueda.interpolationSearch(arrOriginal, x); break;
                case 6: resultado = Busqueda.exponentialSearch(arrOriginal, x); break;
                default: System.out.println("Opción no válida."); continue;
            }

            long endTime = System.nanoTime();
            double durationMs = (endTime - startTime) / 1_000_000.0;

            if (resultado != -1) {
                System.out.println("Elemento encontrado en el índice: " + resultado);
            } else {
                System.out.println("Elemento NO encontrado.");
            }
            System.out.printf("Tiempo de ejecución: %.6f milisegundos (%d ns)\n", durationMs, (endTime - startTime));
        }
        scanner.close();
    }
}
