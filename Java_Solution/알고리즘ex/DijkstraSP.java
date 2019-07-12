public class DijkstraSP {
    final static int infi = 999;
    // 연결x 가중치 무한대
    public static void dijkstra(int[][] c, int n, int[][] t, int vertex) {
        // c배열은 그래프의 인접행렬, n은 정점의 개수, t배열은 연결된 간선 바열, vertex는 시작 정점
        int[] from = new int[n]; // 각 정점이 어느 정점으로부터 연결되었는지 표시하는 배열
        int[] dist = new int[n]; // 현재까지 결정된 목적지에서 각 정점마다의 가중치를 표시
        for(int i=0; i<n; i++) {
            // 최초 시작정점은 첫번째 정점임으로 vertex으로 초기화
            from[i] = vertex;
            dist[i] = c[vertex][i];
        }
        int best = 0;
        for(int i=0; i<n-1; i++) {
            // n-1은 연결될 간선의 수
            best = isBest(dist); // dist배열 중 0을 제외한 최소값을 가진 원소의 인덱스
            t[0][i] = from[best]; // 가중치가 가장 적은 간선을 선택해서 넣어준다.
            t[1][i] = best;
            for(int j=0; j<n; j++) {
                // dist배열과 from배열의 최신화
                if(dist[best] + c[best][j] < dist[j]) {
                    // 최근에 선택된 정점에 인접한 정점의 가중치와 기존 dist배열과 비교
                    from[j] = best;
                    dist[j] = dist[best] + c[best][j];
                }
            }
            dist[best] = 0;
            // 부분 그래프로 선택된 정점까지의 가중치는 0으로 변경
        }
    }
    public static int isBest(int[] dist) {
        // dist배열 중 0을 제외한 최소값을 가진 원소의 인덱스를 반환하는 메소드
        int best = 0;
        for(int i=0; i<dist.length; i++) {
            if(dist[i] != 0) {
                best = i;
                break;
        	}
    	}
    	for(int j=0; j<dist.length; j++) {
        	// dist에서 0이 아닌 값 중 값이 가장 작은 인덱스를 찾는다.
        	if(dist[j] != 0 && dist[j] < dist[best]) 	{
            	best = j;
        	}
    	}
    	return best;
    }
    
    public static void main(String[] args) {
        int[][] graph = {
            {0,8,7,20,14,infi},
            {infi,0,infi,13,infi,infi},
            {infi,infi,0,infi,5,infi},
            {12,infi,infi,0,infi,infi},
            {11,infi,infi,6,0,4},
            {infi,infi,infi,10,infi,0},
        };
        int[][] t = new int[2][5];
        dijkstra(graph, 6, t, 0);
        for(int i=0; i < t[0].length; i++) {
            System.out.println(t[0][i]+1 + " " + (t[1][i]+1));
        }
    }
}