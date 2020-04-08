class Solution {
    private int n, computers[][];
    int visited[], answer;
    
    public void DFS(int n, int start) {
        for(int i=0; i<n; i++) {
            if(visited[i] == 1) continue;
            if(computers[start][i] == 1) {
                visited[i] = 1;
                DFS(n, i);
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        answer = 0;
        this.n = n; this.computers = computers;
        visited = new int[n];
        for(int i=0; i<n; i++) {
            if(visited[i]==1) continue;
            System.out.println(i);
            visited[i] = 1;
            DFS(n, i);
            answer++;
        }
        
        return answer;
    }
}