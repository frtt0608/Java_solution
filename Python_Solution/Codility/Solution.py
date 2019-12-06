import sys
sys.stdin = open("input.txt")

def checkBinary(N):
    global max_cnt
    
    while N>1:
        N, rest = divmod(N,2)
        if rest==1:
            cnt=0


T=int(input())
for tc in range(1, T+1):
    N=int(input())
    cnt=0
    max_cnt=0
