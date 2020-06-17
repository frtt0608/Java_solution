# 가장 긴 바이토닉 부분 수열
# 이진탐색으로 접근

import sys
sys.stdin = open("input.txt")

N = int(input())

def binarySearch(num, arrDP):
    minNum = 0
    maxNum = len(arrDP)-1

    while(maxNum >= minNum):
        mid = (maxNum + minNum) // 2

        if arrDP[mid] < num:
            minNum = mid + 1
        elif arrDP[mid] > num:
            maxNum = mid - 1
        else:
            return mid
    
    return minNum

numArr = list(map(int, input().split()))
cntDP = [1] * N
numDP = [numArr[0]]

# 순방향 LIS찾기
for i in range(1, N):
    num = numArr[i]
    if numDP[-1] < num:
        numDP.append(num)
        cntDP[i] = len(numDP)
    else:
        idx = binarySearch(num, numDP)
        numDP[idx] = num
        cntDP[i] = idx+1


# 역방향 LIS찾기
numArr.reverse()
rev_numDP = [numArr[0]]

for i in range(1, N):
    num = numArr[i]
    if rev_numDP[-1] < num:
        rev_numDP.append(num)
        cntDP[N-i-1] += len(rev_numDP)-1
    else:
        idx = binarySearch(num, rev_numDP)
        rev_numDP[idx] = num
        cntDP[N-i-1] += idx

print(max(cntDP))