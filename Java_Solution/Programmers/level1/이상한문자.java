class 이상한문자 {
    public String solution(String s) {        
        StringBuilder sb = new StringBuilder();
        
        int totalIdx = 0;
        int idx = 0;
        
        while(totalIdx < s.length()) {
            char chr = s.charAt(totalIdx);
            
            if(chr == ' ') {
                sb.append(' ');
                idx = 0;
            } else {
                chr = idx%2 == 0 ? Character.toUpperCase(chr):Character.toLowerCase(chr);
                sb.append(chr);
                
                idx += 1;
            }
            
            totalIdx += 1;
        }
        

        return sb.toString();
    }
}