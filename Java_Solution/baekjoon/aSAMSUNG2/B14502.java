import java.util.*;
import java.io.*;

public class B14502 {
    static int N, M, maxSafeZone;
    static int[] dx={1,0,-1,0}, dy={0,-1,0,1};
    static int[][] map;
    static Queue<Node> virusQ, curVirusQ;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }

    public static void spreadVirus() {
        int[][] newMap = copyMap(map);
        curVirusQ = new LinkedList<>(virusQ);

        while(!curVirusQ.isEmpty()) {
            Node cur = curVirusQ.poll();

            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if(isWall(nx, ny)) continue;
                if(newMap[nx][ny] == 0) {
                    newMap[nx][ny] = 2;
                    curVirusQ.offer(new Node(nx, ny));
                }
            }
        }

        getSaftZone(newMap);
    }

    public static void getSaftZone(int[][] newMap) {
        int safeZone = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(newMap[i][j] == 0) {
                    safeZone += 1;
                }
            }
        }

        maxSafeZone = Math.max(maxSafeZone, safeZone);
    }

    public static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                newMap[i][j] = map[i][j];
            }
        }

        return newMap;
    }

    // 1: 벽, 2: 바이러스
    public static void setWall(int cnt, int sx, int sy, Node[] nodes) {
        if(cnt == 3) {
            spreadVirus();
            return;
        }

        for(int x=sx; x<N; x++) {
            for(int y=0; y<M; y++) {
                if(map[x][y] == 0) {
                    map[x][y] = 1;
                    nodes[cnt] = new Node(x, y);
                    setWall(cnt+1, x, y, nodes);
                    map[x][y] = 0;
                }
            }   
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        virusQ = new LinkedList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2) {
                    virusQ.offer(new Node(i, j));
                }
            }
        }

        setWall(0, 0, 0, new Node[3]);
        System.out.println(maxSafeZone);
    }
}