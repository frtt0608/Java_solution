# 택배

import sys
from collections import deque
sys.stdin = open("input.txt")

N, C = map(int, input().split())

M = int(input())

boxes = [list(map(int, input().split())) for _ in range(M)]
boxes.sort(key = lambda x:(x[1]))
truck = {}

deliveryCnt = 0
truckWeight = 0

for cityNum in range(1, N+1):

    print(boxes)
    print(truck)
    
    # 박스 내리기
    if truck:
        if cityNum in truck.keys():
            deliveryCnt += truck[cityNum]
            truckWeight -= truck[cityNum]
            truck[cityNum] = 0

    # 박스 싣기
    for box in boxes:
        inBox = box

        if inBox[0] == cityNum:
            
            if truckWeight == C:
                break

            if truckWeight + inBox[2] > C:
                if inBox[1] in truck.keys():
                    truck[inBox[1]] += C - truckWeight
                else:
                    truck[inBox[1]] = C - truckWeight
                truckWeight = C
            else:
                if inBox[1] in truck.keys():
                    truck[inBox[1]] += inBox[2]
                else:
                    truck[inBox[1]] = inBox[2]
                truckWeight += inBox[2]  
        elif inBox[0] < cityNum:
            continue
        else:
            break

print(deliveryCnt)