# 파일 합치기
# Knuth's Optimization - O(N^2)

import sys
import pprint

sys.stdin = open("input.txt")

T = int(input())

for _ in range(T):
    K = int(input())
    fileSize = list(map(int, input().split()))
    # fileSize.sort(reverse = True)
    dpMap = [[0] * (K+1) for _ in range(K+1)]
    priceSum = [0] * K
    cost = [[0] * K for _ in range(K)]

    for i in range(K):
        cost[i][i] = i # 단조성과 사각 부등식의 배열 cost 초기화

        if i == 0:
            priceSum[i] = fileSize[i]
        else:
            priceSum[i] = priceSum[i-1] + fileSize[i]


    # i는 구간의 길이
    for i in range(1, K):
        # j는 구간이 시작하는 index
        for j in range(0, K):

            if i+j == K: break
            dpMap[j][i+j] = sys.maxsize

            # k는 구간을 둘로 나누는 역할
            for k in range(cost[j][i+j-1], cost[j+1][i+j]+1):

                temp = 0

                if j == 0:
                    temp = dpMap[j][k] + dpMap[k+1][j+i] + priceSum[i+j]
                else:
                    temp = dpMap[j][k] + dpMap[k+1][i+j] + priceSum[i+j] - priceSum[j-1]

                if dpMap[j][i+j] > temp:
                    dpMap[j][i+j] = temp
                    cost[j][i+j] = k
    
    # pprint.pprint(cost)
    # pprint.pprint(dpMap)
    print(dpMap[0][K-1])