import java.util.*;
import java.io.*;


class Node {
    int x;
    int y;
    int dist;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Node(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}
/**
 * Main
 */
public class B17135 {
    static int N, M, D;
    static int allEnemy, maxKill, cur_Enemy;

    static public void setArcher(int map[][]) {
        int archerArr[] = new int[3];

        for(int i=0; i<M-2; i++) {
            archerArr[0] = i;
            for(int j=i+1; j<M-1; j++) {
                archerArr[1] = j;
                for(int k=j+1; k<M; k++) {
                    archerArr[2] = k;
                    // defenseCastle
                    defenseCastle(map, archerArr);
                }
            }
        }
    }

    static public void defenseCastle(int temp_map[][], int archerArr[]) {
        int dx[] = {0,-1,0};
        int dy[] = {-1,0,1};
        int map[][] = new int[N][M];
        int killCnt = 0;
        cur_Enemy = 0;
        LinkedList<Node> killList = new LinkedList<>();
        Queue<Node> que = new LinkedList<>();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i][j] = temp_map[i][j];
            }
        }

        for(int turn=0; turn<N; turn++) {
            if(allEnemy == cur_Enemy) break;
            killList.clear();

            // attackArcher
            for(int archer: archerArr) {
                que.clear();
                boolean visited[][] = new boolean[N][M];
                que.offer(new Node(N, archer, 0));
    
                loop:
                while(!que.isEmpty()) {
                    Node n = que.poll();
    
                    if(n.dist >= D) break;

                    for(int dir=0; dir<3; dir++) {
                        int nx = n.x + dx[dir];
                        int ny = n.y + dy[dir];
                        if(nx >= N || nx<0 || ny>=M || ny<0 || visited[nx][ny]) continue;
                        if(map[nx][ny]==1) {
                            killList.add(new Node(nx, ny));
                            break loop;
                        }
                        que.offer(new Node(nx, ny, n.dist+1));
                        visited[nx][ny] = true;
                    }
                }
            }

            while(!killList.isEmpty()) {
                Node kill = killList.poll();

                if(map[kill.x][kill.y] == 1) {
                    map[kill.x][kill.y] = 0;
                    killCnt += 1;
                    cur_Enemy += 1;
                }
            }

            // downEnemy
            map = downEnemy(map);
        }

        maxKill = Math.max(maxKill, killCnt);
    }

    static public int[][] downEnemy(int map[][]) {
        int temp_map[][] = new int[N][M];
        
        for(int i=0; i<N-1; i++) {
            for(int j=0; j<M; j++) {
                temp_map[i+1][j] = map[i][j];
            }
        }
        
        for(int j=0; j<M; j++) {
            if(map[N-1][j] == 1) cur_Enemy += 1;
        }

        return temp_map;
    }


    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        int map[][] = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1) allEnemy += 1;
            }
        }

        setArcher(map);

        System.out.println(maxKill);
    }
}