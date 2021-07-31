public class 행렬의곱셈 {
    class Solution {
    
        public int getMutiplyArray(int[][] arr1, int[][] arr2,
                                   int i, int j) {
            int x = 0;
            int y = 0;
            int sum = 0;
            
            while(x<arr1[i].length && y<arr2.length) {
                sum += (arr1[i][y] * arr2[x][j]);
                x += 1;
                y += 1;
            }
            
            return sum;
        }
        
        public int[][] solution(int[][] arr1, int[][] arr2) {
            int[][] answer = new int[arr1.length][arr2[0].length];
            
            for(int i=0; i<arr1.length; i++) {
                for(int j=0; j<arr2[0].length; j++) {
                    answer[i][j] = getMutiplyArray(arr1, arr2, i, j);
                }
            }
            
            return answer;
        }
    }
}
