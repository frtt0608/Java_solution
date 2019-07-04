import sys
sys.stdin = open("input2.txt")

def dfs(temp, e):
    while stack :
        n = stack.pop(-1)
        for m in range(1, V+1):
            if temp[n][m] != 0:
                if visited[m] != 0 and visited[m] < visited[n] + temp[n][m]:
                    continue
                else:
                    stack.append(m)
                    visited[m] = visited[n] + temp[n][m]

V, E = map(int, input().split())
K = int(input())
data = [[0 for _ in range(V+1)] for _ in range(V+1)]
for e in range(E):
    u, v, w = map(int, input().split())
    data[u][v] = w

stack = []
stack.append(K)
visited = [0 for _ in range(V+1)]
dfs(data, 1)

for i in range(1, V+1):
    if visited[i] == 0 and i != K:
        print('INF')
    else:
        print(visited[i])