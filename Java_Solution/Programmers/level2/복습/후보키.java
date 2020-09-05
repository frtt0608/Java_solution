import java.util.*;

class 후보키 {
    int col, row;
    ArrayList<Integer> keyList;
    String[][] relation;
    
    public int solution(String[][] relation) {
        col = relation[0].length;
        row = relation.length;
        keyList = new ArrayList<>();
        this.relation = relation;
        
        for(int set=1; set<Math.pow(2, col); set++) {
            // System.out.println(Integer.toBinaryString(set) + ": " + set);
            
            if(isMinimal(set)) {
                if(isUnique(set)) {
                    keyList.add(set);
                }
            }
        }
        
        return keyList.size();
    }
    
    public boolean isMinimal(int set) {
        for(int key : keyList) {
            if((set & key) == key)
                return false;
        }
        
        return true;
    }
    
    public boolean isUnique(int set) {
        HashSet<String> uniqueSet = new HashSet<>();
        
        for(int r=0; r<row; r++) {
            String key = "";
            
            for(int c=0; c<col; c++) {
                if((set & (1 << c)) != 0) 
                    key += relation[r][c];
            }
            
            if(uniqueSet.contains(key))
                return false;
            else
                uniqueSet.add(key);
        }
        
        return true;
    }
}