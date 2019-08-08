import java.io.*;
import java.util.*;

// S는 365보다 커지면 1로, E는 24보다 커지면 1로, M은 29보다 커지면 1로 돌아온다.

// 예를 들어서 AD 24년은 24 24 24이고 AD 25년은 25 1 25이다.

public class S7532 {
    static int a,b,c; // 365 24 29

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        a=1;
        b=1;
        c=1;

        int T = in.nextInt();
        for (int tc = 1; tc <= 1; tc++) {
            int S = in.nextInt();
            int E = in.nextInt();
            int M = in.nextInt();

            S = E-S;
            E = M-E;
            M = S-M;
            System.out.println(S);
            while((365*a - S)%24 != 0) {
                a += 1;
                while((24*b - E)%29 != 0) {
                    b += 1;
                    while((29*c - M)%365 !=0) {
                        c += 1;
                    }
                }
            }
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
        }
    }
}


// 365 24 29
// 14 24 1
// a = x*365 + 14
// a = y*24 + 24
// a = z*29 + 1
// 365x - 24y = 10
// 24y - 29z = -23
// 29z - 365x = 13