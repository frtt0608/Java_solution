import sys
sys.stdin = open("input.txt")

def solution():
    n = int(input())
    triangle =[]
    for _ in range(n):
        triangle.append(list(map(int, sys.stdin.readline().rstrip().split())))
                   
    accum = []
    for i in range(n):
        accum = [max(a+c, b+c) for a,b,c in zip([0]+accum, accum+[0], triangle[i])]

solution()