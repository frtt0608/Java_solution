# 택배

import sys
from collections import deque
sys.stdin = open("input.txt")

N, C = map(int, input().split())

M = int(input())

boxes = [[] for _ in range(N+1)]
for i in range(M):
    inputBox = list(map(int, input().split()))
    boxes[inputBox[0]].append([inputBox[1], inputBox[2]])


for i in range(1, N+1):
    boxes[i].sort(key = lambda x:(x[0], x[1]))
    
deliveryCnt = 0 # 이동시킨 박스
truckWeight = 0 # 현재 트럭 무게
truck = [0 for _ in range(N+1)] # 트럭에 실은 박스 중 도착지에 내릴 갯수

for cityNum in range(1, N+1):
    deliveryCnt += truck[cityNum]
    truckWeight -= truck[cityNum]
    truck[cityNum] = 0

    for box in boxes[cityNum]:
        if truckWeight + box[1] <= C:
            truckWeight += box[1]
            truck[box[0]] += box[1]
        else:
            cannotLoad = box[1] - (C - truckWeight)

            for i in range(N, box[0], -1):
                if truck[i] >= cannotLoad:
                    truck[i] -= cannotLoad
                    cannotLoad = 0
                    break
                else:
                    cannotLoad -= truck[i]
                    truck[i] = 0

            truckWeight = C
            truck[box[0]] += box[1] - cannotLoad
    
print(deliveryCnt)