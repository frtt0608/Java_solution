// 기능개발
// [93,30,55], [1,30,5]	
// [2,1]

import java.util.*;

class Solution {
    public class Work {
        int progress;
        int speed;
        
        Work(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }
        
        public void working() {
            this.progress += this.speed;
        }
    }
    
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        LinkedList<Work> qu = new LinkedList<>();
        int work_length = progresses.length;
        int time = 0;
        int cnt=0;
        
        for(int i=0; i<work_length; i++) {
            qu.add(new Work(progresses[i], speeds[i]));
        }
        
        while(!qu.isEmpty()) {
            cnt=0;
            time++;
            for(Work work:qu) {
                if(work.progress < 100) 
                    work.working();
            }
            for(Work work:qu) {
                if(work.progress >= 100) {
                    cnt++;
                } else {
                    break;
                }
            }
            if(cnt>0) {
                answer.add(new Integer(cnt));
                for(int i=0; i<cnt; i++) {
                    qu.poll();
                }
            }
        }
        
        int[] result = new int[answer.size()];
        int idx = 0;
        for(Integer res:answer) {
            result[idx] = res.intValue();
            idx++;
        }
        return result;
    }
}