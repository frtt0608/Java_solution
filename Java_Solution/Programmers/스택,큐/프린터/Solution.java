// 프린터
// [2, 1, 3, 2], 2
// 1

// [1, 1, 9, 1, 1, 1], 0
// 5

import java.util.*;

class Solution {
    public class Docu {
        int priority;
        int locate;
        
        Docu(int priority, int locate) {
            this.priority = priority;
            this.locate = locate;
        }
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int cnt=0;
        Boolean flag = false;
        
        LinkedList<Docu> printer = new LinkedList<>();
        for(int i=0; i<priorities.length; i++) {
            printer.add(new Docu(priorities[i], i));
        }
        
        while(!printer.isEmpty()) {
            cnt = 0;
            flag = false;
            
            Docu temp_docu = printer.peek();
            for(Docu docu:printer) {
                if(temp_docu.priority < docu.priority) {
                    flag = true;
                    break;
                }
                cnt++;
            }
            if(flag) {
                for(int i=0; i<cnt; i++) {
                    Docu docu = printer.poll();
                    printer.add(docu);
                }
            } else {
                answer++;
                Docu d = printer.poll();
                if(d.locate == location) {
                    return answer;
                }
            }
            
        }
        return answer;
    }
}