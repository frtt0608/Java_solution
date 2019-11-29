import sys
sys.stdin = open("input.txt")

import math

T=int(input())
for tc in range(1,T+1):
  data=list(map(int, input().split()))
  N,A,B = data[0], data[1], data[2]
  res=9999999
  maxR = math.floor(N**0.5)+1

  for R in range(1, maxR):
    maxC = math.floor(N/R)+1
    for C in range(1, maxC):
      temp = A*(abs(R - C)) + B*(N - R*C)
      if(temp < 0): break
      if res > temp:
        res=temp

  print('#%d %d' %(tc, res))
#1 84497
#2 91576
#3 353553
#4 0
#5 0
#6 8932
#7 175080
#8 40164
#9 61795
#10 20180