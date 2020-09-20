import java.util.*;

class 줄서는방법 {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        LinkedList<Integer> numList = new LinkedList<>();
        long fact = 1;
        for(int i=1; i<=n; i++) {
            fact *= i;
            numList.add(i);
        }
        
        k -= 1; // k는 1부터 시작하므로!!
        int i = 0;
        while(i<n) {
            fact /= (n-i);
            long idx = k/fact;
            answer[i] = numList.get((int)idx);
            numList.remove((int)idx);
            i++;
            
            k %= fact;
        }
        
        return answer;
    }
}