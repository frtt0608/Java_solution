import java.util.*;

public class 보석쇼핑 {

    class Solution {
        Set<String> set;
        Map<String, Integer> gemMap;
        int start, minRange;
        
        public void slidingWindow(String[] gems) {
            Queue<String> gemQue = new LinkedList<>();
            int tempStart = 0;
                
            for(int i=0; i<gems.length; i++) {
                
                if(gemMap.containsKey(gems[i])) {
                    gemMap.put(gems[i], gemMap.get(gems[i]) + 1);
                } else {
                    gemMap.put(gems[i], 1);
                }
                
                gemQue.offer(gems[i]);
                
                while(true) {
                    String gem = gemQue.peek();
                    
                    if(gemMap.get(gem) > 1) {
                        gemMap.put(gem, gemMap.get(gem) - 1);
                        gemQue.poll();
                        tempStart += 1;
                    } else {
                        break;
                    }
                }
                
                if(gemMap.size() == set.size() && minRange > gemQue.size()) {
                    minRange = gemQue.size();
                    start = tempStart;
                }
            }
        }
        
        public int[] solution(String[] gems) {
            set = new HashSet<>();
            gemMap = new HashMap<>();
            
            for(String gem: gems) {
                set.add(gem);
            }
            
            start = 0;
            minRange = Integer.MAX_VALUE;
            
            slidingWindow(gems);
            int[] answer = new int[] {start+1, start + minRange};
                
            return answer;
        }
    }
}
