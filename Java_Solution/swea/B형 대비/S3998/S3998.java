// Inversion Counting
// 1. Divide & Conquer
//    i<j이고, A[i] > A[j] 인 (i,j)의 개수
//    병합정렬

// 2. Indexed Tree
//    수열 A, set(x, v) : A[x]의 값을 v로 수정
//            get(l, r) : get[l] ~ get[r]까지 함수수행
//            tree에 root부터 index부여
//            x/2 - x - (2x, 2x+1)
//            구하고자 하는 tree의 root를 찾는다.
//            인덱스가 짝수인지 홀수인지에 따라 root를 찾는다.
//            
//            v[N] 방문체크
//            구간 합!(x보다 큰 값이 있는지 v배열로 확인)
import java.io.*;
import java.util.*;