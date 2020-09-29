import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map, dpMap_max, dpMap_min;

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        dpMap_max = new int[N][3];
        dpMap_min = new int[N][3];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dpMap_max[0][0] = map[0][0];
        dpMap_max[0][1] = map[0][1];
        dpMap_max[0][2] = map[0][2];

        dpMap_min[0][0] = map[0][0];
        dpMap_min[0][1] = map[0][1];
        dpMap_min[0][2] = map[0][2];

        for(int i=1; i<N; i++) {
            dpMap_max[i][0] = Math.max(dpMap_max[i-1][1] + map[i][0], dpMap_max[i-1][0] + map[i][0]);
            dpMap_max[i][1] = Math.max(dpMap_max[i-1][0] + map[i][1], dpMap_max[i-1][1] + map[i][1]);
            dpMap_max[i][1] = Math.max(dpMap_max[i][1], dpMap_max[i-1][2] + map[i][1]);
            dpMap_max[i][2] = Math.max(dpMap_max[i-1][1] + map[i][2], dpMap_max[i-1][2] + map[i][2]);

            dpMap_min[i][0] = Math.min(dpMap_min[i-1][1] + map[i][0], dpMap_min[i-1][0] + map[i][0]);
            dpMap_min[i][1] = Math.min(dpMap_min[i-1][0] + map[i][1], dpMap_min[i-1][1] + map[i][1]);
            dpMap_min[i][1] = Math.min(dpMap_min[i][1], dpMap_min[i-1][2] + map[i][1]);
            dpMap_min[i][2] = Math.min(dpMap_min[i-1][1] + map[i][2], dpMap_min[i-1][2] + map[i][2]);
        }

        int max = 0;
        int min = Integer.MAX_VALUE;

        // for(int i=0; i<N; i++) {
        //     System.out.println(Arrays.toString(dpMap_min[i]));
        // }

        for(int i=0; i<3; i++) {
            max = Math.max(max, dpMap_max[N-1][i]);
            min = Math.min(min, dpMap_min[N-1][i]);
        }

        System.out.println(max + " " + min);
    }
}
