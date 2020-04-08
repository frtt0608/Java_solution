class Solution {
    int res[], cnt, max, max_idx;
    
    public void find_max() {
        for(int i=1; i<4; i++) {
            if(max <= res[i]) {
                max = res[i];
                max_idx = i;
            }
        }
    }
    
    public int[] solution(int[] answers) {
        int[] answer = {};
        int sol1[] = {1, 2, 3, 4, 5};
        int sol2[] = {2, 1, 2, 3, 2, 4, 2, 5};
        int sol3[] = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        res = new int[4];
        cnt = 0; max = 0; max_idx=0;
        
        for(int i=0; i<answers.length; i++) {
            if(sol1[i%5] == answers[i]) res[1]++;
            if(sol2[i%8] == answers[i]) res[2]++;
            if(sol3[i%10] == answers[i]) res[3]++;
        }
        
        find_max();
        if(max==0) return answer;
        
        for(int i=1; i<4; i++) {
            if(max == res[i]) {
                cnt++;
            }
        }
        
        if(cnt==1) {
            answer = new int[1];
            answer[0] = max_idx;
        } else if(cnt==2) {
            for(int i=1; i<4; i++) {
                if(max==res[i]) {
                    answer = new int[2];
                    answer[0] = i;
                    answer[1] = max_idx;
                    break;
                }
            }
        } else if(cnt==3) {
            answer = new int[3];
            answer[0] = 1;
            answer[1] = 2;
            answer[2] = 3;
        }
        
        return answer;
    }
}