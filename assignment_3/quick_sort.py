# Q. Write a program to implement Quick Sort by taking middle element as pivot element. 
# Also compute the complexity of this program. Explain it to me, and use Python.


def sort_arr(arr):
    # Base case
    if len(arr) <= 1:
        return arr

    # Choose the middle element as pivot
    mid = len(arr) // 2
    pivot = arr[mid]

    # Partition step
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]

    # Recursive step
    return sort_arr(left) + middle + sort_arr(right)


# Example usage
data = [23, 4, 42,15, 15,6,3, 16,29, 8, 3]
sorted_data = sort_arr(data)
print("Sorted array:", sorted_data)
