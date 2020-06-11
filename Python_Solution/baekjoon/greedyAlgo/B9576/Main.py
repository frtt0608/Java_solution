import sys

sys.stdin = open("input.txt")
N = 0
M = 0
bookCnt = 0

# def giveBook(idx, signup, visited, cnt):
#     global bookCnt

#     if idx == M:
#         bookCnt = max(bookCnt, cnt)
#         return

#     for i in range(signup[idx][0], signup[idx][1]+1):
#         if visited[i]: continue

#         visited[i] = 1
#         giveBook(idx+1, signup, visited, cnt+1)
#         visited[i] = 0

def main():
    global N, M

    T = int(input())
    for tc in range(T):
        N, M = map(int, input().split())
        signup = []
        visited = [0] * (N+1)
        bookCnt = 0

        for _ in range(M):
            data = list(map(int, input().split()))
            signup.append(data)
        signup = sorted(signup, key = lambda x:(x[1], x[0]), reverse = False)
        print(signup)
        for sign in signup:
            for i in range(sign[0], sign[1]+1):
                if not visited[i]:
                    visited[i] = 1
                    bookCnt += 1
                    break

        # giveBook(0, signup, visited, 0)

        print(bookCnt)

main()