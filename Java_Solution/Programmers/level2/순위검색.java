import java.util.*;

public class 순위검색 {

    class Solution {
        Map<String, List<Integer>> infoMap;
        int[] answer;
        
        public void setInfoMap(String[] infos) {
            String key;
            int idx, value;
            
            for(String info: infos) {
                idx = info.length()-1;
                
                while(info.charAt(idx) != ' ') {
                    idx -= 1;
                }
                
                key = info.substring(0, idx);
                value = Integer.parseInt(info.substring(idx+1));
                
                if(infoMap.containsKey(key)) {
                    infoMap.get(key).add(value);
                } else {
                    List<Integer> values = new ArrayList<>();
                    values.add(value);
                    infoMap.put(key, values);
                }
            }
        }
        
        public void sortingValues() {
            for(String key: infoMap.keySet()) {
                List<Integer> values = infoMap.get(key);
                Collections.sort(values);
                infoMap.put(key, values);
            }
        }
        
        public void checkQuery(String[] queries) {
            int idx, value;
            String query, key;

            for(int i=0; i<queries.length; i++) {
                query = queries[i];
                idx = query.length()-1;
                
                while(query.charAt(idx) != ' ') {
                    idx -= 1;
                }
                
                key = query.substring(0, idx).replace(" and", "");
                value = Integer.parseInt(query.substring(idx+1));
                compareKey(key, value, i);
            }
        }
        
        public void compareKey(String key, int value, int num) {
            String[] targets = key.split(" ");
            List<String> keys = new ArrayList<>(infoMap.keySet());
            
            for(String target: targets) {
                if(target.equals("-")) continue;
                
                for(int i=0; i<keys.size(); i++) {
                    key = keys.get(i);
                    
                    if(!key.contains(target)) {
                        keys.remove(i);
                        i -= 1;
                    }
                }
            }
            
            for(String answerKey: keys) {
                answer[num] += searchSameScore(value, answerKey);
            }
        }
        
        public int searchSameScore(int score, String answerKey) {
            List<Integer> scores = infoMap.get(answerKey);
            int mid;
            int min = 0;
            int max = scores.size()-1;
            
            while(min <= max) {
                mid = (min+max)/2;
                
                if(score <= scores.get(mid)) {
                    max = mid-1;
                } else {
                    min = mid+1;
                }
            }
            
            return scores.size() - min;
        }
        
        public int[] solution(String[] info, String[] query) {
            answer = new int[query.length];
            infoMap = new HashMap<>();
            setInfoMap(info);
            sortingValues();
            checkQuery(query);
            
            return answer;
        }
    }
}
