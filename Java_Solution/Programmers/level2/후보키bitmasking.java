import java.util.*;

public class 후보키bitmasking {
    class Solution {
        ArrayList<Integer> keyList;
        int col, row;
        String[][] relation;
        
        public int solution(String[][] relation) {
            this.relation = relation;
            keyList = new ArrayList<>();
            col = relation[0].length;
            row = relation.length;
            
            for(int set = 1; set < (1 << col); set++) {
                
                if(isMinimal(set)) {
                    if(isUnique(set)) {
                        System.out.println(Integer.toBinaryString(set));
                        keyList.add(set);
                    }
                }
            }
            
            return keyList.size();
        }
        
        public boolean isMinimal(int set) {
            for(int key: keyList) {
                if((key & set) == key) {
                    return false;
                }
            }
            
            return true;
        }
        
        public boolean isUnique(int set) {
            HashSet<String> tempSet = new HashSet<>();
            
            for(int r=0; r<row; r++) {
                String key = "";

                for(int c=0; c<col; c++) {
                    if((set & (1 << c)) != 0) {
                        key += relation[r][c];
                    }
                }
                
                if(tempSet.contains(key))
                    return false;
                else
                    tempSet.add(key);
            }
            
            return true;
        }
    }
}
