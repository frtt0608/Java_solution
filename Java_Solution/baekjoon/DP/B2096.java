import java.util.*;
import java.io.*;

public class B2096 {
    static int N;

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[] map = new int[3];
        int[] dpMax = new int[3];
        int[] dpMin = new int[3];
        int[] dpTemp = new int[3];
        int max = 0;
        int min = Integer.MAX_VALUE;
        
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<3; j++) {
                map[j] = str.charAt(j+j) - '0';
                if(i == 0) {
                    dpMax[j] = map[j];
                    dpMin[j] = map[j];
                }
            }

            if(i == 0) continue;

            dpTemp[0] = map[0] + Math.max(dpMax[0], dpMax[1]);
            dpTemp[1] = map[1] + Math.max(dpMax[0], Math.max(dpMax[1], dpMax[2]));
            dpTemp[2] = map[2] + Math.max(dpMax[1], dpMax[2]);

            dpMax[0] = dpTemp[0];
            dpMax[1] = dpTemp[1];
            dpMax[2] = dpTemp[2];

            dpTemp[0] = map[0] + Math.min(dpMin[0], dpMin[1]);
            dpTemp[1] = map[1] + Math.min(dpMin[0], Math.min(dpMin[1], dpMin[2]));
            dpTemp[2] = map[2] + Math.min(dpMin[1], dpMin[2]);

            dpMin[0] = dpTemp[0];
            dpMin[1] = dpTemp[1];
            dpMin[2] = dpTemp[2];
        }

        for(int i=0; i<3; i++) {
            max = Math.max(max, dpMax[i]);
            min = Math.min(min, dpMin[i]);
        }

        bw.write(max + " " + min);
        bw.flush();
        bw.close();
    }
}
