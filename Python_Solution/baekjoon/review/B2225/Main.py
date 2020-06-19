# 합분해

import sys
sys.stdin = open("input.txt")

size = 200
billion = 1000000000
dpMap = [[0] * (size+1) for _ in range(size+1)]

for i in range(1, size+1):
    dpMap[0][i] = 1

for i in range(1, size+1):
    for j in range(1, size+1):
        dpMap[i][j] = dpMap[i-1][j] + dpMap[i][j-1]
        dpMap[i][j] %= billion
        
N, K = map(int, input().split())
print(dpMap[N][K])