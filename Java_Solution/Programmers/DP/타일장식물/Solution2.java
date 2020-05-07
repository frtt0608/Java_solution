// 자료형에 주의하자!!!!!!
// 숫자가 커질경우(재귀가 커질경우) int형이 아닌 long형

import java.util.Deque;
import java.util.LinkedList;

class Solution2 {
    
    public long solution(int N) {
        long answer = 0;
        long tile[] = new long[81];
        tile[0] = 1;
        tile[1] = 1;
        
        for(int i=2; i<N; i++) {
            tile[i] = tile[i-1] + tile[i-2];
        }
        
        answer += (tile[N-1] + tile[N-2])*2 + tile[N-1]*2;
        return answer;
    }
}