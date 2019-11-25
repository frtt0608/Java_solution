# 기차 사이의 파리
# 수학적으로 접근.. 거리=시간*속력
import sys
sys.stdin = open("input.txt")

T=int(input())

for tc in range(1,T+1):
  data = list(map(float, input().split()))
  D, A, B, F = data[0], data[1], data[2], data[3]
  
  res = 0.0
  time = D/(A+B)
  res = F*time
  
  print('#%d %.6f' %(tc, res))