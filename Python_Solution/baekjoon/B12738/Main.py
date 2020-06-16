# 가장 긴 증가하는 부분수열 3

import sys
from bisect import bisect_left
sys.stdin = open("input.txt")

N = int(input())
numArr = list(map(int, input().split()))
numDP = [numArr[0]]

def binarySearch(maxNum, num):
    minNum = 0

    while (maxNum >= minNum):
        mid = (maxNum + minNum) // 2

        if numDP[mid] < num:
            minNum = mid+1
        elif numDP[mid] > num:
            maxNum = mid-1
        else:
            return mid

    return minNum

for num in numArr:
    if numDP[-1] < num:
        numDP.append(num)
    else:
        idx = bisect_left(numDP, num)
        numDP[idx] = num

print(len(numDP))