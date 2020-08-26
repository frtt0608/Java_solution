import java.util.*;

class 실패율 {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] fail = new int[N+1];
        List<Stage> stageList = new ArrayList<>();
            
        for(int stage: stages) {
            if(stage <= N)
                fail[stage] += 1;
        }
        
        int p = stages.length;
        for(int i=1; i<=N; i++) {
            if(p != 0) {
                stageList.add(new Stage(i, (double) fail[i] / p));
                p -= fail[i];
            } else {
                stageList.add(new Stage(i, 0.0));
            }
        }
        
        Collections.sort(stageList);
        
        for(int i=0; i<N; i++) {
            answer[i] = stageList.get(i).idx;
        }
        
        // System.out.println(Arrays.toString(fail));
        // System.out.println(Arrays.toString(answer));
        
        return answer;
    }
    
    public class Stage implements Comparable<Stage> {
        int idx;
        double percent;
        
        Stage(int idx, double percent) {
            this.idx = idx;
            this.percent = percent;
        }
        
        @Override
        public int compareTo(Stage s) {
            if(this.percent > s.percent) 
                return -1;
            else if(this.percent < s.percent)
                return 1;
            else
                return this.idx - s.idx;
        }
    }
}