# 욕심쟁이 판다

import sys
from bisect import bisect_left

sys.setrecursionlimit(40000)
sys.stdin = open("input.txt")

N = int(input())
dataMap = [list(map(int, input().split())) for _ in range(N)]
dpMap = [[-1] *N for _ in range(N)]

dx = [0,0,-1,1]
dy = [1,-1,0,0]
maxCnt = 0

def wall(x, y):
    if x<0 or x>=N or y<0 or y>=N: return True
    return False

def DFS(x, y):
    
    if dpMap[x][y] < 0:
        dpMap[x][y] = 0

        for dir in range(4):
            nx = x + dx[dir]
            ny = y + dy[dir]
            if wall(nx, ny): continue

            if dataMap[x][y] < dataMap[nx][ny]:
                dpMap[x][y] = max(dpMap[x][y], DFS(nx, ny))

        dpMap[x][y] += 1

    return dpMap[x][y]

for x in range(N):
    for y in range(N):
        maxCnt = max(maxCnt, DFS(x, y))

print(dpMap)
print(maxCnt)