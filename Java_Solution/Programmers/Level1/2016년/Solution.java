class Solution {
    public String solution(int a, int b) {
        String answer = "";
        String day[] = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
        int dd_Arr[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // 1/1은 day[0];
        int mm = 1;
        int dd = 1;
        int total_dd = 1;
        
        while(true) {
            if(mm==a && dd==b) {
                answer = day[total_dd%7];
                break;
            }
            
            dd++;
            total_dd++;
            
            if(dd_Arr[mm] < dd) {
                mm++;
                dd = 1;
            }
        }
        
        return answer;
    }
}