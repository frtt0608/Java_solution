# 2xn 타일링

import sys

sys.stdin = open("input.txt")
N = int(input())

squareCnt = [0] * (N+5)
squareCnt[1] = 1
squareCnt[2] = 2
squareCnt[3] = 3
squareCnt[4] = 5

for i in range(5, N+1):
    squareCnt[i] = squareCnt[i-1] + squareCnt[i-2]

print(squareCnt[N]%10007)