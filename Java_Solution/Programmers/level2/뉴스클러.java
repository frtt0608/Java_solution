import java.util.*;
import java.util.regex.*;

class 뉴스클러 {
    ArrayList<String> str1List, str2List;
    
    public void makeSet(String str, int num) {
        String pattern = "^[a-zA-Z]*{2}$";
        
        for(int i=0; i<str.length()-1; i++) {
            String tempStr = str.substring(i, i+2);
            if(Pattern.matches(pattern, tempStr)) {
                tempStr = tempStr.toLowerCase();
                // System.out.println(tempStr);
                
                if(num == 1) 
                    str1List.add(tempStr);
                else 
                    str2List.add(tempStr);
            }
        }
    }
    
    public int solution(String str1, String str2) {
        int answer = 0;
        str1List = new ArrayList<>();
        str2List = new ArrayList<>();
        
        makeSet(str1, 1);
        makeSet(str2, 2);
        int totalSize = str1List.size() + str2List.size();
        
        if(totalSize == 0) {
            answer = 65536;
        } else {
            
            int intersection = 0;
            int union = 0;
            
            for(String str : str1List) {
                if(str2List.remove(str)) {
                    intersection += 1;
                }
            }

            union = totalSize - intersection;
            
            answer = (int)((intersection * 65536)/union);
        }
        
        
        return answer;
    }
}