package com.alor.java.Ordenamiento;

import java.util.ArrayList;
import java.util.Collections;

public class algoritmos{

    // 1. Insertion Sort
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // 2. Dual-Pivot QuickSort
    public static void dualPivotQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int[] pivots = particion(arr, low, high);
            dualPivotQuickSort(arr, low, pivots[0] - 1);
            dualPivotQuickSort(arr, pivots[0] + 1, pivots[1] - 1);
            dualPivotQuickSort(arr, pivots[1] + 1, high);
        }
    }

    private static int[] particion(int[] arr, int low, int high) {
        if (arr[low] > arr[high]) {
            int temp = arr[low]; arr[low] = arr[high]; arr[high] = temp;
        }
        int p1 = arr[low], p2 = arr[high];
        int i = low + 1, lt = low + 1, gt = high - 1;
        while (i <= gt) {
            if (arr[i] < p1) {
                int temp = arr[i]; arr[i] = arr[lt]; arr[lt] = temp;
                lt++;
            } else if (arr[i] > p2) {
                int temp = arr[i]; arr[i] = arr[gt]; arr[gt] = temp;
                gt--; i--;
            }
            i++;
        }
        lt--; gt++;
        int temp1 = arr[low]; arr[low] = arr[lt]; arr[lt] = temp1;
        int temp2 = arr[high]; arr[high] = arr[gt]; arr[gt] = temp2;
        return new int[]{lt, gt};
    }

    // 3. Merge Sort
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1; int n2 = r - m;
        int[] L = new int[n1]; int[] R = new int[n2];
        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) { arr[k] = L[i]; i++; }
            else { arr[k] = R[j]; j++; }
            k++;
        }
        while (i < n1) { arr[k] = L[i]; i++; k++; }
        while (j < n2) { arr[k] = R[j]; j++; k++; }
    }

    // 4. Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0]; arr[0] = arr[i]; arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;
        if (largest != i) {
            int swap = arr[i]; arr[i] = arr[largest]; arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    // 5. Radix Sort
    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) if (arr[i] > max) max = arr[i];
        for (int exp = 1; max / exp > 0; exp *= 10) countSort(arr, arr.length, exp);
    }

    private static void countSort(int[] arr, int n, int exp) {
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < n; i++) count[(arr[i] / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        System.arraycopy(output, 0, arr, 0, n);
    }

    // 6. Bucket Sort
    public static void bucketSort(int[] arr) {
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < 10; i++) buckets[i] = new ArrayList<>();
        for (int num : arr) {
            int index = num / 10000000;
            buckets[index].add(num);
        }
        int k = 0;
        for (int i = 0; i < 10; i++) {
            Collections.sort(buckets[i]);
            for (int num : buckets[i]) {
                arr[k++] = num;
            }
        }
    }
}