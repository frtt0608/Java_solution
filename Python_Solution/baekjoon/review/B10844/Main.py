billion = 1000000000

import sys
sys.stdin = open("input.txt")

N = int(input())
numArr = [[0] * 10 for _ in range(N+1)]
totalCnt = 0

for i in range(1, 10):
    numArr[1][i] = 1

for i in range(2, N+1):
    for j in range(10):
        if j-1 >= 0:
            numArr[i][j] += numArr[i-1][j-1]
        if j+1 <= 9:
            numArr[i][j] += numArr[i-1][j+1]

        numArr[i][j] %= billion

for i in range(10):
    totalCnt += numArr[N][i]

print(totalCnt%billion)