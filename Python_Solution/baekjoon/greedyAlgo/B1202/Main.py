# 보석도둑

import sys
from collections import deque
import heapq as hq
jewels = []
bags = []

def find_maxPrice():
    possibleGems = []
    totalPrice = 0

    for bag in bags:
        while jewels and bag >= jewels[0][0]:
            hq.heappush(possibleGems, -jewels.popleft()[1])

        if possibleGems:
            totalPrice -= hq.heappop(possibleGems)
        elif not jewels:
            break

    print(totalPrice)

def main():
    sys.stdin = open("input.txt")
    input = sys.stdin.readline
    N, K = map(int, input().split())
    global jewels, bags

    for i in range(N):
        M, V = map(int, input().split())
        jewels.append((M, V))
    jewels = deque(sorted(jewels))

    for i in range(K):
        bags.append(int(input()))
    bags.sort()

    find_maxPrice()
    
main()
