public class 진법뒤집기 {
    
    class Solution {
    
        public String makeThree(int n) {
            StringBuilder sb = new StringBuilder();
            int m = 0;
            
            while(n > 0) {
                m = n%3;
                n = n/3;
                sb.append(Integer.toString(m));
            }
            
            return sb.reverse().toString();
        }
        
        public int makeInt(String nThree) {
            int n = 1;
            int resultNumber = 0;
            
            for(int idx = 0; idx<nThree.length(); idx++) {
                char chr = nThree.charAt(idx);
                resultNumber += n*(chr - '0');
                n *= 3;
            }
            
            return resultNumber;
        }
        
        public int solution(int n) {
            int answer = 0;
            String nThree = makeThree(n);
            
            answer = makeInt(nThree);
            
            return answer;
        }
        
        public void main(int n) {
            solution(n);
        }
    }
}
