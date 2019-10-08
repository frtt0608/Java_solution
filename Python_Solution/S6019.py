import sys
sys.stdin = open("./input.txt")

T=int(input())
for tc in range(1,T+1):
  data = input().split(" ")
  D,A,B,F = int(data[0]), int(data[1]), int(data[2]), int(data[3])
  AB_D = D
  Ftrain = D
  res=0.0;
  while AB_D>0:
    AB_D -= (A+B)
    res+=float(F)
  print("#" + str(tc) + " " + str(res))