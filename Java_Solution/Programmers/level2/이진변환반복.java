public class 이진변환반복 {

    static class Solution {
        static int[] answer;
        static String binaryString;
        
        public static void changeBinary() {
            int zeroCnt = 0;
            int lengthAfterRemoveZero = 0;
            for(int i=0; i<binaryString.length(); i++) {
                if(binaryString.charAt(i) == '0') {
                    zeroCnt += 1;
                }
            }
            
            answer[0] += 1;
            answer[1] += zeroCnt;
            lengthAfterRemoveZero = binaryString.length() - zeroCnt;
            binaryString = Integer.toBinaryString(lengthAfterRemoveZero);
        }
        
        public static int[] solution(String s) {
            answer = new int[2];
            binaryString = s;
            
            while(!binaryString.equals("1")) {
                changeBinary();
            }
            
            return answer;
        }
        
        public static void main(String[] args) {
            solution("110010101001");
        
        }
    }
}