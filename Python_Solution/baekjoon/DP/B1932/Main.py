import sys
sys.stdin = open("input.txt")

N = int(input())

tri = [list(map(int, input().split())) for _ in range(N)]
triDP = [[0]*i for i in range(1, N+1)]
triDP[0][0] = tri[0][0]

for i in range(1, N):
    for j in range(0, i+1):
        if j == 0:
            triDP[i][j] = triDP[i-1][0] + tri[i][j]
        elif j == i:
            triDP[i][j] = triDP[i-1][i-1] + tri[i][j]
        else:
            triDP[i][j] = max(triDP[i-1][j-1] + tri[i][j], triDP[i-1][j] + tri[i][j])

print(max(triDP[N-1]))