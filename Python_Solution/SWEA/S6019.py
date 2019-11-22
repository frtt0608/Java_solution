import sys
sys.stdin = open("input.txt")

T=int(input())

for tc in range(1,T+1):
  data = list(map(int, input().split()))
  D, A, B, F = data[0], data[1], data[2], data[3]
  t=0
  res=0
  move_train = 0
  move_fly = 0
  flag = True
  while D>0:
    t+=1
    move_train = t*(A+B)
    if flag:
      move_fly = t*(F+B)
      
