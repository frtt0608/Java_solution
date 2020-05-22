import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String input[] = br.readLine().split(" ");
        int leftCnt[] = new int[N];

        for(int i=0; i<N; i++) {
            leftCnt[i] = Integer.parseInt(input[i]);
        }

        LinkedList<Integer> height = new LinkedList<>();
        for(int i=N-1; i>=0; i--) {
            height.add(leftCnt[i], i);
        }

        for(int i=0; i<N; i++) {
            System.out.print(height.get(i)+1);
            if(i < N-1) System.out.print(" ");
        }
    }
}