import sys
sys.stdin = open("input.txt")

R, C = map(int, input().split())
bread = []
visited = [[False]*C for _ in range(R)]

dx = [-1,0,1]
dy = [1,1,1]
flag = False

def checkWall(x, y):
    if x<0 or x>=R or y<0:
        return True
    return False

def backTracking(x, y):
    global flag, visited

    if y == C-1:
        flag = True
        return True

    for dir in range(3):
        nx = x + dx[dir]
        ny = y + dy[dir]
        if checkWall(nx, ny): continue
        if bread[nx][ny] == 'x': continue
        if visited[nx][ny]: continue

        visited[nx][ny] = True
        backTracking(nx, ny)
        if flag:
            return True
    
    return False

def main():
    global flag
    pipeCnt = 0

    for _ in range(R):
        data = list(input())
        bread.append(data)

    for i in range(R):
        flag = False
        if backTracking(i, 0):
            pipeCnt += 1

    print(pipeCnt)

main()