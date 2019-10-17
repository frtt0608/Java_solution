// 원자 소멸 시뮬레이션
// #1 24
// #2 0
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

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


public class S5648 {
    static int N, res;
    static int v[][];
    static int dr[]={0,0,-1,1}, dc[]={1,-1,0,0};
    static Atom map[];
    
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
                r=atom.x;
                c=atom.y;
                if(atom.live==false) continue;
                // System.out.println("r:" + r + " " + "c:" + c +" dir: "+atom.dir);
                if(v[r][c] != atom.e) {
                    v[r][c] = 0;
                    res += atom.e;
                    cnt += 1;
                    atom.live = false;
                    // System.out.println(res);
                    continue;
                }
                nr = r + dr[atom.dir];
                nc = c + dc[atom.dir];
                v[r][c] = 0;
                if(check(nr,nc)) {
                    cnt+=1; 
                    atom.live = false; 
                    continue;
                }
                atom.x=nr; atom.y=nc;
                new_v[nr][nc] += atom.e;
            }
            if(cnt>=N) return;
            v = new_v;
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
