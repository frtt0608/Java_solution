# 뒤집기

import sys

sys.stdin = open("input.txt")

S = input()
flag = False
zeroCnt = 0
oneCnt = 0

for s in S:
    if s == "0":
        flag = True
    elif flag and s == "1":
        flag = False
        zeroCnt += 1
if flag:
    zeroCnt += 1

flag = False
for s in S:
    if s == "1":
        flag = True
    elif flag and s == "0":
        flag = False
        oneCnt += 1
if flag:
    oneCnt += 1

print(oneCnt if zeroCnt > oneCnt else zeroCnt)
# print(zeroCnt)
# print(oneCnt)