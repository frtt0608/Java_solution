import java.util.*;

class 하노이 {
    ArrayList<int[]> routeList;
    
    public void hanoi(int n, int start, int end, int mid) {
        int[] move = {start, end};
        
        if(n == 1) {
            routeList.add(move);
            return;
        } else {
            hanoi(n-1, start, mid, end);
            routeList.add(move);
            hanoi(n-1, mid, end, start);
        }
    }
    
    public int[][] solution(int n) {
        routeList = new ArrayList<>();
        
        hanoi(n, 1, 3, 2);
        
        int[][] answer = new int[routeList.size()][2];
        
        for(int i=0; i<routeList.size(); i++) {
            int[] route = routeList.get(i);
            answer[i][0] = route[0];
            answer[i][1] = route[1];
        }
        
        return answer;
    }
}