# 가장 긴 감소하는 부분수열
# 이진탐색을 활용한 LIS구현 O(NlogN)

import sys
sys.stdin = open("input.txt")

N = int(input())
numArr = list(map(int, input().split()))
numDP = [numArr[0]]

def binarySearch(maxNum, num):
    minNum = 0

    while (maxNum >= minNum):
        mid = (maxNum + minNum) // 2

        if numDP[mid] > num:
            minNum = mid+1
        elif numDP[mid] < num:
            maxNum = mid-1
        else:
            return mid
    
    return minNum

for num in numArr:
    if numDP[-1] > num:
        numDP.append(num)
    else:
        idx = binarySearch(len(numDP)-1, num)
        numDP[idx] = num        

print(len(numDP))