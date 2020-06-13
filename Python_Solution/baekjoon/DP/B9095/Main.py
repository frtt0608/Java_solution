# 1, 2, 3 더하기

# 1: 1 / 1개
# 2: 1+1, 2 / 2개
# 3: 1+1+1, 2+1, 1+2, 3 / 4개
# 4: 1+1+1+1, 1+1+2, 1+2+1, 2+1+1, 3+1, 1+3, 2+2 / 7개
# 5: 11111 2111 1211 1121 1112 311 131 113 221 212 122 23 32 / 13개
# 7: 44개
# 10: 274개


import sys

sys.stdin = open("input.txt")

T = int(input())
sumArr = [0, 1, 2, 4]


for i in range(4, 12):
    num = sumArr[i-1]+sumArr[i-2]+sumArr[i-3]
    sumArr.append(num)

for tc in range(T):
    N = int(input())

    print(sumArr[N])