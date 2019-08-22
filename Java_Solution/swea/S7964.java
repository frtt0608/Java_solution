// 부먹왕국의 차원 관문

import java.io.*;
import java.util.*;

public class S7964 {
    static int N;
    static int D;
    static int[] city;
    static int cnt;

    static void door(int n) {
        if(n >= N+1) {
            // System.out.println(Arrays.toString(city));
            return;
        }

        for(int d=1; d<=D; d++) {
            if(n+d >= N+1) {
                return;
            } else {
                if(city[n+d] == 1) {
                    door(n+d);
                    return;
                } else if(d == D) {
                    city[n+D] = 1;
                    cnt += 1;
                    door(n+D);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N = in.nextInt();
            D = in.nextInt();
            city = new int[N+2];

            city[0] = 1;
            city[N+1] = 1;

            for(int i=1; i<N+1; i++) {
                city[i] = in.nextInt();
            }

            cnt = 0;
            door(0);

            System.out.println("#" + tc + " " + cnt);
        }
    }
}