import java.util.*;

public class 셔틀버스 {

    class Solution {
        public String solution(int n, int t, int m, String[] timetable) {
            int[] busTime = new int[timetable.length];
            int crewCnt = busTime.length;
            
            for(int i=0; i<crewCnt; i++) {
                busTime[i] = Integer.parseInt(timetable[i].substring(0,2))*60 + Integer.parseInt(timetable[i].substring(3,5));
            }
            
            Arrays.sort(busTime);
            
            System.out.println(Arrays.toString(busTime));
            
            int curTime = 9*60;
            int idx = 0;
            
            while(0 < n--) {
                int cnt = 0;
                
                // 버스 탑승
                while(crewCnt > idx) {
                    
                    if(busTime[idx] <= curTime && cnt < m) {
                        cnt += 1;
                        idx += 1;
                    } else 
                        break;
                }
                
                // 마지막 버스
                if(n==0) {
                    // 마지막 버스에 자리가 없을 땐,
                    if(cnt == m) {
                        curTime = busTime[idx-1] - 1;
                    }
                    
                    break;
                }
                    
                curTime += t;
            }
            
            String answer = String.format("%02d", curTime/60) + ":" + String.format("%02d", curTime%60);
            
            return answer;
        }
    }
}
