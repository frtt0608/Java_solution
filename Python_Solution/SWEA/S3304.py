# 최장 공통 부분 수열 LCS
import sys
sys.stdin = open("input.txt")

T=int(input())
for tc in range(1, T+1):
  data = list(map(str, input().split()))
  data1, data2 = data[0], data[1]
  data1 = "0" + data1 # 0acaykp
  data2 = "0" + data2 # 0capcak
  table = [[0 for i in range(len(data1))] for j in range(len(data2))]

  for i in range(1, len(data2)):
    for j in range(1, len(data1)):
      if data1[j]==data2[i]:
        table[i][j] = table[i-1][j] + 1

      else:
        if table[i-1][j] > table[i][j-1]:
          table[i][j] = table[i-1][j]
        else:
          table[i][j] = table[i][j-1]

  for i in table:
    print(i)

  print('#%d %d' %(tc, table[len(data2)-1][len(data1)-1]))