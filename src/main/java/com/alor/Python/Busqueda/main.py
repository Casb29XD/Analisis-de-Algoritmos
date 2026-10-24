import random
import time
# Asegúrate de que el archivo con las funciones se llame algoritmos_busqueda.py
import Busqueda as alg

def main():
    print("--- PRUEBAS DE BÚSQUEDA (PYTHON) ---")
    try:
        n = int(input("Ingrese el tamaño del arreglo ordenado (ej. 10000): "))
    except ValueError:
        n = 10000
        print("Valor inválido, usando 10000 por defecto.")

    # Generar y ordenar el arreglo original
    arr_original = [random.randint(10000000, 99999999) for _ in range(n)]
    arr_original.sort()

    x_input = input("Ingrese el número a buscar (o presione Enter para uno aleatorio que exista): ")
    if x_input.strip() == "":
        x = random.choice(arr_original)
        print(f"Buscando el número: {x}")
    else:
        x = int(x_input)

    while True:
        print("\nSeleccione un algoritmo:")
        print("1. Búsqueda Lineal")
        print("2. Búsqueda Binaria")
        print("3. Búsqueda Ternaria")
        print("4. Jump Search")
        print("5. Interpolation Search")
        print("6. Exponential Search")
        print("0. Salir")
        opcion = input("Opción: ")

        if opcion == '0':
            break

        resultado = -1
        # Medición de alta precisión en nanosegundos
        start_time = time.perf_counter_ns()

        if opcion == '1':
            resultado = alg.linear_search(arr_original, x)
        elif opcion == '2':
            resultado = alg.binary_search(arr_original, x)
        elif opcion == '3':
            resultado = alg.ternary_search(arr_original, x)
        elif opcion == '4':
            resultado = alg.jump_search(arr_original, x)
        elif opcion == '5':
            resultado = alg.interpolation_search(arr_original, x)
        elif opcion == '6':
            resultado = alg.exponential_search(arr_original, x)
        else:
            print("Opción no válida.")
            continue

        end_time = time.perf_counter_ns()
        duracion_ns = end_time - start_time
        duracion_ms = duracion_ns / 1_000_000.0

        if resultado != -1:
            print(f"Elemento encontrado en el índice: {resultado}")
        else:
            print("Elemento NO encontrado.")

        print(f"Tiempo de ejecución: {duracion_ms:.6f} milisegundos ({duracion_ns} ns)")

if __name__ == "__main__":
    main()