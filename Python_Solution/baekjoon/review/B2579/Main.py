import sys
sys.stdin = open("input.txt")

N = int(input())
stairs = [int(input()) for _ in range(N)]
maxScore = [0] * N

maxScore[0] = stairs[0]

if N >= 2:
    maxScore[1] = stairs[0] + stairs[1]
if N >= 3:
    maxScore[2] = max(stairs[1] + stairs[2], stairs[0] + stairs[2])

for i in range(3, N):
    maxScore[i] = max(maxScore[i-3] + stairs[i-1] + stairs[i], maxScore[i-2] + stairs[i])

print(maxScore[-1])