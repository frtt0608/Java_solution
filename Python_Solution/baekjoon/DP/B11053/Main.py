# 가장 긴 증가하는 부분수열
# 이중 for문 O(N^2)

import sys
sys.stdin = open("input.txt")

N = int(input())

numArr = list(map(int, input().split()))
numDP = [0] * N
numDP[0] = 1

for i in range(1, N):
    maxCnt = 0
    for j in range(i):
        if numArr[j] < numArr[i]:
            maxCnt = max(maxCnt, numDP[j])
    numDP[i] = maxCnt + 1
    
print(numDP)
print(max(numDP))