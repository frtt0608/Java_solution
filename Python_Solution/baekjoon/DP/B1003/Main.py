import sys

sys.stdin = open("input.txt")
T = int(input())
zeroDict = {0:1, 1:0, 2:1, 3:1}
oneDict = {0:0, 1:1, 2:1, 3:2}

def fiboZero(num):
    global zeroDict

    if num in zeroDict.keys():
        return zeroDict[num]
    else:
        zero = fiboZero(num-1) + fiboZero(num-2)
        zeroDict[num] = zero
        return zeroDict[num]

def fiboOne(num):
    global oneDict

    if num in oneDict:
        return oneDict[num]
    else:
        one = fiboOne(num-1) + fiboOne(num-2)
        oneDict[num] = one
        return oneDict[num]

fiboZero(40)
fiboOne(40)

for _ in range(T):
    N = int(input())
    print(zeroDict[N], oneDict[N])
