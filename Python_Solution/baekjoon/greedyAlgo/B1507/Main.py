import sys
sys.stdin = open("input.txt")

N = int(input())
minMap = [[0 for _ in range(N)] for _ in range(N)]
dist = [[1 for _ in range(N)] for _ in range(N)]


def findMinDist(target):
    for i in range(N):
        for j in range(N):
            if i == j or i == target or j == target: continue

            if minMap[i][target] == minMap[i][j] + minMap[j][target]:
                dist[i][target] = 0
            elif minMap[i][target] > minMap[i][j] + minMap[j][target]:
                return 0
    
    return 1

def main():
    time = 0

    for i in range(N):
        minMap[i] = list(map(int, input().split()))

    for target in range(N):
        if not findMinDist(target):
            time = -1
            break

    if time==0:
        for i in range(N):
            for j in range(N):
                if dist[i][j]:
                    time += minMap[i][j]
        time //= 2

    print(time)

main()