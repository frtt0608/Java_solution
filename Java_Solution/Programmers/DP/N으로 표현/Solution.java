import java.util.*;

class Solution {
    private int N, number, answer;
    
    public void DFS(int cnt, int result, int continue_N) {
        if(cnt > 8) return; 
        if(cnt > answer) return;
        if(result == number) {
            answer = Math.min(answer, cnt);
            return;
        }
        continue_N = N;
        
        for(int i=0; i<8-cnt; i++) {
            DFS(cnt+i+1, result - continue_N, continue_N);
            
            
            DFS(cnt+i+1, result + continue_N, continue_N);
            DFS(cnt+i+1, result * continue_N, continue_N);
            DFS(cnt+i+1, result/continue_N, continue_N);
            
            continue_N = continue_N*10 + N;
        }
    }
    
    public int solution(int N, int number) {
        answer = 9999999;
        this.N = N; this.number = number;
        
        if(number == 1) return 2;
        else DFS(0, 0, N);
        
        if(answer > 8) answer = -1;
        return answer;
    }
}