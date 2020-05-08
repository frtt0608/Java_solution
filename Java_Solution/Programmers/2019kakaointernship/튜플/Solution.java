// replaceAll과 trim같은 String 메소드에 좀더 유의하자..

import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        Set<String> set = new HashSet<>();
        int idx = 0;
        
        String a = s.replaceAll("[{]", " ").replaceAll("[}]", " ");
        String b = a.trim();
        String input_s[] = b.split(" , ");
        
        Arrays.sort(input_s, (String str1, String str2) -> {
            return str1.length() - str2.length();
        });
        answer = new int[input_s.length];
        
        for(String _str_ : input_s) {
            for(String str : _str_.split(",")) {
                if(set.add(str)) {
                    answer[idx++] = Integer.parseInt(str);
                }
            }
        }
        
        return answer;
    }
}