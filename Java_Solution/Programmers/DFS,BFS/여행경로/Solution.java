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
    
    public LinkedList<String> BFS(int i, String start_airport) {
        LinkedList<String> route = new LinkedList<>();
        visited = new int[ticket_cnt];
        route.offer("ICN");
        route.offer(start_airport);
        
        visited[i] = 1;
        Travel.offer(new Route(start_airport, route));
        
        while(!Travel.isEmpty()) {
            start_airport = Travel.peek().start_airport;
            route = Travel.peek().route;
            Travel.poll();
            
            if(route.size() == ticket_cnt+1) {
                System.out.println(route);
                break;
            }
            
            for(int idx=0; idx<ticket_cnt; idx++) {
                if(visited[idx]==1) continue;
                if(tickets[idx][0].equals(start_airport)) {
                    visited[idx] = 1;
                    route.offer(tickets[idx][1]);
                    Travel.offer(new Route(tickets[idx][1], route));
                    break;
                }
            }
        }
        return route;
    }
    
    public String[] solution(String[][] tickets) {
        ticket_cnt = tickets.length;
        String[] answer = new String[ticket_cnt+1];
        this.tickets = tickets;
        Travel = new LinkedList<>();
        LinkedList<String> sort_route = new LinkedList<>();
        LinkedList<String> res_route = new LinkedList<>();
        Iterator<String> sort_iter;
        Iterator<String> res_iter;
        
        for(int i=0; i<ticket_cnt; i++) {
            if(tickets[i][0].equals("ICN")) {
                res_route = BFS(i, tickets[i][1]);
                if(sort_route.size()==0) {
                    sort_route = res_route;
                } else {
                    sort_iter = sort_route.iterator();
                    res_iter = res_route.iterator();
                    while(sort_iter.hasNext() && res_iter.hasNext()) {
                        if(sort_iter.next().compareTo(res_iter.next()) > 0) {
                            System.out.println("change");
                            sort_route = res_route;
                            break;
                        }
                    }
                }
            }
        }
        answer = sort_route.toArray(new String[ticket_cnt+1]);
        return answer;
    }
}   

// [["ICN", "BOO"], ["ICN", "COO"], ["COO", "DOO"], ["DOO", "COO"], ["BOO", "DOO"], ["DOO", "BOO"], ["BOO", "ICN"], ["COO", "BOO"]]
// ["ICN", "BOO", "DOO", "BOO", "ICN", "COO", "DOO", "COO", "BOO"]




// [["ICN", "COO"], ["ICN", "BOO"], ["COO", "ICN"], ["BOO", "DOO"]]
// ["ICN", "COO", "ICN", "BOO", "DOO"]

// [["ICN", "SFO"], ["SFO", "ICN"], ["ICN", "SFO"], ["SFO", "QRE"]]