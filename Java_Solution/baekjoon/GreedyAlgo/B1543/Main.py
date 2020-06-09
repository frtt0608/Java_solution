import sys
sys.stdin = open("input.txt")

docu = input()
target = input()

cnt = 0
idx = 0
while(1):
    if idx+len(target) > len(docu):
        break

    if docu[idx:idx+len(target)] == target:
        cnt += 1
        idx += len(target)
    else:
        idx += 1

    if(idx > len(docu)):
        break
print(cnt)