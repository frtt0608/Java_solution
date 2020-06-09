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
        int conf[][] = new int[N][2];
        int confCnt = 0;
        int endTime = 0;
    
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            conf[i][0] = Integer.parseInt(st.nextToken());
            conf[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(conf, (int conf1[], int conf2[]) -> {
            return conf1[1] != conf2[1] ? conf1[1] - conf2[1] : conf1[0] - conf2[0];
            // 종료시간이 다르면 오름차순 정렬, 같으면 시작시간이 이른 것부터!
        });

        for(int i=0; i<N; i++) {
            if(endTime <= conf[i][0]) {
                endTime = conf[i][1];
                confCnt += 1;
            }
        }

        System.out.println(confCnt);
    }
}