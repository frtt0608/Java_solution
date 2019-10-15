def bubble_sort(arr):
  for size in reversed(range(len(arr))):
    for i in range(size):
      if arr[i] > arr[i+1]:
        arr[i],arr[i+1]=arr[i+1],arr[i];
  return arr
# 버블정렬
def selection_sort(arr):
  for size in reversed(range(len(arr))):
    max_x=0
    for i in range(1, size+1):
      if arr[i] > arr[max_x]:
        max_x = i
    arr[max_x], arr[size] = arr[size], arr[max_x]
  return arr
# 선택정렬
def insertion_sort(arr):
  for size in range(1, len(arr)):
    val = arr[size]
    i = size
    while i>0 and arr[i-1]>val:
      arr[i] = arr[i-1]
      i -= 1
    arr[i] = val
  return arr
# 삽입정렬
def merge_sort(arr):
  if len(arr) > 1:
    mid = len(arr)//2
    left_arr, right_arr = arr[:mid], arr[mid:]
    merge_sort(left_arr)
    merge_sort(right_arr)

    li, ri, i = 0, 0, 0
    while li<len(left_arr) and ri<len(right_arr):
      if left_arr[li] < right_arr[ri]:
        arr[i] = left_arr[li]
        li += 1
      else:
        arr[i] = right_arr[ri]
        ri += 1
      i+=1
    arr[i:] = left_arr[li:] if li !=len(left_arr) else right_arr[ri:]
  return arr
# 병합정렬

def pivot(arr, left, right):
  pivot_val = arr[left]
  pivot_idx = left
  while left <= right:
    while left <= right and arr[left] <= pivot_val:
      left += 1
    while left <= right and arr[right] >= pivot_val:
      right -= 1
    if left <= right:
      arr[left], arr[right]=arr[right],arr[left]
      left += 1
      right -= 1
  arr[pivot_idx], arr[right] = arr[right], arr[pivot_idx]
  return right

def quick_sort(arr):
  def Qsort(arr, first, last):
    if first < last:
      split = pivot(arr, first, last)
      Qsort(arr, first, split-1)
      Qsort(arr, split+1, last)
  Qsort(arr, 0, len(arr)-1)
  return arr
# 퀵정렬


data = [3, 5, 1, 2, 9, 6, 4, 7, 5]
print("버블정렬: ", bubble_sort(data))
print("선택정렬: ",selection_sort(data))
print("삽입정렬: ",insertion_sort(data))
print("병합정렬: ",merge_sort(data))
print("퀵정렬: ",quick_sort(data))