public class 예상대진표 {
    static class Solution
    {
        public static int solution(int n, int a, int b)
        {
            int numberOfRound = 0;
            
            while(a != b) {
                a = a/2 + a%2;
                b = b/2 + b%2;
                
                numberOfRound += 1;
            }
            
            return numberOfRound;
        }
        
        public static void main(String[] args) {
            solution(8, 4, 7);
        }
    }
}
