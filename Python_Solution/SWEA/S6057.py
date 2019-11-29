# 그래프의 삼각형
import sys
sys.stdin = open("input.txt")

def DFS(init, index, cnt):
  global res
  if cnt==3:
    if init==index:
      res += 1
    return

  for id in range(1,N+1):
    if v[index][id]==1: continue
    if table[index][id]==1:
      v[index][id]=1
      v[id][index]=1
      DFS(init, index, cnt+1)
  

T=int(input())
for tc in range(1,T+1):
  NM=list(map(int, input().split()))
  N,M = NM[0],NM[1]
  table = [[0 for i in range(N+1)] for j in range(N+1)]
  v = [[0 for i in range(N+1)] for j in range(N+1)]
  res = 0

  for n in range(M):
    xy=list(map(int, input().split()))
    table[xy[0]][xy[1]] = 1
    table[xy[1]][xy[0]] = 1

  for i in range(1,N+1):
    for j in range(1,N+1):
      if table[i][j]==1:
        DFS(i,i,0)

  print('#%d %d' %(tc, res))