public class 쿼드트리 {

    static class Solution {
        
        static int[] answer;
        
        public static boolean isCompress(int[][] arr, int row, int col, int size) {
            int value = arr[row][col];
            
            for(int i=row; i<row+size; i++) {
                for(int j=col; j<col+size; j++) {
                    if(value != arr[i][j]) return false;
                }
            }
            return true;
        }
        
        public static void quardTree(int[][] arr, int row, int col, int size) {
            if(isCompress(arr, row, col, size)) {
                answer[arr[row][col]] += 1;
                return;
            } else {
                int halfSize = size/2;
                
                quardTree(arr, row, col, halfSize);
                quardTree(arr, row + halfSize, col, halfSize);
                quardTree(arr, row, col + halfSize, halfSize);
                quardTree(arr, row + halfSize, col + halfSize, halfSize);
            }
        }
        
        public static int[] solution(int[][] arr) {
            answer = new int[2];
            quardTree(arr, 0, 0, arr.length);
            
            return answer;
        }
        
        public static void main(String[] args) {
            int[][] arr = {};
            
            solution(arr);
        }
    }
}
