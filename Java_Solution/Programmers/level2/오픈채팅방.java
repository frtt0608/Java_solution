import java.util.*;

class 오픈채팅방 {
    
    public String[] solution(String[] record) {
        Map<String, String> userMap = new HashMap<>();
        int cnt = 0;
        
        for(int i=0; i<record.length; i++) {
            String[] records = record[i].split(" ");
            // System.out.println(Arrays.toString(records));
            
            if(records[0].equals("Leave")) {
                cnt += 1;
            } else if(records[0].equals("Enter")) {
                cnt += 1;
                userMap.put(records[1], records[2]);
            } else {
                userMap.put(records[1], records[2]);
            }
        }
        
        int idx = 0;
        String[] answer = new String[cnt];
        for(int i=0; i<record.length; i++) {
            String[] records = record[i].split(" ");
            String nick_name = userMap.get(records[1]);
            // System.out.println(nick_name);
            
            if(records[0].equals("Leave")) {
                answer[idx++] = nick_name + "님이 나갔습니다.";
            } else if(records[0].equals("Enter")) {
                answer[idx++] = nick_name + "님이 들어왔습니다.";
            }
        }
        
        return answer;
    }
}