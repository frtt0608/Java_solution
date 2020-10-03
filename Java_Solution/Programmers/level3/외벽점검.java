import java.util.*;

public class 외벽점검 {

    class Solution {
        int totalWeak, n, answer;
        int[] dists, weaks;
        boolean flag;
        
        public void inspectWeak(int[] distPerm) {
            
            // 시작점 모든 경우
            for(int i=0; i<weaks.length; i++) {
                int inspectCnt = 0;
                int idx = i;
                
                // 배열에 저장된 dist순서대로
                for(int j=0; j<distPerm.length; j++) {
                    int term = 0;
                    int cur = weaks[idx];

                    // circle case, 완탐
                    while(term <= distPerm[j]) {
                        if(cur == weaks[idx]) {
                            idx = (idx+1)%weaks.length;
                            inspectCnt += 1;
                        }
                        
                        if(inspectCnt == totalWeak) {
                            answer = distPerm.length;
                            flag = true;
                            return;
                        }
                        
                        term++;
                        cur = (cur+1)%n;
                    }
                }
            }
        }
        
        public void makeTotalcase(int cnt, int[] distPerm, boolean[] v) {
            if(flag)
                return;
            
            if(cnt == distPerm.length) {
                inspectWeak(distPerm);
                return;
            }
            
            for(int i=0; i<dists.length; i++) {
                if(v[i]) continue;
                
                v[i] = true;
                distPerm[cnt] = dists[i];
                makeTotalcase(cnt+1, distPerm, v);
                v[i] = false;
            }
        }
        
        public int solution(int n, int[] weak, int[] dist) {
            this.n = n;
            this.dists = dist;
            this.weaks = weak;
            answer = -1;
            totalWeak = weak.length;
            flag = false;
            
            // 순열
            for(int i=1; i<=dist.length; i++) {
                if(flag)
                    break;
                
                makeTotalcase(0, new int[i], new boolean[dist.length]);            
            }

            return answer;
        }
    }
}
