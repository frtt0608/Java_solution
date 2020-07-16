// 안전 영역

import java.io.*;
import java.util.*;

public class Main {
    static int N, maxHeight, maxSection, section;
    static int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
    static int[][] map;
    static boolean[][] visited;

    static boolean wall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    static void DFS(int x, int y, int height) {
        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if(wall(nx, ny)) continue; // 범위 밖
            if(visited[nx][ny]) continue; // 이미 방문
            if(map[nx][ny] <= height) continue; // 물에 잠긴 영역

            visited[nx][ny]  = true;
            DFS(nx, ny, height);
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        for(int h=0; h<maxHeight; h++) {
            visited = new boolean[N][N];
            section = 0;

            for(int x=0; x<N; x++) {
                for(int y=0; y<N; y++) {
                    if(visited[x][y]) continue;
                    if(map[x][y] <= h) continue;

                    DFS(x, y, h);
                    section++;
                }
            }

            maxSection = Math.max(maxSection, section);
        }

        System.out.println(maxSection);
    }
}
