import java.util.*;

public class 숫자게임 {

    static class Solution {
        
        public static int solution(int[] A, int[] B) {
            int answer = 0;
            
            Arrays.sort(A);
            Arrays.sort(B);
            int A_index = A.length-1;
            int B_index = B.length-1;
            
            while(A_index >= 0 && B_index >= 0) {
                if(A[A_index] >= B[B_index]) {
                    A_index -= 1;
                } else {
                    A_index -= 1;
                    B_index -= 1;
                    answer += 1;
                }
            }
            
            return answer;
        }
        public static void main(String[] args) {
            int[] A = {};
            int[] B = {};
            solution(A, B);
        }
    }
}
