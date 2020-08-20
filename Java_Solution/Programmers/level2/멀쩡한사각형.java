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
            int num = gcd(w, h);
            answer = totalSquare - (w+h-num);
        }
        // System.out.println(totalSquare);
        // System.out.println(answer);
        return answer;
    }
}


// w와 h의 최대공약수로 각각을 나누면 w', h'이 된다.
// 서로소인 w'과 h'으로 대각선을 그으면 w'+h'-1만큼 사용가능.
// 여기서 다시 최대공약수를 곱해주면 w+h-gcd(w,h)가 된다.