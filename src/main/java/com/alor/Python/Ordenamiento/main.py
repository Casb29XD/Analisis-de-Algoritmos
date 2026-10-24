import random
import time
# Asegúrate de que el archivo con las funciones se llame algoritmos_ordenamiento.py
import algoritmos_ordenamiento as alg

def mostrar_vista_previa(arr, titulo="Estado"):
    n = len(arr)
    if n <= 10:
        print(f"[{titulo}] ({n} elementos): {arr}")
    else:
        print(f"[{titulo}] ({n} elementos): {arr[:5]} ... {arr[-5:]}")

def main():
    print("--- PRUEBAS DE ORDENAMIENTO (PYTHON) ---")
    try:
        n = int(input("Ingrese el tamaño del arreglo de prueba (ej. 10000): "))
    except ValueError:
        n = 10000
        print("Valor inválido, usando 10000 por defecto.")

    # Generar arreglo original
    arr_original = [random.randint(10000000, 99999999) for _ in range(n)]

    while True:
        print("\nSeleccione un algoritmo:")
        print("1. Insertion Sort")
        print("2. Dual-Pivot QuickSort")
        print("3. Merge Sort")
        print("4. Heap Sort")
        print("5. Radix Sort")
        print("6. Bucket Sort")
        print("0. Salir")
        opcion = input("Opción: ")

        if opcion == '0':
            break

        print("\n" + "="*60)
        print("🚀 Procesando datos...")
        mostrar_vista_previa(arr_test, "ANTES de ordenar")

        start_time = time.perf_counter_ns()
        # 1. Clonar el arreglo PRIMERO
        arr_test = arr_original.copy()

        # 2. Mostrar la vista previa ANTES de iniciar el cronómetro
        print("\n" + "="*60)
        print("🚀 Procesando datos...")
        mostrar_vista_previa(arr_test, "ANTES de ordenar")

        # 3. Iniciar cronómetro de alta precisión justo antes del algoritmo
        start_time = time.perf_counter_ns()

        if opcion == '1':
            alg.insertion_sort(arr_test)
        elif opcion == '2':
            alg.dual_pivot_quicksort(arr_test, 0, len(arr_test) - 1)
        elif opcion == '3':
            alg.merge_sort(arr_test)
        elif opcion == '4':
            alg.heap_sort(arr_test)
        elif opcion == '5':
            alg.radix_sort(arr_test)
        elif opcion == '6':
            alg.bucket_sort(arr_test)
        else:
            print("Opción no válida.")
            continue

        # 4. Detener cronómetro inmediatamente después
        end_time = time.perf_counter_ns()

        # 5. Mostrar resultados visuales y tiempos
        mostrar_vista_previa(arr_test, "DESPUÉS de ordenar")
        print("="*60)

        duracion_ns = end_time - start_time
        duracion_ms = duracion_ns / 1_000_000.0

        print(f"Tiempo de ejecución: {duracion_ms:.4f} milisegundos ({duracion_ns} ns)")

if __name__ == "__main__":
    main()