# 가장 큰 정사각형
# dataMap을 탐색하다가 1이나오면 정사각형의 오른쪽 아래끝점으로 가정.
# 왼쪽, 위, 왼쪽 위의 값중 최솟값 + 1로 해당 값을 update

import sys
sys.stdin = open("input.txt")

N, M = map(int, input().split())
dataMap = [[0] * (M+1) for _ in range(N+1)]
maxSize = 0

for i in range(1, N+1):
    data = input()
    for j in range(1, M+1):
        dataMap[i][j] = int(data[j-1])


for i in range(1, N+1):
    for j in range(1, M+1):

        if not dataMap[i][j]: continue

        dataMap[i][j] = min(dataMap[i][j-1], dataMap[i-1][j], dataMap[i-1][j-1]) + 1
        maxSize = max(dataMap[i][j], maxSize)

print(maxSize**2)