// 디스크 컨트롤러
// [[0, 3], [1, 9], [2, 6]]
// 9

import java.util.*;

class Solution {
    public class JOB implements Comparable<JOB> {
        int startTime;
        int requestTime;
        
        JOB(int startTime, int requestTime) {
            this.startTime = startTime;
            this.requestTime = requestTime;
        }
        
        @Override
        public int compareTo(JOB target) {
            return (this.requestTime > target.requestTime) ? 1:-1;
        }
    }
    public int solution(int[][] jobs) {
        int answer = 0;
        int time = 0;
        int idx = 1;
        PriorityQueue<JOB> DISK = new PriorityQueue<>();
        List<JOB> work = new LinkedList<>();
        
        for(int i=0; i<jobs.length; i++) {
            DISK.add(new JOB(jobs[i][0], jobs[i][1]));
        }
        for(int i=0; i<jobs.length; i++) {
            work.add(DISK.poll());
        }
        
        while(work.size() > 0) {
            for(int i=0; i<work.size(); i++) {
                if(work.get(i).startTime <= time) {
                    time += work.get(i).requestTime;
                    answer += time - work.get(i).startTime;
                    work.remove(i);
                    break;
                }
                if(i==work.size()-1) time++;
            }
        }
        System.out.println(answer);
        answer /= jobs.length;
        Math.floor(answer);
        return answer;
    }
}