# LCS (최장 공통 부분수열)

import sys
sys.stdin = open("input.txt")

inputStr1 = input()
inputStr2 = input()

lenStr1 = len(inputStr1)
lenStr2 = len(inputStr2)

LCS_DP = [[0] * (lenStr2+1) for _ in range(lenStr1+1)]

for i in range(1, lenStr1+1):
    tempStr1 = inputStr1[i-1]

    for j in range(1, lenStr2+1):
        tempStr2 = inputStr2[j-1]
        if tempStr1 == tempStr2:
            # 전 CASE에서 가장 긴 LCS에 1을 더해야 하기 때문에!
            LCS_DP[i][j] = LCS_DP[i-1][j-1] + 1 
        else:
            LCS_DP[i][j] = max(LCS_DP[i][j-1], LCS_DP[i-1][j])

print(LCS_DP[-1][-1])