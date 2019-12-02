# 그래프의 삼각형
import sys
sys.stdin = open("input.txt")

T=int(input())
for tc in range(1,T+1):
  NM=list(map(int, input().split()))
  N,M = NM[0],NM[1]
  table = [[0 for i in range(N+1)] for j in range(N+1)]
  res = 0

  for n in range(M):
    xy=list(map(int, input().split()))
    table[xy[0]][xy[1]] = 1
    table[xy[1]][xy[0]] = 1

  for i in range(1,N+1):
    for j in range(i,N+1):
      if table[i][j]==1:
        for k in range(1,N+1):
          if table[i][k]==1 and table[j][k]==1:
            res += 1

  res /= 3

  print('#%d %d' %(tc, res))