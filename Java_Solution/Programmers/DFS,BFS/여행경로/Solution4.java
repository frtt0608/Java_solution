import java.util.LinkedList;
import java.util.Collections;

class Solution {
    private String tickets[][];
    LinkedList<String> route_list;
    int visited[];
    
    public void DFS(int n, int r, String airport, String route) {
        if(n==r) {
            route_list.add(route);
            return;
        }
        
        for(int i=0; i<n; i++) {
            if(visited[i]==1) continue;
            if(tickets[i][0].equals(airport)) {
                visited[i]=1;
                DFS(n, r+1, tickets[i][1], route+tickets[i][1]+"|");
                visited[i]=0;
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        this.tickets = tickets;
        route_list = new LinkedList<>();
        visited = new int[tickets.length];
        String route = "ICN|";
        
        DFS(tickets.length, 0, "ICN", route);
        
        Collections.sort(route_list);
        answer = route_list.get(0).split("\\|");
        
        return answer;
    }
}