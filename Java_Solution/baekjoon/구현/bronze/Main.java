import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int num = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            num = Integer.parseInt(st.nextToken());
            if(num < X) {
                System.out.print(num +" ");
            }
        }
    }
}