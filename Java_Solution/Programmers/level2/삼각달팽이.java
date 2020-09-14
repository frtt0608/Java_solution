import java.util.*;

class 삼각달팽이 {
    int max, r, c;
    int num, cnt;
    ArrayList<int[]> numList;
    
    public void pyramid(int n) {
        
        
        // 왼쪽
        while(r<n-cnt && num <= max) {
            if(numList.get(r)[c] != 0) break;
            
            numList.get(r)[c] = num++;
            r += 1;
        }
        
        // 아래쪽
        while(c<numList.get(r).length-cnt && num <= max) {
            if(numList.get(r)[c] != 0) break;
            
            numList.get(r)[c] = num;
            num += 1;
            c += 1;
        }
        
        // 왼쪽
        while(r>=cnt && num <= max) {
            if(numList.get(r)[c] != 0) break;
            
            numList.get(r)[c] = num;
            num += 1;
            r -= 1;
            c -= 1;
        }
        
        
        // System.out.println(r + ", " + c);
    }
    
    public int[] solution(int n) {
        if(n==1) 
            return new int[]{1};
        
        max = n*(n+1)/2;    
        numList = new ArrayList<>();
        
        for(int i=0; i<n; i++) {
            numList.add(new int[i+1]);
        }
        
        r = 0;
        c = 0;
        cnt = 1;
        num = 1;
        
        // pyramid(n);
        while(num <= max) {
            pyramid(n);
            r += 2;
            c += 1;
            cnt += 1;
        }

        int[] answer = new int[max];

        int idx = 0;
        for(int i=0; i<n; i++) {
            for(int ans: numList.get(i)) {
                answer[idx++] = ans;
            }
        }
        
        // System.out.println(Arrays.toString(answer));
        
        return answer;
    }
}