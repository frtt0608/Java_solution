
public class 순위 {

    class Solution {
        int answer;
        int[][] plays;
        
        public void getRanking1(int n) {
            
            for(int k=1; k<n+1; k++) {
                for(int i=1; i<n+1; i++) {
                    for(int j=1; j<n+1; j++) {
                        if(k==i || i==j) continue;
                        
                        if(plays[i][k] == 1 && plays[k][j] == 1) {
                            plays[i][j] = 1;
                            plays[j][i] = -1;
                        } else if(plays[i][j] == -1 && plays[k][j] == -1) {
                            plays[i][j] = -1;
                            plays[j][i] = 1;
                        }
                    }
                }
            }
        }
        
        public void findExactRanking(int n) {
            boolean flag;        
            
            for(int i=1; i<n+1; i++) {
                flag = true;
                for(int j=1; j<n+1; j++) {
                    if(i == j) continue;
                    
                    if(plays[i][j] == 0) {
                        flag = false;
                        break;
                    }
                }
                
                if(flag) {
                    answer += 1;
                }
            }
        }
        
        public int solution(int n, int[][] results) {
            plays = new int[n+1][n+1];
            
            for(int i=0; i<results.length; i++) {
                plays[results[i][0]][results[i][1]] = 1;
                plays[results[i][1]][results[i][0]] = -1;
            }
            
            getRanking1(n);
            findExactRanking(n);
            
            return answer;
        }
    }
}
