import math

# 1. Insertion Sort
def insertion_sort(arr):
    for i in range(1, len(arr)):
        key = arr[i]
        j = i - 1
        while j >= 0 and key < arr[j]:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key
    return arr

# 2. Dual-Pivot QuickSort
def dual_pivot_quicksort(arr, low, high):
    if low < high:
        lp, rp = partition(arr, low, high)
        dual_pivot_quicksort(arr, low, lp - 1)
        dual_pivot_quicksort(arr, lp + 1, rp - 1)
        dual_pivot_quicksort(arr, rp + 1, high)

def partition(arr, low, high):
    if arr[low] > arr[high]:
        arr[low], arr[high] = arr[high], arr[low]
    p1, p2 = arr[low], arr[high]
    i = low + 1
    lt, gt = low + 1, high - 1
    while i <= gt:
        if arr[i] < p1:
            arr[i], arr[lt] = arr[lt], arr[i]
            lt += 1
        elif arr[i] > p2:
            arr[i], arr[gt] = arr[gt], arr[i]
            gt -= 1
            i -= 1
        i += 1
    lt -= 1
    gt += 1
    arr[low], arr[lt] = arr[lt], arr[low]
    arr[high], arr[gt] = arr[gt], arr[high]
    return lt, gt

# 3. Merge Sort
def merge_sort(arr):
    if len(arr) > 1:
        mid = len(arr) // 2
        L = arr[:mid]
        R = arr[mid:]
        merge_sort(L)
        merge_sort(R)
        i = j = k = 0
        while i < len(L) and j < len(R):
            if L[i] < R[j]:
                arr[k] = L[i]
                i += 1
            else:
                arr[k] = R[j]
                j += 1
            k += 1
        while i < len(L):
            arr[k] = L[i]
            i += 1
            k += 1
        while j < len(R):
            arr[k] = R[j]
            j += 1
            k += 1
    return arr

# 4. Heap Sort
def heapify(arr, n, i):
    largest = i
    l = 2 * i + 1
    r = 2 * i + 2
    if l < n and arr[l] > arr[largest]:
        largest = l
    if r < n and arr[r] > arr[largest]:
        largest = r
    if largest != i:
        arr[i], arr[largest] = arr[largest], arr[i]
        heapify(arr, n, largest)

def heap_sort(arr):
    n = len(arr)
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i)
    for i in range(n - 1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]
        heapify(arr, i, 0)
    return arr

# 5. Radix Sort
def counting_sort_radix(arr, exp1):
    n = len(arr)
    output = [0] * n
    count = [0] * 10
    for i in range(0, n):
        index = arr[i] // exp1
        count[index % 10] += 1
    for i in range(1, 10):
        count[i] += count[i - 1]
    i = n - 1
    while i >= 0:
        index = arr[i] // exp1
        output[count[index % 10] - 1] = arr[i]
        count[index % 10] -= 1
        i -= 1
    for i in range(0, len(arr)):
        arr[i] = output[i]

def radix_sort(arr):
    max1 = max(arr)
    exp = 1
    while max1 / exp >= 1:
        counting_sort_radix(arr, exp)
        exp *= 10
    return arr

# 6. Bucket Sort
def bucket_sort(arr):
    buckets = [[] for _ in range(10)]
    for num in arr:
        index = num // 10000000
        buckets[index].append(num)
    for i in range(10):
        buckets[i] = insertion_sort(buckets[i])
    k = 0
    for i in range(10):
        for j in range(len(buckets[i])):
            arr[k] = buckets[i][j]
            k += 1
    return arr