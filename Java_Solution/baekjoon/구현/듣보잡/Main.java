import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static String input_N[], input_M[];
    static int N, M, cnt;


    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input_N = new String[N];
        input_M = new String[M];

        for(int i=0; i<N; i++) {
            input_N[i] = br.readLine();
        }
        for(int i=0; i<M; i++) {
            input_M[i] = br.readLine();   
        }

        Arrays.sort(input_N);
        Arrays.sort(input_M);

        ArrayList<String> resList = new ArrayList<>();
        for(int i=0; i<M; i++) {
            int idx = Arrays.binarySearch(input_N, input_M[i]);
            if(idx>=0) {
                resList.add(input_M[i]);
            }
        }

        System.out.println(resList.size());
        for(String res: resList) {
            System.out.println(res);
        }
    }
}