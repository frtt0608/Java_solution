// 무선충전
// #1 1200
// #2 3290
// #3 16620
// #4 40650
// #5 52710
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class S5644 {
    static int M, A, moveA[], moveB[];
    static int dx[]={0,-1,0,1,0}, dy[]={0,0,1,0,-1};
    static int map[][];

    static Boolean checkWall(int x, int y) {
        if(x>10 || x<0 || y>10 || y<0) return true;
        return false;
    }
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            M=sc.nextInt();
            A=sc.nextInt();
            moveA = new int[M];
            moveB = new int[M];
            map = new int[11][11];
            for(int i=0; i<A; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int c = sc.nextInt();
                int p = sc.nextInt();
                map[x][y] = p;
                if(c!=1) {
                    
                }
            }
        }
    }
}