class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        
        while(n>0) {
            int remain = n%3;
            n /= 3;
            
            if(remain == 0) {
                remain = 4;
                n -= 1;
            }
            
            sb.append(remain);
        }
        
        return sb.reverse().toString();
    }
}