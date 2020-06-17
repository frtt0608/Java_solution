# 가장 긴 바이토닉 부분 수열

import sys
sys.stdin = open("input.txt")

N = int(input())
numArr = list(map(int, input().split()))
numDP = [[0 for _ in range(N)] for _ in range(2)]
numDP[0][0] = 1
numDP[1][N-1] = 1
maxCnt = 0

for i in range(1, N):
    for j in range(i):
        if numArr[i] > numArr[j]:
            numDP[0][i] = max(numDP[0][i], numDP[0][j])
    numDP[0][i] += 1

for i in range(N-2, -1, -1):
    for j in range(N-1, i, -1):
        if numArr[i] > numArr[j]:
            numDP[1][i] = max(numDP[1][i], numDP[1][j])
    numDP[1][i] += 1

for i in range(N):
    maxCnt = max(maxCnt, numDP[0][i] + numDP[1][i])

print(maxCnt-1)