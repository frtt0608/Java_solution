import sys
sys.stdin = open("input.txt")

N = int(input())
RGB = [list(map(int, input().split())) for _ in range(N)]
priceDP = [[0]*3 for _ in range(N)]

priceDP[0][0] = RGB[0][0]
priceDP[0][1] = RGB[0][1]
priceDP[0][2] = RGB[0][2]

for i in range(1, N):
    for j in range(3):
        priceDP[i][j] = RGB[i][j] + min(priceDP[i-1][(j+1)%3], priceDP[i-1][(j+2)%3])

print(min(priceDP[N-1]))