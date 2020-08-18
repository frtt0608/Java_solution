import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        LinkedList<Integer> numList = new LinkedList<>();
        
        for(int num: arr) {
            if(num % divisor == 0) {
                numList.add(num);
            }
        }
        
        if(numList.size() == 0) {
            int[] answer = {-1};
            return answer;
        } else {
            int[] answer = new int[numList.size()];
            int idx = 0;
            while(!numList.isEmpty()) {
                answer[idx] = numList.poll();
                idx++;
            }
            Arrays.sort(answer);
            System.out.println(Arrays.toString(answer));
            return answer;
        }
    }
}