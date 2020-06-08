import sys
sys.stdin = open("input.txt")

data = list(map(int, input().split()))
N, K = data[0], data[1]
plugList = list(map(int, input().split()))

visited = [0 for i in range(0, K+2)]
plug = [0 for i in range(0, N)]

resCnt = 0

for i in range(0, K):
    visited[plugList[i]] += 1

for i in range(0, K):
    flag = False

    for j in range(0, N):
        if plug[j]==0 or plug[j] == plugList[i]:
            plug[j] = plugList[i]
            visited[plug[j]] -= 1
            flag = True
            break
    
    if flag == False:
        plugCnt = 0
        plugNum = 0
        for j in range(0, N):
            if visited[plug[j]]==0:
                plugNum = j
                flag = True
                break
        
        if flag == False:
            for j in range(0, N):
                cnt = 0
                for k in range(i, K):
                    if plug[j] == plugList[k]:
                        if plugCnt < cnt:
                            plugCnt = cnt
                            plugNum = j
                        break
                    else:
                        cnt += 1

        plug[plugNum] = plugList[i]
        visited[plug[plugNum]] -= 1
        resCnt += 1

    print(plug)
    # print(visited)
print(resCnt)