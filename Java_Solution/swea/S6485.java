// 삼성시의 버스노선
import java.io.*;
import java.util.*;

public class S6485 {
    static int N, A[], B[], P, C[];
    static int cnt[], v[];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N = sc.nextInt();
            A = new int[N+1];
            B = new int[N+1];
            cnt = new int[5001];
            
            for(int i=1; i<=N; i++) {
                int a=sc.nextInt();
                int b=sc.nextInt();
                for(int t=a; t<=b; t++) {
                    cnt[t] += 1;
                }
            }
            P =  sc.nextInt();
            C = new int[P];
            for(int i=0; i<P; i++) {
                C[i] = cnt[sc.nextInt()];
            }
            System.out.print("#" + tc + " ");
            for(int i:C) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
        sc.close();
    }
}