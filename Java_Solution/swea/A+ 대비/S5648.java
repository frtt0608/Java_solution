// 원자 소멸 시뮬레이션
// #1 24
// #2 0
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

class Atom {
    int x,y,dir,e;
    Boolean live;

    Atom(int x, int y, int dir, int e, Boolean live) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.e = e;
        this.live = live;
    }
}

class Node {
    int X,Y;

    Node(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
}

public class S5648 {
    static int N, res;
    static int v[][];
    static int dr[]={0,0,-1,1}, dc[]={1,-1,0,0};
    static Atom map[];
    static LinkedList<Node> smash;
    
    static Boolean check(int x, int y) {
        if(x>=4001 || x<0 || y>=4001 || y<0) return true;
        return false;
    }
    static void crush() {
        int cnt = 0;
        int r,c,nr,nc;
        int new_v[][] = new int[4001][4001];
        
        for(int i=0; i<4001; i++) {
            for(Atom atom:map) {
                if(!atom.live) continue;
                r=atom.x;
                c=atom.y;
                // System.out.println("r:" + r + " " + "c:" + c +" dir: "+atom.dir);
                nr = r + dr[atom.dir];
                nc = c + dc[atom.dir];
                v[r][c] = 0;
                if(check(nr,nc)) {cnt+=1; atom.live = false; continue;}
                if(v[nr][nc] != 0) {
                    smash.add(new Node(nr,nc));
                    v[nr][nc] += atom.e;
                    cnt += 1;
                    atom.live = false;
                    continue;
                }
                atom.x=nr; atom.y=nc;
                v[nr][nc] = atom.e;
            }
            while(!smash.isEmpty()) {
                Node node = smash.poll();
                if(v[node.X][node.Y]==0) continue;
                res += v[node.X][node.Y];
                v[node.X][node.Y] = 0;
                cnt += 1;
                // System.out.println(node.X +", "+node.Y+" cnt: "+cnt);
            }
            if(cnt>=N) {
                return;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N = sc.nextInt();
            map = new Atom[N];
            v = new int[4001][4001];
            smash = new LinkedList<Node>();
            res = 0;
            for(int i=0; i<N; i++) {
                int x = (sc.nextInt()+1000)*2; // x
                int y = (sc.nextInt()+1000)*2; // y
                int dir = sc.nextInt(); // 방향
                int e = sc.nextInt(); // 에너지
                map[i] = new Atom(x,y,dir,e,true);
                v[x][y] = e;
            }
            crush();
            System.out.println("#" + tc + " " + res);
        }
        sc.close();
    }
}
