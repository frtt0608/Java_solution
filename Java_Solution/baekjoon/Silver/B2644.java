import java.io.*;
import java.util.*;

public class B2644 {

    static int N, M, targetX, targetY, relationCount;
    static int[][] relation;
    static boolean[] visited;

    public static void findRelationCount(int idx, int count) {
        visited[idx] = true;

        if(idx == targetY) {
            relationCount = count;
            return;
        }

        for(int i=1; i<N+1; i++) {
            if(relation[idx][i] == 1 && !visited[i]) {
                visited[i] = true;
                findRelationCount(i, count+1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        targetX = Integer.parseInt(st.nextToken());
        targetY = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        relation = new int[N+1][N+1];
        visited = new boolean[N+1];
        relationCount = -1;

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            relation[x][y] = 1;
            relation[y][x] = 1;
        }

        findRelationCount(targetX, 0);
        System.out.println(relationCount);
    }
}
