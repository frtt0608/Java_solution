// begin "hit" 
// target "cog" 
// words [hot, dot, dog, lot, log, cog]
// return 4

// begin "hit" 
// target "cog" 
// words [hot, dot, dog, lot, log]
// return 0


import java.util.LinkedList;
import java.util.Queue;

class Word_Num {
    String word;
    int num;
    
    Word_Num(String word, int num) {
        this.word = word;
        this.num = num;
    }
}

class Solution {
    private String words[];
    Queue<Word_Num> que;
    int visited[];
    int length;
    
    public int BFS(String begin, String target) {
        que.offer(new Word_Num(begin,0));
        int word_length = begin.length();
        int differ;
        
        while(!que.isEmpty()) {
            String word = que.peek().word;
            int num = que.peek().num;
            que.poll();
            
            if(word.equals(target)) return num;
            
            for(int i=0; i<length; i++) {
                if(visited[i]==1) continue;
                differ = 0;
                for(int idx=0; idx<word_length; idx++) {
                    if(differ > 1) break;
                    if(word.charAt(idx) != words[i].charAt(idx))
                        differ++;
                }
                if(differ==1) {
                    que.offer(new Word_Num(words[i], num+1));
                    visited[i] = 1;
                }
            }
        }
        return 0;
    }
    
    public Boolean contains(String target) {
        for(int i=0; i<length; i++) {
            if(words[i].equals(target)) 
                return true;
        }
        return false;
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 100;
        int min_res = 0;
        this.words = words;
        length = words.length;
        que = new LinkedList<Word_Num>();
        visited = new int[words.length];
        
        if(contains(target)) {
            min_res = BFS(begin, target);
            if(min_res != 0 && answer > min_res) {
                answer = min_res;
            }
            System.out.println(min_res);
        } else 
            return 0;
        
        return answer;
    }
}