import java.io.*;
import java.util.*;

public class B1978 {

    static final int MAX = 1000;
    static boolean[] primeArr;

    static public void eratos() {
        primeArr[1] = true;
        for(int i=2; i<Math.sqrt(MAX); i++) {
            if(!primeArr[i]) {
                int j = i*2;
                while(j <= MAX) {
                    primeArr[j] = true;
                    j += i;
                }
            }
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        primeArr = new boolean[1001];
        eratos();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int count = 0;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(!primeArr[num]) {
                count += 1;
            }
        }

        System.out.println(count);
    }
}
