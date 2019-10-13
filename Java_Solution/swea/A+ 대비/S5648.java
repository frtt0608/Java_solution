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

public class S5648 {
    static int N;
    static int dx[]={0,0,-1,1}, dy[]={1,-1,0,0};
    static LinkedList<Atom> map;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N = sc.nextInt();
            map = new LinkedList<Atom>();
            for(int i=0; i<N; i++) {
                int x = sc.nextInt(); // x
                int y =sc.nextInt(); // y
                int dir = sc.nextInt(); // 방향
                int e = sc.nextInt(); // 에너지
                map.add(new Atom(x,y,dir,e,true));
            }

        }
    }
}
