// 원자 소멸 시뮬레이션
// #1 24
// #2 0
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

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
    int X,Y,E;

    Node(int X, int Y, int E) {
        this.X = X;
        this.Y = Y;
        this.E = E;
    }
}

public class S5648 {
    static int N, res;
    static int[][] v;
    static int dx[]={0,0,-1,1}, dy[]={1,-1,0,0};
    static Queue<Atom> map;
    static LinkedList<Node> smash;
    
    static Boolean check(int x, int y) {
        if(x>=2001 || x<0 || y>=2001 || y<0) return true;
        return false;
    }
    static Queue<Atom> BFS(Queue<Atom> map) {
        Queue<Atom> rmap = new LinkedList<>();
        smash = new LinkedList<Node>();
        while(!map.isEmpty()) {
            Atom atom = map.poll();
            int x = atom.x;
            int y = atom.y;
            int dir = atom.dir;
            
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            // System.out.println("x:"+x+", "+"y:"+y);
            v[x][y] = 0;
            if(check(nx,ny)) continue;
            if(v[nx][ny]!=0) {
                Node node = new Node(nx,ny,atom.e);
                if(!smash.contains(node)) smash.add(node);
                continue;
            }
            v[nx][ny] = atom.e;
            rmap.add(new Atom(nx, ny, dir, atom.e, true));
        }
        return rmap;
    }
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N = sc.nextInt();
            map = new LinkedList<Atom>();
            v = new int[2001][2001];
            res = 0;
            for(int i=0; i<N; i++) {
                int x = sc.nextInt()+1000; // x
                int y = sc.nextInt()+1000; // y
                int dir = sc.nextInt(); // 방향
                int e = sc.nextInt(); // 에너지
                map.add(new Atom(x,y,dir,e,true));
                v[x][y] = e;
            }
            while(!map.isEmpty()) {
                map = BFS(map);
                if(smash.isEmpty()) continue;
                for(int i=0; i<smash.size(); i++) {
                    Node node = smash.poll();
                    res += v[node.X][node.Y];
                    v[node.X][node.Y]=0;
                }
            }
            System.out.println("#" + tc + " " + res);
        }
    }
}
