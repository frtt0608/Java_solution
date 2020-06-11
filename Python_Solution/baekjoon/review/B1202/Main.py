import sys
import heapq as hq
from collections import deque as dq

sys.stdin = open("input.txt")
input = sys.stdin.readline
N, K = map(int, input().split())
jewels = []
bags = []

for i in range(N):
    M, V = map(int, input().split())
    jewels.append([M,V])

for i in range(K):
    bags.append(int(input()))

def calculate_price():
    possibleGems = []
    maxPrice = 0

    for bag in bags:
        while jewels and bag >= jewels[0][0]:
            hq.heappush(possibleGems, -jewels.popleft()[1])

        if possibleGems:
            maxPrice -= hq.heappop(possibleGems)
        elif not jewels:
            break
        
    return maxPrice

def main():
    global bags, jewels

    bags.sort()
    jewels = dq(sorted(jewels))
    
    maxPrice = calculate_price()
    print(maxPrice)

main()