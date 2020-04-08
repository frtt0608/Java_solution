import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int cam = 0;
        Arrays.sort(routes, (int route1[], int route2[]) -> Integer.compare(route1[1], route2[1]));
               
        for(int i=0; i<routes.length; i++) {
            System.out.println(routes[i][1]);
            if(i==0) {
                cam = routes[i][1];
                answer++;
                continue;
            }
            if(cam >= routes[i][0]) continue;
            else {
                cam = routes[i][1];
                answer++;
            }
        }
        return answer;
    }
}