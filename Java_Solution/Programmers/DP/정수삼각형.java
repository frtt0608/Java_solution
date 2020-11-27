class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int[][] dpArr = new int[triangle.length][triangle.length];
        dpArr[0][0] = triangle[0][0];
        
        for(int i=1; i<triangle.length; i++) {
            for(int j=0; j<triangle[i].length; j++)
                if(j == 0) {
                    dpArr[i][j] = dpArr[i-1][j] + triangle[i][j];
                } else if(j > 0 && j < triangle[i].length-1) {
                    dpArr[i][j] = Math.max(dpArr[i-1][j-1] + triangle[i][j], dpArr[i-1][j] + triangle[i][j]);
                } else {
                    dpArr[i][j] = dpArr[i-1][j-1] + triangle[i][j];
                }
        }
        
        for(int dp: dpArr[triangle.length-1])
            answer = Math.max(answer, dp);
        
        return answer;
    }
}