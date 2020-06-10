import sys
sys.stdin = open("input.txt")

def main():
    N, M = map(int, input().split(" "))
    data = [[0, 0, 0, 0] for _ in range(0, M)]
    dictData = {"A":0, "C":1, "G":2, "T":3}
    alpha = ["A", "C", "G", "T"]

    minCnt = 0
    minData = ""

    for _ in range(0, N):
        dna = input()
        for i,j in enumerate(dna):
            data[i][dictData[j]] += 1
    
    for dist in data:
        maxDist = max(dist)
        minData += alpha[dist.index(maxDist)]
        minCnt += N - maxDist

    print(minData)
    print(minCnt)

main()