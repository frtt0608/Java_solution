class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        char chr;
        int len = s.length();
        if(len%2 == 1) {
            chr = s.charAt(len/2);
            sb.append(Character.toString(chr));
        } else {
            chr = s.charAt(len/2-1);
            sb.append(Character.toString(chr));
            chr = s.charAt(len/2);
            sb.append(Character.toString(chr));
        }
        
        return sb.toString();
    }
}