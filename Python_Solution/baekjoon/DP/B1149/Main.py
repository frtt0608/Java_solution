# RGB거리

import sys

sys.stdin = open("input.txt")

N = int(input())
RGBprice = [list(map(int, input().split())) for _ in range(N)]

priceDP = [[0] * 3 for _ in range(N)]
priceDP[0][0] = RGBprice[0][0]
priceDP[0][1] = RGBprice[0][1]
priceDP[0][2] = RGBprice[0][2]

for i in range(1,N):
    for j in range(3):
        priceDP[i][j] = RGBprice[i][j] + min(priceDP[i-1][(j+1)%3], priceDP[i-1][(j+2)%3])

minPrice = min(priceDP[N-1])
print(minPrice)

# def paintColor(idx, price):
#     global minPrice

#     if N == idx:
#         minPrice = min(minPrice, price)
#         return

#     for i in range(3):
#         if idx-1 >= 0:
#             if colorHome[idx-1] == i: continue
#         elif idx+1 < N:
#             if colorHome[idx+1] == i: continue

#         colorHome[idx] = i
#         paintColor(idx+1, price + RGBprice[idx][i])
#         colorHome[idx] = -1