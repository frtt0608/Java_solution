// 알파벳

import java.util.*;
import java.io.*;

public class Main {
    static int R, C, maxCnt;
    static int[] dx={1,-1,0,0}, dy={0,0,1,-1};
    static int[][] map;
    static boolean[] visited;

    static boolean wall(int x, int y) {
        if(x<0 || x>=R || y<0 || y>=C) return true;
        return false;
    }

    static void DFS(int x, int y, int cnt) {
        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(wall(nx, ny)) continue;
            if(visited[map[nx][ny]]) {
                maxCnt = Math.max(maxCnt, cnt);
                continue;
            }

            visited[map[nx][ny]] = true;
            DFS(nx, ny, cnt+1);
            visited[map[nx][ny]] = false;
        }
    }
    
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[26];

        for(int i=0; i<R; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                map[i][j] = input[j] - 'A';
            }
        }

        visited[map[0][0]] = true;
        DFS(0, 0, 1);

        System.out.println(maxCnt);
    }
}