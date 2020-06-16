# 가장 긴 증가하는 부분 수열
# 이진탐색을 활용한 LIS구현 O(NlogN)

import sys
sys.stdin = open("input.txt")

N = int(input())

numArr = list(map(int, input().split()))
numDP = [numArr[0]]

def binarySearch(minNum, maxNum, num):

    while maxNum >= minNum:
        midNum = (maxNum+minNum)//2

        if numDP[midNum] < num:
            minNum = midNum+1
        elif numDP[midNum] > num:   
            maxNum = midNum-1
        else:
            return midNum
    
    return minNum

for i in range(1, N):
    if numDP[-1] < numArr[i]:
        numDP.append(numArr[i])
    else:
        idx = binarySearch(0, len(numDP)-1, numArr[i])
        numDP[idx] = numArr[i]

print(numDP)
print(len(numDP))