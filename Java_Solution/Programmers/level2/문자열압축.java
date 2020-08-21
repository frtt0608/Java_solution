class 문자열압축 {
    int len;
    int answer;
    String s;
    
    public String convertInt(int cnt) {
        return cnt > 1 ? Integer.toString(cnt) : "";
    }
    
    public int zip(int term) {
        StringBuilder sb = new StringBuilder();
        
        String now = s.substring(0, term);
        String next = "";
        int cnt = 1;
        int idx = term;
        
        while(true) {
            next = idx + term <= len ? s.substring(idx, idx+term) : s.substring(idx, len);
            
            if(now.equals(next)) {
                cnt += 1;
            } else {
                sb.append(convertInt(cnt)).append(now);
                now = next;
                
                cnt = 1;
            }
            
            idx += term;
            
            if(idx >= len) break;
        }
        
        sb.append(convertInt(cnt)).append(next);
        
        // System.out.println(sb.toString());
        
        return sb.toString().length();
    }
    
    public int solution(String s) {
        answer = Integer.MAX_VALUE;
        this.s = s;
        len = s.length();
        
        for(int i=1; i<=len/2+1; i++) {
            int res = zip(i);
            
            if(res == 0) continue;
            else answer = Math.min(answer, res);
        }
        
        System.out.println(answer);
        return answer;
    }
}