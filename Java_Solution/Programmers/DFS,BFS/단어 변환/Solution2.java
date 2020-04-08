class Solution {
    private String begin, target, words[];
    int answer, visited[], compare;
    
    public void DFS(int n, int r, String word) {
        if(n<r) return;
        
        if(word.equals(target)) {
            if(answer > r) answer = r;
            return;
        }
        for(int i=0; i<n; i++) {
            if(visited[i]==1) continue;
            compare = 0;
            for(int idx=0; idx<begin.length(); idx++) {
                if(compare>=2) break;
                if(word.charAt(idx) != words[i].charAt(idx)) compare++;
            }
            if(compare==1) {
                visited[i] = 1; 
                DFS(n, r+1, words[i]);
            }
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        answer = 51; compare = 0;
        this.begin = begin; this.target= target; this.words = words;
        visited = new int[words.length];
        
        for(String word : words) {
            if(word.equals(target)) {
                DFS(words.length, 0, begin);
                return answer;
            }
        }
        
        if(answer==51) answer=0;
        return answer;
    }
}