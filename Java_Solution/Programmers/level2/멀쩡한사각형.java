class 멀쩡한사각형 {
    
    public int gcd(int a, int b) {
        if(a%b == 0) return b;
        else {
            return gcd(b, a%b);
        }
    }
    
    public long solution(int w, int h) {
        long answer = 1;
        long totalSquare = (long)w*(long)h;
        
        if(w==1 || h==1) {
            answer = 0;
        } else if(w == h) {
            answer = totalSquare - w;
        } else {
            if(w%2 == 0 && h%2 == 0) {
                int num = gcd(w, h);
                answer = totalSquare - (long)Math.pow(num, 2);
            } else if(w%2 == 0 || h%2 == 0) {
                answer = totalSquare - 2*Math.min(w, h);
            } else {
                answer = totalSquare - (w+h-1);
            }
        }
        System.out.println(totalSquare);
        System.out.println(answer);
        return answer;
    }
}