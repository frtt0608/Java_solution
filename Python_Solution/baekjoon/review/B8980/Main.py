import sys
sys.stdin = open("input.txt")

cityN, truckC = map(int, input().split())
boxCnt = int(input())

boxes = [[] for _ in range(cityN+1)]

for i in range(boxCnt):
    box = list(map(int, input().split()))
    boxes[box[0]].append([box[1], box[2]])

for box in boxes:
    box.sort(key = lambda x:(x[0], x[1]))

truck = [0 for _ in range(cityN+1)]
deliveryCnt = 0
truckLoad = 0

print(boxes)
for cityNum in range(1, cityN+1):
    deliveryCnt += truck[cityNum]
    truckLoad -= truck[cityNum]
    truck[cityNum] = 0

    for box in boxes[cityNum]:
        
        if box[1] + truckLoad <= truckC:
            truck[box[0]] += box[1]
            truckLoad += box[1]
        else:
            cannotLoad = box[1] - (truckC - truckLoad)

            for i in range(cityN, box[0], -1):
                if truck[i] >= cannotLoad:
                    truck[i] -= cannotLoad
                    cannotLoad = 0
                    break
                else:
                    cannotLoad -= truck[i]
                    truck[i] = 0

            truck[box[0]] += box[1] - cannotLoad
            truckLoad = truckC
    print(truck, deliveryCnt)

print(deliveryCnt)