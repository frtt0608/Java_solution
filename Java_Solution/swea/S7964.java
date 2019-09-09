// 부먹왕국의 차원 관문
// 2
// 3
// 10

import java.io.*;
import java.util.*;

public class S7964 {
    static int N;
    static int D;
    static int[] city;
    static int max;
    static int[] v;

    static void BFS(int n, int cnt) {
        LinkedList<Integer[]> qu = new LinkedList<>();
        qu.add(new Integer[] {n, cnt});

        while(!qu.isEmpty()) {
            int s = qu.peek()[0];
            int num  = qu.peek()[1];
            qu.poll();
            // System.out.println(s + ", " +num);
            v[s] = 1;
            if(max <= num) continue;
            if(s >= N+1-D) {
                if(max > num) {
                    max = num;
                    // System.out.println(max);
                }
            }
            for(int d=1; d<=D; d++) {
                if(s+d <= N+1) {
                    if(v[s+d]==1) continue;
                    if(city[s+d]==1) {
                        qu.add(new Integer[] {s+d,num});
                        break;
                    } else if(city[s+d]==0 && d==D) {
                        city[s+d] = 1;
                        qu.add(new Integer[] {s+d, num+1});
                        break;
                    }
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
            v = new int[N+2];
            max = 999999;

            city[0] = 1;
            city[N+1] = 1;

            for(int i=1; i<N+1; i++) {
                city[i] = in.nextInt();
            }
            v[0] = 1;
            v[N+1] = 1;
            BFS(0,0);
            // System.out.println(Arrays.toString(city));
            System.out.println("#" + tc + " " + max);
        }
    }
}