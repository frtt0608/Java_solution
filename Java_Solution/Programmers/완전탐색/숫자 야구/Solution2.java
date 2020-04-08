class Solution {
    int visited[], baseball[][];
    int ball, strike, answer;
    String input_num, balls[] = {"1", "2", "3", "4", "5", "6", "7", "8","9"};
    
    public void baseball_game(int n, int r, String num) {
        if(n==r && num.length()==3) {
            compare_Ball(num);
            return;
        }
        
        for(int i=0; i<9; i++) {
            if(visited[i]==1) continue;
            visited[i] = 1;
            baseball_game(n, r+1, num+balls[i]);
            visited[i] = 0;
        }
    }
        
    public void compare_Ball(String num) {
    
        for(int i=0; i<baseball.length; i++) {
            input_num = Integer.toString(baseball[i][0]);
            strike = 0;
            ball = 0;
            
            for(int j=0; j<3; j++) {
                if(input_num.charAt(j) == num.charAt(j)) strike++;
                else if(num.contains(Character.toString(input_num.charAt(j)))) ball++;
            }
            if(strike == baseball[i][1] && ball == baseball[i][2]) {
                continue;
            } else return;
        }
        answer++;
    }
    
    public int solution(int[][] baseball) {
        answer = 0;
        visited = new int[10];
        this.baseball = baseball;
        input_num = "";
        
        baseball_game(3, 0, "");
        
        return answer;
    }
}