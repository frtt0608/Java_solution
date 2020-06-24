# 파일합치기2
# 시간초과

import sys
sys.stdin = open("input.txt")

T = int(input())

for _ in range(T):
    K = int(input())
    priceArr = list(map(int, input().split()))
    priceArr.sort()
    sumArr = [0] * K
    sumArr[0] = priceArr[0]
    cost = [[0] * K for _ in range(K)]

    for i in range(1, K):
        cost[i][i] = i
        sumArr[i] = sumArr[i-1] + priceArr[i]
    
    # print(sumArr)

    dpMap = [[0] * K for _ in range(K)]

    for i in range(1, K):
        for j in range(K):

            if i+j == K: break

            dpMap[j][i+j] = sys.maxsize

            for k in range(cost[j][i+j-1], cost[j+1][i+j]+1):
                if k+1 >= K: break
                
                if j == 0:
                    temp = dpMap[j][k] + dpMap[k+1][i+j] + sumArr[i+j]
                else:
                    temp = dpMap[j][k] + dpMap[k+1][i+j] + sumArr[i+j] - sumArr[j-1]

                if temp < dpMap[j][i+j]:
                    dpMap[j][i+j] = temp
                    cost[j][i+j] = k

    print(dpMap)
    print(dpMap[0][K-1])