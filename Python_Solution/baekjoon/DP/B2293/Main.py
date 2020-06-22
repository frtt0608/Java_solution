# 동전 1

import sys
sys.stdin = open("input.txt")

N, K = map(int, input().split())

coins = [int(input()) for _ in range(N)]
coins.sort()
dpMap = [[0] * K for _ in range(N)]

