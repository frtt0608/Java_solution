# 동전 2

import sys
sys.stdin = open("input.txt")

N, K = map(int, input().split())

coins = [int(input()) for _ in range(N)]
dpMap = [sys.maxsize for _ in range(K+1)]
totalCnt = 0

coins = list(set(coins))
coins.sort()
N = len(coins)

for coin in coins:
    for i in range(coin, K+1):
        if i%coin == 0:
            dpMap[i] = min(dpMap[i], i//coin)
        else:
            dpMap[i] = min(dpMap[i], dpMap[i-coin]+1)

if dpMap[K] == sys.maxsize:
    totalCnt = -1
else:
    totalCnt = dpMap[K]

print(totalCnt)