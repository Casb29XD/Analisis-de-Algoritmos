package com.alor.java.Busqueda;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("--- PRUEBAS DE BÚSQUEDA AUTOMATIZADAS (JAVA) ---");
        System.out.print("Ingrese el tamaño del arreglo ordenado (ej. 1000000): ");
        int n = scanner.nextInt();

        System.out.println("Generando arreglo de " + n + " elementos...");
        int[] arrOriginal = new int[n];
        for (int i = 0; i < n; i++) {
            arrOriginal[i] = 10000000 + rand.nextInt(90000000);
        }
        Arrays.sort(arrOriginal);

        // Preparar lote de 1000 claves (500 presentes, 500 ausentes)
        ArrayList<Integer> clavesMezcladas = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            clavesMezcladas.add(arrOriginal[rand.nextInt(n)]);
            clavesMezcladas.add(rand.nextInt(9999999) + 1);
        }
        Collections.shuffle(clavesMezcladas);

        // Claves para pruebas de posición
        int claveInicio = arrOriginal[0];
        int claveCentro = arrOriginal[n / 2];
        int claveFin = arrOriginal[n - 1];

        while (true) {
            System.out.println("\nSeleccione el tipo de prueba:");
            System.out.println("1. Promedio General (1.000 claves mezcladas)");
            System.out.println("2. Prueba por Posición (Inicio, Centro, Fin - 1.000 repeticiones c/u)");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 0) break;

            if (opcion == 1) {
                System.out.println("\n--- PROMEDIO GENERAL (" + n + " elementos) ---");
                for (int alg = 1; alg <= 6; alg++) {
                    long startTime = System.nanoTime();
                    for (int clave : clavesMezcladas) {
                        ejecutarAlgoritmo(alg, arrOriginal, clave);
                    }
                    long endTime = System.nanoTime();
                    double promedioNs = (endTime - startTime) / 1000.0;
                    System.out.printf("Algoritmo %d: %.6f ms (%.2f ns)\n", alg, promedioNs / 1_000_000.0, promedioNs);
                }
            } else if (opcion == 2) {
                System.out.println("\n--- PRUEBA POR POSICIÓN (" + n + " elementos) ---");
                for (int alg = 1; alg <= 6; alg++) {
                    // Inicio
                    long t1 = System.nanoTime();
                    for (int i = 0; i < 1000; i++) ejecutarAlgoritmo(alg, arrOriginal, claveInicio);
                    double promInicio = (System.nanoTime() - t1) / 1000.0;

                    // Centro
                    long t2 = System.nanoTime();
                    for (int i = 0; i < 1000; i++) ejecutarAlgoritmo(alg, arrOriginal, claveCentro);
                    double promCentro = (System.nanoTime() - t2) / 1000.0;

                    // Fin
                    long t3 = System.nanoTime();
                    for (int i = 0; i < 1000; i++) ejecutarAlgoritmo(alg, arrOriginal, claveFin);
                    double promFin = (System.nanoTime() - t3) / 1000.0;

                    System.out.printf("\nAlgoritmo %d:\n", alg);
                    System.out.printf("  Inicio: %.6f ms\n", promInicio / 1_000_000.0);
                    System.out.printf("  Centro: %.6f ms\n", promCentro / 1_000_000.0);
                    System.out.printf("  Final:  %.6f ms\n", promFin / 1_000_000.0);
                }
            }
        }
        scanner.close();
    }

    private static void ejecutarAlgoritmo(int opcion, int[] arr, int x) {
        switch (opcion) {
            case 1: Busqueda.linearSearch(arr, x); break;
            case 2: Busqueda.binarySearch(arr, x); break;
            case 3: Busqueda.ternarySearch(arr, x); break;
            case 4: Busqueda.jumpSearch(arr, x); break;
            case 5: Busqueda.interpolationSearch(arr, x); break;
            case 6: Busqueda.exponentialSearch(arr, x); break;
        }
    }
}