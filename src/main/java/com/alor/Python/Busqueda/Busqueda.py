def linear_search(arr, x):
    for i in range(len(arr)):
        if arr[i] == x: return i
    return -1

def binary_search(arr, x):
    l, r = 0, len(arr) - 1
    while l <= r:
        mid = l + (r - l) // 2
        if arr[mid] == x: return mid
        elif arr[mid] < x: l = mid + 1
        else: r = mid - 1
    return -1

def ternary_search(arr, x):
    l, r = 0, len(arr) - 1
    while l <= r:
        mid1 = l + (r - l) // 3
        mid2 = r - (r - l) // 3
        if arr[mid1] == x: return mid1
        if arr[mid2] == x: return mid2
        if x < arr[mid1]: r = mid1 - 1
        elif x > arr[mid2]: l = mid2 + 1
        else:
            l = mid1 + 1
            r = mid2 - 1
    return -1

def jump_search(arr, x):
    n = len(arr)
    step = int(math.sqrt(n))
    prev = 0
    while arr[min(step, n) - 1] < x:
        prev = step
        step += int(math.sqrt(n))
        if prev >= n: return -1
    while arr[prev] < x:
        prev += 1
        if prev == min(step, n): return -1
    if arr[prev] == x: return prev
    return -1

def interpolation_search(arr, x):
    l, r = 0, len(arr) - 1
    while l <= r and x >= arr[l] and x <= arr[r]:
        if l == r:
            if arr[l] == x: return l
            return -1
        pos = l + int(((float(r - l) / (arr[r] - arr[l])) * (x - arr[l])))
        if arr[pos] == x: return pos
        if arr[pos] < x: l = pos + 1
        else: r = pos - 1
    return -1

def exponential_search(arr, x):
    if arr[0] == x: return 0
    n = len(arr)
    i = 1
    while i < n and arr[i] <= x:
        i = i * 2
    # Búsqueda binaria en el subarreglo
    l, r = i // 2, min(i, n - 1)
    while l <= r:
        mid = l + (r - l) // 2
        if arr[mid] == x: return mid
        elif arr[mid] < x: l = mid + 1
        else: r = mid - 1
    return -1