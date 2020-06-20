# 파일 합치기
import sys
import math

sys.stdin = open("input.txt")

T = int(input())

for _ in range(T):
    K = int(input())
    fileSize = list(map(int, input().split()))
    fileSize.sort(reverse = True)
    dataMap = [[sys.maxsize] * K for _ in range(K)]
    totalPrice = 0

    for i in range(K-1):
        for j in range(1, K):
            if i+j >= K: break

            

    print(totalPrice)