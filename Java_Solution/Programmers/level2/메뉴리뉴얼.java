import java.util.*;


// 입력범위가 작아서 조합으로 해겷가능
// 조합과 자료구조를 활용한 구현문제
public class 메뉴리뉴얼 {

    class Solution {
        int N, maxCount;
        Map<String, Integer> courseMap;
        
        public String sortingString(String courseName) {
            char[] chars = courseName.toCharArray();
            Arrays.sort(chars);
            
            courseName = "";
            for(char chr: chars) {
                courseName += chr;
            }
            
            return courseName;
        }
        
        public void getOrderWithcombination(int idx, int targetNum, 
                                            String targetStr, String courseName) {
            if(courseName.length() == targetNum) {
                courseName = sortingString(courseName);
                courseMap.put(courseName, courseMap.getOrDefault(courseName, 0)+1);
                maxCount = Math.max(maxCount, courseMap.get(courseName));
                return;
            }
            
            for(int i=idx; i<N; i++) {
                char curChar = targetStr.charAt(i);
                getOrderWithcombination(i+1, targetNum, targetStr, courseName + curChar);
            }
        }
        
        public String[] solution(String[] orders, int[] courses) {
            String[] answer;
            ArrayList<String> results = new ArrayList<>();
            
            for(int course: courses) {
                courseMap = new HashMap<>();
                maxCount = 0;
                
                for(String order: orders) {
                    N = order.length();
                    getOrderWithcombination(0, course, order, "");
                }
                
                for(String courseName: courseMap.keySet()) {
                    if(courseMap.get(courseName) == maxCount && maxCount > 1) {
                        results.add(courseName);
                    }
                }
            }
            Collections.sort(results);
            answer = new String[results.size()];
            for(int i=0; i<results.size(); i++) {
                answer[i] = results.get(i);
            }
            
            return answer;
        }   
    }
}
