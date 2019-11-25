# 계산기

import sys
sys.stdin = open("input.txt")

def DFS(index, total):
  global res
  if index==N:
    if res < total:
      res = total
    return

  DFS(index+1, total+data[index])
  if data[index] != 0 and total != 0:
    DFS(index+1, total*data[index])

T=int(input())

for tc in range(1,T+1):
  N = int(input())
  data = list(map(int, input().split()))
  res = 0
  DFS(0,0)
  print('#%d %d' %(tc, res))
