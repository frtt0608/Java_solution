import java.util.*;

public class 표편집_origin {

    class Solution {
        int curPos, tableSize;
        Stack<Integer> deleteNodes;
        
        public void operateCommand(String command) {
            char cmd = command.charAt(0);
            
            if(cmd == 'U') {
                curPos -= Integer.parseInt(command.substring(2));
            } else if(cmd == 'D') {
                curPos += Integer.parseInt(command.substring(2));
            } else if(cmd == 'C') {
                deleteNodes.push(curPos);
                tableSize -= 1;
                
                if(tableSize == curPos) {
                    curPos -= 1;
                }
            } else {
                int num = deleteNodes.pop();
                tableSize += 1;
                if(curPos >= num) curPos += 1;
            }
        }
        
        public String getAnswerTable() {
            StringBuilder sb = new StringBuilder();
            
            for(int i=0; i<tableSize; i++) {
                sb.append("O");
            }
            
            while(!deleteNodes.isEmpty()) {
                sb.insert(deleteNodes.pop().intValue(), "X");
            }
            
            return sb.toString();
        }
        
        public String solution(int n, int k, String[] cmd) {
            curPos = k;
            tableSize = n;
            deleteNodes = new Stack<>();
            
            for(String command: cmd) {
                operateCommand(command);
            }
            
            return getAnswerTable();
        }
    }
}
