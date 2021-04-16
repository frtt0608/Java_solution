import java.util.*;
import java.io.*;


//   2
// 4 1 3
//   5
//   6
public class B14499 {
    static int N, M, x, y, K;
    static int[] dx={0,0,-1,1}, dy={1,-1,0,0};
    static int[][] map;
    static String[] commands;
    static StringBuilder sb = new StringBuilder();
    // 1동, 2서, 3북, 4남

    public static void moveDice() {
        int[] dice = new int[6];
        int temp;

        for(String command: commands) {
            int dir = Integer.parseInt(command)-1;

            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(nx<0 || nx>=N || ny<0 || ny>=M) continue;

            temp = dice[1];
            switch(dir) {
                case 0:
                    dice[1] = dice[4];
                    dice[4] = dice[3];
                    dice[3] = dice[5];
                    dice[5] = temp;
                    break;
                case 1:
                    dice[1] = dice[5];
                    dice[5] = dice[3];
                    dice[3] = dice[4];
                    dice[4] = temp;
                    break;
                case 2:
                    dice[1] = dice[2];
                    dice[2] = dice[3];
                    dice[3] = dice[0];
                    dice[0] = temp;
                    break;
                case 3:
                    dice[1] = dice[0];
                    dice[0] = dice[3];
                    dice[3] = dice[2];
                    dice[2] = temp;
                    break;
            }

            // 바닥이 0이면 주사위 -> 바닥
            // 바닥이 0이 아니면 -> 주사위로, 바닥=0 
            if(map[nx][ny] == 0) {
                map[nx][ny] = dice[3];
            } else {
                dice[3] = map[nx][ny];
                map[nx][ny] = 0;
            }

            x = nx;
            y = ny;
            sb.append(dice[1]+ "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        commands = br.readLine().split(" ");
        
        moveDice();
        System.out.println(sb.toString());
    }
}