public class FloydSP {
    static final int infi = 999;
   
    static void Floyd(int[][] c, int n, int[][] d, int[][] p) {
        // 플로이드 알고리즘을 구현한 함수
        // c는 최초 인접행렬, n은 정점의 개수, d는 결과를 담을 배열, p는 path배열로 경로를 기억합니다.
        System.arraycopy(c, 0, d, 0, n); // 배열 복사
        for(int k=1; k<n; k++) {
            // 1번 정점부터 마지막 정점까지 차례대로 계산
            for(int i=1; i<n; i++) {
                
                for(int j=1; j<n; j++) {
                    if(d[i][j] > d[i][k] + d[k][j]) {
                        // 직접가는 것이 빠른지 경로를 거쳐서 가는것이 빠른지 확인합니다.
                        d[i][j] = d[i][k];
                        p[i][j] = k; // 연결한 정점의 경로를 가지고 있습니다.
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        int[][] c = {
            {0,0,0,0,0,0},
            {0,0,6,4,infi,infi},
            {0,infi,0,infi,7,5},
            {0,3,infi,0,2,infi},
            {0,infi,4,infi,0,6},
            {0,2,infi,7,infi,0}};
        int[][] d = new int[6][6];
        int[][] p = new int[6][6];
        
        Floyd(c, c.length, d, p);
    }
}