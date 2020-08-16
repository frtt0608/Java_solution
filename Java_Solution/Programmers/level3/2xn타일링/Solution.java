import java.util.*;

class Solution {
    int num = 1000000007;
    int answer;
    int cnt;
    int[] fiboArr;
    
//     public void comp(int two) {
//         long a = 1;
//         long b = 1;
        
//         for(int i=1; i<=two; i++) {
//             b *= i;
//         }
        
//         while(two > 0) {
//             a *= cnt;
//             cnt -= 1;
//             two -= 1;
//         }
        
//         answer += a/b;
//         answer %= num;
//     }
// 조합을 순열로 변형해서 풀이했지만 시간초과 + 타입에 따른 오버플로
    
    public int fibo(int n) {
        if(fiboArr[n] != 0) {
            return fiboArr[n];
        } else {
            fiboArr[n] = (fibo(n-1) + fibo(n-2))%num;
        }
        
        return fiboArr[n];
    }
    
    public int solution(int n) {
        fiboArr = new int[n+1];
        fiboArr[1] = 1;
        fiboArr[2] = 2;
        fiboArr[3] = 3;
        fiboArr[4] = 5;
        
        return fibo(n);
        
//         if(n > 1) {
//             int two = 0;
//             cnt = 0;
            
//             while(n>0) {
//                 n -= 2;
                
//                 if(n < 0) break;
                
//                 if(n == 0) {
//                     answer += 1;
//                     break;
//                 }
                
//                 two += 1;
//                 cnt = n + two;
                
//                 // comp(two);
                
//                 // System.out.println(two + ", " + answer);
//             }
//         }
//         조합을 순열로 변형해서 풀이 / 시간초과
    }
}