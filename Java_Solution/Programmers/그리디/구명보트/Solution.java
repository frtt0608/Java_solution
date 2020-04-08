import java.util.*;

class Solution {
    Deque<Integer> weights;
    
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        weights = new LinkedList<>();
            
        for(int i=0; i<people.length; i++) {
            int weight = people[i];
            if(limit - weight < 40) answer++;
            else weights.add(weight);
        }
        
        System.out.println(weights);
        
        while(!weights.isEmpty()) {
            if(weights.size()==1) {
                answer++;
                break;
            }
            int total = weights.peek() + weights.peekLast();
            if(total <= limit) {
                while(total<=limit && weights.size()>1) {
                    weights.removeFirst();
                    total += weights.peek();
                }
            }
            weights.removeLast();
            answer++;
        }
        
        return answer;
    }
}