import java.util.*;
import java.io.*;

public class B15684 {
    static int N, M, H, minLadderCnt;
    static boolean[][] map;
    static boolean isRight;

    public static void downToBottom(int needCnt) {
        int si, sj;

        for(int i=1; i<N+1; i++) {
            si = i;
            sj = 1;

            while(sj < H+1) {
                if(map[sj][si]) {
                    si += 1;
                } else if(map[sj][si-1]) {
                    si -= 1;
                }

                sj += 1;
            }

            if(si != i) return;
        }

        isRight = true;
        minLadderCnt = needCnt;
    }

    public static void setTotalCase(int needCnt, int curCnt, int si) {
        if(isRight) return;

        if(needCnt == curCnt) {
            downToBottom(needCnt);
            return;
        }

        for(int i=si; i<N; i++) {
            for(int j=1; j<H+1; j++) {
                if(map[j][i] || map[j][i-1] || map[j][i+1]) continue;

                map[j][i] = true;
                setTotalCase(needCnt, curCnt+1, i);
                map[j][i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());    

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new boolean[H+1][N+1];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = true;
        }

        for(int cnt=0; cnt<=3; cnt++) {
            setTotalCase(cnt, 0, 1);
            if(isRight) break;
        }

        System.out.println(isRight ? minLadderCnt:-1);
    }
}   
