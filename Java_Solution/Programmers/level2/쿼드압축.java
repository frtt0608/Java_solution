public class 쿼드압축 {
    
    class Solution {
        int[] answer;
        
        public int checkArr(int xStart, int xEnd, int yStart, int yEnd, int[][] arr) {
            int isCompress = arr[xStart][yStart];
            
            for(int i=xStart; i<xEnd; i++) {
                for(int j=yStart; j<yEnd; j++) {
                    if(arr[i][j] != isCompress) {
                        return 2;
                    }
                }
            }
            
            return isCompress;
        }
        
        public void quardTree(int xStart, int xEnd, int yStart, int yEnd, int[][] arr) {
            if(xEnd - xStart == 1) {
                answer[arr[xStart][yStart]] += 1;
                return;
            }
            
            // Left_top
            int isCompress1 = checkArr(xStart, (xStart+xEnd)/2, yStart, (yStart+yEnd)/2, arr);
            int isCompress2 = checkArr((xStart+xEnd)/2, xEnd, yStart, (yStart+yEnd)/2, arr);
            int isCompress3 = checkArr(xStart, (xStart+xEnd)/2, (yStart+yEnd)/2, yEnd, arr);
            int isCompress4 = checkArr((xStart+xEnd)/2, xEnd, (yStart+yEnd)/2, yEnd, arr);
            
            if(isCompress1 < 2 && isCompress1 == isCompress2 && isCompress2 == isCompress3 && isCompress3 == isCompress4) {
                answer[isCompress1] += 1;
                return;
            }
            
            if(isCompress1 == 2)
                quardTree(xStart, (xStart+xEnd)/2, yStart, (yStart+yEnd)/2, arr);
            else {
                answer[isCompress1] += 1;
            }
            
            // Right_top
            if(isCompress2 == 2)
                quardTree((xStart+xEnd)/2, xEnd, yStart, (yStart+yEnd)/2, arr);
            else {
                answer[isCompress2] += 1;
            }
            
            // Left_bottom
            if(isCompress3 == 2)
                quardTree(xStart, (xStart+xEnd)/2, (yStart+yEnd)/2, yEnd, arr);
            else {
                answer[isCompress3] += 1;
            }
        
            // Right_bottom
            if(isCompress4 == 2)
                quardTree((xStart+xEnd)/2, xEnd, (yStart+yEnd)/2, yEnd, arr);
            else
                answer[isCompress4] += 1;
        }
        
        public int[] solution(int[][] arr) {
            answer = new int[2];     
            quardTree(0, arr.length, 0, arr.length, arr);
            
            return answer;
        }
        
        public void main(int[][] arr) {
            solution(arr);
        }
    }
}
