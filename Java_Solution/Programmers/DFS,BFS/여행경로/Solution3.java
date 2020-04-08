import java.util.LinkedList;
import java.util.Collections;

class Solution {
    private String tickets[][];
    int ticket_cnt, visited[];
    LinkedList<String> res_route;
    String route;
    
    public void DFS(String airport, int cnt) {
        route += airport + "|";
        
        if(cnt == tickets.length) {
            res_route.add(route);
            return;
        }
        
        for(int i=0; i<ticket_cnt; i++) {
            if(visited[i]==0 && tickets[i][0].equals(airport)) {
                visited[i] = 1;
                DFS(tickets[i][1], cnt+1);
                visited[i] = 0;
                route = route.substring(0, route.length()-4);
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        ticket_cnt = tickets.length;
        String[] answer;
        visited = new int[ticket_cnt];
        route = "";
        this.tickets = tickets;
        
        res_route = new LinkedList<>();
        
        for(int i=0; i<ticket_cnt; i++) {
            if(tickets[i][0].equals("ICN")) {
                visited[i] = 1;
                route = "ICN" + "|";
                DFS(tickets[i][1], 1);
                visited[i] = 0;
            }
        }
        
        Collections.sort(res_route);
        System.out.println(res_route.get(0));
        answer = res_route.get(0).split("\\|");
        return answer;
    }
}