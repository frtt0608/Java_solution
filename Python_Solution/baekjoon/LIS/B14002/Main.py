# 가장 긴 증가하는 부분수열4

import sys
from bisect import bisect_left
sys.stdin = open("input.txt")

N = int(input())
numArr = list(map(int, input().split()))
numDP = [1 for _ in range(N)]
dpArr = [[i] for i in numArr]

# 길이 구하기
for i in range(0, N):
    for j in range(i):
        if numArr[j] < numArr[i]:
            if numDP[i] < numDP[j]+1:
                dpArr[i] = dpArr[j] + [numArr[i]]
                numDP[i] = numDP[j] + 1

maxlen = 0
idx = 0
for i in range(N):
    if maxlen < len(dpArr[i]):
        maxlen = len(dpArr[i])
        idx = i

print(maxlen)
print(*dpArr[idx])