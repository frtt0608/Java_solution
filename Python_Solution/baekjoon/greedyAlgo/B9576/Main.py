import sys

sys.stdin = open("input.txt")
input = sys.stdin.readline

T = int(input())
signup = []

def giveBook(idx, visited):
    global signup

    if visited[idx]: 
        return 0
    visited[idx] = 1
    
    for i in range(signup[idx-1][0], signup[idx-1][1]+1):
        if signMatch[i]==0 or giveBook(signMatch[i], visited):
            signMatch[i] = idx
            return 1

    return 0

for tc in range(T):
    N, M = map(int, input().split())
    signup = []
    bookCnt = 0

    for _ in range(M):
        data = list(map(int, input().split()))
        signup.append(data)
    signup = sorted(signup, key = lambda x:(x[1], -x[0]), reverse = True)

    signMatch = [0] * (N+1)

    for idx in range(1, M+1):
        visited = [0] * (M+1)
        if giveBook(idx, visited):
            bookCnt += 1

    print(bookCnt)