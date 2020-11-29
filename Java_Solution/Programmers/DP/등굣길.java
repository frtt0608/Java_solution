public class 등굣길 {
    class Solution {
        boolean[][] puddleMap;
        int[][] routeDP;
        final int MOD = 1000000007;
            
        public void calculateMinRoute(int m, int n, int[][] puddles) {
            routeDP[1][1] = 1;
            
            for(int i=1; i<n+1; i++) {
                for(int j=1; j<m+1; j++) {
                    
                    if(!puddleMap[i][j]) {
                        routeDP[i][j] = (routeDP[i][j] + routeDP[i-1][j]  + routeDP[i][j-1])%MOD;
                    }
                }
            }    
        }
        
        public int solution(int m, int n, int[][] puddles) {
            puddleMap = new boolean[n+1][m+1];
            routeDP = new int[n+1][m+1];
            
            for(int[] puddle: puddles) 
                puddleMap[puddle[1]][puddle[0]] = true;
            
            calculateMinRoute(m, n, puddles);
            
            return routeDP[n][m];
        }
    }
}
