import java.util.*;
import java.io.*;

public class Main {
    static int N, M, emptyCnt, minSpendTime;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int[][] lab;
    static List<Node> virusList;
    static boolean[][] visited;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isWall(int x, int y) {
        return x<0 || x>=N || y<0 || y>=N;
    }

    public static void initVisited() {
        for(int i=0; i<N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    public static void getMinSpendTime(int[] indexArr) {
        int time = 0, zeroCnt = 0, x, y, nx, ny;
        Queue<Node> que = new LinkedList<>();
        initVisited();

        for(int index: indexArr) {
            Node virus = virusList.get(index);
            que.offer(virus);
            visited[virus.x][virus.y] = true;
        }

        while(zeroCnt != emptyCnt) {
            int qSize = que.size();
            time += 1;

            if(qSize == 0) break;

            for(int i=0; i<qSize; i++) {
                Node cur = que.poll();
                x = cur.x;
                y = cur.y;

                if(zeroCnt == emptyCnt) break;

                for(int dir=0; dir<4; dir++) {
                    nx = x + dx[dir];
                    ny = y + dy[dir];

                    if(isWall(nx, ny) || visited[nx][ny] || lab[nx][ny] == 1) continue;

                    visited[nx][ny] = true;
                    if(lab[nx][ny] == 0) {
                        zeroCnt += 1;
                    }

                    que.offer(new Node(nx,ny));
                }
            }
        }

        if(zeroCnt == emptyCnt) {
            minSpendTime = Math.min(minSpendTime, time);
        } 
    }

    public static void getTotalCase(int cnt, int idx, int[] indexArr) {
        if(cnt == M) {
            getMinSpendTime(indexArr);
            return;
        }

        if(idx == virusList.size()) return;

        for(int i=idx; i<virusList.size(); i++) {
            indexArr[cnt] = i;
            getTotalCase(cnt+1, i+1, indexArr);
            indexArr[cnt] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][N];
        visited = new boolean[N][N];
        virusList = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());

                if(lab[i][j] == 2) {
                    virusList.add(new Node(i, j));
                } else if(lab[i][j] == 0) {
                    emptyCnt += 1;
                }
            }
        }

        if(emptyCnt != 0) {
            minSpendTime = 1000000;
            getTotalCase(0, 0, new int[M]);
        }

        System.out.println(minSpendTime == 1000000 ? -1:minSpendTime);
    }
}