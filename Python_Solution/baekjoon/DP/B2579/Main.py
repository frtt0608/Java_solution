# 계단 오르기

import sys
sys.stdin = open("input.txt")

N = int(input())

stairs = [int(input()) for _ in range(N)]

def upStair():
    for i in range(3, N):
        score = max(dpStairs[i-3] + stairs[i-1] + stairs[i], dpStairs[i-2] + stairs[i])
        dpStairs.append(score)


# DP에서 초기값 만들어줄 때, 낮은 N값에 주의하자 (런타임 에러가 날 수 있다)
dpStairs = []
dpStairs.append(stairs[0])

# N  >= 2인 경우
if N > 1:
    dpStairs.append(stairs[0] + stairs[1])
    if N > 2:
        dpStairs.append(max(stairs[0] + stairs[2], stairs[1] + stairs[2]))
        upStair()

print(dpStairs[N-1])