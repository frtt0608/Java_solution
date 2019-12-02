# 최장 공통 부분 수열
import sys
sys.stdin = open("input.txt")

def find(index, data):
  global data2
  for i in range(index, len(data2)):
    if data==data2[i]:
      return i, True
  return index, False

T=int(input())
for tc in range(1, T+1):
  data = list(map(str, input().split()))
  data1, data2 = data[0], data[1]
  
  max_res = 0
  for i in range(len(data1)-1):
    temp=0
    ret=0
    for j in range(i, len(data1)):
      ret, flag = find(ret+1,data1[j])
      if flag:
        temp+=1

    if max_res < temp:
      max_res = temp

  print('#%d %d' %(tc, max_res))