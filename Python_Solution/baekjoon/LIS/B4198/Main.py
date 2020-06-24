# 열차정렬

import sys
from collections import deque
from bisect import bisect_left, bisect_right
sys.stdin = open("input.txt")

N = int(input())
trains = [int(input()) for _ in range(N)]

def LDS_BS(maxNum, num):
    minNum = 0

    while(maxNum >= minNum):
        midNum = (maxNum + minNum) // 2

        if(dpArr_LDS[midNum] < num):
            maxNum = midNum - 1
        elif(dpArr_LDS[midNum] > num):
            minNum = midNum + 1
        else:
            return midNum

    return minNum

dpArr_LIS = [[0] * N]
dpArr_LDS = [[0] * N]

def LDS(idx):
    tempLDS = [-1]

    for i in range(1, N):
        if dpArr_LDS[-1] > trains[i]:
            dpArr_LDS.append(trains[i])
        else:
            idx = LDS_BS(len(dpArr_LDS)-1, trains[i])
            dpArr_LDS[idx] = trains[i]

    print(dpArr_LDS)

    
def LIS(idx):
    tempLIS = [sys.maxsize]
    
    for i in range(1, N):
        
        if dpArr_LIS[-1] < trains[i]:
            dpArr_LIS.append(trains[i])
        else:
            idx = bisect_left(dpArr_LIS, trains[i])
            dpArr_LIS[idx] = trains[i]

    print(dpArr_LIS)