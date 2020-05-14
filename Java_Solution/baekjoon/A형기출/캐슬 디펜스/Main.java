import java.util.*;
import java.io.*;

/**
 * Main
 */

// 궁수 3명 배치
// 궁수는 동시에 공격, 가장 왼쪽에 적 공격, 같은 적 공격 가능
// 1. 궁수 배치
// 2. 게임 진행
// 3. 가장많이 잡은 적군 수 출력.

public class Main {
    static int N, M, D, map[][], maxKill, allEnemy, checkEnemy;

    static public class Node {
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

    static public boolean wall(int x, int y) {
        if(x>=N || x<0 || y>=M || y<0) return true;
        return false;
    }
    
    static public int[][] downEnemy(int copy_map[][]) {
        int temp_map[][] = new int[N][M];

        for(int i=0; i<N-1; i++) {
            for(int j=0; j<M; j++) {
                temp_map[i+1][j] = copy_map[i][j];
            }
        }

        for(int i=0; i<M; i++) {
            if(copy_map[N-1][i]==1) checkEnemy++;
        }
        
        return temp_map;
    }

    // static public void setArcher(int cnt, int idx, boolean visited[]) {
    //     if(cnt==3) {
    //         defenseCastle(visited);
    //         return;
    //     }
        
    //     if(idx >= M) return;

    //     visited[idx] = true;
    //     setArcher(cnt+1, idx+1, visited);
    //     visited[idx] = false;
    //     setArcher(cnt, idx+1, visited);
    // }

    static public LinkedList<Node> attackArcher(int setArcher[], int copy_map[][]) {
        LinkedList<Node> killEnemyList = new LinkedList<>();
        int[] dx = {0,-1,0}, dy={-1,0,1};
        
        for(int archer: setArcher) {
            boolean visitedArcher[][] = new boolean[N][M];
            Queue<Node> que = new LinkedList<>();
            que.offer(new Node(N, archer, 0));
            
            loop:
            while(!que.isEmpty()) {
                Node n = que.poll();

                if(n.dist >= D) continue;

                for(int dir=0; dir<3; dir++) {
                    int nx = n.x + dx[dir];
                    int ny = n.y + dy[dir];
                    if(wall(nx, ny) || visitedArcher[nx][ny]) continue;
                    if(copy_map[nx][ny]==1) {
                        killEnemyList.add(new Node(nx, ny));
                        break loop;
                    } 
                    que.offer(new Node(nx, ny, n.dist+1));
                    visitedArcher[nx][ny] = true;
                }
            }
        }

        return killEnemyList;
    }

    static public void defenseCastle(int setArcher[]) {
        int copy_map[][] = new int[N][M];
        int killCnt = 0;
        checkEnemy = 0;
        // ArrayList<Integer> archerList = new ArrayList<>();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                copy_map[i][j] = map[i][j];
            }
        }

        for(int turn=0; turn<N; turn++) {
            
            LinkedList<Node> killEnemyList = new LinkedList<>();
            if(checkEnemy == allEnemy) break;

            killEnemyList = attackArcher(setArcher, copy_map);
            if(killEnemyList.size() != 0) {
                for(Node kill: killEnemyList) {
                    if(copy_map[kill.x][kill.y] == 1) {
                        copy_map[kill.x][kill.y] = 0;
                        killCnt += 1;
                        checkEnemy += 1;
                    }
                }
            }

            copy_map = downEnemy(copy_map);
        }

        maxKill = Math.max(maxKill, killCnt);
    }


    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        boolean visited[] = new boolean[M];
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1) allEnemy += 1;
            }
        }

        int setArcher[] = new int[3];

        for(int i=0; i<M-2; i++) {
            setArcher[0] = i;
            for(int j=i+1; j<M-1; j++) {
                setArcher[1] = j;
                for(int k=j+1; k<M; k++) {
                    setArcher[2] = k;
                    defenseCastle(setArcher);
                }
            }
        }

        // setArcher(0, 0, visited);

        System.out.println(maxKill);
    }
}