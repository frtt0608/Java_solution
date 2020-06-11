import sys

sys.stdin = open("input.txt")

input = sys.stdin.readline

N = int(input())
minMap = []
dist = [[1] * N for _ in range(N)]

def calculate_time():
    totalTime = 0

    for i in range(N):
        for j in range(N):
            if dist[i][j]:
                totalTime += minMap[i][j]

    return totalTime//2

def check_minBridge():
    for i in range(N):
        for j in range(N):

            if i==j: continue
            for target in range(N):

                if i==target or j==target: continue

                street = minMap[i][target]
                passBystreet = minMap[i][j] + minMap[j][target]

                if street == passBystreet:
                    dist[i][target] = 0
                elif street > passBystreet:
                    return -1

    return calculate_time()

def main():
    global N, minMap, dist

    for __ in range(N):
        data = list(map(int, input().split()))
        minMap.append(data)

    totalTime = check_minBridge()
    print(totalTime)
    
main()