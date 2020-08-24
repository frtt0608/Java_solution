import java.util.*;

class p와y개수 {
    public String[] solution(String[] strings, int n) {
        
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                
                String s1 = Character.toString(str1.charAt(n));
                String s2 = Character.toString(str2.charAt(n));

                if(s1.compareTo(s2) != 0) {
                    return s1.compareTo(s2);
                } else {
                    return str1.compareTo(str2);
                }
            }
        });
        
        System.out.println(Arrays.toString(strings));
        return strings;
    }
}

// 람다식
// class Solution {
//     boolean solution(String s) {
//         s = s.toUpperCase();
        
//         boolean flag = s.chars().filter(p -> p == 'P').count() == s.chars().filter(y -> y == 'Y').count();
//         return flag;
//     }
// }