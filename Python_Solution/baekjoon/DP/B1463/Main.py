import sys

sys.stdin = open("input.txt")
N = int(input())
numArr = [i-1 for i in range(N+1)]

for i in range(1, N+1):
    if i%2 == 0:
        numArr[i] = min(numArr[i//2]+1, numArr[i])
    if i%3 == 0:
        numArr[i] = min(numArr[i//3]+1, numArr[i])
    numArr[i] = min(numArr[i-1]+1, numArr[i])

print(numArr[N])