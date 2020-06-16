# 쉬운 계단 수

billion = 1000000000

import sys
sys.stdin = open("input.txt")

N = int(input())
numDP = [[0]* (10) for _ in range(N+1)]
totalCnt = 0

for i in range(1, 10):
    numDP[1][i] = 1

for i in range(1, N+1):
    for j in range(10):
        if j-1 >= 0:
            numDP[i][j] += numDP[i-1][j-1]
        
        if j+1 <= 9:
            numDP[i][j] += numDP[i-1][j+1]

        numDP[i][j] %= billion

    
for i in range(10):
    totalCnt += numDP[N][i]

# print(numDP)
print(totalCnt%billion)