import java.util.*;

public class 보행자천국 {

    static class Solution {
        static final int MOD = 20170805;
        static int M, N, totalRoute;
        
        public static void searchTotalRoute(int[][] cityMap) {
            int[][] rightMove = new int[M+1][N+1];
            int[][] downMove = new int[M+1][N+1];
            rightMove[1][1] = 1;
            downMove[1][1] = 1;
            
            for(int i=1; i<M+1; i++) {
                for(int j=1; j<N+1; j++) {
                    if(cityMap[i-1][j-1] == 1) continue;
                    
                    if(cityMap[i-1][j-1] == 0) {
                        rightMove[i][j] += (rightMove[i][j-1] + downMove[i-1][j])%MOD;
                        downMove[i][j] += (rightMove[i][j-1] + downMove[i-1][j])%MOD;
                    } else {
                        rightMove[i][j] += (rightMove[i][j-1])%MOD;
                        downMove[i][j] += (downMove[i-1][j])%MOD;
                    }
                }
            }
            
            totalRoute = (rightMove[M][N-1] + downMove[M-1][N])%MOD;
        }
        
        public static int solution(int m, int n, int[][] cityMap) {
            totalRoute = 0;
            M = m;
            N = n;
            searchTotalRoute(cityMap);
            
            return totalRoute;
        }
        
        public static void main(String[] args) {
            int[][] cityMap = {};
            solution(3, 3, cityMap);
        }
    }
}
