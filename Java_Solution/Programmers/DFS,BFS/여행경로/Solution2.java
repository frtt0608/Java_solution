import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

class Route {
    String start_airport;
    LinkedList<String> route;
    
    Route(String start_airport, LinkedList<String> route) {
        this.start_airport = start_airport;
        this.route = route;
    }
}

class Solution {
    private String tickets[][];
    Queue<Route> Travel;
    int ticket_cnt, visited[];
    
    public LinkedList<String> BFS(String start_airport) {
        LinkedList<String> route = new LinkedList<>();
        visited = new int[ticket_cnt];
        route.offer(start_airport);
        String departure_airport = "";
        int v_idx = 0;
        
        Travel.offer(new Route(start_airport, route));
        
        while(!Travel.isEmpty()) {
            start_airport = Travel.peek().start_airport;
            route = Travel.peek().route;
            departure_airport = "";
            Travel.poll();
            
            if(route.size() == ticket_cnt+1) {
                System.out.println(route);
                break;
            }
            
            for(int idx=0; idx<ticket_cnt; idx++) {
                if(visited[idx]==1) continue;
                if(tickets[idx][0].equals(start_airport)) {
                    if(departure_airport.equals("")) {
                        departure_airport = tickets[idx][1];
                        v_idx = idx;
                    } else {
                        if(departure_airport.compareTo(tickets[idx][1]) > 0) {
                            departure_airport = tickets[idx][1];
                            v_idx = idx;
                        }
                    }
                }
            }
            visited[v_idx] = 1;
            route.offer(departure_airport);
            Travel.offer(new Route(departure_airport, route));
        }
        return route;
    }
    
    public String[] solution(String[][] tickets) {
        ticket_cnt = tickets.length;
        String[] answer = new String[ticket_cnt+1];
        this.tickets = tickets;
        Travel = new LinkedList<>();
        LinkedList<String> res_route = new LinkedList<>();
        
        res_route = BFS("ICN");
        
        answer = res_route.toArray(new String[ticket_cnt+1]);
        return answer;
    }
}