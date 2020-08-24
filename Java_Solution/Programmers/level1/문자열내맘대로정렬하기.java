import java.util.*;

class 문자열내맘대로정렬하기 {
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