// SEM력
import java.io.*;
import java.util.*;

// S는 365보다 커지면 1로, E는 24보다 커지면 1로, M은 29보다 커지면 1로 돌아온다.

// 예를 들어서 AD 24년은 24 24 24이고 AD 25년은 25 1 25이다.

public class S7532 {
    static int a, i; // 365 24 29

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int S = in.nextInt();
            int E = in.nextInt();
            int M = in.nextInt();

            i = 0;
            a = 0;

            if (S==365)
                S = 0;
            if(E==24)
                E = 0;
            if(M==29)
                M = 0;

            if(S==0 && E==0 & M==0)
                i = 1;

            while(true) {
                a = 24*i + E;
                if(a%29 != M) {
                    i+=1;
                } else {
                    if (a%365 != S) {
                        i += 1;
                    } else {
                        break;
                    } 
                }
                
            }

            System.out.println("#" + tc + " " + a);
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