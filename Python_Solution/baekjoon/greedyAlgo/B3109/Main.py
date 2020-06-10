# 빵집

import sys
sys.stdin = open("input.txt")

R, C = map(int, input().split())
bread = [list(input()) for _ in range(R)]
visited = [[0 for _ in range(C)] for _ in range(R)]

dx = [-1,0,1]
dy = [1,1,1]
pipeCnt = 0
flag = False

def wall(x, y):
    if x<0 or x>=R or y<0:
        return True
    return False

def backTracking(x, y):
    global pipeCnt, flag, visited

    if  y == C-1:
        pipeCnt+=1
        flag = True
        return

    for dir in range(3):
        nx = x + dx[dir]
        ny = y + dy[dir]
        if wall(nx,ny): continue
        if bread[nx][ny] == "x": continue
        if visited[nx][ny]: continue

        visited[nx][ny] = True
        backTracking(nx, ny)
        if flag: return

def main():
    global flag

    for i in range(R):
        flag = False
        backTracking(i, 0)

    print(pipeCnt)
    
main()