import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 1000000;
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
        primeArr = new boolean[MAX+1];
        eratos();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        
        for(int i=M; i<=N; i++) {
            if(!primeArr[i]) {
                sb.append(i).append('\n');
            }
        }

        System.out.println(sb.toString());
    }
}
