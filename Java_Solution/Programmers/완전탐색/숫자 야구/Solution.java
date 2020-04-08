// baseball [[123, 1, 1], [356, 1, 0], [327, 2, 0], [489, 0, 1]]
// return 2

import java.util.HashSet;

class Solution {
    private int baseball[][];
    HashSet<Integer> right;
    
    public void perm(int n, int r, String arr[], int visited[], String num) {
        if(n==r) {
            check_baseball(num);
            return;
        }
        for(int i=0; i<arr.length; i++) {
            if(visited[i] == 1) continue;
            visited[i] = 1;
            perm(n, r+1, arr, visited, num + arr[i]);
            visited[i] = 0;            
        }
    }
    
    public void check_baseball(String num) {
        int strike;
        int ball;
        char num_chr;
        char Ans_num_chr;
        String Ans_num;
            
        for(int i=0; i<baseball.length; i++) {
            strike = 0;
            ball = 0;
            Ans_num = Integer.toString(baseball[i][0]);
            for(int idx=0; idx<3; idx++) {
                num_chr = num.charAt(idx);
                Ans_num_chr = Ans_num.charAt(idx);
                if(num_chr == Ans_num_chr) {
                    strike++;
                } else if(num.contains(Character.toString(Ans_num_chr))) {
                    ball++;
                }
            }
            if(strike == baseball[i][1] && ball == baseball[i][2]) {
                continue;
            } else 
                return;
        }
        right.add(Integer.parseInt(num));
    }
           
    public int solution(int[][] baseball) {
        int answer = 0;
        this.baseball = baseball;
        right = new HashSet<>();
        
        String arr[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int visited[] = new int[9];
        String num_str = "";
        
        perm(3, 0, arr, visited, num_str);
        
        answer = right.size();
        return answer;
    }
}