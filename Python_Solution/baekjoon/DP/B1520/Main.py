# 내리막 길
# DFS와 DP의 조합으로..
# 한번 방문한 곳은 재방문x

import sys
sys.setrecursionlimit(10000)
sys.stdin = open("input.txt")

M, N = map(int, input().split())
dataMap = [list(map(int, input().split())) for _ in range(M)]
dpMap = [[-1] * N for _ in range(M)]

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def wall(x, y):
    if x<0 or x>=M or y<0 or y>=N: return True
    return False

def DFS(x, y):
    global totalCnt

    if dpMap[x][y] != -1:
        return dpMap[x][y]

    if x == M-1 and y == N-1:
        return 1

    dpMap[x][y] = 0

    for dir in range(4):
        nx = x + dx[dir]
        ny = y + dy[dir]
        if wall(nx, ny): continue

        if dataMap[x][y] > dataMap[nx][ny]:
            dpMap[x][y] += DFS(nx, ny)
    
    return dpMap[x][y]

totalCnt = DFS(0, 0)
print(totalCnt)