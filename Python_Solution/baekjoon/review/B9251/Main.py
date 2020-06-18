# LCS

import sys
sys.stdin = open("input.txt")

str1 = input()
str2 = input()

lenStr1 = len(str1)
lenStr2 = len(str2)

cntMap = [[0] * (lenStr2+1) for _ in range(lenStr1+1)]

for i in range(1, lenStr1+1):
    for j in range(1, lenStr2+1):
        
        if str1[i-1] == str2[j-1]:
            cntMap[i][j] = cntMap[i-1][j-1] + 1
        else:
            cntMap[i][j] = max(cntMap[i-1][j], cntMap[i][j-1])

print(cntMap[-1][-1])