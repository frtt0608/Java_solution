# 캡틴 이다솜

import sys, time
sys.stdin = open("input.txt")
start = time.time()

N = int(input())
tempN = N
tetraArr = [0]
target = 0
bomb = 0

for i in range(1, 122):
    bomb += i
    target += bomb

    if target > N:
        break

    tetraArr.append(target)

print(tetraArr)
dpMap = [i for i in range(N+1)]

for i in range(2, len(tetraArr)):
    dpMap[tetraArr[i]] = 1

    for j in range(tetraArr[i], N+1):
        
        dpMap[j] = min(dpMap[j], dpMap[j - tetraArr[i]]+1)

# print(dpMap)
print(dpMap[N])
print(time.time() - start)