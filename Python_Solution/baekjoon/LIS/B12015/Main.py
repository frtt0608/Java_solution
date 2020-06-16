# 가장 긴 증가 부분수열2

import sys
sys.stdin = open("input.txt")

N = int(input())
numArr = list(map(int, input().split()))
numDP = [numArr[0]]

def binarySearch(maxNum, num):
    minNum = 0

    while (maxNum >= minNum):
        mid = (maxNum + minNum) // 2

        if numDP[mid] < num:
            minNum = mid + 1
        elif numDP[mid] > num:
            maxNum = mid - 1
        else:
            return mid

    return minNum    


for i in range(1, N):
    if numDP[-1] < numArr[i]:
        numDP.append(numArr[i])
    else:
        idx = binarySearch(len(numDP)-1, numArr[i])
        numDP[idx] = numArr[i]

print(len(numDP))