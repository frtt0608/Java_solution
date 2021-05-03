public class 신규아이디추천 {
    
    class Solution {
    
        public String changeBigToSmall(String new_id) {
            return new_id.toLowerCase();
        }
        
        public String removeAnotherCharatcer(String new_id) {
            StringBuilder sb = new StringBuilder();
            
            for(char chr: new_id.toCharArray()) {
                if((chr >= '0' && chr <= '9') || (chr >= 'a' && chr <= 'z')) {
                    sb.append(chr);          
                } else if(chr == '-' || chr == '_' || chr == '.') {
                    sb.append(chr);
                }
            }
            
            return sb.toString();
        }
        
        public String replaceContinuousEndPoint(String new_id) {
            while(new_id.contains("..")) {
                new_id = new_id.replace("..", ".");
            }
            
            return new_id;
        }
        
        public String removeEndPoint(String new_id) {
            if(new_id.length() > 0 && new_id.charAt(0) == '.') {
                new_id = new_id.replaceFirst(".", "");
            }
            
            if(new_id.length() > 0 && new_id.charAt(new_id.length()-1) == '.') {
                new_id = new_id.substring(0, new_id.length()-1);
            }
            
            return new_id;
        }
        
        public String checkEmptyString(String new_id) {
            if(new_id.length() == 0) {
                new_id = "a";
            }
            
            return new_id;
        }
        
        public String checkStringLength(String new_id) {
            if(new_id.length() >= 16) {
                new_id = new_id.substring(0, 15);
                
                if(new_id.charAt(14) == '.') {
                    new_id = new_id.substring(0, 14);
                }
            }
            
            return new_id;
        }
        
        public String checkStringLength2(String new_id) {
            while(new_id.length() <= 2) {
                new_id += new_id.charAt(new_id.length()-1);
            }
            
            return new_id;
        }
        
        public String solution(String new_id) {
            new_id = changeBigToSmall(new_id);
            new_id = removeAnotherCharatcer(new_id);
            new_id = replaceContinuousEndPoint(new_id);
            new_id = removeEndPoint(new_id);
            new_id = checkEmptyString(new_id);
            new_id = checkStringLength(new_id);
            new_id = checkStringLength2(new_id);
            
            return new_id;
        }
    }
}
