# 열차정렬

import sys
from collections import deque
from bisect import bisect_left, bisect_right
sys.stdin = open("input.txt")

N = int(input())
trains = [int(input()) for _ in range(N)]
dpArr_LIS = [sys.maxsize]
dpArr_LDS = [0]

for i in range(N):
    dpArr_LIS[0] = min(dpArr_LIS[0], trains[i])
    dpArr_LDS[0] = max(dpArr_LDS[0], trains[i])

# def binarySearch(maxNum, num):
#     minNum = 0

#     while(maxNum >= minNum):
#         midNum = (maxNum + minNum) // 2

#         if(dpArr[midNum] < num):
#             maxNum = midNum - 1
#         elif(dpArr[midNum] > num):
#             minNum = midNum + 1
#         else:
#             return midNum

#     return minNum

for i in range(1, N):
    print(dpArr_LDS[-1], trains[i])
    if dpArr_LDS[-1] > trains[i]:
        dpArr_LDS.append(trains[i])
    else:
        idx = bisect_right(dpArr_LDS, trains[i])
        print(idx, dpArr_LDS)
        dpArr_LDS[idx] = trains[i]
    print(dpArr_LDS)
    

for i in range(1, N):
    
    if dpArr_LIS[-1] < trains[i]:
        dpArr_LIS.append(trains[i])
    else:
        idx = bisect_left(dpArr_LIS, trains[i])
        dpArr_LIS[idx] = trains[i]

print(dpArr_LIS)