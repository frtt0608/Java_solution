import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean friend[][] = new boolean[N+1][N+1];
        String input[] = {};

        for(int i=0; i<M; i++) {
            input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            friend[x][y] = true;
            friend[y][x] = true;
        }

        Set<Integer> friendSet = new HashSet<>();
        for(int j=1; j<N+1; j++) {
            if(friend[1][j]) {
                friendSet.add(j);
                for(int k=2; k<N+1; k++) {
                    if(friend[j][k]) friendSet.add(k);
                }
            }
        }

        System.out.println(friendSet.size());
    }
}