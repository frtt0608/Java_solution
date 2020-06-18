# 합분해
# 점화식 세워서 구현하기

billion = 1000000000
size = 201
dpMap = [[0] * size for _ in range(size)]

for i in range(1, size):
    dpMap[0][i] = 1

for i in range(1, size):
    for j in range(1, size):

        dpMap[i][j] = dpMap[i-1][j] + dpMap[i][j-1]
        dpMap[i][j] %= billion

import sys
sys.stdin = open("input.txt")

N, K = map(int, input().split())
print(dpMap[N][K])