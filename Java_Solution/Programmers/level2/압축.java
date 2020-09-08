import java.util.*;

class 압축 {
    String[] alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    ArrayList<String> alphaList;
    LinkedList<Integer> ansList;
    
    public int[] solution(String msg) {
        ansList = new LinkedList<>();
        alphaList = new ArrayList<>(Arrays.asList(alpha));
        String[] msgArr = msg.split("");
        
        String curStr = "";
        String nextStr = "";
        int idx = 0;
        
        while(idx < msg.length()) {
            curStr = "";
            nextStr = "";
            
            while(true) {
                if(idx < msg.length()) {
                    nextStr = curStr + msgArr[idx];
                
                    if(alphaList.contains(nextStr)) {
                        curStr = nextStr;
                        idx += 1;
                    } else {
                        alphaList.add(nextStr);
                        // System.out.println(curStr +", " + nextStr);
                        ansList.add(alphaList.indexOf(curStr) + 1);
                        break;
                    }
                } else {
                    ansList.add(alphaList.indexOf(curStr) + 1);
                    break;
                }
            }
        }
        
        int[] answer = new int[ansList.size()];
        idx = 0;
        while(ansList.size() != 0) {
            answer[idx++] = ansList.remove(0);
        }

        return answer;
    }
}