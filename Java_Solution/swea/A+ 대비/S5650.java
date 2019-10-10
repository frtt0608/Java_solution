// 핀볼게임
// #1 9
// #2 0
// #3 7
// #4 5
// #5 19
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class Node {
    int X,Y;

    Node(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
}

class Go {
    int X,Y;
    int dir, cnt;

    Go(int X, int Y, int dir, int cnt) {
        this.X = X;
        this.Y = Y;
        this.dir = dir;
        this.cnt = cnt;
    }
}

public class S5650 {
    static int N, map[][], res;
    static int dr[] = {0,0,1,-1}, dc[] = {1,-1,0,0};
    static Node hole[][];

    static Boolean check(int x, int y) {
        if(x>=N || x<0 || y>=N || y<0) return true;
        return false;
    }

    static int square(int dir) {
        if(dir==0) return 1;
        if(dir==1) return 0;
        if(dir==2) return 3;
        else return 2;
    }

    static int triangle(int dir, int num) {
        if(num==1) {
            if(dir==0) return 1;
            if(dir==1) return 3;
            if(dir==2) return 0;
            if(dir==3) return 2;
        }
        if(num==2) {
            if(dir==0) return 1;
            if(dir==1) return 2;
            if(dir==2) return 3;
            if(dir==3) return 0;
        }
        if(num==3) {
            if(dir==0) return 2;
            if(dir==1) return 0;
            if(dir==2) return 3;
            if(dir==3) return 1;
        }
        if(num==4) {
            if(dir==0) return 3;
            if(dir==1) return 0;
            if(dir==2) return 1;
            if(dir==3) return 2;
        }
        System.out.println(dir +", "+num);
        return -1;
    }

    static Node wormhole(int x, int y, int num) {
        Node node = hole[num-6][0];
        if(node.X == x && node.Y == y) {
            node = hole[num-6][1];
        }
        return node;
    }

    static int BFS(int x, int y, int d) {
        int temp = 0;
        Queue<Go> qu = new LinkedList<>();
        qu.add(new Go(x,y,d,0));
        while(!qu.isEmpty()) {
            Go go = qu.poll();
            int r = go.X;
            int c = go.Y;
            int dir = go.dir;
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            // if(check(nr,nc) && map[r][c]>=1 && map[r][c]<=4)
            //     qu.add(new Go(r,c,triangle(square(dir), map[r][c]),go.cnt+2));
            if(check(nr,nc)) qu.add(new Go(nr,nc,square(dir),go.cnt+1));
            else if(map[nr][nc] == -1) return go.cnt;
            else if(nr==x && nc==y) return go.cnt;
            else if(map[nr][nc] == 0) qu.add(new Go(nr,nc,dir,go.cnt));
            else if(map[nr][nc] == 5) qu.add(new Go(nr, nc, square(dir), go.cnt+1));
            else if(map[nr][nc] < 5) qu.add(new Go(nr, nc, triangle(dir, map[nr][nc]), go.cnt+1));
            else if(map[nr][nc] >= 6) {
                Node node = wormhole(nr,nc,map[nr][nc]);
                qu.add(new Go(node.X, node.Y, dir, go.cnt));
                
            }
            // else {
            //     if(map[r][c] < 5) qu.add(new Go(r, c, triangle(dir, map[r][c]), go.cnt+1));
            // }
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N=sc.nextInt();
            map = new int[N][N];
            hole = new Node[5][2];
            res=0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    map[i][j] = sc.nextInt();
                    if(map[i][j]>=6) {
                        if(hole[map[i][j]-6][0]==null) {
                            hole[map[i][j]-6][0] = new Node(i,j);
                        } else {
                            hole[map[i][j]-6][1] = new Node(i,j);
                        }
                    }
                }
            }
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j]==0) {
                        for(int dir=0; dir<4; dir++) {
                            // System.out.println(i+","+j+"  dir: "+dir);
                            if(check(i+dr[dir], j+dc[dir])) continue;
                            int bfs = BFS(i,j,dir);
                            if(res < bfs) res=bfs;
                        }
                    }
                }
            }
            System.out.println("#" + tc + " " + res);
        }
        sc.close();
    }
}