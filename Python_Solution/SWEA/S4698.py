# 테네스의 특별한 소수
# 에라토스테네스의 체

import sys
sys.stdin = open("input.txt")

intList = [False, False] +[True]*999999
decimal = []
for i in range(2, 1000001):
  if intList[i]:
    decimal.append(i)
    for j in range(2*i, 1000001, i):
      intList[j]=False
T=int(input())

for tc in range(1, T+1):
  data = list(map(int, input().split()))
  D, A, B = data[0], data[1], data[2]
  res=0
  for i in decimal:
    if i>B:
      break
    if i>=A and i<=B:
      strdata = str(i)
      for j in strdata:
        if int(j)==D:
          res+=1
          break
  print('#%d %d' %(tc, res))