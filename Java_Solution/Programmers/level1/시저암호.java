class 시저암호 {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<s.length(); i++) {
            char chr = s.charAt(i);
            
            if(chr == ' ') {
                sb.append(" ");
                continue;
            }
            
            char aA = Character.isLowerCase(chr) ? 'a':'A';
            sb.append((char)((chr - aA + n)%26  + aA));
            
        }
        
        return sb.toString();
    }
}