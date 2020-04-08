import java.util.*;

class Solution2 {
    int parent[];
    
    public int getParent(int p) {
        if(parent[p]==p) return p;
        return parent[p] = getParent(parent[p]);
    }
    
    public void unionParent(int p1, int p2) {
        p1 = getParent(p1);
        p2 = getParent(p2);
        if(p1 < p2) parent[p1] = p2;
        else parent[p2] = p1;
    }
    
    public int findParent(int p1, int p2) {
        p1 = getParent(p1);
        p2 = getParent(p2);
        if(p1==p2) return 1;
        else return 0;
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        int cnt = 0;
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int cost1[], int cost2[]) {
                return Integer.compare(cost1[2],cost2[2]);
            }
        });
        
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;
        
        int index = 0;
        while(cnt < n-1) {
            int start = costs[index][0];
            int end = costs[index][1];
            int cost = costs[index][2];
            
            if(findParent(start, end)==0) {
                unionParent(start, end);
                answer += cost;
                cnt++;
            }
            
            index++;
        }
        
        return answer;
    }
}