import java.util.*;

public class 끝말잇기 {

    static class Solution {
        public static int[] solution(int n, String[] words) {
            int[] answer = {0,0};
            Set<String> wordSet = new HashSet<>();
            char lastChar = words[0].charAt(words[0].length()-1);
            wordSet.add(words[0]);
            
            for(int i=1; i<words.length; i++) {
                String word = words[i];
                
                if(!wordSet.contains(word) && lastChar == word.charAt(0)) {
                    wordSet.add(word);
                    lastChar = word.charAt(word.length()-1);
                } else {
                    answer[0] = i%n + 1;
                    answer[1] = i/n + 1;
                    break;
                }
            }

            return answer;
        }
        
        public static void main(String[] args) {
            String[] words = {};
            solution(0, words);
        }
    }
}
