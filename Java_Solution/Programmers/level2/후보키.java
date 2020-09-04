import java.util.*;

class 후보키 {
    String[][] relation;
    ArrayList<HashSet<Integer>> keyList;
    int col;
    
    public int solution(String[][] relation) {
        this.relation = relation;
        keyList = new ArrayList<>();
        col = relation[0].length;
        
        for(int i=1; i<=col; i++) {
            makeSet(i, -1, 0, new HashSet<>());
        }
        
        return keyList.size();
    }
    
    public void makeSet(int size, int idx, int cnt, HashSet<Integer> keySet) {
        if(size == cnt) {
            for(int k : keySet)
                System.out.print(k + ", ");

            
            for(HashSet<Integer> key : keyList) {
                if(keySet.containsAll(key)) {
                    System.out.println(key + "를 포함하고 있습니다.");
                    return;
                }
            }
            
            if(chkUnique(keySet)) {
                System.out.println("후보키입니다.");
                keyList.add(keySet);
            } else {
                System.out.println("후보키가 아닙니다.");
            }
            
            return;
        }
        
        for(int i=idx+1; i<col; i++) {
            HashSet<Integer> newKeySet = new HashSet<>(keySet);
            newKeySet.add(i);
            makeSet(size, i, cnt+1, newKeySet);
        }
    }
    
    public boolean chkUnique(HashSet<Integer> keySet) {
        HashMap<String, String> keyMap = new HashMap<>();
        
        for(int r=0; r<relation.length; r++) {
            String key = "";
            
            for(int c : keySet) {
                key += relation[r][c];
            }
            
            if(keyMap.containsKey(key))
                return false;
            else
                keyMap.put(key, key);
        }
        
        return true;
    }
}