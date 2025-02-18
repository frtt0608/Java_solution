class 두정수사이의합 {
    
    public long solution(int a, int b) {
        long answer = 0;
        long maxNum = 0;
        long minNum = 0;
        // long a = inta;
        // long b = intb;
        
        if(a == b) answer = a;
        else {
            maxNum = (long)Math.max(a, b);
            minNum = (long)Math.min(a,b);
            
            if(maxNum >= 0 && minNum >= 0) {
                answer = maxNum*(maxNum+1)/2 - minNum*(minNum-1)/2;
            } else if(maxNum > 0 || minNum < 0) {
                answer = maxNum*(maxNum+1)/2 + (-1)*minNum*(minNum-1)/2;
            } else if(maxNum < 0 && minNum < 0) {
                answer = (-1)*minNum*(minNum-1)/2 + (-1)*(maxNum+1)*(maxNum)/2;
            }
        }
        
        return answer;
    }
}

// 등차수열 공식
// class Solution {
    
//     public long calc(long max, long min) {
//         return (max-min+1)*(max+min)/2;
//     }
    
//     public long solution(int a, int b) {
//         long answer = 0;
        
//         if(a == b) answer = a;
//         else answer = calc(Math.max(a, b), Math.min(a,b));
        
//         return answer;
//     }
// }