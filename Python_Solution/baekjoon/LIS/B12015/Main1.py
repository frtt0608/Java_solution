# 가장 긴 증가 부분수열2

import sys
from bisect import bisect_left

sys.stdin = open("input.txt")

N = int(input())
numArr = list(map(int, input().split()))
numDP = [numArr[0]]


for i in range(1, N):
    if numDP[-1] < numArr[i]:
        numDP.append(numArr[i])
    else:
        idx = bisect_left(numDP, numArr[i])
        numDP[idx] = numArr[i]
        
print(len(numDP))