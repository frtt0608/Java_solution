import java.io.*;
import java.util.*;

public class B11051 {
    static final int MOD = 10007;
    static final int MAX = 1000;
    static int N, K;
    static ArrayList<Integer>[] binomials;

    // nCr = n-1Cr + n-1Cr-1

    public static void fillBinomials() {
        int num = 0;
        binomials = new ArrayList[MAX+1];

        for(int i=1; i<MAX+1; i++) {
            binomials[i] = new ArrayList<>();

            binomials[i].add(1);
            binomials[i].add(i);
        }
        binomials[2].add(1);

        for(int n=2; n<MAX+1; n++) {
            int size = n%2==0 ? n/2 : n/2+1;

            for(int r=2; r<=size; r++) {
                if((n-1)/2 < r) {
                    num = (binomials[n-1].get(r-1) + binomials[n-1].get(n-1-r))%MOD;
                } else {
                    num = (binomials[n-1].get(r-1) + binomials[n-1].get(r))%MOD;
                }
                binomials[n].add(num);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        fillBinomials();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N/2 < K) {
            K = N - K;
        }

        System.out.println(binomials[N].get(K));
    }
}