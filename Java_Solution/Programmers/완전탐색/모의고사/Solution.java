import java.util.Arrays;

class Solution {
    public int search_max(int[] array) {
        int max = 0;
        for(int i=1; i<4; i++) {
            if(max < array[i]) {
                max = array[i];
            }
        }    
        return max;
    }
    
    public int[] solution(int[] answers) {
        int[] answer = {};
        int sol1[] = {1, 2, 3, 4, 5};
        int sol2[] = {2, 1, 2, 3, 2, 4, 2, 5};
        int sol3[] = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int res[] = new int[4];
        
        for(int i=0; i<answers.length; i++) {
            if(sol1[i%5] == answers[i]) {
                res[1]++;
            }
            if(sol2[i%8] == answers[i]) {
                res[2]++;
            }
            if(sol3[i%10] == answers[i]) {
                res[3]++;
            }
        }
        
        System.out.println(Arrays.toString(res));
        int cnt=0;
        Boolean zero[] = new Boolean[4];
        
        int max = search_max(res);
        if(max==0) {
            return answer;
        }
        
        for(int i=1; i<4; i++) {
            zero[i] = false;
            if(max == res[i]) {
                cnt++;
                zero[i] = true;
            }
        }
        
        answer = new int[cnt];
        
        if(cnt==1) {
            for(int i=1; i<4; i++) {
                if(zero[i]) answer[0] = i;
            }
        } else if(cnt==2){
            for(int i=1; i<4; i++) {
                if(zero[i] == false) continue;
                if(max == res[i]) {
                    if(answer[0]==0) {
                        answer[0] = i;    
                    } else {
                        answer[1] = i;
                    }
                }
            }
        } else {
            answer[0] = 1;
            answer[1] = 2;
            answer[2] = 3;
        }
        System.out.println(Arrays.toString(answer));
        
        return answer;
    }
}