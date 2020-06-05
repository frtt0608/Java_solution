// 직사각형과 점
import java.io.*;
import java.util.*;

public class S6853 {
    static int X1,X2,Y1,Y2,N;
    static int res[];

    static void check(int r, int c) {
        if(r<X1 || r>X2 || c>Y2 || c<Y1) res[2] += 1;
        else if(r==X1 || r==X2 || c==Y1 || c==Y2) res[1]+=1;
        else if(r>X1 && r<X2 && c>Y1 && c<Y2) res[0] += 1;
    }
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T=sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            X1=sc.nextInt();
            Y1=sc.nextInt();
            X2=sc.nextInt();
            Y2=sc.nextInt();
            N=sc.nextInt();

            res = new int[3];
            for(int n=0; n<N; n++) {
                int r = sc.nextInt();
                int c = sc.nextInt();
                check(r,c);
            }
            System.out.println("#" + tc + " " + res[0] + " " + res[1] + " " + res[2]);
        }
    }
}