package com.alor.java.Ordenamiento;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("--- PRUEBAS DE ORDENAMIENTO (JAVA) ---");
        System.out.print("Ingrese el tamaño del arreglo de prueba (ej. 10000): ");
        int n = scanner.nextInt();

        // Generar arreglo original con números aleatorios de 8 dígitos
        int[] arrOriginal = new int[n];
        for (int i = 0; i < n; i++) {
            arrOriginal[i] = 10000000 + rand.nextInt(90000000);
        }

        while (true) {
            System.out.println("\nSeleccione un algoritmo:");
            System.out.println("1. Insertion Sort");
            System.out.println("2. Dual-Pivot QuickSort");
            System.out.println("3. Merge Sort");
            System.out.println("4. Heap Sort");
            System.out.println("5. Radix Sort");
            System.out.println("6. Bucket Sort");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 0) break;

            // Clonar el arreglo para ordenar siempre una lista desordenada
            int[] arrTest = arrOriginal.clone();

            long startTime = System.nanoTime(); // Medición de alta precisión

            switch (opcion) {
                case 1: algoritmos.insertionSort(arrTest); break;
                case 2: algoritmos.dualPivotQuickSort(arrTest, 0, arrTest.length - 1); break;
                case 3: algoritmos.mergeSort(arrTest, 0, arrTest.length - 1); break;
                case 4: algoritmos.heapSort(arrTest); break;
                case 5: algoritmos.radixSort(arrTest); break;
                case 6: algoritmos.bucketSort(arrTest); break;
                default: System.out.println("Opción no válida."); continue;
            }

            long endTime = System.nanoTime();
            double durationMs = (endTime - startTime) / 1_000_000.0;

            System.out.printf("Tiempo de ejecución: %.4f milisegundos (%d ns)\n", durationMs, (endTime - startTime));
        }
        scanner.close();
    }
}