public class 광고삽입 {

    class Solution {
        int minStartTime;
        int[] viewerCnt;
        
        public int changeStringToTime(String[] hhmmss) {
            return Integer.parseInt(hhmmss[0]) * 3600 +
                    Integer.parseInt(hhmmss[1]) * 60 +
                        Integer.parseInt(hhmmss[2]);
        }
        
        public void processingLogs(String[] logs) {
            for(int i=0; i<logs.length; i++) {
                
                String[] log = logs[i].split("-");
                int startTime = changeStringToTime( log[0].split(":") );
                int endTime = changeStringToTime( log[1].split(":") );
                for(int j=startTime; j<endTime; j++) {
                    viewerCnt[j] += 1;
                }
            }
        }
        
        public void getStartTime(int playTime, int advTime) {
            int startTime = 0;
            int endTime = advTime;
            long totalCnt = 0;
            
            for(int i=0; i<endTime; i++) {
                totalCnt += viewerCnt[i];
            }
            
            long maxTotalCnt = totalCnt;
            minStartTime = 0;
            
            while(endTime <= playTime) {
                totalCnt -= viewerCnt[startTime];
                totalCnt += viewerCnt[endTime];
                
                if(totalCnt > maxTotalCnt) {
                    maxTotalCnt = totalCnt;
                    minStartTime = startTime + 1;
                }
                
                startTime += 1;
                endTime += 1;
            }
        }
        
        public String solution(String play_time, String adv_time, String[] logs) {
            String answer = "00:00:00";
            int playTime = changeStringToTime( play_time.split(":") );
            int advTime = changeStringToTime( adv_time.split(":") );
            viewerCnt = new int[playTime+1];
            
            processingLogs(logs);
            getStartTime(playTime, advTime);
            
            if(minStartTime != 0) {
                answer = changeTimeToString();
            }
            
            return answer;
        }
        
        public String changeTimeToString() {
            StringBuilder sb = new StringBuilder();
            long hour = minStartTime/3600;
            minStartTime %= 3600;
            
            long minute = minStartTime/60;
            minStartTime %= 60;
            
            if(hour < 10) {
                sb.append("0");
            }
            
            sb.append(hour + ":");
            
            if(minute < 10) {
                sb.append("0");
            }
            sb.append(minute + ":");
            
            if(minStartTime < 10) {
                sb.append("0");
            }
            sb.append(minStartTime);
            
            return sb.toString();
        }
    }
}
