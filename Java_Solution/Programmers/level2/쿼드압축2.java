public class 쿼드압축2 {

    static class Solution {
        
        static int[] answer;
        
        public static boolean checkArr(int row, int col, int arrLen, int[][] arr) {
            int isCompress = arr[row][col];
            
            for(int i=row; i<row+arrLen; i++) {
                for(int j=col; j<col+arrLen; j++) {
                    if(arr[i][j] != isCompress) 
                        return false;
                }
            }
            
            return true;
        }
        
        public static void quardTree(int row, int col, int arrLen, int[][] arr) {
            if(checkArr(row, col, arrLen, arr)) {
                answer[arr[row][col]] += 1;
                
                return;
            } else {
                quardTree(row, col, arrLen/2, arr);
                quardTree(row+arrLen/2, col, arrLen/2, arr);
                quardTree(row, col+arrLen/2, arrLen/2, arr);
                quardTree(row+arrLen/2, col+arrLen/2, arrLen/2, arr);
            }
        }
        
        public static int[] solution(int[][] arr) {
            answer = new int[2];     
            quardTree(0, 0, arr.length, arr);
            
            return answer;
        }
        
        public static void main(int[][] arr) {
            solution(arr);
        }
    }
}
