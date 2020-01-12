// 다리는 지나는 트럭
// 2, 10, [7,4,5,6]
// 8

// 100, 100, [10,10,10,10,10,10,10,10,10,10]
// 110

import java.util.*;

class Truck {
    int weight;
    int time;
    
    Truck(int weight, int time) {
        this.weight = weight;
        this.time = time;
    }
}
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        LinkedList<Truck> qu = new LinkedList<Truck>();
        int idx = 1;
        int qu_size = 1;
        int temp;
        int bridge_weight=truck_weights[0];
        qu.add(new Truck(truck_weights[0], 0));
        
        while(!qu.isEmpty()) {
            temp = 0;
            while(true) {
                Truck truck = qu.poll();
                truck.time += 1;
                if(truck.time==bridge_length) {
                    bridge_weight -= truck.weight;
                    qu_size -= 1;
                } else {
                    qu.add(truck);
                    temp += 1;
                }
                if(temp==qu_size) break;
            }
            if(idx < truck_weights.length && bridge_weight + truck_weights[idx] <= weight) {
                qu.add(new Truck(truck_weights[idx], 0));
                bridge_weight += truck_weights[idx];
                qu_size += 1;
                idx++;
            }
            answer++;
        }
        
        return answer;
    }
}