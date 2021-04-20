import java.util.*;
import java.io.*;

public class B14889 {
    static int N, minScore;
    static int[][] map;
    static boolean[][] visited;

    public static void getMinDiffScore(boolean[] startTeam) {
        int startScore = 0;
        int linkScore = 0;

        for(int i=0; i<N; i++) {
            if(startTeam[i]) {
                for(int j=0; j<N; j++) {
                    if(startTeam[j])
                        startScore += map[i][j];
                }
            } else {
                for(int j=0; j<N; j++) {
                    if(!startTeam[j])
                        linkScore += map[i][j];
                }
            }
        }

        minScore = Math.min(minScore, Math.abs(startScore - linkScore));
    }

    public static void makeStartTeam(int cnt, int index, boolean[] startTeam) {
        if(cnt == N/2) {
            getMinDiffScore(startTeam);
            return;
        }

        if(index >= N) return;

        startTeam[index] = true;
        makeStartTeam(cnt+1, index+1, startTeam);
        startTeam[index] = false;
        makeStartTeam(cnt, index+1, startTeam);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        minScore = Integer.MAX_VALUE;
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        makeStartTeam(0, 0, new boolean[N]);
        System.out.println(minScore);
    }
}