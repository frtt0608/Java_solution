# 가장 큰 증가 부분 수열

import sys
sys.stdin = open("input.txt")

N = int(input())
numArr = list(map(int, input().split()))
numDP = [numArr[0]]

for i in range(1, N):
    sumSeq = 0
    for j in range(i):
        if numArr[i] > numArr[j]:
            sumSeq = max(sumSeq, numDP[j] + numArr[i])
        else:
            sumSeq = max(sumSeq, numArr[i])
    numDP.append(sumSeq)

print(numDP)
print(max(numDP))