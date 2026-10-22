package com.alor.java.Busqueda;

public class Busqueda {

    public static int linearSearch(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x) return mid;
            if (arr[mid] < x) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    public static int ternarySearch(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid1 = l + (r - l) / 3;
            int mid2 = r - (r - l) / 3;
            if (arr[mid1] == x) return mid1;
            if (arr[mid2] == x) return mid2;

            if (x < arr[mid1]) r = mid1 - 1;
            else if (x > arr[mid2]) l = mid2 + 1;
            else {
                l = mid1 + 1;
                r = mid2 - 1;
            }
        }
        return -1;
    }

    public static int jumpSearch(int[] arr, int x) {
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;

        while (arr[Math.min(step, n) - 1] < x) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) return -1;
        }

        while (arr[prev] < x) {
            prev++;
            if (prev == Math.min(step, n)) return -1;
        }

        if (arr[prev] == x) return prev;
        return -1;
    }

    public static int interpolationSearch(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r && x >= arr[l] && x <= arr[r]) {
            if (l == r) {
                if (arr[l] == x) return l;
                return -1;
            }
            int pos = l + (((r - l) / (arr[r] - arr[l])) * (x - arr[l]));
            if (arr[pos] == x) return pos;
            if (arr[pos] < x) l = pos + 1;
            else r = pos - 1;
        }
        return -1;
    }

    public static int exponentialSearch(int[] arr, int x) {
        if (arr.length == 0) return -1;
        if (arr[0] == x) return 0;
        int i = 1;
        while (i < arr.length && arr[i] <= x) {
            i = i * 2;
        }
        // Llamada a búsqueda binaria en el subarreglo encontrado
        return binarySearchSubArray(arr, i / 2, Math.min(i, arr.length - 1), x);
    }

    // Método auxiliar para Exponential Search
    private static int binarySearchSubArray(int[] arr, int l, int r, int x) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x) return mid;
            if (arr[mid] < x) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }
}