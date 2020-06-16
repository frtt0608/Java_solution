# 가장 긴 감소하는 부분수열
# 이중 for문으로 구현 O(N^2)

import sys
sys.stdin = open("input.txt")

N = int(input())
numArr = list(map(int, input().split()))
numDP = [0] * N
numDP[0] = 1

for i in range(1, N):
    maxCnt = 0
    for j in range(i):
        if numArr[i] < numArr[j]:
            maxCnt = max(maxCnt, numDP[j])

    numDP[i] = maxCnt + 1

print(max(numDP))