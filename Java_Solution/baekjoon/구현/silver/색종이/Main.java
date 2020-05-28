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
        int size = 100;
        int map[][] = new int[size][size];
        int area = 0;

        // 색종이 10x10
        for(int i=0; i<N; i++) {
            String input[] = br.readLine().split(" ");
            int y = Integer.parseInt(input[0]);
            int x = Integer.parseInt(input[1]);

            for(int r=x; r<x+10; r++) {
                for(int c=y; c<y+10; c++) {
                    map[r][c] = 1;
                }
            }
        }

        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(map[i][j]==1) area += 1;
            }
        }

        System.out.println(area);
    }
}