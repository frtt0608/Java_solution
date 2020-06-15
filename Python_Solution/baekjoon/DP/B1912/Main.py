# 연속합

import sys
sys.stdin = open("input.txt")

N = int(input())
numArr = list(map(int, input().split()))
numDP = [0] * N
numDP[0] = numArr[0]

if N >= 2:
    numDP[1] = max(numArr[1], numArr[0] + numArr[1])

for i in range(2, N):
    numDP[i] = max(numArr[i], numDP[i-1] + numArr[i])

# print(numArr)
# print(numDP)

print(max(numDP))