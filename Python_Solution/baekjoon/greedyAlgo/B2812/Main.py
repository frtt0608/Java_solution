import sys
from collections import deque

sys.stdin = open("input.txt")

N, K = map(int, input().split())
nums = deque(input())
numStack = []

idx = 0
while K>0:
    if not nums:
        numStack.pop()
        K -= 1
        continue

    num = nums.popleft()
    if not numStack:
        numStack.append(num)
    else:
        while K>0 and numStack and numStack[-1] < num:
            numStack.pop()
            K -= 1
        numStack.append(num)

while nums:
    num = nums.popleft()
    numStack.append(num)

print(''.join(numStack))