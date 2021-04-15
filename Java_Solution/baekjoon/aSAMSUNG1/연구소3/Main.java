import java.util.*;
import java.io.*;

class Node {
    int x;
    int y;
    int time;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Node(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}
/**
 * Main
 */
public class Main {
    static int N, M, office[][], emptyCnt, virusListsize, minTime;
    static int[] dx={1,-1,0,0}, dy={0,0,1,-1};
    static ArrayList<Node> virusList;
    static boolean  perm_v[];

    static boolean wall(int x, int y) {
        if(x>N || x<=0 || y>N || y<=0) return true;
        return false;
    }

    static int[][] deep_copy(int copy_office[][]) {
        for(int i=1; i<=N; i++) {
            System.arraycopy(office[i], 1, copy_office[i], 1, N);
        }
        return copy_office;
    }

    static void BFS(boolean virusArr[]) {
        Queue<Node> que = new LinkedList<>();
        boolean visited[][] = new boolean[N+1][N+1];
        int copy_office[][] = new int[N+1][N+1];
        copy_office = deep_copy(copy_office);

        int time = 0;
        int zeroCnt = 0;
        
        for(int i=0; i<virusListsize; i++) {
            Node node = virusList.get(i);
            int r = node.x;
            int c = node.y;
            copy_office[r][c] = -2;
            if(virusArr[i]==true) {
                visited[r][c] = true;
                que.offer(new Node(r, c, 0));
                copy_office[r][c] = 0;
            }
        }
        
        loop:
        while(!que.isEmpty()) {
            Node node = que.poll();
            int x = node.x;
            int y = node.y;
            time = node.time;

            time+=1;
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(wall(nx,ny) || visited[nx][ny] || copy_office[nx][ny]==-1) continue;
                visited[nx][ny] = true;
                
                if(copy_office[nx][ny]==0) {
                    copy_office[nx][ny] = time;
                    zeroCnt += 1;
                    if(zeroCnt==emptyCnt) break loop;
                }
                que.offer(new Node(nx, ny, time));
            }
        }

        if(zeroCnt!=emptyCnt) return;
        else minTime = Math.min(minTime, time);
    }

    static void perm(int idx, int cnt, boolean virusArr[]) {
        if(cnt==M) {
            BFS(virusArr);
            return;
        }

        if(idx < virusListsize) {
            virusArr[idx] = true;
            perm(idx+1, cnt+1, virusArr);
            virusArr[idx] = false;
            perm(idx+1, cnt, virusArr);
        }
    }

     static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        office = new int[N+1][N+1];
        virusList = new ArrayList<>();
        minTime = 999999;
        
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());

                if(office[i][j]==1) office[i][j] = -1;
                else if(office[i][j]==2) virusList.add(new Node(i,j));       
                else emptyCnt += 1;
            }
        }

        virusListsize = virusList.size();
        perm_v = new boolean[virusListsize];
        boolean virusArr[] = new boolean[virusListsize];

        if(emptyCnt!=0) {
            perm(0, 0, virusArr);
            if(minTime==999999) minTime=-1;
        } else {
            minTime = 0;
        }

        System.out.println(minTime);
    }
}