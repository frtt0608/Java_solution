import java.util.Arrays;

class 문자열내림차순 {
    public String solution(String s) {
        char[] chrs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        
        Arrays.sort(chrs);
        sb.append(new String(chrs)).reverse();
        
        return sb.toString();
    }
}