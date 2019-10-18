// 원자 소멸 시뮬레이션
// #1 24
// #2 0
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

class Atom {
    int x,y,dir,e;

    Atom(int x, int y, int dir, int e) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.e = e;
    }
}

public class S5648 {
    static int N, res, v[][];
    static int arr[][], new_v[][] = new int[4001][4001];
    static int dr[]={0,0,-1,1}, dc[]={1,-1,0,0};
    static Atom map[], atoms[];
    
    static Boolean check(int x, int y) {
        if(x>=4001 || x<0 || y>=4001 || y<0) return true;
        return false;
    }
    static void crush(int n, int[][] arr, Atom[] map) {
        int r,c,nr,nc,e;
        int tmp[][];

        v = arr;
        atoms = map;

        for(int i=0; i<4001; i++) {
            int cnt = 0;
            for(Atom atom:atoms) {
                r=atom.x;
                c=atom.y;
                e=atom.e;
                if(e==0) {cnt+=1; continue;}
                if(v[r][c]==0) {atom.e=0; continue;}
                // System.out.println("r:" + r + " " + "c:" + c +" dir: "+atom.dir);
                if(v[r][c] != e) {
                    res += v[r][c];
                    v[r][c]=0;
                    atom.e=0;
                    continue;
                }
                nr = r + dr[atom.dir];
                nc = c + dc[atom.dir];
                v[r][c] = 0;
                if(check(nr,nc)) {
                    atom.e=0;
                    continue;
                }
                atom.x=nr; atom.y=nc;
                new_v[nr][nc] += e;
            }
            if(cnt==n) return;
            tmp = v;
            v = new_v;
            new_v = tmp;
        }
        return;
    }
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            long start = System.currentTimeMillis();
            N = sc.nextInt();
            map = new Atom[N];
            arr = new int[4001][4001];
            res = 0;
            int x,y,dir,e;
            for(int i=0; i<N; i++) {
                x = (sc.nextInt()+1000)*2; // x
                y = (sc.nextInt()+1000)*2; // y
                dir = sc.nextInt(); // 방향
                e = sc.nextInt(); // 에너지
                map[i] = new Atom(x,y,dir,e);
                arr[x][y] = e;
            }
            crush(N, arr, map);
            System.out.println("#" + tc + " " + res);
            long end = System.currentTimeMillis();
            System.out.println( "실행 시간 : " + ( end - start )/1000.0 +"초");
        }
        sc.close();
    }
}
