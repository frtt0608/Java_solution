// 9월 15일 로그데이터 분석, 초당 최대 처리량 계산
// 11, 12 : 14, 15 : 17, 18 . 20 21 22
// 24~28
import java.util.*;

class Solution {
    double[][] time;
    int answer;
    int len;
    
    public int solution(String[] lines) {
        answer = 0;
        len = lines.length;
        time = new double[len][2];
        
        if(len > 1) {
            for(int i=0; i<len; i++) {
                String line = lines[i];


                int h = ((line.charAt(11)-'0')*10 + (line.charAt(12)-'0'))*3600;
                int m = ((line.charAt(14)-'0')*10 + (line.charAt(15)-'0'))*60;
                String s_str = line.substring(17, 23);
                String append_str = line.substring(24, line.length()-1);

                double s_ = Double.parseDouble(s_str);
                double append = Double.parseDouble(append_str);

                double endTime = h + m + s_;
                time[i][0] = endTime - append + 0.001;
                time[i][0] = Math.round(time[i][0]*1000)/1000.0;
                time[i][1] = endTime;
                // System.out.println(h + ": " + m + ": " + s_str);
                // System.out.println(endTime);
            }

            Arrays.sort(time, new Comparator<double[]>() {
                @Override
                public int compare(double[] d1, double[] d2) {
                    if(d1[1] - d2[1] < 0) return -1;
                    else return 1;
                }
            });
            
            for(int i=0; i<len; i++) {
                for(int t=0; t<2; t++) {
                    double s_time = time[i][t];
                    double e_time = time[i][t] + 1;
                    int cnt = 0;
                    for(int j=0; j<len; j++) {
                        if(time[j][1] >= s_time && time[j][0] < e_time) {
                            // System.out.println(time[j][1] + " >= " + s_time);
                            // System.out.println(time[j][0] + " <= " + e_time);
                            cnt += 1;
                        }
                    }
                    
                    answer = Math.max(answer, cnt);
                }
            }
        } else {
            answer = 1;
        }
        
        // for(int i=0; i<len; i++) {
        //     System.out.println(Arrays.toString(time[i]));    
        // }
    
        System.out.println(answer);
        return answer;
    }
}