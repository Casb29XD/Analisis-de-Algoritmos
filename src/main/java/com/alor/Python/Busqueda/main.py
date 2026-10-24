import random
import time
import Busqueda as alg

def main():
    print("--- PRUEBAS DE BÚSQUEDA AUTOMATIZADAS (PYTHON) ---")
    try:
        n = int(input("Ingrese el tamaño del arreglo ordenado (ej. 1000000): "))
    except ValueError:
        n = 1000000
        print("Valor inválido, usando 1.000.000 por defecto.")

    print(f"Generando arreglo de {n} elementos...")
    arr_original = [random.randint(10000000, 99999999) for _ in range(n)]
    arr_original.sort()

    # Preparar lote de 1000 claves para el Promedio General
    presentes = random.sample(arr_original, 500)
    ausentes = [random.randint(1, 9999999) for _ in range(500)]
    claves_mezcladas = presentes + ausentes
    random.shuffle(claves_mezcladas)

    # Claves para pruebas de posición
    clave_inicio = arr_original[0]
    clave_centro = arr_original[n // 2]
    clave_fin = arr_original[-1]

    algoritmos = [
        ("Lineal", alg.linear_search),
        ("Binaria", alg.binary_search),
        ("Ternaria", alg.ternary_search),
        ("Jump", alg.jump_search),
        ("Interpolación", alg.interpolation_search),
        ("Exponencial", alg.exponential_search)
    ]

    while True:
        print("\nSeleccione el tipo de prueba:")
        print("1. Promedio General (1.000 claves mezcladas)")
        print("2. Prueba por Posición (Inicio, Centro, Fin - 1.000 repeticiones c/u)")
        print("0. Salir")
        opcion = input("Opción: ")

        if opcion == '0':
            break

        elif opcion == '1':
            print(f"\n--- PROMEDIO GENERAL ({n} elementos) ---")
            for nombre, funcion in algoritmos:
                start_time = time.perf_counter_ns()
                for clave in claves_mezcladas:
                    funcion(arr_original, clave)
                end_time = time.perf_counter_ns()
                
                promedio_ns = (end_time - start_time) / 1000
                print(f"{nombre}: {promedio_ns / 1_000_000.0:.6f} ms ({promedio_ns:.2f} ns)")

        elif opcion == '2':
            print(f"\n--- PRUEBA POR POSICIÓN ({n} elementos) ---")
            for nombre, funcion in algoritmos:
                # Inicio
                t_start = time.perf_counter_ns()
                for _ in range(1000): funcion(arr_original, clave_inicio)
                t_end = time.perf_counter_ns()
                prom_inicio = (t_end - t_start) / 1000.0

                # Centro
                t_start = time.perf_counter_ns()
                for _ in range(1000): funcion(arr_original, clave_centro)
                t_end = time.perf_counter_ns()
                prom_centro = (t_end - t_start) / 1000.0

                # Fin
                t_start = time.perf_counter_ns()
                for _ in range(1000): funcion(arr_original, clave_fin)
                t_end = time.perf_counter_ns()
                prom_fin = (t_end - t_start) / 1000.0

                print(f"\n{nombre}:")
                print(f"  Inicio: {prom_inicio / 1_000_000.0:.6f} ms")
                print(f"  Centro: {prom_centro / 1_000_000.0:.6f} ms")
                print(f"  Final:  {prom_fin / 1_000_000.0:.6f} ms")
        else:
            print("Opción inválida.")

if __name__ == "__main__":
    main()