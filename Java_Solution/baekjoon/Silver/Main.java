import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 10000;
    static boolean[] primeArr;

    static public void eratos() {
        primeArr[1] = true;
        for(int i=2; i<Math.sqrt(MAX); i++) {
            if(!primeArr[i]) {
                int j = i*i;
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

        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++) {
            int num = Integer.parseInt(br.readLine());
            int prime1=num/2, prime2=num/2;

            System.out.println(prime1+" "+prime2);
        }
    }
}
