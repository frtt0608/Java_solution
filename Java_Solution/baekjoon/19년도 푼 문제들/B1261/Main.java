// 알고스팟
import java.io.*;
import java.util.*;

class Wall implements Comparable<Wall>{
    int X;
    int Y;
    int cnt;

    Wall(int X, int Y, int cnt) {
        this.X = X;
        this.Y = Y;
        this.cnt = cnt;
    }

    public int getX(){
        return X;
    }
    public int getY() {
        return Y;
    }
    public int getCnt() {
        return cnt;
    }

    @Override
    public int compareTo(Wall wall) {
        return (this.cnt > wall.getCnt()) ? 1:-1;
    }
}

public class Main {
    static int N;
    static int M;
    static int[][] maze;
    static int[][] v;
    static int[] dr;
    static int[] dc;

    static void BFS(int x, int y, int cnt) {
        PriorityQueue<Wall> qu = new PriorityQueue();
        qu.add(new Wall(x, y, cnt));
        v[x][y] = 0;

        while(!qu.isEmpty()) {
            Wall wall = qu.poll();

            int r = wall.getX();
            int c = wall.getY();
            if(r==N-1 && c==M-1) {
                System.out.println(wall.getCnt());
                break;
            }
            if(v[r][c] < wall.getCnt()) continue;
            for(int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr>=N || nr<0 || nc>=M || nc<0) continue;
                if(maze[nr][nc]==1) {
                    if(v[nr][nc] > v[r][c] + 1) {
                        v[nr][nc] = v[r][c] + 1;
                        qu.add(new Wall(nr, nc, v[nr][nc]));
                    }
                } else {    
                    if(v[nr][nc] > v[r][c]) {
                        v[nr][nc] = v[r][c];
                        qu.add(new Wall(nr, nc, v[nr][nc]));
                    }
                }
                // System.out.println(nr+ ", " + nc + " ! " + v[nr][nc] + " vs " + r + ", " + c + " ! " + v[r][c]);
            }
        }
    }
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);
        dr = new int[] {0,0,1,-1};
        dc = new int[] {1,-1,0,0};

        M = in.nextInt();
        N = in.nextInt();
        maze = new int[N][M];
        v = new int[N][M];
        for(int i=0; i<N; i++) {
            Arrays.fill(v[i], Integer.MAX_VALUE);
        }

        for(int i=0; i<N; i++) {
            String[] temp = in.next().split("");
            // System.out.println(Arrays.toString(temp));
            for(int j=0; j<M; j++) {
                maze[i][j] = Integer.parseInt(temp[j]);
            }
        }
        BFS(0,0,0);
    }
}