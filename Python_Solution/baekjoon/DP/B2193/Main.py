import sys
sys.stdin = open("input.txt")

N = int(input())
numDP = [-1] * 91
numDP[0] = 0
numDP[1] = 1
numDP[2] = 1

for i in range(3, N+1):
    numDP[i] = numDP[i-1] + numDP[i-2]

print(numDP[N])