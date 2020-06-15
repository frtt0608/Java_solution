import sys
sys.stdin = open("input.txt")

N = int(input())
drink = [int(input()) for _ in range(N)]
drinkDP = [0] * N

drinkDP[0] = drink[0]

if N >= 2:
    drinkDP[1] = drink[0] + drink[1]
if N >= 3:
    drinkDP[2] = max(drink[0] + drink[1], drink[0] + drink[2], drink[1] + drink[2])

for i in range(3, N):
    drinkDP[i] = max(drink[i] + drinkDP[i-2],
                    drink[i] + drink[i-1] + drinkDP[i-3], 
                    drinkDP[i-1])

print(drinkDP[-1])