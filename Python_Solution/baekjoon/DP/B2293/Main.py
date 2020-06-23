# 동전 1

import sys
sys.stdin = open("input.txt")

N, K = map(int, input().split())

coins = [int(input()) for _ in range(N)]
coins.sort()
dpArr = [0] * (K+1)
dpArr[0] = 1

for coin in coins:
    coin =  int(coin)

    for i in range(coin, K+1):  
        dpArr[i] += dpArr[i - coin]

print(dpArr)
print(dpArr[K])